package com.example.hospital.management.Controllers;

import com.example.hospital.management.Models.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
public class PatientController {
    HashMap<Integer, Patient> patientDb = new HashMap<>();

    @PostMapping("/addPatientViaParamaters")
    public String addPatient(@RequestParam("patientId")Integer patientId, @RequestParam("name")String name,
                             @RequestParam("age")Integer age, @RequestParam("disease")String disease){


        Patient patient = new Patient(patientId,name,disease,age);
        patientDb.put(patientId,patient);

        return "Patient added successfullly";
    }
    @PostMapping("/addPatientViaRequestBody")
    public String addPatient(@RequestBody Patient patient){

        int key = patient.getPatientId();

        patientDb.put(key,patient);

        return "Patient added via Request Body";
    }
    @GetMapping("/getInfoViaPathVariable/{patientId}")
    public Patient getPatientInfo(@PathVariable("patientId")Integer patientId){

        Patient patient = patientDb.get(patientId);
        return patient;
    }
    @GetMapping("/getPatients/{age}/{disease}")
    public List<Patient> getPatients(@PathVariable("age")Integer age,@PathVariable("disease")String disease){
            //is me jo age hai and disease hai ager vo swap ho jata hai to bhi koi
            //problem nahi hogi wo swap ho sakta hai
        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDb.values()){

            if(p.getAge()>age && p.getDisease().equals(disease)){
                patients.add(p);
            }
        }
        return patients;
    }
    @GetMapping("/getPatientInfo")
    public Patient getPatient(@RequestParam("patientId")Integer patientId){

        Patient patient = patientDb.get(patientId);
        return patient;
    }
    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();

        for(Patient p : patientDb.values()){
            patients.add(p);
        }
        return patients;
    }
    @GetMapping("/getPatientByName")
    public Patient getPatientByName(@RequestParam("name")String name){
        for(Patient p:patientDb.values()){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    @GetMapping("/getPatientsListGreaterThanAge")
    public List<Patient> getPatientsGreaterThanAge(@RequestParam("age")Integer age){

        List<Patient> patients = new ArrayList<>();

        for(Patient p:patientDb.values()){

            if(p.getAge()>age){
                patients.add(p);
            }
        }
        return patients;
    }
    @PutMapping("/updateDisease")
    public String updateDisease(@RequestParam("patientId")Integer patientId,@RequestParam("disease")String disease){

        if(patientDb.containsKey(patientId)){

            Patient patient = patientDb.get(patientId);

            patient.setDisease(disease);

            patientDb.put(patientId,patient);

            return "Updated successfully";
        }else {
            return "Patient doesnot exist";
        }
    }
    @PutMapping("/updatePatientDetails")
    public String updatePatientDetails(@RequestBody Patient patient){

        int key = patient.getPatientId();

        if(patientDb.containsKey(key)){
            patientDb.put(key,patient);
            return "Updated patient successfully";
        }
        else{
            return "Data was not existing";
        }

    }
    @DeleteMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId")Integer patientId){

        patientDb.remove(patientId);
        return "Patient deleted successfully";
    }
}
