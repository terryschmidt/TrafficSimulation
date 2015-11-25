package myproject.model;

import java.util.ArrayList;
import java.util.List;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class CarDestroyer implements CarHolder {
	
	private List<Car> carList;
	private CarHolder nextRoad;
	private CarHolder nextHorizontalRoad;
	private CarHolder nextVerticalRoad;
	private double destroyerLength;
	
	CarDestroyer() {
		this.destroyerLength = 0.25;
		this.nextRoad = null;
		this.nextHorizontalRoad = null;
		this.nextVerticalRoad = null;
		this.carList = new ArrayList<Car>();
	}
	
	@Override
	public CarHolder getNextHorizontalCarHolder() {
		throw new IllegalStateException();
	}

	@Override
	public CarHolder getNextVerticalCarHolder() {
		throw new IllegalStateException(); // can't go anywhere other than here because this is a sink!
	}

	@Override
	public void setNextVerticalCarHolder(CarHolder nextVCH) {
		// does nothing.  Sinks signify the end of the path, there is no next road.
		throw new IllegalStateException();
	}

	@Override
	public void setNextHorizontalCarHolder(CarHolder nextHCH) {
		// does nothing.  Sinks signify the end of the path, there is no next road.
		throw new IllegalStateException();
	}

	@Override
	public double getCarHolderLength() {
		return destroyerLength;
	}

	@Override
	public void holdOntoCar(Car car, double frontPosition) {
		// asking a car sink to hold a new car is equivalent to asking the car be removed from the simulation, thus:
		//carList.remove(car);
		car = null;
	}

	@Override
	public List<Car> getAllCars() {
		return carList;
	}

	@Override
	public double lengthUntilStopRequired(Car car, double fromPosition) {
		return MP.inf;
	}

	@Override
	public CarHolder getNextCarHolder(Car car) {
		throw new IllegalStateException();
	}

	@Override
	public void setNextCarHolder(CarHolder road) {
		throw new IllegalStateException();
	}

	@Override
	public void setAllCars(List<Car> l) {
		carList = l;
	}

	@Override
	public void deleteCar(Car car) {
		for (Car c : carList) {
			if (c == car) {
				carList.remove(c);
			}
		}
	}

	@Override
	public void setCarHolderLength(double d) {
		destroyerLength = d;
	}
}