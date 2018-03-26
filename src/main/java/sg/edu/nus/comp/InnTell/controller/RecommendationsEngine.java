package sg.edu.nus.comp.InnTell.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.db.DataAccess;
import sg.edu.nus.comp.InnTell.model.HotelStat;
import sg.edu.nus.comp.InnTell.model.InnTellModel.TopVisitors;
import sg.edu.nus.comp.InnTell.model.Recommendation;

@Component
public class RecommendationsEngine {

	@Autowired
	DataAccess db;

	public Recommendation getRecommendations(int month, String tier, double price) {

		Recommendation recommendation = new Recommendation();

		HotelStat hotelStat = db.getHotelStats(month, tier);
		updatePredictedPrice(hotelStat, month);

		double percentageChange = BigDecimal.valueOf((((hotelStat.getArrPred() - price) / price) * 100))
				.setScale(2, RoundingMode.HALF_UP).doubleValue();

		if (percentageChange < 0) {
			recommendation.setIncrease(false);
			percentageChange = -percentageChange;
		} else {
			recommendation.setIncrease(true);
		}

		recommendation.setMinimum(percentageChange - 0.5);
		recommendation.setMaximum(percentageChange + 0.4);
		recommendation.setFoodPreferences(getFoodPreferences(month));

		return recommendation;
	}

	private List<String> getFoodPreferences(int month) {

		Map<String, String> food = new HashMap<String, String>();
		food.put("INDIA", "Indian");
		food.put("JAPAN", "Japanese");
		food.put("CHINA", "Chinese");
		food.put("INDONESIA", "Indonesian");
		food.put("AUSTRALIA", "Australian");
		food.put("UNITED KINGDOM", "English");
		food.put("MALAYSIA", "Malay");

		List<TopVisitors> topCountries = db.getTopVisitors(month);
		List<String> foodPreferences = new ArrayList<String>();
		
		for (TopVisitors t : topCountries) {
			if (food.get(t.getCountry()) != null) {
				foodPreferences.add(food.get(t.getCountry()));
			}
		}
		
		foodPreferences.add("Continental");
		return foodPreferences;
	}

	private void updatePredictedPrice(HotelStat hotelStat, int month) {

		double percentChange = 0.0;

		int monthArrivalRank = db.getMonthArrivalRank(month);
		percentChange += (6.5 - monthArrivalRank) * 0.2;

		int monthRevenueRank = db.getMonthRevenueRank(month);
		percentChange += (6.5 - monthRevenueRank) * 0.1;

		int monthAgeGroupRank = db.getMonthAgeGroupRank(month);
		percentChange += (6.5 - monthAgeGroupRank) * 0.2;

		int monthRegionRank = db.getMonthRegionRank(month);
		percentChange += (6.5 - monthRegionRank) * 0.2;

		int monthRainfallRank = db.getMonthRainfallRank(month);
		percentChange += (6.5 - monthRegionRank) * 0.1;

		hotelStat.setArrPred(hotelStat.getArrPred() + (hotelStat.getArrPred() * percentChange));

	}

}
