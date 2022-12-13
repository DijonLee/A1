/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.dao;

import com.analyst1.eval.model.AttackerWeb;
import com.analyst1.eval.model.Malware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.analyst1.eval.model.Indicator;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    Indicator findByTypeAndIndicatorValue(Indicator.IndicatorType type, String indicatorValue);
}
