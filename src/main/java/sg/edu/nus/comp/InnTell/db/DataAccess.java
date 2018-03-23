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
import sg.edu.nus.comp.InnTell.model.HotelRank;
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

	public int getMonthArrivalRank(int month) {
		int monthArrivalRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthArrivalRank,month));
			rs.first();
			monthArrivalRank = Integer.parseInt(rs.getString(1));
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
		return monthArrivalRank;
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
		InnTellModel.VisitorsAgeGroup visitor1 = new InnTellModel.VisitorsAgeGroup();
		InnTellModel.VisitorsAgeGroup visitor2 = new InnTellModel.VisitorsAgeGroup();
		InnTellModel.VisitorsAgeGroup visitor3 = new InnTellModel.VisitorsAgeGroup();
		visitor1.setYear(2013);
		visitor2.setYear(2014);
		visitor3.setYear(2015);

		try {
			Statement stmt = connection.createStatement();
			String query = String.format(Constants.DB2Queries.ageGroupDistribution, month);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next() != false) {
				if(rs.getInt(1) == 2013) {
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup1)) {
						visitor1.setTotal1(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup2)) {
						visitor1.setTotal2(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup3)) {
						visitor1.setTotal3(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup4)) {
						visitor1.setTotal4(rs.getInt(3));
					}
				}
				if(rs.getInt(1) == 2014) {
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup1)) {
						visitor2.setTotal1(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup2)) {
						visitor2.setTotal2(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup3)) {
						visitor2.setTotal3(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup4)) {
						visitor2.setTotal4(rs.getInt(3));
					}
				}
				if(rs.getInt(1) == 2013) {
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup1)) {
						visitor3.setTotal1(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup2)) {
						visitor3.setTotal2(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup3)) {
						visitor3.setTotal3(rs.getInt(3));
					}
					if(rs.getString(2).equalsIgnoreCase(Constants.ApplicationConstants.ageGroup4)) {
						visitor3.setTotal4(rs.getInt(3));
					}
				}
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
		result.add(visitor1);
		result.add(visitor2);
		result.add(visitor3);
		return result;

	}
	
	public HotelRank getHotelRank(int month, String tier) {
		HotelRank hotelRank = new HotelRank();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.hotelRank,tier,month));
			rs.first();
			hotelRank.setAorRank(Integer.parseInt(rs.getString(1)));
			hotelRank.setArrRank(Integer.parseInt(rs.getString(2)));
			hotelRank.setRevRank(Integer.parseInt(rs.getString(3)));
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
		return hotelRank;

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
