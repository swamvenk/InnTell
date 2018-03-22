package sg.edu.nus.comp.InnTell.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.constants.Constants;
import sg.edu.nus.comp.InnTell.model.InnTellModel;

/**
 * This interface contains all the data access methods necessary for the InnTell
 * application. This can be implemented to use any database connection as the
 * backend.
 * 
 * @author Swaminathan
 *
 */

@Component
public class DataAccess {

	private Connection connection;

	public DataAccess() {
		connection = DB2DataBaseConnection.getConnection();
	}

	public List<InnTellModel.TopVisitors> getTopVisitors(int month) {
		List<InnTellModel.TopVisitors> result = new ArrayList<InnTellModel.TopVisitors>();
		InnTellModel.TopVisitors visitor;
		try {
			Statement stmt = connection.createStatement();
			String query = String.format(Constants.DB2Queries.topVisitors, month);
			ResultSet rs = stmt.executeQuery(query);
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

	public List<InnTellModel.TopVisitorsNoHotel> getTopVisitorsNoHotel(int month) {
		List<InnTellModel.TopVisitorsNoHotel> result = new ArrayList<InnTellModel.TopVisitorsNoHotel>();
		InnTellModel.TopVisitorsNoHotel visitor;
		try {
			Statement stmt = connection.createStatement();
			String query = String.format(Constants.DB2Queries.topVisitorsNoHotel, month);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next() != false) {
				visitor = new InnTellModel.TopVisitorsNoHotel();
				visitor.setCountry(rs.getString(1));
				visitor.setTotal(rs.getInt(2));
				visitor.setCount(rs.getInt(4));
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

	public List<InnTellModel.TopVisitorsPurposeOfVisit> getTopVisitorsPurposeOfVisit(int month) {
		List<InnTellModel.TopVisitorsPurposeOfVisit> result = new ArrayList<InnTellModel.TopVisitorsPurposeOfVisit>();
		InnTellModel.TopVisitorsPurposeOfVisit visitor;
		try {
			Statement stmt = connection.createStatement();
			String query = String.format(Constants.DB2Queries.topVisitorsPurposeOfVisit, month);
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				visitor = new InnTellModel.TopVisitorsPurposeOfVisit();
				visitor.setCategory(rsMetaData.getColumnName(i));
				visitor.setCount(rs.getInt(i));
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

	public List<InnTellModel.VisitorsAgeGroup> getVisitorsAgeGroup(int month) {
		List<InnTellModel.VisitorsAgeGroup> result = new ArrayList<InnTellModel.VisitorsAgeGroup>();
		InnTellModel.VisitorsAgeGroup visitor;
		try {
			Statement stmt = connection.createStatement();
			String query = String.format(Constants.DB2Queries.ageGroupDistribution, month);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next() != false) {
				visitor = new InnTellModel.VisitorsAgeGroup();
				visitor.setYear(rs.getInt(1));
				visitor.setAgeGroup(rs.getString(2));
				visitor.setTotal(rs.getInt(3));
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

	public List<InnTellModel.HotelTiers> getHotelTierOccupancyRoomRate(int month) {
		List<InnTellModel.HotelTiers> result = new ArrayList<InnTellModel.HotelTiers>();
		InnTellModel.HotelTiers visitor;
		try {
			Statement stmt = connection.createStatement();
			String query = String.format(Constants.DB2Queries.hotelOcupancyRoomRate, month);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next() != false) {
				visitor = new InnTellModel.HotelTiers();
				visitor.setTier(rs.getString(1));
				visitor.setAor(rs.getFloat(2));
				visitor.setArr(rs.getFloat(3));
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
