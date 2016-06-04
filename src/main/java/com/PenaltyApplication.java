package com;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import com.casaprestations.burs.attachement.itextpdf.IGeneratePdfSitaMetier;
import com.casaprestations.burs.attachement.metier.postgres.IGeometryMetier;
import com.casaprestations.burs.attachement.metier.postgres.IHistoriqueMetier;
import com.casaprestations.burs.attachement.poi.IGenerateExcelSitaMetier;
import com.casaprestations.burs.attachement.shedule.MethodsForShedules;
import com.casaprestations.burs.attachement.shedule.SheduleForAverda;
import com.casaprestations.burs.attachement.shedule.SheduleForSita;
import com.itextpdf.text.DocumentException;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;

@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication
public class PenaltyApplication {

	public static void main(String[] args) throws MalformedURLException, DocumentException, IOException {
		ConfigurableApplicationContext ctx = SpringApplication.run(PenaltyApplication.class, args);
		IHistoriqueMetier bean = ctx.getBean(IHistoriqueMetier.class);
		IGeometryMetier geom = ctx.getBean(IGeometryMetier.class);
		MethodsForShedules methodsTest=ctx.getBean(MethodsForShedules.class);
		SheduleForAverda sheduleForAverda=ctx.getBean(SheduleForAverda.class);
		SheduleForSita sheduleForSita=ctx.getBean(SheduleForSita.class);
		IGenerateExcelSitaMetier poi1=ctx.getBean(IGenerateExcelSitaMetier.class);
		IGeneratePdfSitaMetier pdf=ctx.getBean(IGeneratePdfSitaMetier.class);
		
		GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

		Coordinate[] coordinates = new Coordinate[] { new Coordinate(8, 6), new Coordinate(0, 2),
				new Coordinate(2, 0) };

		LineString line = geometryFactory.createLineString(coordinates);
		double distance = line.getLength();
		System.out.println(distance);

		List<Point> ps = new ArrayList<Point>();
		// intersecte vc place 1
		Point p1 = (Point) bean.createPointfromXY_43(-7.616511246372329, 33.52846864911328);
		ps.add(p1);
		// intersecte vc place 2
		Point p2 = (Point) bean.createPointfromXY_43(-7.617156766346362, 33.53076079557553);
		ps.add(p2);
		// intersecte vc place 3
		Point p3 = (Point) bean.createPointfromXY_43(-7.5954721033095245, 33.558629012229666);
		ps.add(p3);
		// intersecte vc place 4
		Point p4 = (Point) bean.createPointfromXY_43(-7.594273111007768, 33.557186232944986);
		ps.add(p4);
		// intersecte vc place 5
		Point p5 = (Point) bean.createPointfromXY_43(-7.589607310507972, 33.55073282635528);
		ps.add(p5);
		// intersecte vc place
		Point p6 = (Point) bean.createPointfromXY_43(-7.5463638888889, 33.554752777778);
		ps.add(p6);
		// intersecte vc place
		Point p7 = (Point) bean.createPointfromXY_43(-7.5369194444444, 33.480263888889);
		ps.add(p7);
		// intersecte vc place
		Point p8 = (Point) bean.createPointfromXY_43(-7.5380583333333, 33.481191666667);
		ps.add(p8);
		// intersecte vc place
		Point p9 = (Point) bean.createPointfromXY_43(-7.5461972222222, 33.554802777778);
		ps.add(p9);
		// intersecte vc place
		Point p10 = (Point) bean.createPointfromXY_43(-7.546025, 33.554830555556);
		ps.add(p10);
		// intersecte vc place
		Point p11 = (Point) bean.createPointfromXY_43(-7.5460277777778, 33.554808333333);
		ps.add(p11);
		// intersecte vc place
		Point p12 = (Point) bean.createPointfromXY_43(-7.5370166666667, 33.480275);
		ps.add(p12);
		// intersecte vc place
		Point p13 = (Point) bean.createPointfromXY_43(-7.5461555555556, 33.554761111111);
		ps.add(p13);
		// intersecte vc place
		Point p14 = (Point) bean.createPointfromXY_43(-7.5463361111111, 33.554680555556);
		ps.add(p14);
		// intersect vc place 153
		Point p15 = (Point) bean.createPointfromXY_43(-7.609029926637805, 33.58891318969192);
		ps.add(p15);
		Point p16 = (Point) bean.createPointfromXY_43(-7.67814314814810039, 33.57059518518499885);
		ps.add(p16);
		//List<Point> pointsss = geom.getIntersectedFeature(ps);
		//System.out.println(+pointsss.size() + " Points qui intersectent avec table places : " + pointsss.toString());

		//geom.getIntersectedForInsertingLavageBrossage();
		
		System.out.println("date d'hier c'est : "+methodsTest.getDateYesterday()); 
		//sheduleForAverda.scheduleForInsertingBalayageMecaniseAverda();
		//sheduleForSita.scheduleForInsertingBalayageMecaniseSita();
		//sheduleForAverda.scheduleForInsertingLavageMecaniseAverda();
		//sheduleForSita.scheduleForInsertingLavageMecaniseSita();
		//poi1.generateExcelOfBalayagesSita("2015-05-01 00:00:00");
		pdf.generatePdfOfBalayagesSita("2015-05-01 00:00:00");

	}
}