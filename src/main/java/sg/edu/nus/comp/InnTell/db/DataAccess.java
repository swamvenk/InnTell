package sg.edu.nus.comp.InnTell.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Component;

import sg.edu.nus.comp.InnTell.constants.Constants;
import sg.edu.nus.comp.InnTell.model.HotelStat;
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
		int monthRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthArrivalRank,month));
			while(rs.next()) {
				monthRank = Integer.parseInt(rs.getString(1));
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
		return monthRank;
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
		InnTellModel.VisitorsAgeGroup visitor4 = new InnTellModel.VisitorsAgeGroup();
		InnTellModel.VisitorsAgeGroup visitor5 = new InnTellModel.VisitorsAgeGroup();
		InnTellModel.VisitorsAgeGroup visitor6 = new InnTellModel.VisitorsAgeGroup();

		visitor1.setYear(2013);
		visitor2.setYear(2014);
		visitor3.setYear(2015);
		visitor4.setYear(2016);
		visitor5.setYear(2017);
		visitor6.setYear(2018);
		
		SimpleRegression reg;

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
				if(rs.getInt(1) == 2015) {
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
		
		reg = new SimpleRegression();
		reg.addData(visitor1.getYear(),visitor1.getTotal1());
		reg.addData(visitor2.getYear(),visitor2.getTotal1());
		reg.addData(visitor3.getYear(),visitor3.getTotal1());
		visitor4.setTotal1((int) reg.predict(visitor4.getYear()));
		visitor5.setTotal1((int) reg.predict(visitor5.getYear()));
		visitor6.setTotal1((int) reg.predict(visitor6.getYear()));
		
		reg = new SimpleRegression();
		reg.addData(visitor1.getYear(),visitor1.getTotal2());
		reg.addData(visitor2.getYear(),visitor2.getTotal2());
		reg.addData(visitor3.getYear(),visitor3.getTotal2());
		visitor4.setTotal2((int) reg.predict(visitor4.getYear()));
		visitor5.setTotal2((int) reg.predict(visitor5.getYear()));
		visitor6.setTotal2((int) reg.predict(visitor6.getYear()));

		reg = new SimpleRegression();
		reg.addData(visitor1.getYear(),visitor1.getTotal3());
		reg.addData(visitor2.getYear(),visitor2.getTotal3());
		reg.addData(visitor3.getYear(),visitor3.getTotal3());
		visitor4.setTotal3((int) reg.predict(visitor4.getYear()));
		visitor5.setTotal3((int) reg.predict(visitor5.getYear()));
		visitor6.setTotal3((int) reg.predict(visitor6.getYear()));

		
		reg = new SimpleRegression();
		reg.addData(visitor1.getYear(),visitor1.getTotal4());
		reg.addData(visitor2.getYear(),visitor2.getTotal4());
		reg.addData(visitor3.getYear(),visitor3.getTotal4());
		visitor4.setTotal4((int) reg.predict(visitor4.getYear()));
		visitor5.setTotal4((int) reg.predict(visitor5.getYear()));
		visitor6.setTotal4((int) reg.predict(visitor6.getYear()));


		result.add(visitor1);
		result.add(visitor2);
		result.add(visitor3);
		result.add(visitor4);
		result.add(visitor5);
		result.add(visitor6);

		return result;

	}
	
	public HotelStat getHotelStats(int month, String tier) {
		HotelStat hotelStat = new HotelStat();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.hotelStats,tier,month));
			SimpleRegression reg1 = new SimpleRegression();
			SimpleRegression reg2 = new SimpleRegression();
			SimpleRegression reg3 = new SimpleRegression();
			
			int count = 0;
			double arrSum = 0.0;
			double aorSum = 0.0;
			
			while (rs.next() != false) {
				reg1.addData(rs.getInt(1),rs.getDouble(2));				
				count++;
				aorSum += rs.getDouble(2);
				arrSum += rs.getDouble(3);
				//reg2.addData(rs.getInt(1),rs.getInt(3));
				//reg3.addData(rs.getInt(1),rs.getInt(4));
			}
			hotelStat.setAorPred(reg1.predict(2018));
			hotelStat.setAorAvg(aorSum/count);
			hotelStat.setArrAvg(arrSum/count);
			hotelStat.calculateArrPred();
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
		return hotelStat;
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
				visitor.setRevpar(rs.getFloat(3));
				visitor.setArr(rs.getFloat(4));
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


	public int getMonthBusinessArrivalRank(int month) {
		int monthRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthBusinessArrivalRank,month));
			while(rs.next()) {
				monthRank = Integer.parseInt(rs.getString(1));
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
		return monthRank;
	}


	public int getMonthLeisureArrivalRank(int month) {
		int monthRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthLeisureArrivalRank,month));
			while(rs.next()) {
				monthRank = Integer.parseInt(rs.getString(1));
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
		return monthRank;
	}


	public int getMonthRevenueRank(int month) {
		int monthRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthRevenueRank,month));
			while(rs.next()) {
				monthRank = Integer.parseInt(rs.getString(1));
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
		return monthRank;
	}


	public int getMonthAgeGroupRank(int month) {
		int monthRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthAgeGroupRank,month));
			while(rs.next()) {
				monthRank = Integer.parseInt(rs.getString(1));
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
		return monthRank;
	}


	public int getMonthRegionRank(int month) {
		int monthRank = 0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(Constants.DB2Queries.monthRegionRank,month));
			while(rs.next()) {
				monthRank = Integer.parseInt(rs.getString(1));
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
		return monthRank;
	}
}
