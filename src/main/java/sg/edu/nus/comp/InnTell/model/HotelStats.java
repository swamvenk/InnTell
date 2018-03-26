package sg.edu.nus.comp.InnTell.model;

public class HotelStats {

	double aorAvg;

	double arrAvg;

	double aorPred;

	double arrPred;

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

	public double getArrPred() {
		return arrPred;
	}

	public void setArrPred(double arrPred) {
		this.arrPred = arrPred;
	}

	public void calculateArrPred() {
		this.arrPred = Math.pow((Math.pow(this.arrAvg, this.elasticity) * this.aorPred) / this.aorAvg,
				1 / this.elasticity);
	}

}