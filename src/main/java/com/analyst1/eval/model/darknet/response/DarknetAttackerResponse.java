package com.analyst1.eval.model.darknet.response;

import com.analyst1.eval.model.darknet.pojo.attacker.DarknetAttackLink;
import com.analyst1.eval.model.darknet.pojo.DarknetPage;
import com.analyst1.eval.model.darknet.pojo.attacker.EmbeddedAttacker;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DarknetAttackerResponse {
    @JsonProperty("page")
    private DarknetPage page;
    @JsonProperty("_links")
    private DarknetAttackLink _links;
    // Normally I would write A JSON Deserializer to deserialize embed determining if attacker or malware based on node
    // and use generics but for sake of time I'll just have two embededed classes
    @JsonProperty("_embedded")
    private EmbeddedAttacker _embedded;

    public DarknetAttackerResponse(DarknetPage page, DarknetAttackLink _links, EmbeddedAttacker _embedded) {
        this.page = page;
        this._links = _links;
        this._embedded = _embedded;
    }

    public DarknetAttackerResponse(){}

    public DarknetPage getPage() {
        return page;
    }

    public void setPage(DarknetPage page) {
        this.page = page;
    }

    public DarknetAttackLink get_links() {
        return _links;
    }

    public void set_links(DarknetAttackLink _links) {
        this._links = _links;
    }

    public EmbeddedAttacker get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbeddedAttacker _embedded) {
        this._embedded = _embedded;
    }

    @Override
    public String toString() {
        return "DarknetAttackerResponse{" +
                "page=" + page +
                ", _links=" + _links +
                ", _embedded=" + _embedded +
                '}';
    }
}
