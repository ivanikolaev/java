package org.example.service;

import org.example.entity.Patient;
import org.example.repository.PatientRepository;
import org.example.specification.PatientSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    public List<Patient> findAll() {
        logger.debug("Finding all patients");
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        logger.debug("Finding patient by id {}", id);
        return patientRepository.findById(id).orElse(null);
    }

    public Patient save(Patient patient) {
        logger.debug("Saving patient {}", patient);
        Patient savedPatient = patientRepository.save(patient);
        emailService.sendSaveNotification("Patient", savedPatient.toString());
        return savedPatient;
    }

    public void deleteById(Long id) {
        logger.debug("Deleting patient by id {}", id);
        patientRepository.deleteById(id);
    }

    public List<Patient> findByCriteria(String firstName, String lastName, Long doctorId) {
        logger.debug("Finding patient by criteria - Name: {}, Last name: {}, Doctor id: {}", firstName, lastName, doctorId);
        Specification<Patient> spec = PatientSpecification.getPatientsByCriteria(firstName, lastName, doctorId);
        return patientRepository.findAll(spec);
    }
}
