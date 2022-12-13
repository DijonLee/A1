package com.analyst1.eval.utils;

import com.analyst1.eval.client.AttackerClient;
import com.analyst1.eval.client.CountriesClient;
import com.analyst1.eval.client.MalwareClient;
import com.analyst1.eval.model.Malware;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfiguration {

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper;
    }



    @Bean
    public AttackerClient attakcerClient(){
        return new AttackerClient();
    }


    @Bean
    public CountriesClient countriesClient(){
        return new CountriesClient();
    }

    @Bean
    public MalwareClient malwareClient(){
        return new MalwareClient();
    }
}