package com.example.location.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.location.bean.Reservation;
import com.example.location.dao.ReservationRepository;
import com.example.location.service.facade.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public int save(Reservation reservation) {
		Reservation res = reservationRepository.findByReference(reservation.getReference());
		if (res != null) {
			return -1;
		} else {
			reservationRepository.save(reservation);
			return 1;
		}
	}

	@Override
	public int modify(Reservation reservation) {
		Reservation res = reservationRepository.findByReference(reservation.getReference());
		if (res == null) {
			return -1;
		} else {
			reservationRepository.save(reservation);
			return 1;
		}
	}

	@Override
	public int remove(Reservation reservation) {
		Reservation res = reservationRepository.findByReference(reservation.getReference());
		if (res == null) {
			return -1;
		} else {
			reservationRepository.delete(res);
			return 1;
		}
	}
 
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findByReference(String reference) {
		return reservationRepository.findByReference(reference);
	}

	@Override
	public List<String> getListVoiture() {
		return reservationRepository.getListVoiture();
	}

	@Override
	public List<String> getListMarques() {
		return reservationRepository.getListMarques();
	}

	@Override
	public List<Reservation> findAllByAnneeAndMois(String annee, String mois) {
		return reservationRepository.findAllByAnneeAndMois(annee, mois);
	}

	@Override
	public int countReservation(String jour, String mois, String annee) {
		return reservationRepository.countReservation(jour, mois, annee);
	}

}
