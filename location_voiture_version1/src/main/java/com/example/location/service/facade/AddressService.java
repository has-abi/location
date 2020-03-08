package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Address;

public interface AddressService {
	int save(Address address);
	int modify(Address address);
	int remove(Address address);
	List<Address> findAll();
}
