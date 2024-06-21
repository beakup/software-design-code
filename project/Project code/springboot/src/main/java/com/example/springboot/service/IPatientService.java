package com.example.springboot.service;

import com.example.springboot.entity.Patient;

public interface IPatientService {
    void save(Patient patient);
    Patient getById(Integer id);
}
