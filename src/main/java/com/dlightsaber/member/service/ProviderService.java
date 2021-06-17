package com.dlightsaber.member.service;

import com.dlightsaber.member.model.Provider;
import com.dlightsaber.member.repository.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository repository;

    public ProviderRepository getRepository() {
        return repository;
    }

    public void setRepository(ProviderRepository repository) {
        this.repository = repository;
    }

    public Map<String, Object> findProviders(String city, String speciality, String lastname, int page, int size){
        List<Provider> providers = new ArrayList<Provider>();
        Pageable paging = PageRequest.of(page, size);
        Page<Provider> pageTuts;

        if ((null == city) && (null == speciality) && (null == lastname)){
            pageTuts = repository.findAll(paging);
            System.out.println("Search All");
        }
        else if ((null != city) && (null == speciality) && (null == lastname)){
            pageTuts = repository.findByCity(city,paging);
            System.out.println("Search By City");
        }
        else if ((null == city) && (null != speciality) && (null == lastname)){
            pageTuts = repository.findBySpeciality(speciality,paging);
            System.out.println("Search By Speciality");
        }
        else if ((null == city) && (null == speciality) && (null != lastname)){
            pageTuts = repository.findByLastname(lastname,paging);
            System.out.println("Search By Last Name");
        }
        else if ((null != city) && (null != speciality) && (null == lastname)){
            pageTuts = repository.findByCityAndSpeciality(city,speciality,paging);
            System.out.println("Search By City and Speciality");
        }
        else if ((null == city) && (null != speciality) && (null != lastname)){
            pageTuts = repository.findBySpecialityAndLastname(speciality,lastname,paging);
            System.out.println("Search By Speciality and Last Name");
        }
        else if ((null != city) && (null == speciality) && (null != lastname)){
            pageTuts = repository.findByCityAndLastname(city,lastname,paging);
            System.out.println("Search By City and Last Name");
        }
        else {
            pageTuts = repository.findByCityAndSpecialityAndLastname(city, speciality, lastname, paging);
            System.out.println("Search By City, Speciality and Last Name");
        }

        providers = pageTuts.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("providers", providers);
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalProviders", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());
        return response;
    }
}
