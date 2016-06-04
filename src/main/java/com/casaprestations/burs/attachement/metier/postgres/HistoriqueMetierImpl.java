package com.casaprestations.burs.attachement.metier.postgres;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.postgres.Historique;
import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.casaprestations.burs.attachement.repository.postgres.HistoriqueRepository;
import com.casaprestations.burs.attachement.repository.postgres.VehiculeRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

@Service
public class HistoriqueMetierImpl implements IHistoriqueMetier {

	@Autowired
	private HistoriqueRepository historiqueRepository;
	@Autowired
	private VehiculeRepository vehiculeRepository;

	// La liste des idVehicules du balayage mecanise d'Averda qui ont travaillé
	// durant un jour x
	@Override
	public List<Integer> findVehiculesOfBalayageAverdaByDate(Date date) {
		return historiqueRepository.findVehiculesOfBalayageAverdaByDate(date);
	}

	// La liste des Historiques du balayage mecanise d'Averda durant un jour x
	// par la vehicule idVehicule
	@Override
	public List<Historique> findBalayagesAverdaByDateAndVehicule(Date date, Integer idVehicule) {
		return historiqueRepository.findBalayagesAverdaByDateAndVehicule(date, idVehicule);
	}

	// La liste des idVehicules du Lavage mecanise d'Averda qui ont travaillé
	// durant un jour x
	@Override
	public List<Integer> findVehiculesOfLavageAverdaByDate(Date date) {
		// TODO Auto-generated method stub
		return historiqueRepository.findVehiculesOfLavageAverdaByDate(date);
	}

	// La liste des Historiques du Lavage mecanise d'Averda durant un jour x
	// par la vehicule idVehicule
	@Override
	public List<Historique> findLavagesAverdaByDateAndVehicule(Date date, Integer idVehicule) {
		// TODO Auto-generated method stub
		return historiqueRepository.findLavagesAverdaByDateAndVehicule(date, idVehicule);
	}

	// La liste des idVehicules du balayage mecanise de Sita qui ont travaillé
	// durant un jour x
	@Override
	public List<Integer> findVehiculesOfBalayageSitaByDate(Date date) {
		// TODO Auto-generated method stub
		return historiqueRepository.findVehiculesOfBalayageSitaByDate(date);
	}

	// La liste des Historiques du balayage mecanise de Sita durant un jour x
	// par la vehicule idVehicule
	@Override
	public List<Historique> findBalayagesSitaByDateAndVehicule(Date date, Integer idVehicule) {
		// TODO Auto-generated method stub
		return historiqueRepository.findBalayagesSitaByDateAndVehicule(date, idVehicule);
	}

	// La liste des idVehicules du Lavage mecanise de Sita qui ont travaillé
	// durant un jour x
	@Override
	public List<Integer> findVehiculesOfLavageSitaByDate(Date date) {
		// TODO Auto-generated method stub
		return historiqueRepository.findVehiculesOfLavageSitaByDate(date);
	}

	// La liste des Historiques du Lavage mecanise de Sita durant un jour x
	// par la vehicule idVehicule
	@Override
	public List<Historique> findLavagesSitaByDateAndVehicule(Date date, Integer idVehicule) {
		// TODO Auto-generated method stub
		return historiqueRepository.findLavagesSitaByDateAndVehicule(date, idVehicule);
	}

	@Override
	public List<Historique> findHistoriquesByDateAndVehicule(String dateString, Integer idVehicule) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return historiqueRepository.findHistoriquesByDateAndVehicule(date, idVehicule);
	}

	@Override
	public Point createPointfromXY(Double x, Double y) {
		if (x != null && y != null) {
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate source = new Coordinate(x, y);
			try {
				// wgs 84
				CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
				// projection nord maroc merchich - lambert
				CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:26191");
				MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
				Coordinate targetCoordinate = JTS.transform(source, null, transform);
				Point point = geometryFactory.createPoint(targetCoordinate);
				return point;
			} catch (FactoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Point createPointfromXY_43(Double x, Double y) {
		if (x != null && y != null) {
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			Coordinate source = new Coordinate(x, y);
			Point point = geometryFactory.createPoint(source);
			return point;
		}
		return null;
	}

	@Override
	public List<Point> createPointsFromHistory(List<Historique> historiques) {
		List<Point> result = new ArrayList<>();
		for (Historique historique : historiques) {
			Point p = createPointfromXY(historique.getLongitude(), historique.getLatitude());
			if (p != null)
				result.add(p);
		}
		return result;
	}

	@Override
	public Double getDistanceFromPoints(List<Point> points) {
		try {
			GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
			List<Coordinate> coordinates = new ArrayList<>();
			for (Point point : points) {
				Coordinate coordinate = new Coordinate(point.getX(), point.getY());
				coordinates.add(coordinate);
			}
			Coordinate[] tabl = coordinates.toArray(new Coordinate[points.size()]);
			LineString line = geometryFactory.createLineString(tabl);
			// LineString targetGeometry = (LineString) JTS.transform( line,
			// transform);
			double distance = line.getLength();
			return distance;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	Geometry placeBuffer(Geometry geo, double d) {
		return geo.buffer(d);
	}

	/*
	 * public Boolean getIntersectedFeature(Geometry geom, List<Integer>
	 * idVehicules) { Geometry g= placeBuffer(geom, 10); List<Integer>
	 * idVehiules= historiqueRepository; SimpleFeatureSource fs; try { fs =
	 * postGisDataStore.getFeatureSource(lav_brossage_places); String
	 * geometryColumnName =
	 * fs.getSchema().getGeometryDescriptor().getLocalName(); Filter
	 * pointInPolygon = CQL.toFilter("CONTAINS(" + geometryColumnName +
	 * ", POINT(" + x + " " + y + "))"); SimpleFeatureCollection fc =
	 * fs.getFeatures(pointInPolygon); SimpleFeatureIterator iter =
	 * fc.features();
	 * 
	 * if (iter.hasNext()) { iter.close(); return true; } else { iter.close();
	 * return false; }
	 * 
	 * } catch (CQLException e) { e.printStackTrace(); } }
	 */

	@Override
	public Vehicule findVehicule(Integer id) {
		// TODO Auto-generated method stub
		return vehiculeRepository.findOne(id);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return historiqueRepository.count();
	}

	@Override
	public List<Integer> findVehiculesOfBrossageLavageByDate(Date dateAvant) {
		// TODO Auto-generated method stub
		return historiqueRepository.findVehiculesOfBrossageLavageByDate(dateAvant);
	}

	@Override
	public List<Historique> findBrossagesLavageByDateAndVehicule(Date date, Integer idVehicule) {
		// TODO Auto-generated method stub
		return historiqueRepository.findBrossagesByDateAndVehicule(date, idVehicule);
	}

	@Override
	public List<Historique> findBrossagesByDate(Date date) {
		// TODO Auto-generated method stub
		return historiqueRepository.findBrossagesByDate(date);
	}

}
