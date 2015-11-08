package template.algorithm;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import template.main.CustomEmbeddedWebApplicationContext;
import template.models.NationalParkLocation;
import template.services.AlgorithmService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../../configuration/applicationContext.xml")
public class RankingTest {

	@Autowired
	AlgorithmService as;
	
	@Test
	public void wacoToNewYorkDistance() {
		NationalParkLocation testPark = new NationalParkLocation("New York", 40.7, -74.0);
		double wacoLat = 31.6;
		double wacoLong = -97.2;
		double distance = Ranking.distance(wacoLat, wacoLong, testPark);
		System.out.println(distance);
		assertTrue(distance > 1000.0);
	}
	
	@Test
	public void wacoToDallasDistance() {
		NationalParkLocation testPark = new NationalParkLocation("Dallas", 32.8, -96.8);
		double wacoLat = 31.6;
		double wacoLong = -97.2;
		double distance = Ranking.distance(wacoLat, wacoLong, testPark);
		System.out.println(distance);
		assertTrue(distance > 80.0);
		assertTrue(distance < 90.0);
	}
	
	@Test
	public void wacoToNatchezDistance() {
		NationalParkLocation testPark = new NationalParkLocation("Natchez", 33.8, -89.2);
		double wacoLat = 31.6;
		double wacoLong = -97.2;
		double distance = Ranking.distance(wacoLat, wacoLong, testPark);
		System.out.println(distance);
		assertTrue(distance > 550.0);
		assertTrue(distance < 600.0);
	}
	
	@Test
	public void wacoToSamoaDistance() {
		NationalParkLocation testPark = new NationalParkLocation("Samoa", -14.2, -170.1);
		double wacoLat = 31.6;
		double wacoLong = -97.2;
		double distance = Ranking.distance(wacoLat, wacoLong, testPark);
		System.out.println(distance);
		assertTrue(distance > 4900.0);
		assertTrue(distance < 5000.0);
	}

}
