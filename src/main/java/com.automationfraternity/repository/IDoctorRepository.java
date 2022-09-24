package com.automationfraternity.repository;

import com.automationfraternity.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByName(String name);
    Optional<Doctor> findByRegistrationID(String registrationID);
    void deleteByRegistrationID(String registrationID);
}
