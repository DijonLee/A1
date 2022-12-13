/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * This shouldn't need to be modified.
 */
@Entity
public class AttackerWeb {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int memberCount;

    @OneToMany
    private Set<Attacker> attackers;

    @ManyToMany
    private Set<Indicator> indicators;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public Set<Attacker> getAttackers() {
        return attackers;
    }

    public void setAttackers(Set<Attacker> attackers) {
        this.attackers = attackers;
    }

    public Set<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<Indicator> indicators) {
        this.indicators = indicators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttackerWeb that = (AttackerWeb) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
