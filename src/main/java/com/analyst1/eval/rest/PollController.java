/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.rest;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analyst1.eval.dao.AttackerRepository;
import com.analyst1.eval.dao.AttackerWebRepository;
import com.analyst1.eval.dao.IndicatorRepository;
import com.analyst1.eval.dao.MalwareRepository;
import com.analyst1.eval.service.PollService;

/**
 * This shouldn't need to be modified.
 */
@RestController()
@RequestMapping("util")
public class PollController {

    final PollService pollService;
    final AttackerRepository attackerRepository;
    final AttackerWebRepository attackerWebRepository;
    final IndicatorRepository indicatorRepository;
    final MalwareRepository malwareRepository;

    public PollController(PollService pollService, AttackerRepository attackerRepository, AttackerWebRepository attackerWebRepository,
            IndicatorRepository indicatorRepository, MalwareRepository malwareRepository) {
        this.pollService = pollService;
        this.attackerRepository = attackerRepository;
        this.attackerWebRepository = attackerWebRepository;
        this.indicatorRepository = indicatorRepository;
        this.malwareRepository = malwareRepository;
    }

    /**
     * Convenience for testing multiple times without restarting.
     */
    @GetMapping("poll")
    @Transactional
    public ResponseEntity<String> pollData() throws JsonMappingException {
        return ResponseEntity.ok(pollService.pollData());
    }

    /**
     * Convenience for clearing data without restarting, or in case you change to file based storage instead of in-memory.
     */
    @GetMapping("clear")
    @Transactional
    public ResponseEntity<String> clearData() {
        attackerRepository.deleteAll();
        attackerWebRepository.deleteAll();
        indicatorRepository.deleteAll();
        malwareRepository.deleteAll();
        return ResponseEntity.ok("Cleared");
    }
}
