package sg.edu.nus.comp.InnTell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.db.DataAccess;
import sg.edu.nus.comp.InnTell.model.HotelStats;
import sg.edu.nus.comp.InnTell.model.Recommendation;

@Component
public class RecommendationsEngine {

	@Autowired
	DataAccess db;

	public double getScoreForArrivals(int monthArrivalRank, HotelStats hotelRank) {
		return 0.0;
	}

	public Recommendation getRecommendations(int month, String tier, double price) {

		Recommendation recommendation = new Recommendation();

		HotelStats hotelStat = db.getHotelStats(month, tier);
		double percentageChange = ((hotelStat.getArrPred() - price)/price)*100;
		
		if(percentageChange < 0) {
			recommendation.setIncrease(false);
			percentageChange = - percentageChange;
		}
		else {
			recommendation.setIncrease(true);
		}
		
		recommendation.setMinimum(percentageChange-0.5);
		recommendation.setMaximum(percentageChange+0.4);
		
		//score += getScoreForArrivals(monthArrivalRank, hotelRank);

		/*if (score < 0) {
			recommendation.setIncrease(false);
		}

		else {
			recommendation.setIncrease(true);
		}*/

		return recommendation;
	}

}
