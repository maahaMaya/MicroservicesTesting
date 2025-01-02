package com.restservice.sourav.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restservice.sourav.bean.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
