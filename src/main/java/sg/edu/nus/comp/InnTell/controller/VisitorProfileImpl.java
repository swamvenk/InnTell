package sg.edu.nus.comp.InnTell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.db.DataAccess;
import sg.edu.nus.comp.InnTell.model.InnTellModel;

@Component
public class VisitorProfileImpl {
	
	@Autowired
	DataAccess db;
	
	public List<InnTellModel.TopVisitors> getTopVisitorsByMonth(String month) {
		return db.getTopVisitors(month);
	}
}
