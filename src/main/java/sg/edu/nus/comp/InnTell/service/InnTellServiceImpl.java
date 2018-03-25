package sg.edu.nus.comp.InnTell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.comp.InnTell.controller.RecommendationsEngine;
import sg.edu.nus.comp.InnTell.controller.VisitorProfileImpl;
import sg.edu.nus.comp.InnTell.model.InnTellModel;
import sg.edu.nus.comp.InnTell.model.Recommendation;

/**
 * This class is the enrty point for all the rest APIs that InnTell has to offer.
 * 
 * @author Swaminathan
 *
 */
@RestController
public class InnTellServiceImpl {
	
	
	@Autowired
	VisitorProfileImpl visitorProfileImpl;
	@Autowired
	RecommendationsEngine recommendationsEngine;
	
	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/month/{month}/visitors")
	public List<InnTellModel.TopVisitors> topVisitorsbyMonth(@PathVariable(value="month") int month) {
		return visitorProfileImpl.getTopVisitorsByMonth(month);
	}
	
	@RequestMapping("/month/{month}/visitors/nohotel")
	public List<InnTellModel.TopVisitorsNoHotel> topVisitorsbyMonthWithoutHotel(@PathVariable(value="month") int month) {
		return visitorProfileImpl.getTopVisitorsByMonthWithoutHotel(month);
	}
	
	@RequestMapping("/month/{month}/visitors/purpose-of-visit")
	public List<InnTellModel.TopVisitorsPurposeOfVisit> topVisitorsbyMonthPurposeOfVisit(@PathVariable(value="month") int month) {
		return visitorProfileImpl.getTopVisitorsByMonthPurposeOfVisit(month);
	}
	
	@RequestMapping("/month/{month}/visitors/age-group")
	public List<InnTellModel.VisitorsAgeGroup> visitorsMonthAgeGroup(@PathVariable(value="month") int month) {
		return visitorProfileImpl.getVisitorsByMonthAgeGroup(month);
	}
	
	@RequestMapping("/month/{month}/hotels/tiers")
	public List<InnTellModel.HotelTiers> hotelOccupancyRoomRate(@PathVariable(value="month") int month) {
		return visitorProfileImpl.getHotelTierOccupancyRoomRate(month);
	}
	
	@RequestMapping("/month/{month}")
	public InnTellModel allInnTellData(@PathVariable(value="month") int month) {
		return visitorProfileImpl.getAllInnTellData(month);
	}
	
	@RequestMapping("/month/{month}/hotel/{hotel}/recommendation")
	public Recommendation hotelRecommendations(@PathVariable(value="month") int month, @PathVariable(value="hotel") String tier) {
		return recommendationsEngine.getRecommendations(month, tier);
	}
}
