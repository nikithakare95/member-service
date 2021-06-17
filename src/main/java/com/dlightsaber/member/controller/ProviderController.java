package com.dlightsaber.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlightsaber.member.service.ProviderService;

import java.util.Map;

@RestController
public class ProviderController{

    @Autowired
    private ProviderService service;

    public ProviderService getService() {
        return service;
    }

    public void setService(ProviderService service) {
        this.service = service;
    }

    @GetMapping("/providers")
    public ResponseEntity<Map<String, Object>> getAllTutorialsPage(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String speciality,
            @RequestParam(required = false) String lastname,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        try {
            Map<String, Object> response = service.findProviders(city,speciality,lastname,page,size);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
