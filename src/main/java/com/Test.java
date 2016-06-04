package com;

import org.geotools.referencing.GeodeticCalculator;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			double distance = 0;
			double km;
			
			GeodeticCalculator geodeticCalculator = new GeodeticCalculator();
			geodeticCalculator.setStartingGeographicPoint(33, 7);
			geodeticCalculator.setDestinationGeographicPoint(4, 8);
			
			distance = geodeticCalculator.getOrthodromicDistance();
			double totalmetres = distance;
			km = totalmetres / 1000;
			
			System.out.println(distance);
			System.out.println(totalmetres);
			System.out.println(km);
			
		} catch (Exception e) {
			System.out.println("erroooooooooooooooooor");
		}
	}
}
