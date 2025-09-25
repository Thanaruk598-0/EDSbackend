package com.candyhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.candyhouse.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
