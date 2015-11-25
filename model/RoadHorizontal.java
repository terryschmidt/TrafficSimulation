package myproject.model;

import java.util.ArrayList;
import java.util.List;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class RoadHorizontal extends Road implements CarHolder {
	
	private double length;
	private List<Car> carList;
	private CarHolder nextHorizontalRoad;
	private CarHolder nextRoad;
	
	RoadHorizontal () {
		length = MP.getRandomRoadSegmentLength();
		carList = new ArrayList<Car>();
		nextHorizontalRoad = null;
		nextRoad = null;
	}

	@Override
	public CarHolder getNextHorizontalCarHolder() {
		return nextHorizontalRoad;
	}

	@Override
	public CarHolder getNextVerticalCarHolder() {
		throw new IllegalStateException();
	}

	@Override
	public void setNextVerticalCarHolder(CarHolder nextVCH) {
		throw new IllegalStateException();
	}

	@Override
	public void setNextHorizontalCarHolder(CarHolder nextHCH) {
		nextHorizontalRoad = nextHCH;
		nextRoad = nextHCH;
	}

	@Override
	public double getCarHolderLength() {
		return length;
	}

	@Override
	public void holdOntoCar(Car car, double frontBumperPos) {
		carList.remove(car);
		
		if (getCarHolderLength() < frontBumperPos == true) {
			setToNextRoad(car, frontBumperPos);
		} else {
			resetToThis(car, frontBumperPos);
		}
	}
	
	public void setToNextRoad(Car car, double front) {
		CarHolder next = getNextCarHolder(car);
		next.holdOntoCar(car, front - getCarHolderLength());
	}
	
	public void resetToThis(Car car, double f) {
		car.setRoad(this);
		car.setCarFrontPosition(f);
		carList.add(car);
		TaskKeeperImpl.getKeeper().addTask(TaskKeeperImpl.getKeeper().getTime() + MP.getTimeStep(), car);
	}

	@Override
	public List<Car> getAllCars() {
		return carList;
	}

	@Override
	public double lengthUntilStopRequired(Car car, double from) {
		double distanceToObst = ObstacleDistanceHelper.distanceToNextCar(carList, car, from, (CarHolder) this);
		return distanceToObst;
	}

	@Override
	public CarHolder getNextCarHolder(Car car) {
		return nextHorizontalRoad;
	}

	@Override
	public void setNextCarHolder(CarHolder road) {
		nextRoad = road;
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
		length = d;
	}
}