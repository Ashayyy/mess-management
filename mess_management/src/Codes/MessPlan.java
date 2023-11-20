package Codes;

public enum MessPlan {
	
   MONTHLY(3000), QUARTERLY(11700), HALFYEARLY(17500), YEARLY(32000);
	
	double price;
	
	MessPlan(double price){
		this.price=price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
