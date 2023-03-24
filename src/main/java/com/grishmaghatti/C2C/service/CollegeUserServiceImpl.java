package com.grishmaghatti.C2C.service;

import com.grishmaghatti.C2C.dto.CollegeDetailsDTO;
import com.grishmaghatti.C2C.dto.CollegeUserDTO;
import com.grishmaghatti.C2C.entities.CollegeUser;
import com.grishmaghatti.C2C.exception.UserException;
import com.grishmaghatti.C2C.repositories.AddressRepo;
import com.grishmaghatti.C2C.repositories.CollegeUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CollegeUserServiceImpl implements CollegeUserService{
    @Autowired
    private CollegeUserRepo collegeUserRepo;

    @Autowired
    private AddressRepo addressRepo;


    @Override
    public CollegeUser addUser(CollegeUserDTO collegeUserDTO) {
        if (!checkForMail(collegeUserDTO.getCollegeUser().getCollegeMail())) {
            if(passwordValidation(collegeUserDTO.getCollegeUser().getPassword())) {
                return collegeUserRepo.save(collegeUserDTO.getCollegeUser());
            }else {
                throw new UserException("Password pattern incorrect");
            }
        }else {
            throw new UserException("Mail exist, try with other mail");
        }
    }

    @Override
    public String login(String mail, String password) {
        List<CollegeUser> user = collegeUserRepo.findAll();
        if(user.stream().anyMatch(p -> p.getCollegeMail().equals(mail))){
            CollegeUser users = collegeUserRepo.findByCollegeMail(mail);
            if (users.getPassword().equals(password)) {
                return mail;
            }else {
                throw new UserException("Password is incorrect");
            }
        }else {
            throw new UserException("Mail is not been register");
        }
    }

    public CollegeDetailsDTO getCollegeDetails() {
        CollegeUser collegeUser = collegeUserRepo.getCollegeDetails();
        CollegeDetailsDTO collegeDetailsDTO = new CollegeDetailsDTO();
        collegeDetailsDTO.setCollegeName(collegeUser.getCollegeName());
        collegeDetailsDTO.setCollegeMail(collegeUser.getCollegeMail());
        collegeDetailsDTO.setStreet(collegeUser.getAddress().getStreet());
        collegeDetailsDTO.setCity(collegeUser.getAddress().getCity());
        collegeDetailsDTO.setState(collegeUser.getAddress().getState());
        collegeDetailsDTO.setCountry(collegeUser.getAddress().getCountry());
        collegeDetailsDTO.setPinCode(collegeUser.getAddress().getPinCode());
        return collegeDetailsDTO;
    }

    @Override
    public CollegeUser updateUser(CollegeUserDTO collegeUserDTO) {
        CollegeUser user = collegeUserRepo.findByCollegeMail(collegeUserDTO.getCollegeUser().getCollegeMail());
        user.setCollegeName(collegeUserDTO.getCollegeUser().getCollegeName());
        user.setAddress(collegeUserDTO.getCollegeUser().getAddress());
        return collegeUserRepo.save(user);
    }

    @Override
    public String deleteUser(String mail, String password) {
        CollegeUser user = collegeUserRepo.findByCollegeMail(mail);
        if(user.getPassword().equals(password)){
            collegeUserRepo.delete(user);
            return "User is been deleted";
        }else {
            throw new UserException("Password is incorrect");
        }
    }

    private boolean checkForMail(String collegeMail) {
        List<CollegeUser> user = collegeUserRepo.findAll();
        if(user.stream().anyMatch(p -> p.getCollegeMail().equals(collegeMail))){
            return true;
        }
        return false;
    }

    private boolean passwordValidation(String password) {
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,}$");
        Matcher key = pattern.matcher(password);
        if (key.matches()) {
            return true;
        }
        return false;
    }
}
