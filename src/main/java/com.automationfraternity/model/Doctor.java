package com.automationfraternity.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
public class Doctor {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String registrationID;
    @Column(nullable = false)
    String qualifications;
    @Column(nullable = false)
    String specialization;
    @Column(nullable = false)
    Integer experienceInYears;
    @Column(nullable = false)
    String emailID;
    @Column(nullable = false)
    String clinicNameAndAddress;
    @Column(nullable = false)
    Boolean canDoHomeVisit;
}
