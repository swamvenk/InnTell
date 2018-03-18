package sg.edu.nus.comp.InnTell;

import java.sql.*;

import com.ibm.db2.jcc.DB2SimpleDataSource;

public class DataConnection {

	String url;
	String user;
	String password;
	Connection con;
	Statement stmt;
	ResultSet rs;
	String cor = "";

	public DataConnection() {
		url = "jdbc:db2://dash5856:Dz_n37VNzC_f@dashdb-entry-yp-dal09-10.services.dal.bluemix.net:50000/BLUDB";
		user = "dash5856";
		password = "Dz_n37VNzC_f";
		try {
			// Load the driver
			Class.forName("com.ibm.db2.jcc.DB2Driver");

			DB2SimpleDataSource dataSource = new DB2SimpleDataSource();

			dataSource.setServerName("dashdb-entry-yp-dal09-10.services.dal.bluemix.net");
			dataSource.setPortNumber(50000);
			dataSource.setDatabaseName("BLUDB");
			dataSource.setUser("dash5856");
			dataSource.setPassword("Dz_n37VNzC_f");
			dataSource.setDriverType(4);
			con = dataSource.getConnection();
			con.setAutoCommit(false);

			// Create the connection using the IBM Data Server Driver for JDBC and SQLJ
			// con = DriverManager.getConnection(url, user, password);
			// Commit changes manually
			// con.setAutoCommit(false);

			// Create the Statement
			stmt = con.createStatement();

			// Execute a query and generate a ResultSet instance
			rs = stmt.executeQuery("SELECT * FROM DASH5856.VISITORS_PURPOSE_OF_VISIT_YEARLY");

			// Print all of the employee numbers to standard output device
			while (rs.next()) {
				cor = rs.getString(3);
			}
			// Close the ResultSet
			rs.close();

			// Close the Statement
			stmt.close();

			// Connection must be on a unit-of-work boundary to allow close
			con.commit();

			// Close the connection
			con.close();
		}

		catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			System.out.println("Exception: " + e);
			e.printStackTrace();
		}

		catch (SQLException ex) {
			System.err.println("SQLException information");
			while (ex != null) {
				System.err.println("Error msg: " + ex.getMessage());
				System.err.println("SQLSTATE: " + ex.getSQLState());
				System.err.println("Error code: " + ex.getErrorCode());
				ex.printStackTrace();
				ex = ex.getNextException(); // For drivers that support chained exceptions
			}
		}

	}
	
	public String getDBResponse() {
		return cor; 
	}
}