package org.example.service;

import org.example.entity.Doctor;
import org.example.entity.Patient;
import org.example.repository.DoctorRepository;
import org.example.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.List;

@Service
@ManagedResource(objectName = "org.example:type=DataExportServiceMBean")
public class DataExportService {

    private static final Logger logger = LoggerFactory.getLogger(DataExportService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional
    public void exportData() {
        clearDirectory("data");
        exportDoctors();
        exportPatients();
    }

    private void clearDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        } else {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    private void exportDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        logger.info("Doctors were written");
        for (Doctor doctor : doctors) {
            writeToFile("data/doctor_" + doctor.getId() + ".txt", doctor.toString());
        }
    }

    private void exportPatients() {
        List<Patient> patients = patientRepository.findAll();
        logger.info("Patients were written");
        for (Patient patient : patients) {
            writeToFile("data/patient_" + patient.getId() + ".txt", patient.toString());
        }
    }

    private void writeToFile(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            logger.error("Failed to write to file {}: {}", filePath, e.getMessage());
        }
    }

    @ManagedOperation(description = "Export data")
    public void exportDataViaJMX() {
        exportData();
    }

    public void registerMBean() {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName name = new ObjectName("org.example:type=DataExportServiceMBean");
            mBeanServer.registerMBean(this, name);
        } catch (Exception e) {
            logger.error("Failed to register MBean: {}", e.getMessage());
        }
    }
}