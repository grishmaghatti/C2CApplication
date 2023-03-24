package com.grishmaghatti.C2C.service;

import com.grishmaghatti.C2C.dto.CollegeDetailsDTO;
import com.grishmaghatti.C2C.dto.CollegeUserDTO;
import com.grishmaghatti.C2C.entities.CollegeUser;


public interface CollegeUserService {
    CollegeUser addUser(CollegeUserDTO collegeUserDTO);

    String login(String mail, String password);

    CollegeDetailsDTO getCollegeDetails();

    CollegeUser updateUser(CollegeUserDTO collegeUserDTO);

    String deleteUser(String mail, String password);
}
