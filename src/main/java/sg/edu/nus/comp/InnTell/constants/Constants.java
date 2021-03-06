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

		public static final String topVisitors = "SELECT COUNTRY, ROUND(AVG(TOTAL),0) AS TOTAL "
				+ "from VISITORS_NATIONALITY_MONTHLY WHERE MONTH = %d AND COUNTRY!= 'ASEAN' "
				+ "GROUP BY COUNTRY ORDER BY TOTAL DESC LIMIT 5";

		public static final String topVisitorsNoHotel = "SELECT C1 AS COUNTRY, ROUND(TOTAL,0)"
				+ " AS TOTAL, HOTEL, ROUND(((100-HOTEL)/100)* TOTAL,0) AS UNBOOKED "
				+ "FROM (SELECT COUNTRY AS C1, AVG(TOTAL) AS TOTAL "
				+ "from VISITORS_NATIONALITY_MONTHLY WHERE MONTH = %d AND COUNTRY!= 'ASEAN' "
				+ "GROUP BY COUNTRY) AS T1, (SELECT COUNTRY AS C2, AVG(HOTEL) AS HOTEL "
				+ "FROM VISITORS_ACCOMODATION_YEARLY WHERE COUNTRY!= 'TOTAL' "
				+ "GROUP BY COUNTRY) AS T2 WHERE T1.C1 = T2.C2 " + "ORDER BY UNBOOKED"
				+ " DESC LIMIT 5";

		public static final String topVisitorsPurposeOfVisit = "SELECT *, 100-LEISURE-HOLIDAY-SOCIAL_VISIT-BUSINESS AS OTHER "
				+ "FROM (SELECT ROUND(AVG((LEISURE*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),1) AS LEISURE, "
				+ "ROUND(AVG((HOLIDAY*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),1) AS HOLIDAY, "
				+ "ROUND(AVG((VFR*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),1) AS SOCIAL_VISIT, "
				+ "ROUND(AVG(((BUSINESS+GENERAL_BUSINESS+MICE)*100)/(LEISURE+HOLIDAY+VFR+BUSINESS+GENERAL_BUSINESS+MICE+OTHERS+NOT_STATED)),1) AS BUSINESS "
				+ "FROM VISITORS_PURPOSE_OF_VISIT_YEARLY WHERE COUNTRY IN "
				+ "(SELECT COUNTRY FROM VISITORS_NATIONALITY_MONTHLY WHERE MONTH = %d AND COUNTRY!= 'ASEAN' "
				+ "GROUP BY COUNTRY ORDER BY AVG(TOTAL) DESC LIMIT 5))";

		public static final String ageGroupDistribution = "SELECT YEAR, AGE_GROUP, ROUND(AVG(TOTAL),0) AS TOTAL "
				+ "FROM VISITORS_AGE_GROUP_MONTHLY WHERE MONTH = %d AND AGE_GROUP != 'Not Stated' GROUP BY YEAR,AGE_GROUP ORDER BY YEAR, AGE_GROUP";

		public static final String hotelOcupancyRoomRate = "SELECT TIER, ROUND(AVG(AOR),1) AS AOR, ROUND(AVG(REVPAR),1) AS REVPAR, ROUND(AVG(ARR),1) AS ARR "
				+ "FROM HOTELS_TIER_MONTHLY WHERE MONTH = %d GROUP BY TIER";

		public static final String monthArrivalRank = "SELECT RANK FROM(SELECT ROW_NUMBER() OVER"
				+ " (ORDER BY ROUND(AVG(TOTAL),2) DESC) AS RANK, MONTH  FROM "
				+ "VISITORS_ARRIVAL_MONTHLY GROUP BY MONTH) WHERE MONTH = %d";
		
		public static final String hotelStats = "SELECT YEAR, ROUND(AOR,2) AS AOR, "
				+ "ROUND(ARR,2) AS ARR, ROUND(REVPAR,2) AS REVPAR FROM HOTELS_TIER_MONTHLY"
				+ " WHERE TIER = '%s' AND MONTH = %d";		
		
		public static final String monthRevenueRank = "SELECT RANK FROM "
				+ "(SELECT ROW_NUMBER() OVER (ORDER BY ROUND(AVG(REVPAR),2) DESC)"
				+ " AS RANK, MONTH  FROM HOTELS_MONTHLY GROUP BY MONTH) WHERE MONTH = %d";
		
		public static final String monthAgeGroupRank = "SELECT RANK FROM(SELECT"
				+ " ROW_NUMBER() OVER (ORDER BY ROUND(AVG(TOTAL),2) DESC) AS RANK, "
				+ "MONTH  FROM VISITORS_AGE_GROUP_MONTHLY WHERE AGE_GROUP = "
				+ "'Between 36 and 64' GROUP BY MONTH) WHERE MONTH = %d";
		
		public static final String monthRegionRank = "SELECT RANK FROM(SELECT ROW_NUMBER()"
				+ " OVER"
				+ " (ORDER BY ROUND(AVG(TOTAL),2) DESC) AS RANK, MONTH  FROM "
				+ "VISITORS_ARRIVAL_MONTHLY GROUP BY MONTH) WHERE (COUNTRY = 'KUWAIT'"
				+ " OR COUNTRY = 'UNITED ARAB EMIRATES' OR COUNTRY = 'USA' OR COUNTRY"
				+ " = 'QATAR' OR COUNTRY = 'AUSTRALIA' OR COUNTRY = 'BRUNEI' OR COUNTRY"
				+ " = 'SAUDI ARABIA') AND MONTH = %d";	
		
		public static final String monthRainfallRank = "SELECT RANK FROM"
				+ "(SELECT ROW_NUMBER() OVER (ORDER BY ROUND(AVG(RAINFALL),2) ASC)"
				+ " AS RANK, MONTH  FROM CLIMATE_RAINFALL_MONTHLY GROUP BY MONTH) "
				+ "WHERE MONTH = %d";
		}
	
	static public class ApplicationConstants {

		public static final String ageGroup1 = "Below 18";	
		public static final String ageGroup2 = "Between 19 and 35";	
		public static final String ageGroup3 = "Between 36 and 64";	
		public static final String ageGroup4 = "Above 65";	

	}

}
