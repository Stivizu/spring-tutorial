package com.stivizu.tutorial.spring.service;

import com.stivizu.tutorial.spring.model.Address;
import com.stivizu.tutorial.spring.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

}
