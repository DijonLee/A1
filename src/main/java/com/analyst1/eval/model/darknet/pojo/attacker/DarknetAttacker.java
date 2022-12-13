package com.analyst1.eval.model.darknet.pojo.attacker;

import java.util.Collection;

public class DarknetAttacker {
    private int id;
    private String name;
    private String country;
    private Collection<String> relatedAttackerNames;
    private Collection<Integer> relatedMalwareIds;

    public DarknetAttacker(int id, String name, String country, Collection<String> relatedAttackerNames, Collection<Integer> relatedMalwareIds) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.relatedAttackerNames = relatedAttackerNames;
        this.relatedMalwareIds = relatedMalwareIds;
    }
    public DarknetAttacker(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Collection<String> getRelatedAttackerNames() {
        return relatedAttackerNames;
    }

    public void setRelatedAttackerNames(Collection<String> relatedAttackerNames) {
        this.relatedAttackerNames = relatedAttackerNames;
    }

    public Collection<Integer> getRelatedMalwareIds() {
        return relatedMalwareIds;
    }

    public void setRelatedMalwareIds(Collection<Integer> relatedMalwareIds) {
        this.relatedMalwareIds = relatedMalwareIds;
    }



}
