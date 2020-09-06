package com.example.location.service.facade;

import java.util.List;

import com.example.location.bean.Reservation;

public interface ReservationService {
	int save(Reservation reservation);

	int modify(Reservation reservation);

	int remove(Reservation reservation);

	List<Reservation> findAll();

	Reservation findByReference(String reference);

	List<String> getListVoiture();
	
	List<String> getListMarques();
	
	List<Reservation> findAllByAnneeAndMois(String annee,String mois);
	
	int countReservation(String jour,String mois,String annee);
}
