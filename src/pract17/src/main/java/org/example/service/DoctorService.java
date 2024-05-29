package org.example.service;

import org.example.entity.Doctor;
import org.example.repository.DoctorRepository;
import org.example.specification.DoctorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> findByCriteria(String firstName, String lastName, String position) {
        Specification<Doctor> spec = DoctorSpecification.getDoctorsByCriteria(firstName, lastName, position);
        return doctorRepository.findAll(spec);
    }
}