package myproject.model;

import java.awt.Color;

// Terry Schmidt, SE450

public class LightControl {
	
	Color green;
	Color yellow;
	Color red;
	Color currentColor;
	double greenTime;
	double yellowTime;
	double redTime;
	
	LightControl(double gTime, double yTime, double rTime) {
		green = Color.GREEN;
		yellow = Color.YELLOW;
		red = Color.RED;
		currentColor = red;
		greenTime = gTime;
		yellowTime = yTime;
		redTime = rTime;
	}

	public Color getCurrentColor() {
		return currentColor;
	}
	
	public void setCurrentColor(Color c) {
		currentColor = c;
	}
	
	public Color nextColor() {
		if(currentColor == green == true) {
			return yellow;
		} else if (currentColor == yellow == true) {
			return red;
		} else {
			return green;
		}
	}
	
	public double illuminationTime() {
		if(currentColor == green) {
			return greenTime;
		} else if(currentColor == yellow) {
			return yellowTime;
		} else {
			return redTime;
		}
	}
	
	public double getDistanceToNextObstacle(Car c, double frontOfCarPos, Light l) {
		double d; 
		if (currentColor == yellow) {
			d = MP.inf;
			
			if (c.getDirectionOfCar().equals("northsouth") && c.getCurrentHolder() != l) {
				d = c.getCurrentHolder().getCarHolderLength() - frontOfCarPos;
			}
			
			if (c.getDirectionOfCar().equals("eastwest") == true) {
				if (c.getMaxVeloc() > c.getCurrentHolder().getCarHolderLength() - frontOfCarPos + l.getCarHolderLength() || c.getBrakeDist() < c.getCurrentHolder().getCarHolderLength() - frontOfCarPos + l.getCarHolderLength()) {
					d = MP.inf;
				} else {
					d = c.getCurrentHolder().getCarHolderLength() - frontOfCarPos;
				}
			}
			return d;
		} else if(currentColor == green) {
			if (c.getDirectionOfCar().equals("northsouth") && c.getCurrentHolder() != l) {
				d = c.getCurrentHolder().getCarHolderLength() - frontOfCarPos;
			} else {
				d = MP.inf;
			}
			
			return d;
		} else {
			if (c.getDirectionOfCar().equals("eastwest") && c.getCurrentHolder() != l) {
				d = c.getCurrentHolder().getCarHolderLength() - frontOfCarPos;
			} else {
				d = MP.inf;
			}

			return d;
		}
	}
}
