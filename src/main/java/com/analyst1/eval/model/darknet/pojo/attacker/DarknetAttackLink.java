package com.analyst1.eval.model.darknet.pojo.attacker;

import java.util.Map;

public class DarknetAttackLink {
    private Map<String,String> first;
    private Map<String,String> prev;
    private Map<String,String> self;
    private Map<String,String> next;
    private Map<String,String> last;
    private Map<String,String> profile;

    public DarknetAttackLink(Map<String, String> first, Map<String, String> prev, Map<String, String> self, Map<String, String> next, Map<String, String> last, Map<String, String> profile) {
        this.first = first;
        this.prev = prev;
        this.self = self;
        this.next = next;
        this.last = last;
        this.profile = profile;
    }

    public DarknetAttackLink(){}

    public Map<String, String> getFirst() {
        return first;
    }

    public void setFirst(Map<String, String> first) {
        this.first = first;
    }

    public Map<String, String> getPrev() {
        return prev;
    }

    public void setPrev(Map<String, String> prev) {
        this.prev = prev;
    }

    public Map<String, String> getSelf() {
        return self;
    }

    public void setSelf(Map<String, String> self) {
        this.self = self;
    }

    public Map<String, String> getNext() {
        return next;
    }

    public void setNext(Map<String, String> next) {
        this.next = next;
    }

    public Map<String, String> getLast() {
        return last;
    }

    public void setLast(Map<String, String> last) {
        this.last = last;
    }

    public Map<String, String> getProfile() {
        return profile;
    }

    public void setProfile(Map<String, String> profile) {
        this.profile = profile;
    }



}
