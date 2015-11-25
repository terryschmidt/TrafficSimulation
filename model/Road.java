package myproject.model;

import java.util.List;


/**
 * A road holds cars.
 */

//Terry Schmidt, SE450 Final Project, Fall 2015

public abstract class Road implements CarHolder {
	
	public abstract CarHolder getNextVerticalCarHolder();
	
	public abstract CarHolder getNextHorizontalCarHolder();
	
	public abstract void setNextVerticalCarHolder(CarHolder nextVCH);

	public abstract void setNextHorizontalCarHolder(CarHolder nextHCH);

	public abstract double getCarHolderLength();
	
	public abstract void setCarHolderLength(double d);

	public abstract void holdOntoCar(Car car, double frontBumperPosition);
	
	public abstract void deleteCar(Car car);

	public abstract List<Car> getAllCars();
	
	public abstract void setAllCars(List<Car> newList);

	public abstract double lengthUntilStopRequired(Car car, double pos);

	public abstract CarHolder getNextCarHolder(Car car);

	public abstract void setNextCarHolder(CarHolder road);
	
}