/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

/**
 * This shouldn't need to be modified.
 */
@Entity
@Table(name = "indicator", uniqueConstraints = @UniqueConstraint(columnNames = {"indicatorValue", "type"}))
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    String indicatorValue;

    IndicatorType type;

    @ManyToMany(cascade = { CascadeType.ALL })

    Set<Malware> relatedMalware;

    public Long getId() {
        return id;
    }

    public String getIndicatorValue() {
        return indicatorValue;
    }

    public void setIndicatorValue(String indicatorValue) {
        this.indicatorValue = indicatorValue;
    }

    public IndicatorType getType() {
        return type;
    }

    public void setType(IndicatorType type) {
        this.type = type;
    }

    public Set<Malware> getRelatedMalware() {
        return relatedMalware;
    }

    public void setRelatedMalware(Set<Malware> relatedMalware) {
        this.relatedMalware = relatedMalware;
    }

    public enum IndicatorType {
        FILE, IP, DOMAIN_NAME,HASH
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Indicator indicator = (Indicator) o;
        return Objects.equals(id, indicator.id) && Objects.equals(indicatorValue, indicator.indicatorValue) && type == indicator.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, indicatorValue, type);
    }
}
