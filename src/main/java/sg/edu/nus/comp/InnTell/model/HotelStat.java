package sg.edu.nus.comp.InnTell.model;

public class HotelStat {

	double aorAvg;

	double arrAvg;

	double aorPred;

	double arrLow = 0.0;

	double arrHigh = 0.0;

	private double elasticity = 2.0;

	public double getAorAvg() {
		return aorAvg;
	}

	public void setAorAvg(double aorAvg) {
		this.aorAvg = aorAvg;
	}

	public double getArrAvg() {
		return arrAvg;
	}

	public void setArrAvg(double arrAvg) {
		this.arrAvg = arrAvg;
	}

	public double getAorPred() {
		return aorPred;
	}

	public void setAorPred(double aorPred) {
		this.aorPred = aorPred;
	}

	public double getArrLow() {
		return arrLow;
	}

	public void setArrLow(double arrLow) {
		this.arrLow = arrLow;
	}

	public void calculateArrLow() {
		this.arrLow = Math.pow((Math.pow(this.arrAvg, this.elasticity) * this.aorPred) / this.aorAvg,
				1 / this.elasticity);
	}

	public double getArrHigh() {
		return arrHigh;
	}

	public void setArrHigh(double arrHigh) {
		this.arrHigh = arrHigh;
		if(this.arrHigh < this.arrLow) {
			double temp = this.arrHigh;
			this.arrHigh = this.arrLow;
			this.arrLow = temp;
		}
	}

	public double getElasticity() {
		return elasticity;
	}

	public void setElasticity(double elasticity) {
		this.elasticity = elasticity;
	}

}