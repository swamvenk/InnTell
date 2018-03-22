package sg.edu.nus.comp.InnTell.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.constants.Constants;
import sg.edu.nus.comp.InnTell.model.InnTellModel;

/**
 * This interface contains all the data access methods necessary for the InnTell application.
 * This can be implemented to use any database connection as the backend.
 * 
 * @author Swaminathan
 *
 */

@Component
public class DataAccess {
	
	private Connection connection; 
	
	public DataAccess(){
		connection = DB2DataBaseConnection.getConnection();
	}
	public List<InnTellModel.TopVisitors> getTopVisitors(String month) {
		List<InnTellModel.TopVisitors> result = new ArrayList<InnTellModel.TopVisitors>();
		InnTellModel.TopVisitors visitor;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(Constants.DB2Queries.topVisitors);
			while (rs.next() != false) {
				visitor = new InnTellModel.TopVisitors();
				visitor.setCountry(rs.getString(1));
				visitor.setCount(rs.getInt(2));
				result.add(visitor);
			}
			rs.close();
			stmt.close();
			connection.commit();
		} catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				System.err.println("SQLSTATE: " + ex.getSQLState());
				System.err.println("Error code: " + ex.getErrorCode());
				ex.printStackTrace();
				ex = ex.getNextException();
			}
		}
		return result;
	}
}
