package com.grishmaghatti.C2C.repositories;

import com.grishmaghatti.C2C.entities.CollegeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeUserRepo extends JpaRepository<CollegeUser, Long> {
    CollegeUser findByCollegeMail(String mail);

    @Query("SELECT c FROM CollegeUser c")
    CollegeUser getCollegeDetails();
}
