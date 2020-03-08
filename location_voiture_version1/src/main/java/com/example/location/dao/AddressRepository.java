package com.example.location.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	List<Address> findByPaye(String paye);
	List<Address> findByVille(String ville);
	List<Address> findByRegion(String region);
	List<Address> findByProvince(String province);

}
