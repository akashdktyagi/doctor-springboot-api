package com.automationfraternity.services;

import com.automationfraternity.model.Doctor;
import com.automationfraternity.repository.IDoctorRepository;
import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;

class DoctorServiceTest {

    DoctorService doctorService;
    IDoctorRepository doctorRepository;

    Doctor doctor = Doctor.builder()
            .withName("Akash").withCanDoHomeVisit(true).withEmailID("akash.tyagi@wow.com")
            .withClinicNameAndAddress("patal lok").withExperienceInYears(90).withRegistrationID("1234567")
            .withSpecialization("MBBS").build();
    Doctor doctor1 = Doctor.builder()
            .withName("Akash").withCanDoHomeVisit(true).withEmailID("akash.tyagi@wow.com")
            .withClinicNameAndAddress("patal lok").withExperienceInYears(90).withRegistrationID("1234567")
            .withSpecialization("MBBS").build();
    Doctor doctorToBeUpdated = Doctor.builder()
            .withName("AkashUpdated").withCanDoHomeVisit(true).withEmailID("akash.tyagi@wow.com")
            .withClinicNameAndAddress("patal lok").withExperienceInYears(90).withRegistrationID("1234567")
            .withSpecialization("MBBS").build();
    Doctor doctorToBeDeleted = Doctor.builder()
            .withName("AkashUpdated").withCanDoHomeVisit(true).withEmailID("akash.tyagi@wow.com")
            .withClinicNameAndAddress("patal lok").withExperienceInYears(90).withRegistrationID("1")
            .withSpecialization("MBBS").build();

    @BeforeEach
    void init() {
        doctorRepository  = Mockito.mock(IDoctorRepository.class);
        Mockito.when(doctorRepository.save(any(Doctor.class))).then(returnsFirstArg());
        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    void createDoctor() {
        Doctor doctorReturned = doctorService.createDoctor(doctor);
        Assertions.assertThat(doctorReturned).isNotNull().isEqualTo(doctor);
    }

    @Test
    void updateDoctorIfDoctorIsFound() throws Exception {
        Mockito.when(doctorRepository.findByRegistrationID(any())).thenReturn(Optional.ofNullable(doctorToBeUpdated));
        Doctor doctorReturned = doctorService.updateDoctor(doctorToBeUpdated);
        Assertions.assertThat(doctorReturned.getName()).isEqualTo(doctorToBeUpdated.getName());
    }

    @Test
    void updateDoctorIfDoctorIsNotFound() throws Exception {
        Throwable thrown = catchThrowable(()->{doctorService.updateDoctor(doctorToBeUpdated);});
        Assertions.assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessage("Doctor not present with this registration id: 1234567  can not Update.");
    }

    @Test
    void deleteDoctorIfDoctorIsNotFound() throws Exception {
        Mockito.doNothing().when(doctorRepository).deleteByRegistrationID(isA(String.class));
        Throwable thrown = catchThrowable(()->{doctorService.deleteDoctorByRegistrationID("1");});
        Assertions.assertThat(thrown).isInstanceOf(Exception.class)
                .hasMessage("Doctor not present with this registration id: 1  can not Delete.");
    }

    @Test
    void deleteDoctorIfDoctorIsFound() throws Exception {
        Mockito.doNothing().when(doctorRepository).deleteByRegistrationID(isA(String.class));
        Mockito.when(doctorRepository.findByRegistrationID(any())).thenReturn(Optional.ofNullable(doctorToBeDeleted));
        Optional<Doctor> docDeleted = doctorService.deleteDoctorByRegistrationID("1");
        Assertions.assertThat(docDeleted.get().getRegistrationID()).isEqualTo("1");
    }

    @Test
    void getAllDoctors() {
        Mockito.when(doctorRepository.findAll()).thenReturn(Lists.newArrayList(doctor,doctor1));
        List<Doctor> returnList = doctorService.getAllDoctors();
        Assertions.assertThat(returnList).hasAtLeastOneElementOfType(Doctor.class);
    }

    @Test
    void getDoctorListByName() {
        Mockito.when(doctorRepository.findByName(any())).thenReturn(Lists.newArrayList(doctor,doctor1));
        List<Doctor> returnList = doctorService.getDoctorListByName("Akash");
        Assertions.assertThat(returnList).hasAtLeastOneElementOfType(Doctor.class);
    }

    @Test
    void getDoctorListByRegistrationID() {
        Mockito.when(doctorRepository.findByRegistrationID(any())).thenReturn(Optional.ofNullable(doctorToBeDeleted));
        Optional<Doctor> docGetByID = doctorService.getDoctorListByRegistrationID("1");
        Assertions.assertThat(docGetByID.get().getRegistrationID()).isEqualTo("1");
    }
}