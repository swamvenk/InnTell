package sg.edu.nus.comp.InnTell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.comp.InnTell.controller.VisitorProfileImpl;
import sg.edu.nus.comp.InnTell.model.InnTellModel;

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
	
	@RequestMapping("/month/{month}/visitors")
	public List<InnTellModel.TopVisitors> topVisitorsProfile(@PathVariable(value="month") String month) {
		return visitorProfileImpl.getTopVisitorsByMonth(month);
	}
}
