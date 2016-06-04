package com.casaprestations.burs.attachement.metier.postgres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.filter.text.cql2.CQL;
import org.geotools.filter.text.cql2.CQLException;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.referencing.CRS;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.filter.Filter;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBrossage;
import com.casaprestations.burs.attachement.entity.db.calcul.Place;
import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.casaprestations.burs.attachement.repository.calcul.LavageBrossageRepository;
import com.casaprestations.burs.attachement.repository.calcul.PlaceRepository;
import com.casaprestations.burs.attachement.repository.calcul.VehiculeDestinationRepository;
import com.casaprestations.burs.attachement.repository.postgres.VehiculeRepository;
import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.util.VersionFSHierarchyMaker;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

@Service
public class GeometryMetierImpl implements IGeometryMetier {

	private static String ZONE_INTERVENTION_TABLE_NAME = "places";
	// private final Logger LOGGER 
	// LoggerFactory.getLogger(GeometryMetierImpl.class);

	@Autowired
	private JDBCDataStore postGisDataStore;
	@Autowired
	private IHistoriqueMetier historiqueMetier;
	@Autowired
	private LavageBrossageRepository lavageBrossageRepository;
	@Autowired
	private IVehiculeMetier vehiculeMetier;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private VehiculeDestinationRepository vehiculeDestinationRepository; 
	
	public List<Point> getIntersectedFeature(List<Point> points) {
		SimpleFeatureSource fs = null;
		try {
			fs = postGisDataStore.getFeatureSource(ZONE_INTERVENTION_TABLE_NAME);
			String geometryColumnName = fs.getSchema().getGeometryDescriptor().getLocalName();

			System.out.println("geometryColumnName : " + geometryColumnName);
			SimpleFeatureCollection sfc = null;
			sfc = fs.getFeatures();
			SimpleFeatureIterator iter = sfc.features();
			SimpleFeature sf;
			List<Point> resultPoints = new ArrayList<Point>();
			while (iter.hasNext()) {
				System.out.println("****************************************");
				sf = iter.next();
				String featureIdentifier = sf.getIdentifier().getID();
				String idPlace = featureIdentifier.replaceAll("[a-z]", "").replace(".", "");
				int idP = Integer.parseInt(idPlace);
				System.out.println("idPlace : " + idP);

				Geometry geom = (Geometry) sf.getAttribute(geometryColumnName);
				// System.out.println("Points de ce geometry : "+geom);
				// String.
				Geometry bufferedGeom = geom.buffer(1);
				// System.out.println("Les points de place 1 : " +geom);
				for (Point point : points) {
					if (point.intersects(geom)) {
						resultPoints.add(point);
						System.out.println("ce point :" + point + " intersete vc place num : " + idP);
					}
				}
				HashSet<Point> pts = new HashSet<Point>();
				for (Point point : resultPoints) {
					pts.add(point);
				}
				// System.out.println("Hashset pts : " + pts);
			}
			return resultPoints;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting intersection", e);
		}
	}

