package org.example.service;

import org.example.entity.Doctor;
import org.example.repository.DoctorRepository;
import org.example.specification.DoctorSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EmailService emailService;

    public List<Doctor> findAll() {
        logger.debug("Finding all doctors");
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        logger.debug("Finding doctor by id {}", id);
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor save(Doctor doctor) {
        logger.debug("Saving doctor {}", doctor);
        Doctor savedDoctor = doctorRepository.save(doctor);
        emailService.sendSaveNotification("Doctor", savedDoctor.toString());
        return savedDoctor;
    }

    public void deleteById(Long id) {
        logger.debug("Deleting doctor by id {}", id);
        doctorRepository.deleteById(id);
    }

    public List<Doctor> findByCriteria(String firstName, String lastName, String position) {
        logger.debug("Finding doctor by criteria - Name: {}, Last name: {}, Position: {}", firstName, lastName, position);
        Specification<Doctor> spec = DoctorSpecification.getDoctorsByCriteria(firstName, lastName, position);
        return doctorRepository.findAll(spec);
    }
}