package com.senacbooks.senacbooks.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    public AddressDTO findByZipCode(String zipCode);
}
