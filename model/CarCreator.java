package myproject.model;

import java.util.ArrayList;
import java.util.List;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class CarCreator implements Agent, CarHolder {
	
	private List<Car> carList;
	private CarHolder nextRoad;
	private CarHolder nextHorizontalRoad;
	private CarHolder nextVerticalRoad;
	private double creatorLength;
	private double carCreationRate;
	private boolean resetRate;
	
	CarCreator() {
		nextRoad = null;
		nextHorizontalRoad = null;
		nextVerticalRoad = null;
		creatorLength = 0;
		carCreationRate = MP.getRandomCarGenDelay();
		carList = new ArrayList<Car>();
		resetRate = true;
	}
	
	@Override
	public void run() {
		Car newCar = makeCar();
		newCar.setRoad(nextRoad);
		String direction = creatorDirection();
		newCar.setDirectionOfCar(direction);
		
		if (nextRoad.lengthUntilStopRequired(newCar, 1) >= 1.0 == true) {
			nextRoad.holdOntoCar(newCar, 1);
		}
		
		TaskKeeperImpl.getKeeper().addTask(TaskKeeperImpl.getKeeper().getTime() + carCreationRate, this);
		
		if (resetRate == true) {
			setFinalCreationRate();
		}
	}
	
	public void setFinalCreationRate() {
		carCreationRate = MP.getRandomCarEntryRate();
		resetRate = false;
	}
	
	public Car makeCar() {
		Car newCar = ObjFactory.newCarInstance();
		return newCar;
	}
	
	public String creatorDirection() {
		if(this.nextVerticalRoad == null) {
			return "eastwest";
		} else {
			return "northsouth";
		}
	}

	@Override
	public CarHolder getNextVerticalCarHolder() {
		return nextVerticalRoad;
	}

	@Override
	public CarHolder getNextHorizontalCarHolder() {
		return nextHorizontalRoad;
	}

	@Override
	public void setNextVerticalCarHolder(CarHolder nextVCH) {
		nextVerticalRoad = nextVCH;
		nextRoad = nextVCH;
	}

	@Override
	public void setNextHorizontalCarHolder(CarHolder nextHCH) {
		nextHorizontalRoad = nextHCH;
		nextRoad = nextHCH;
	}

	@Override
	public double getCarHolderLength() {
		return creatorLength;
	}

	@Override
	public void holdOntoCar(Car car, double frontPosition) {
		nextRoad.holdOntoCar(car, frontPosition);
	}

	@Override
	public List<Car> getAllCars() {
		return carList;
	}

	@Override
	public double lengthUntilStopRequired(Car car, double fromPosition) {
		return MP.inf;
		//throw new IllegalStateException();
	}

	@Override
	public CarHolder getNextCarHolder(Car car) {
		if (car.getDirectionOfCar().equals("northsouth") == true) {
			return getNextVerticalCarHolder();
		} 
		
		if (car.getDirectionOfCar().equals("eastwest") == true) {
			return getNextHorizontalCarHolder();
		}
		
		return null;
	}
	
	public double getRandomCarGenDelay() {
		return carCreationRate;
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
		creatorLength = d;
	}
}
