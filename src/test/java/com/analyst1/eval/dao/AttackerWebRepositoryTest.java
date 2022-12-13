/*
 * Copyright (c) Analyst1 2022.
 */

package com.analyst1.eval.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.analyst1.eval.model.AttackerWeb;

@SpringBootTest
@Transactional
class AttackerWebRepositoryTest {

    @Autowired
    private AttackerWebRepository attackerWebRepository;

    @Test
    void saveAndLoadWorks() {
        AttackerWeb r = new AttackerWeb();
        AttackerWeb savedAttackerWeb = attackerWebRepository.save(r);
        AttackerWeb loadedAttackerWeb = attackerWebRepository.findById(savedAttackerWeb.getId()).orElseThrow();
        assertThat(savedAttackerWeb).usingRecursiveComparison().isEqualTo(loadedAttackerWeb);
    }
}