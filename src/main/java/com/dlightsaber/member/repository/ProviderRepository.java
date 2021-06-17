package com.dlightsaber.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dlightsaber.member.model.Provider;


@Repository

public interface ProviderRepository extends MongoRepository<Provider, String> {
    Page<Provider> findByCity(String city, Pageable pageable);
    Page<Provider> findBySpeciality(String speciality, Pageable pageable);
    Page<Provider> findByLastname(String lastname, Pageable pageable);
    Page<Provider> findByCityAndSpecialityAndLastname(String city, String speciality, String lastname, Pageable pageable);
    Page<Provider> findByCityAndSpeciality(String city, String speciality, Pageable pageable);
    Page<Provider> findBySpecialityAndLastname(String speciality, String lastname, Pageable pageable);
    Page<Provider> findByCityAndLastname(String city, String lastname, Pageable pageable);

}
