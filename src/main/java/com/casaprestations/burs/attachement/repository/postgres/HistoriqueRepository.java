package com.casaprestations.burs.attachement.repository.postgres;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.entity.db.postgres.HistoriquePK;

public interface HistoriqueRepository extends JpaRepository<Historique, HistoriquePK> {

	// Averda(A34) : Les vehicules de Balayage mécanisé durant un jour
	@Query("select Distinct h.vehicule.id from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id=v.id and v.nom LIKE 'A34%'")
	List<Integer> findVehiculesOfBalayageAverdaByDate(Date date);

	// Averda(A34): L'historiques du Balayage mécanisé effectuée par un vehicule
	// durant un jour
	@Query("select h from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id= ?2 and h.vehicule.id=v.id and v.nom LIKE 'A34%' order by dateheure")
	List<Historique> findBalayagesAverdaByDateAndVehicule(Date date, Integer idVehicule);

	// Averda(A37) : Les vehicules de lavage mécanisé durant un jour
	@Query("select Distinct h.vehicule.id from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id=v.id and v.nom LIKE 'A37%'")
	List<Integer> findVehiculesOfLavageAverdaByDate(Date date);

	// Averda(A37): L'historiques du lavage mécanisé effectuée par un vehicule
	// durant un jour
	@Query("select h from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id= ?2 and h.vehicule.id=v.id and v.nom LIKE 'A37%' order by dateheure")
	List<Historique> findLavagesAverdaByDateAndVehicule(Date date, Integer idVehicule);

	// Sita(S34, S33) : Les vehicules du Balayage mécanisé durant un jour
	@Query("select Distinct h.vehicule.id from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id=v.id and (v.nom LIKE 'S34%' or v.nom LIKE 'S33%') ")
	List<Integer> findVehiculesOfBalayageSitaByDate(Date date);

	// Sita(S34, S33) : L'historiques du Balayage mécanisé effectuée par
	// un vehicule durant un jour
	@Query("select h from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id= ?2 and h.vehicule.id=v.id and (v.nom LIKE 'S34%' or v.nom LIKE 'S33%') order by dateheure")
	List<Historique> findBalayagesSitaByDateAndVehicule(Date date, Integer idVehicule);

	// Sita(S37) : Les vehicules du lavage mécanisé durant un jour
	@Query("select Distinct h.vehicule.id from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id=v.id and v.nom LIKE 'S37%' ")
	List<Integer> findVehiculesOfLavageSitaByDate(Date date);

	// Sita(S37) : L'historiques du lavage mécanisé effectuée par
	// un vehicule durant un jour
	@Query("select h from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id= ?2 and h.vehicule.id=v.id and v.nom LIKE 'S37%' order by dateheure")
	List<Historique> findLavagesSitaByDateAndVehicule(Date date, Integer idVehicule);

	// Averda(A32) : Les vehicules du prestation Lavage Brossage durant un jour
	@Query("select Distinct h.vehicule.id from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id=v.id and v.nom LIKE 'A32%'")
	List<Integer> findVehiculesOfBrossageLavageByDate(Date date);

	// Averda(A32) : L'historiques du prestation Lavage Brossage effectuée par
	// un vehicule durant un jour
	@Query("select h from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id= ?2 and h.vehicule.id=v.id and v.nom LIKE 'A32%' order by dateheure")
	List<Historique> findBrossagesByDateAndVehicule(Date date, Integer idVehicule);

	// Averda(A32) : L'historiques du prestation Lavage Brossage durant un jour
	@Query("select h from Historique h, Vehicule v where date(h.id.dateheure) = ?1 and h.vehicule.id=v.id and v.nom LIKE 'A32%' order by dateheure")
	List<Historique> findBrossagesByDate(Date date);

	// L'historiques
	@Query("select h from Historique h where date(h.id.dateheure) = ?1 and h.vehicule.id= ?2 order by dateheure")
	List<Historique> findHistoriquesByDateAndVehicule(Date date, Integer idVehicule);

	// ********************************Test*********************************//
	@Query("select count(v)  from Vehicule v")
	long count();

}
