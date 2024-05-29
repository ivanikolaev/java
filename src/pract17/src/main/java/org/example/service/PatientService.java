package org.example.service;

import org.example.entity.Patient;
import org.example.repository.PatientRepository;
import org.example.specification.PatientSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findByCriteria(String firstName, String lastName, Long doctorId) {
        Specification<Patient> spec = PatientSpecification.getPatientsByCriteria(firstName, lastName, doctorId);
        return patientRepository.findAll(spec);
    }
}
