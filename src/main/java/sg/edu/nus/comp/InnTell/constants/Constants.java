package sg.edu.nus.comp.InnTell.constants;

public class Constants {

	static public class DB2Params {

		/*
		 * Username for the DB2 database connection
		 */
		public static final String dbUsername = "dash5856";

		/*
		 * Password for the DB2 database connection
		 */
		public static final String dbPassword = "Dz_n37VNzC_f";

		/*
		 * The DB2 database host name
		 */
		public static final String dbHost = "dashdb-entry-yp-dal09-10.services.dal.bluemix.net";

		/*
		 * The DB2 database port number
		 */
		public static final int dbPort = 50000;

		/*
		 * The DB2 database name
		 */
		public static final String dbName = "BLUDB";

		/*
		 * The DB2 database connection url
		 */
		public static final String dbUrl = "jdbc:db2://" + dbHost + ":" + dbPort + "/" + dbName;

		/*
		 * The DB2 driver class to load
		 */
		public static final String dbDriver = "com.ibm.db2.jcc.DB2Driver";

	}

}
