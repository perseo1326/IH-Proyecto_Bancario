package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.models.Address;
import com.josko.proyecto_bancario.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public Address saveNewAddress(Address address) {

        return addressRepository.save(address);

    }




}
