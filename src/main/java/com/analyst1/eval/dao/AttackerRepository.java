/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.analyst1.eval.model.Attacker;

import java.util.Optional;

@Repository
@RepositoryRestResource
public interface AttackerRepository extends JpaRepository<Attacker, Long> {
}
