package org.example.controller;

import org.example.entity.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private List<Patient> patients = new ArrayList<>();

    @GetMapping
    public List<Patient> getAllPatients() {
        return patients;
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient) {
        patients.add(patient);
    }

    @DeleteMapping("/{lastName}")
    public void deletePatient(@PathVariable String lastName) {
        patients.removeIf(patient -> patient.getLastName().equals(lastName));
    }

    @PutMapping("/{lastName}")
    public void updatePatient(@PathVariable String lastName, @RequestBody Patient updatedPatient) {
        for (Patient patient : patients) {
            if (patient.getLastName().equals(lastName)) {
                patient.setFirstName(updatedPatient.getFirstName());
                break;
            }
        }
    }
}