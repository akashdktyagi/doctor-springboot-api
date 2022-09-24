package com.automationfraternity.services;

import com.automationfraternity.model.Doctor;
import com.automationfraternity.repository.IDoctorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class DoctorService {

    private IDoctorRepository doctorRepository;

    public DoctorService(IDoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public Doctor createDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) throws Exception {
        Optional<Doctor> doctorIfPresent = doctorRepository.findByRegistrationID(doctor.getRegistrationID());
        if (doctorIfPresent.isPresent()){
            return doctorRepository.save(doctor);
        }else{
            throw new Exception("Doctor not present with this registration id: " + doctor.getRegistrationID() + "  can not Update.");
        }
    }

    public Optional<Doctor> deleteDoctorByRegistrationID(String registrationID) throws Exception {
        Optional<Doctor> doctorToBeDelete = getDoctorListByRegistrationID(registrationID);
        if (doctorToBeDelete.isPresent()){
            doctorRepository.deleteByRegistrationID(registrationID);
            log.info("Doctor Deleted with registration id as :" + registrationID);
        }else{
            throw new Exception("Doctor not present with this registration id: " + registrationID + "  can not Delete.");
        }
        return doctorToBeDelete;

    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public List<Doctor> getDoctorListByName(String name){
        return doctorRepository.findByName(name);
    }

    public Optional<Doctor> getDoctorListByRegistrationID(String id){
        return doctorRepository.findByRegistrationID(id);
    }
}
