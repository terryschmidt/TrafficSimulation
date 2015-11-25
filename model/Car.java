package myproject.model;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */

//Terry Schmidt, SE450 Final Project, Fall 2015

public class Car implements Agent {
	// terry added:
	private double brakeDist;
	private double stopDist;
	private double maxVeloc;
	private double length;
	private double positionOfFrontOfCar;
	private String directionOfCar;
	private CarHolder currentHolder;
	private java.awt.Color color;
	
	//Car() { } // Created only by this package
	
	Car() {
		this.brakeDist = MP.getRandomBrakeDistance();
		this.stopDist = MP.getRandomStopDistance();
		this.maxVeloc = MP.getRandomCarVelocity();
		this.length = MP.getRandomCarLength();
		this.currentHolder = null;
		this.positionOfFrontOfCar = 0;
		this.directionOfCar = "northsouth";
		this.color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));
	}

	public void run() {
		double newVelocity = findAppropriateNewVelocity();
		currentHolder.holdOntoCar(this, (newVelocity * MP.getTimeStep()) + positionOfFrontOfCar);
	}
	
	public double findAppropriateNewVelocity() {
		// closest obstacle:
		double obstacleDist = currentHolder.lengthUntilStopRequired(this, positionOfFrontOfCar) - getCurrentHolder().getCarHolderLength();
				
		// find new velocity based on new obstacle:
		double tempNewVeloc;
		if (obstacleDist <= stopDist) {
			tempNewVeloc = 0; // slam on brakes!
		} else if (obstacleDist > maxVeloc) {
			tempNewVeloc = maxVeloc; // floor it!  no obstacles nearby.
		} else { // if we're not flooring it and we're not hiting the brakes, then we need to slow down...
			double finalNewVelocity;
			finalNewVelocity = (maxVeloc / (brakeDist - stopDist)) * (obstacleDist - stopDist);
			finalNewVelocity = Math.max(0.0, finalNewVelocity);
			finalNewVelocity = Math.min(maxVeloc, finalNewVelocity);
					
			while (finalNewVelocity > obstacleDist) {
				finalNewVelocity = finalNewVelocity / 2; // slow down some
			}
					
			tempNewVeloc = finalNewVelocity;
		}
		
		return tempNewVeloc;
	}
	
	public double getBrakeDist() {
		return brakeDist;
	}
	
	public void setBrakeDist(double d) {
		brakeDist = d;
	}
	
	public double getStopDist() {
		return stopDist;
	}
	
	public void setStopDist(double d) {
		stopDist = d;
	}
	
	public double getMaxVeloc() {
		return maxVeloc;
	}
	
	public void setMaxVeloc(double d) {
		maxVeloc = d;
	}
	
	public double getLength() {
		return length;
	}
	
	public void setLength(double d) {
		length = d;
	}

	public double getPosition() {
		double pos = positionOfFrontOfCar - (length / 2);
		return pos;
	}
	
	public void setPosition(double d) {
		positionOfFrontOfCar = d;
	}
	
	public java.awt.Color getColor() {
		return color;
	}
	
	public void setColor(java.awt.Color colorArg) {
		color = colorArg;
	}
	
	public CarHolder getCurrentHolder() {
		return currentHolder;
	}
	
	public void setRoad (CarHolder newRoad) {
		currentHolder = newRoad;
	}
	
	public CarHolder nextRoad() {
		if (directionOfCar.equals("northsouth") == true) {
			return currentHolder.getNextVerticalCarHolder();
		}
		
		if (directionOfCar.equals("eastwest") == true) {
			return currentHolder.getNextHorizontalCarHolder();
		}
		
		return null;
	}
	
	public double getCarMiddlePosition() {
		double midPos = positionOfFrontOfCar - (length / 2);
		return midPos;
	}
	
	public double getCarFrontBumperPosition() {
		return positionOfFrontOfCar;
	}
	
	public void setCarFrontPosition(double newPos) {
		positionOfFrontOfCar = newPos;
	}
	
	public double getCarBackBumperPosition() {
		double backBump = positionOfFrontOfCar - length;
		return backBump;
	}
	
	public String getDirectionOfCar() {
		return directionOfCar;
	}
	
	public void setDirectionOfCar(String newDirection) {
		directionOfCar = newDirection;
	}
}
