package com.example.location.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.location.bean.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	Reservation findByReference(String reference);

	@Query(value = "select voiture.libelle from voiture,reservation where voiture.id = reservation.voiture", nativeQuery = true)
	List<String> getListVoiture();

	@Query(value = "select marque.brand from voiture,reservation,marque where voiture.id = reservation.voiture and marque.id = voiture.marque", nativeQuery = true)
	List<String> getListMarques();
	
	@Query(value = "select * from reservation where DATE_FORMAT(date_reserv,'%Y-%m') = DATE_FORMAT(:annee-:mois,'%Y-%m')",nativeQuery = true)
	List<Reservation> findAllByAnneeAndMois(@Param("annee") String annee,@Param("mois") String mois);
	
	@Query(value = "select count(*) from reservation where DATE_FORMAT(date_reserv,'%Y-%m-%d') = DATE_FORMAT(':annee-:mois-:jour','%Y-%m-%d')",nativeQuery = true)
	int countReservation(@Param("jour") String jour,@Param("mois") String mois,@Param("annee") String annee);
}
