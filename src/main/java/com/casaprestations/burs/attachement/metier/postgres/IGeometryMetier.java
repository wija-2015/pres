package com.casaprestations.burs.attachement.metier.postgres;

import java.util.HashSet;
import java.util.List;

import com.vividsolutions.jts.geom.Point;

public interface IGeometryMetier {
	
	public List<Point> getIntersectedFeature(List<Point> points);

	public void getIntersectedForInsertingLavageBrossage();

}
