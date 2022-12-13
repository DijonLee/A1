package com.analyst1.eval.model.darknet.pojo.attacker;

import java.util.Collection;

public class EmbeddedAttacker {
    private Collection<DarknetAttacker> attackers;

    public EmbeddedAttacker(Collection<DarknetAttacker> attackers) {
        this.attackers = attackers;
    }

    public EmbeddedAttacker() {
    }

    public Collection<DarknetAttacker> getAttackers() {
        return attackers;
    }

    public void setAttackers(Collection<DarknetAttacker> attackers) {
        this.attackers = attackers;
    }


}
