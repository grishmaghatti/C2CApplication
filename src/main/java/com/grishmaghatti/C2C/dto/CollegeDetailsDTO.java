package com.grishmaghatti.C2C.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CollegeDetailsDTO {
    private String collegeName;
    private String collegeMail;
    private String street;
    private String city;
    private String state;
    private String country;
    private int pinCode;

}
