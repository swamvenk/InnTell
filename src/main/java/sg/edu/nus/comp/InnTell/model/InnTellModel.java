package sg.edu.nus.comp.InnTell.model;

import java.util.List;

public class InnTellModel {

	static public class TopVisitors {

		public String country;
		public int count;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

	}

	static public class TopVisitorsNoHotel {

		public String country;
		public int total;
		public int count;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}

	static public class TopVisitorsPurposeOfVisit {

		public String category;
		public int count;

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
	}

	static public class VisitorsAgeGroup {

		public String ageGroup;
		public int year;
		public int total;

		public String getAgeGroup() {
			return ageGroup;
		}

		public void setAgeGroup(String ageGroup) {
			this.ageGroup = ageGroup;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

	}

	static public class HotelTiers {

		public String tier;
		public float aor;
		public float arr;

		public String getTier() {
			return tier;
		}

		public void setTier(String tier) {
			this.tier = tier;
		}

		public float getAor() {
			return aor;
		}

		public void setAor(float aor) {
			this.aor = aor;
		}

		public float getArr() {
			return arr;
		}

		public void setArr(float arr) {
			this.arr = arr;
		}

	}

	public List<TopVisitors> topVisitors;
	public List<TopVisitorsNoHotel> topVisitorsNoHotel;
	public List<TopVisitorsPurposeOfVisit> purposeOfVisit;
	public List<VisitorsAgeGroup> visitorsAgeGroup;
	public List<HotelTiers> hotelTiers;

	public List<TopVisitors> getTopVisitors() {
		return topVisitors;
	}

	public void setTopVisitors(List<TopVisitors> topVisitors) {
		this.topVisitors = topVisitors;
	}

	public List<TopVisitorsNoHotel> getTopVisitorsNoHotel() {
		return topVisitorsNoHotel;
	}

	public void setTopVisitorsNoHotel(List<TopVisitorsNoHotel> topVisitorsNoHotel) {
		this.topVisitorsNoHotel = topVisitorsNoHotel;
	}

	public List<TopVisitorsPurposeOfVisit> getPurposeOfVisit() {
		return purposeOfVisit;
	}

	public void setPurposeOfVisit(List<TopVisitorsPurposeOfVisit> purposeOfVisit) {
		this.purposeOfVisit = purposeOfVisit;
	}

	public List<VisitorsAgeGroup> getVisitorsAgeGroup() {
		return visitorsAgeGroup;
	}

	public void setVisitorsAgeGroup(List<VisitorsAgeGroup> visitorsAgeGroup) {
		this.visitorsAgeGroup = visitorsAgeGroup;
	}

	public List<HotelTiers> getHotelTiers() {
		return hotelTiers;
	}

	public void setHotelTiers(List<HotelTiers> hotelTiers) {
		this.hotelTiers = hotelTiers;
	}

}
