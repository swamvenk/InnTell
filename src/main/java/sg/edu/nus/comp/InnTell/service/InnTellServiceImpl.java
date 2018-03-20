package sg.edu.nus.comp.InnTell.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.comp.InnTell.controller.VisitorProfileImpl;

/**
 * This class is the enrty point for all the rest APIs that InnTell has to offer.
 * 
 * @author Swaminathan
 *
 */
@RestController
public class InnTellServiceImpl {
	
	//ApplicationContext appContext = new ClassPathXmlApplicationContext("/WEB-INF/classes/META-INF/servlet-context.xml"); 
	//VisitorProfileImpl visitorProf = (VisitorProfileImpl)appContext.getBean("visitorProfile");
	@Autowired
	VisitorProfileImpl visitorProfileImpl;
	@RequestMapping("/month/{month}/visitors")
	public List<String> topVisitorsProfile(@PathVariable(value="month") String month) {
		return visitorProfileImpl.getTopVisitorsByMonth(month);
	}
}
