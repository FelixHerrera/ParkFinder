package template.algorithm;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import template.googlePlaceConsumer.json.GooglePlace;
import template.main.CustomEmbeddedWebApplicationContext;
import template.models.NationalParkLocation;
import template.services.AlgorithmService;
import template.services.GooglePlacesService;
import template.services.NationalParkLocationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("../../configuration/applicationContext.xml")
public class RankingTest {

	@Autowired
	AlgorithmService as;
	
	@Autowired
	GooglePlacesService gps;
	
	private static final NationalParkLocation newYorkPark = new NationalParkLocation("New York", 40.7, -74.0, "City", "New York");
	private static final NationalParkLocation dallasPark = new NationalParkLocation("Dallas", 32.8, -96.8, "City", "Dallas");
	private static final NationalParkLocation phoenixPark = new NationalParkLocation("Phoenix", 33.5, -112.1, "City", "Phoenix");
	
	private static final double wacoLat = 31.6;
	private static final double wacoLong = -97.2;
	
	@Test
	public void wacoToNewYorkDistance() {
		double distance = newYorkPark.distance(wacoLat, wacoLong);
		assertTrue(distance > 1000.0);
	}
	
	@Test
	public void wacoToDallasDistance() {
		double distance = dallasPark.distance(wacoLat, wacoLong);
		assertTrue(distance > 80.0);
		assertTrue(distance < 90.0);
	}
	
	@Test
	public void rankingTest() {
		Ranking ranking = new Ranking(wacoLat, wacoLong, new NationalParkLocationService() {
			@Override
			public List<NationalParkLocation> getAllParkLocations() {
				List<NationalParkLocation> result = new ArrayList<NationalParkLocation>();
				result.add(newYorkPark);
				result.add(dallasPark);
				result.add(phoenixPark);
				return result;
			}
		}, gps, null, null, null);
		List<NationalParkLocation> rankingList = ranking.getRanking();
		assertEquals(dallasPark, rankingList.get(0));
		assertEquals(phoenixPark, rankingList.get(1));
		assertEquals(newYorkPark, rankingList.get(2));
	}

}
