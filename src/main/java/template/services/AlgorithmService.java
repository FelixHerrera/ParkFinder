package template.services;

import org.springframework.stereotype.Service;

import template.algorithm.Ranking;

@Service
public class AlgorithmService {

	public Ranking getRanking(double latitude, double longitude) {
		return new Ranking(latitude, longitude);
	}
	
}
