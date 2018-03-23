package sg.edu.nus.comp.InnTell.controller;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.comp.InnTell.constants.Constants;
import sg.edu.nus.comp.InnTell.db.DataAccess;
import sg.edu.nus.comp.InnTell.model.HotelRank;
import sg.edu.nus.comp.InnTell.model.Recommendation;

public class RecommendationsEngine {

	@Autowired
	DataAccess db;

	public double getScoreForArrivals(int monthArrivalRank, HotelRank hotelRank) {
		return (Double) null;
	}

	public Recommendation getRecommendations(int month, String tier) {

		Recommendation recommendation = new Recommendation();
		double score = 0.0;

		int monthArrivalRank = db.getMonthArrivalRank(month);
		HotelRank hotelRank = db.getHotelRank(month, tier);
		score += getScoreForArrivals(monthArrivalRank, hotelRank);

		if (score < 0) {
			recommendation.setIncrease(false);
		}

		else {
			recommendation.setIncrease(true);
		}

		return recommendation;
	}

	public static void main(String args[]) {
		RecommendationsEngine r = new RecommendationsEngine();
		for (int i = 1; i <= 12; i++) {
			System.out.println(i);
		}
	}

}
