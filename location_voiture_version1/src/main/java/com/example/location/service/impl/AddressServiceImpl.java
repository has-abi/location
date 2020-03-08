package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Address;
import com.example.location.dao.AddressRepository;
import com.example.location.service.facade.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

		@Autowired
		private AddressRepository addressRepository;
	@Override
	public int save(Address address) {
		addressRepository.save(address);
		return 0;
	}

	@Override
	public int modify(Address address) {
		addressRepository.save(address);
		return 0;
	}

	@Override
	public int remove(Address address) {
		addressRepository.delete(address);
		return 0;
	}

	@Override
	public List<Address> findAll() {
		return addressRepository.findAll();
	}

}
