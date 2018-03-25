package sg.edu.nus.comp.InnTell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.db.DataAccess;
import sg.edu.nus.comp.InnTell.model.InnTellModel;
import sg.edu.nus.comp.InnTell.model.Recommendation;

@Component
public class VisitorProfileImpl {
	
	@Autowired
	DataAccess db;
	
	public List<InnTellModel.TopVisitors> getTopVisitorsByMonth(int month) {
		return db.getTopVisitors(month);
	}
	
	public List<InnTellModel.TopVisitorsNoHotel> getTopVisitorsByMonthWithoutHotel(int month) {
		return db.getTopVisitorsNoHotel(month);
	}
	
	public List<InnTellModel.TopVisitorsPurposeOfVisit> getTopVisitorsByMonthPurposeOfVisit(int month) {
		return db.getTopVisitorsPurposeOfVisit(month);
	}
	
	public List<InnTellModel.VisitorsAgeGroup> getVisitorsByMonthAgeGroup(int month) {
		return db.getVisitorsAgeGroup(month);
	}
	
	public List<InnTellModel.HotelTiers> getHotelTierOccupancyRoomRate(int month) {
		return db.getHotelTierOccupancyRoomRate(month);
	}
	
	public InnTellModel getAllInnTellData(int month) {
		InnTellModel result = new InnTellModel();
		result.setTopVisitors(db.getTopVisitors(month));
		result.setTopVisitorsNoHotel(db.getTopVisitorsNoHotel(month));
		result.setPurposeOfVisit(db.getTopVisitorsPurposeOfVisit(month));
		result.setVisitorsAgeGroup(db.getVisitorsAgeGroup(month));
		result.setHotelTiers(db.getHotelTierOccupancyRoomRate(month));
		return result;
	}
}
