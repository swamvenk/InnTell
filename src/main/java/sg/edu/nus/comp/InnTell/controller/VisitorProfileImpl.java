package sg.edu.nus.comp.InnTell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.db.DB2DataBaseConnection;

@Component
public class VisitorProfileImpl {
	
	public VisitorProfileImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	DB2DataBaseConnection db;
	
	public List<String> getTopVisitorsByMonth(String month) {
		return db.getTopVisitors(month);
	}
}
