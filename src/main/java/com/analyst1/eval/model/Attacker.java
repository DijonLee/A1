/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This shouldn't need to be modified.
 */
@Entity
public class Attacker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    /**
     * This should be the name, not the code. For example: "United States of America"
     */
    private String countryName;

    public Long getId() {
		return id;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Attacker attacker = (Attacker) o;
        return Objects.equals(id, attacker.id) && Objects.equals(name, attacker.name) && Objects.equals(countryName,
                attacker.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countryName);
    }
}
