package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Location;

public interface LocationService {
	int save(Location location);
	int modify(Location location);
	int remove(Location location);
	List<Location> findAll();
}
