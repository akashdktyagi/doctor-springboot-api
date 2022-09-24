package com.automationfraternity.controller;

import com.automationfraternity.model.Doctor;
import com.automationfraternity.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
@Slf4j
public class DoctorController {

    private DoctorService doctorService;
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/doctors")
    public Doctor updateDoctor(@RequestBody Doctor doctor) throws Exception {
        return doctorService.updateDoctor(doctor);
    }

    @DeleteMapping("/doctors/{registrationID}")
    public void deleteDoctorWithRegistrationID(@PathVariable String registrationID) throws Exception {
        doctorService.deleteDoctorByRegistrationID(registrationID);
    }

    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/doctors/{name}")
    public List<Doctor> getDoctorListByName(@PathVariable String name){
        return doctorService.getDoctorListByName(name);
    }

    @GetMapping("/doctors/{registrationID}")
    public Optional<Doctor> getDoctorListByRegistrationID(@PathVariable String registrationID){
        return doctorService.getDoctorListByRegistrationID(registrationID);
    }

}