	public void getIntersectedForInsertingLavageBrossage() {
		Date dateCourant = new Date();
		dateCourant.setDate(dateCourant.getDate() - 1);
		Date dateAvant = dateCourant;

		SimpleFeatureSource fs = null;
		try {
			fs = postGisDataStore.getFeatureSource(ZONE_INTERVENTION_TABLE_NAME);
			String geometryColumnName = fs.getSchema().getGeometryDescriptor().getLocalName();
			SimpleFeatureCollection sfc = null;
			sfc = fs.getFeatures();
			SimpleFeatureIterator iter = sfc.features();
			SimpleFeature sf;
			List<Historique> allHistoriques = historiqueMetier.findBrossagesByDate(dateAvant);
			System.out.println("findBrossagesByDate(dateAvant) de A32 : " + allHistoriques.size());
			Set<Integer> idVehicules = new HashSet<Integer>();
			for (int i = 0; i < allHistoriques.size(); i++) {
				idVehicules.add(allHistoriques.get(i).getVehicule().getId());
			}
			System.out.println("idVehicules de allHistoriques  : " + idVehicules.size() + " qui sont " + idVehicules);
			while (iter.hasNext()) {
				System.out.println("**************************************************************");
				sf = iter.next();
				Geometry geom = (Geometry) sf.getAttribute(geometryColumnName);
				// Geometry bufferedeGeom = geom.buffer(10);
				Iterator idV = idVehicules.iterator();
				while (idV.hasNext()) {
					Integer idVeh = (Integer) idV.next();
					System.out.println("**************id Vehicule new************** : " + idVeh);
					Double duree = (double) 0;
					System.out.println("duree new : " + duree);
					// Historiques du vehicule idVeh
					List<Historique> historiquesOfThisVehicule = new ArrayList<Historique>();
					getHistoriqueV(allHistoriques, idVeh, historiquesOfThisVehicule);
					System.out.println("size historiquesOfThisVehicule : " + historiquesOfThisVehicule.size());

					label: for (int i = 0; i < historiquesOfThisVehicule.size(); i++) {
						System.out.println("*************");
						if ((historiqueMetier.createPointfromXY_43(historiquesOfThisVehicule.get(i).getLongitude(),
								historiquesOfThisVehicule.get(i).getLatitude()).intersects(geom))
								&& (historiquesOfThisVehicule.get(i).getCapteurTor() == 16
										|| historiquesOfThisVehicule.get(i).getCapteurTor() == 64)
								&& (i != historiquesOfThisVehicule.size() - 1)) {
							System.out.println(
									"Point intersecte+active : " + historiquesOfThisVehicule.get(i).getLatitude() + ","
											+ historiquesOfThisVehicule.get(i).getLongitude());
							for (int j = i + 1; j < historiquesOfThisVehicule.size(); j++) {
								if ((historiqueMetier
										.createPointfromXY_43(historiquesOfThisVehicule.get(j).getLongitude(),
												historiquesOfThisVehicule.get(j).getLatitude())
										.intersects(geom))) {
									System.out.println(
											"Suiviste intersect : " + historiquesOfThisVehicule.get(j).getLatitude()
													+ "," + historiquesOfThisVehicule.get(j).getLongitude());
									long diff = historiquesOfThisVehicule.get(j).getId().getDateheure().getTime()
											- historiquesOfThisVehicule.get(i).getId().getDateheure().getTime();
									long diffSeconds = diff / 1000 % 60;
									long diffMinutes = diff / (60 * 1000) % 60;
									long diffHours = diff / (60 * 60 * 1000);
									Double d = (double) (diffSeconds + (diffMinutes * 60) + (diffHours * 3600));
									System.out.println("duree : " + d);
									duree = duree + d;
									System.out.println("somme : " + duree);
									continue label;
								}
							}
						}
					}

					if (duree != 0) {
						System.out.println("duree totale : " + duree);
						String featureIdentifier = sf.getIdentifier().getID();
						String idPlaceString = featureIdentifier.replaceAll("[a-z]", "").replace(".", "");
						int idPlace = Integer.parseInt(idPlaceString);
						System.out.println("idPlace : " + idPlace);

						LavageBrossage brossage = new LavageBrossage();
						brossage.setDateJour(dateAvant);
						//brossage.setIdVehicule(idVeh);
						Vehicule v = vehiculeMetier.findVehicule(idVeh);
						String nomV=v.getNom();
						VehiculeDestination vehDest=vehiculeDestinationRepository.getVehiculeByNom(nomV);
						brossage.setVehicule(vehDest);
						brossage.setDuree(duree);
						Place place=placeRepository.getOne(idPlace);
						brossage.setPlace(place);
						LavageBrossage obj = lavageBrossageRepository.save(brossage);
						System.out.println("id lavageBrossage : " + obj.getId());
						//System.out.println("id Vehicule : " + obj.getIdVehicule());
						//System.out.println("Nom Vehicule : " + obj.getNomVehicule());
						System.out.println("Date jours : " + obj.getDateJour());
						System.out.println("Duree : " + obj.getDuree());
					} else
						System.out.println("duree vide");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while getting intersection", e);
		}
	}

	/**
	 * pour extraire l'historiques les vihcules
	 * 
	 * @param allHistoriques
	 * @param idVeh
	 * @param historiquesOfThisVehicule
	 */
	private void getHistoriqueV(List<Historique> allHistoriques, Integer idVeh,
			List<Historique> historiquesOfThisVehicule) {
		for (Historique historique : allHistoriques) {
			if (idVeh == historique.getVehicule().getId()) {
				historiquesOfThisVehicule.add(historique);
				System.out.println(
						"Historique de ce vehicule : " + historique.getLatitude() + "," + historique.getLongitude());
			}
		}
	}
}