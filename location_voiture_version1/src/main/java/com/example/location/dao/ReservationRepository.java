package com.example.location.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Reservation findByReference(String reference);
}
