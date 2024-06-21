package com.example.springboot.controller.DeepLearningModelPredict;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Patient;
import com.example.springboot.service.IPatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/patien")
public class PatientController {
    @Autowired
    IPatientService patientService;

    @PostMapping("/save")
    public Result save(@RequestBody Patient patient){

        Patient one = null;
        if (patient.getId() != null) {
            one = patientService.getById(patient.getId());
        }
        if(one == null && patient.getId() != null) {
            patientService.save(patient);
        }
        return Result.success();
    }
}
