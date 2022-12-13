package com.analyst1.eval.mapper;


import com.analyst1.eval.model.darknet.response.DarknetAttackerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//For sake of time I wont do an end to end test -- just test sample json response
public class AttackerMapperTest {
    private ObjectMapper OBJECT_MAPPER = new Jackson2ObjectMapperBuilder().build();

    @Test
    public void attackerMapperTest() throws IOException {
        // I would mock this or do an end to end test
        //curl -X GET "http://localhost:8081/api/attackers?page=1&size=1" -H "accept: application/json"

        String jsonResponse = "{\n" +
                "  \"_embedded\": {\n" +
                "    \"attackers\": [\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Doll R. Bill\",\n" +
                "        \"country\": \"nz\",\n" +
                "        \"relatedAttackerNames\": [\n" +
                "          \"Stanton Legros\",\n" +
                "          \"Tonie Kuvalis\",\n" +
                "          \"Len DeHande\"\n" +
                "        ],\n" +
                "        \"relatedMalwareIds\": [\n" +
                "          35,\n" +
                "          19,\n" +
                "          3,\n" +
                "          5,\n" +
                "          28,\n" +
                "          30,\n" +
                "          15\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"_links\": {\n" +
                "    \"first\": {\n" +
                "      \"href\": \"http://localhost:8081/api/attackers?page=0&size=1\"\n" +
                "    },\n" +
                "    \"prev\": {\n" +
                "      \"href\": \"http://localhost:8081/api/attackers?page=0&size=1\"\n" +
                "    },\n" +
                "    \"self\": {\n" +
                "      \"href\": \"http://localhost:8081/api/attackers?page=1&size=1\"\n" +
                "    },\n" +
                "    \"next\": {\n" +
                "      \"href\": \"http://localhost:8081/api/attackers?page=2&size=1\"\n" +
                "    },\n" +
                "    \"last\": {\n" +
                "      \"href\": \"http://localhost:8081/api/attackers?page=109&size=1\"\n" +
                "    },\n" +
                "    \"profile\": {\n" +
                "      \"href\": \"http://localhost:8081/api/profile/attackers\"\n" +
                "    }\n" +
                "  },\n" +
                "  \"page\": {\n" +
                "    \"size\": 1,\n" +
                "    \"totalElements\": 110,\n" +
                "    \"totalPages\": 110,\n" +
                "    \"number\": 1\n" +
                "  }\n" +
                "}";

        DarknetAttackerResponse attacker = OBJECT_MAPPER.readValue(jsonResponse, DarknetAttackerResponse.class);
        assertEquals(1, attacker.get_embedded().getAttackers().size());
        assertEquals(2, attacker.get_embedded().getAttackers().stream().findFirst().get().getId());
        assertEquals("Doll R. Bill", attacker.get_embedded().getAttackers().stream().findFirst().get().getName());
        assertEquals("nz", attacker.get_embedded().getAttackers().stream().findFirst().get().getCountry());
        assertEquals(Lists.newArrayList(  "Stanton Legros", "Tonie Kuvalis", "Len DeHande"), attacker.get_embedded().getAttackers().stream().findFirst().get().getRelatedAttackerNames());
        assertThat(Lists.newArrayList( 35, 3, 19, 5, 28, 30, 15).stream().sorted().toArray()).usingRecursiveComparison().isEqualTo(attacker.get_embedded().getAttackers().stream().findFirst().get().getRelatedMalwareIds().stream().sorted().toArray());

        assertEquals(110,attacker.getPage().getTotalPages());
        assertEquals(1,attacker.getPage().getSize());
        assertEquals(1,attacker.getPage().getNumber());
        assertEquals(110,attacker.getPage().getTotalElements());

        //TODO
//        assertEquals(attacker.get_links().);

    }

}
