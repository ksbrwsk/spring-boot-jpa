/*
 * Copyright 2016-2017 Shawn Sherwood
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.undertree.symptom.repositories;

import static com.undertree.symptom.domain.QPatient.patient;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.undertree.symptom.domain.Patient;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

/**
 * Spring Data JPA Repository for the Patient entity with QueryDsl support.
 */
public interface PatientRepository extends JpaRepository<Patient, Long>,
    QueryDslPredicateExecutor<Patient> {

  Optional<Patient> findByPatientId(UUID patientId);
  //Optional<Patient> findByPatientIdAndVersion(UUID id, Long version);

  class Predicates {

    private Predicates() {
    }

    public static BooleanExpression hasBirthdayOn(final LocalDate date) {
      return patient.birthDate.month().eq(date.getMonthValue()).and(
          patient.birthDate.dayOfMonth().eq(date.getDayOfMonth()));
    }
  }
}
