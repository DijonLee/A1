/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.service;

import com.analyst1.eval.client.AttackerClient;
import com.analyst1.eval.client.CountriesClient;
import com.analyst1.eval.client.MalwareClient;
import com.analyst1.eval.dao.AttackerRepository;
import com.analyst1.eval.dao.AttackerWebRepository;
import com.analyst1.eval.dao.IndicatorRepository;
import com.analyst1.eval.dao.MalwareRepository;
import com.analyst1.eval.model.Attacker;
import com.analyst1.eval.model.AttackerWeb;
import com.analyst1.eval.model.Indicator;
import com.analyst1.eval.model.Malware;
import com.analyst1.eval.model.darknet.pojo.DarknetPage;
import com.analyst1.eval.model.darknet.pojo.attacker.DarknetAttacker;
import com.analyst1.eval.model.darknet.pojo.malware.DarknetMalware;
import com.analyst1.eval.model.darknet.response.DarknetAttackerResponse;
import com.analyst1.eval.utils.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.analyst1.eval.utils.Utils.isValidCountryCode;
import static com.analyst1.eval.utils.Utils.map;

@Service
public class PollService {
    @Autowired
    private AttackerClient attackerClient;
    @Autowired
    private MalwareClient malwareClient;
    @Autowired

    private CountriesClient countriesClient;
    @Autowired

    private AttackerRepository attackerRepository;
    @Autowired

    private MalwareRepository malwareRepository;
    @Autowired

    private IndicatorRepository indicatorRepository;
    @Autowired
    private AttackerWebRepository attackerWebRepository;
    private static final Logger logger = LogManager.getLogger();


    /**
     * Instructions:
     * TODO Add detail to outline below
     * Get attackers and malware from endpoints.
     * Split malware/IOCs into separate entities.
     * Group Attackers and Indicators into "AttackerWeb" based on association.
     *
     * @return a message about what was implemented.
     */

//    @Transactional
//    public String pollData() {
//        int ingestedAttackers = pollforData();
//        return "ss";
//    }
    @Transactional
    public String pollData() {
        //Pagination
        int currAttackerPage = 0;
        int totalAttackerPage;
        try {
            DarknetPage pageInfo = new Utils().OBJECT_MAPPER.readValue(attackerClient.findAllAttackers(currAttackerPage, 50).body(), DarknetAttackerResponse.class).getPage();
            totalAttackerPage = pageInfo.getTotalPages();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        while (currAttackerPage <= totalAttackerPage) {
            ingestData(currAttackerPage);
            currAttackerPage++;
        }
        logger.info("Ingested %s malwares, %s indicators, %s attackers, %s attackersWeb".formatted(malwareRepository.count(), indicatorRepository.count(), attackerRepository.count(), attackerWebRepository.count()));

        return null;
    }

    public void ingestData(int currPage) {
        // Iterate through All Attackers via Pagination
        DarknetAttackerResponse darknetAttackersResponse = null;
        try {
            darknetAttackersResponse = new Utils().OBJECT_MAPPER.readValue(attackerClient.findAllAttackers(currPage, 50).body(), DarknetAttackerResponse.class);
            Collection<DarknetAttacker> darkNetattackers = darknetAttackersResponse.get_embedded().getAttackers();
            for (DarknetAttacker dnAttacker : darkNetattackers) {
                String dnTwoLetterCountryCode = dnAttacker.getCountry();
                if (isValidCountryCode(dnTwoLetterCountryCode)) {
                    String countryName = countriesClient.getCountryNameByCountryCode(dnTwoLetterCountryCode);
                    //Save Attacker
                    Attacker newAttacker = new Attacker();
                    AtomicInteger attackerWebMemberCount = new AtomicInteger(); // Indicators +
                    newAttacker.setName(dnAttacker.getName());
                    newAttacker.setCountryName(countryName);
                    attackerRepository.saveAndFlush(newAttacker);

                    //Initialize Attacker Web
                    AttackerWeb attackerWeb = new AttackerWeb();



                    // If all attackers are related to malware then we just need to run the ingesting on attackers.
                    // Then create the malware and IOC at the time we create the attackerweb.
                    // Save malwares,indicators,attakcerweb on attackers as we loop through
                    if (!dnAttacker.getRelatedMalwareIds().isEmpty()) {
                        dnAttacker.getRelatedMalwareIds().forEach(malwareId -> {
                            DarknetMalware dnMalware = null;
                            try {
                                dnMalware = new Utils().OBJECT_MAPPER.readValue(malwareClient.findMalwareById(malwareId).body(), DarknetMalware.class);

                                //Build Malware
                                Malware malware = new Malware();
                                malware.setMalwareName(dnMalware.getName());

                                //Build Indicator
                                Set<Indicator> indicatorsOnMalware = new HashSet();
                                dnMalware.getRelatedIOCs().forEach(relatedIOC -> {
                                    Indicator indicator = new Indicator();
                                    indicator.setType(map.get(relatedIOC.getType()));
                                    indicator.setIndicatorValue(relatedIOC.getValue());


                                    //Ignore Indicator Dupes, build and add to attacker web
                                    // TODO change this to findById but seems to not come back -- not familiar with JPA -- see my impl in interface
                                    List<Indicator> ind = indicatorRepository.findAll();
                                    Set<String> existingIndicatorValues = ind.stream().map(it -> it.getIndicatorValue()).collect(Collectors.toSet());

                                    if (!existingIndicatorValues.contains(indicator.getIndicatorValue())) {
                                        indicatorsOnMalware.add(indicator);
                                        attackerWeb.setIndicators(new HashSet<>(Arrays.asList(indicator)));
                                        attackerWebMemberCount.getAndIncrement();
                                    }

                                    // Add to attacker web
                                    attackerWeb.setAttackers(new HashSet<>(Arrays.asList(newAttacker)));
                                });

                                //Save malwares
                                malware.setRelatedIndicators(indicatorsOnMalware);
                                malwareRepository.saveAndFlush(malware);
                                attackerWebMemberCount.getAndIncrement();

                                //Save Attacker Web
                                attackerWebRepository.saveAndFlush(attackerWeb);


                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @EventListener
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        pollData();
    }
}
