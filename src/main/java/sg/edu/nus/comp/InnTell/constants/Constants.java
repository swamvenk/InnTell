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
	
	static public class DB2Queries {
		
		public static final String topVisitors = "SELECT COUNTRY,SUM(TOTAL) AS TOTAL "
				+ "from VISITORS_NATIONALITY_MONTHLY WHERE MONTH = 5 AND COUNTRY!= 'ASEAN' "
				+ "GROUP BY COUNTRY ORDER BY TOTAL DESC LIMIT 5";
		
		public static final String topVisitorsNoHotel = "SELECT C1 AS COUNTRY, ROUND(TOTAL) AS TOTAL, HOTEL, ROUND(((100-HOTEL)/100)* TOTAL) AS UNBOOKED "
				+ "FROM (SELECT COUNTRY AS C1, AVG(TOTAL) AS TOTAL "
				+ "from VISITORS_NATIONALITY_MONTHLY WHERE MONTH = 5 AND COUNTRY!= 'ASEAN' "
				+ "GROUP BY COUNTRY) AS T1, (SELECT COUNTRY AS C2, AVG(HOTEL) AS HOTEL "
				+ "FROM VISITORS_ACCOMODATION_YEARLY WHERE COUNTRY!= 'TOTAL' "
				+ "GROUP BY COUNTRY) AS T2 WHERE T1.C1 = T2.C2 "
				+ "ORDER BY UNBOOKED DESC LIMIT 5";
		
		public static final String topVisitorsPurposeOfVisit = "SELECT *, 100-LEISURE-HOLIDAY-SOCIAL_VISIT-BUSINESS AS OTHER "
				+ "FROM (SELECT ROUND(AVG((LEISURE*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),2) AS LEISURE, "
				+ "ROUND(AVG((HOLIDAY*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),2) AS HOLIDAY, "
				+ "ROUND(AVG((VFR*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),2) AS SOCIAL_VISIT, "
				+ "ROUND(AVG(((BUSINESS+GENERAL_BUSINESS+MICE)*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),2) AS BUSINESS "
				+ "FROM VISITORS_PURPOSE_OF_VISIT_YEARLY WHERE COUNTRY IN "
				+ "(SELECT COUNTRY FROM VISITORS_NATIONALITY_MONTHLY WHERE MONTH = 5 AND COUNTRY!= 'ASEAN' "
				+ "GROUP BY COUNTRY ORDER BY AVG(TOTAL) DESC LIMIT 5))";
		
		public static final String ageGroupDistribution = "SELECT YEAR, AGE_GROUP, SUM(TOTAL) AS TOTAL "
				+ "FROM VISITORS_AGE_GROUP_MONTHLY WHERE MONTH = 3 AND AGE_GROUP != 'Not Stated' GROUP BY YEAR,AGE_GROUP ORDER BY YEAR, AGE_GROUP";
		
		public static final String hotelOcupancyRoomRate = "SELECT TIER, ROUND(AVG(AOR),2) AS AOR, ROUND(AVG(ARR),2) AS ARR "
				+ "FROM HOTELS_TIER_MONTHLY WHERE MONTH = 3 GROUP BY MONTH,TIER";
		
		
	}

}
