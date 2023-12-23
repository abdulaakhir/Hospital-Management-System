package com.example.hospital.management.Controllers;

import com.example.hospital.management.Models.Doctor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
//@RequestMapping("/doctor")  ager request mapping ka use karte hai to hum ko har method ke age
//aur api me bar bar doctor likhne ki zarurat nahi hoti hai
public class DoctorController {

    HashMap<Integer, Doctor> doctorDb = new HashMap<>();

    @PostMapping("/addDoctor")
    //ager upar request mapping me doctor likha hai to is line me hum ko
    //add ke age doctor likhne ki zarurat nahi hoti hai wo har jagah automatically
    //add kar dega
    public String addDoctor(@RequestBody Doctor doctor){

        int doctorId = doctor.getDoctorId();
        doctorDb.put(doctorId,doctor);

        return "doctor added successfully";
    }

}
