package sg.edu.nus.comp.InnTell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.db.DataAccess;
import sg.edu.nus.comp.InnTell.model.HotelStat;
import sg.edu.nus.comp.InnTell.model.Recommendation;

@Component
public class RecommendationsEngine {

	@Autowired
	DataAccess db;

	public Recommendation getRecommendations(int month, String tier, double price) {

		Recommendation recommendation = new Recommendation();

		HotelStat hotelStat = db.getHotelStats(month, tier);		
		updatePredictedPrice(hotelStat);
		
		double percentageChange = Math.round((((hotelStat.getArrPred() - price) / price) * 100) * 100.0) / 100.0;

		if (percentageChange < 0) {
			recommendation.setIncrease(false);
			percentageChange = -percentageChange;
		} else {
			recommendation.setIncrease(true);
		}

		recommendation.setMinimum(percentageChange - 0.5);
		recommendation.setMaximum(percentageChange + 0.4);		
		recommendation.setFoodPreferences(getFoodPreferences());

		return recommendation;
	}

	private List<String> getFoodPreferences() {
		return null;
	}

	private void updatePredictedPrice(HotelStat hotelStat) {
		
	}

}
