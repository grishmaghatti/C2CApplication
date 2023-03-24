package com.grishmaghatti.C2C.controller;

import com.grishmaghatti.C2C.dto.CollegeDetailsDTO;
import com.grishmaghatti.C2C.dto.CollegeUserDTO;
import com.grishmaghatti.C2C.entities.CollegeUser;
import com.grishmaghatti.C2C.service.CollegeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CollegeUserController {
    @Autowired
    private CollegeUserService collegeUserService;


    @GetMapping("/login")
    public ResponseEntity<String> login (@RequestParam String mail, @RequestParam String password){
        return ResponseEntity.ok(collegeUserService.login(mail, password));
    }

    @PostMapping("/addCollegeUser")
    public ResponseEntity<CollegeUser> addUser (@RequestBody CollegeUserDTO collegeUserDTO){
        return ResponseEntity.ok(collegeUserService.addUser(collegeUserDTO));
    }

    @GetMapping("/collegeDetail")
    public ResponseEntity<CollegeDetailsDTO> getCollegeDetails(){
        return ResponseEntity.ok(collegeUserService.getCollegeDetails());
    }

    @PostMapping("/updateCollegeUser")
    public ResponseEntity<CollegeUser> updateUser (@RequestBody CollegeUserDTO collegeUserDTO){
       return ResponseEntity.ok(collegeUserService.updateUser(collegeUserDTO));
    }

    @DeleteMapping("/deleteCollegeUser")
    public ResponseEntity<String> deleteUser(@RequestParam String mail, @RequestParam String password){
        return ResponseEntity.ok(collegeUserService.deleteUser(mail, password));
    }


}
