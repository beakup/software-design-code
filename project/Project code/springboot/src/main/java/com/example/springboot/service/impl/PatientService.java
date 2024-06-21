package com.example.springboot.service.impl;

import com.example.springboot.entity.Patient;
import com.example.springboot.mapper.PatientMapper;
import com.example.springboot.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements IPatientService {
    @Autowired
    PatientMapper patientMapper;

    @Override
    public void save(Patient patient) {
        patientMapper.save(patient);
    }

    @Override
    public Patient getById(Integer id) {
        return patientMapper.getById(id);
    }
}
