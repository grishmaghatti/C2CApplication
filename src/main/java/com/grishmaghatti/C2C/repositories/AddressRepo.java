package com.grishmaghatti.C2C.repositories;

import com.grishmaghatti.C2C.entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Long> {
}
