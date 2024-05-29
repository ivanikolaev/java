package org.example.controller;

import org.example.entity.Doctor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private List<Doctor> doctors = new ArrayList<>();

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    @PostMapping
    public void addDoctor(@RequestBody Doctor doctor) {
        doctors.add(doctor);
    }

    @DeleteMapping("/{lastName}")
    public void deleteDoctor(@PathVariable String lastName) {
        doctors.removeIf(doctor -> doctor.getLastName().equals(lastName));
    }

    @PutMapping("/{lastName}")
    public void updateDoctor(@PathVariable String lastName, @RequestBody Doctor updatedDoctor) {
        for (Doctor doctor : doctors) {
            if (doctor.getLastName().equals(lastName)) {
                doctor.setFirstName(updatedDoctor.getFirstName());
                doctor.setPosition(updatedDoctor.getPosition());
                break;
            }
        }
    }
}