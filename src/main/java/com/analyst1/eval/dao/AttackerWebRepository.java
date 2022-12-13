/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.dao;

import com.analyst1.eval.model.Attacker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.analyst1.eval.model.AttackerWeb;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface AttackerWebRepository extends JpaRepository<AttackerWeb, Long> {
}
