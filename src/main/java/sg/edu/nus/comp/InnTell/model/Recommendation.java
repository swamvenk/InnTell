package sg.edu.nus.comp.InnTell.model;

import java.util.ArrayList;
import java.util.List;

public class Recommendation {

	private boolean increase;

	private double minimum;

	private double maximum;

	private List<String> foodPreferences = new ArrayList<String>();

	public Recommendation() {
		this.increase = true;
		this.minimum = 4.5;
		this.maximum = 5.2;
		this.foodPreferences.add("Chinese");
		this.foodPreferences.add("Malay");
		this.foodPreferences.add("Indian");
		this.foodPreferences.add("Japanese");

	}

	public boolean isIncrease() {
		return increase;
	}

	public void setIncrease(boolean increase) {
		this.increase = increase;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public List<String> getFoodPreferences() {
		return foodPreferences;
	}

	public void setFoodPreferences(List<String> foodPreferences) {
		this.foodPreferences = foodPreferences;
	}

}
