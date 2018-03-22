package sg.edu.nus.comp.InnTell.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

import com.ibm.db2.jcc.DB2SimpleDataSource;

import sg.edu.nus.comp.InnTell.constants.Constants;

@Component
public class DB2DataBaseConnection {

	private static Connection connection;

	private DB2DataBaseConnection() {

		try {
			DB2SimpleDataSource dataSource = new DB2SimpleDataSource();
			dataSource.setServerName(Constants.DB2Params.dbHost);
			dataSource.setPortNumber(Constants.DB2Params.dbPort);
			dataSource.setDatabaseName(Constants.DB2Params.dbName);
			dataSource.setUser(Constants.DB2Params.dbUsername);
			dataSource.setPassword(Constants.DB2Params.dbPassword);
			dataSource.setDriverType(4);
			connection = dataSource.getConnection();
			connection.setAutoCommit(false);
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

	}

	public static Connection getConnection() {
		if(connection == null)
			new DB2DataBaseConnection();		
		
		return connection;
	}

	public String getSampleData() {

		String result = null;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DASH5856.VISITORS_PURPOSE_OF_VISIT_YEARLY");
			result = rs.getString(3);
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