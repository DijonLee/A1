package com.analyst1.eval.utils;

import com.analyst1.eval.client.CountriesClient;
import com.analyst1.eval.client.MalwareClient;
import com.analyst1.eval.model.Indicator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Map;

public class Utils {
    private static CountriesClient countriesClient;
    private static MalwareClient malwareClient;
    public static ObjectMapper OBJECT_MAPPER = new Jackson2ObjectMapperBuilder().build();

    public static Map<String,Indicator.IndicatorType> map = Map.of(
            "IPV4", Indicator.IndicatorType.IP,
            "DOMAIN", Indicator.IndicatorType.DOMAIN_NAME,
            "HASH",Indicator.IndicatorType.FILE // Note: Theres no HASH in Indicator, so let's map to File?
    );


    public static boolean isValidCountryCode(String countryCode){
        return !(countryCode.isBlank() || countryCode.length() > 2);
    }



}
