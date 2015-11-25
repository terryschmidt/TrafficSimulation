package myproject.model;

import java.util.ArrayList;
import java.awt.Color;
import java.util.List;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class Light implements Agent, CarHolder {
	
	public LightControl controller;
	private double intersectionLength;
	public double greenTime;
	public double yellowTime;
	private CarHolder nextVerticalRoad;
	private CarHolder nextHorizontalRoad;
	private CarHolder nextRoad;
	private List<Car> carList;
	
	Light() {  // Created only by this package
		intersectionLength = MP.getRandomIntersectionLength();
		greenTime = MP.getRandomGreenTime();
		yellowTime = MP.getRandomYellowTime();
		nextVerticalRoad = null;
		nextHorizontalRoad = null;
		nextRoad = null;
		carList = new ArrayList<Car>();
		controller = ObjFactory.newLightControlInstance(greenTime, yellowTime, greenTime);
	}

	public Color getCurrentColor() {
		return controller.getCurrentColor();
	}
	
	public void run() {
		setNextLightColor();
		TaskKeeperImpl.getKeeper().addTask(TaskKeeperImpl.getKeeper().getTime() + controller.illuminationTime(), this);
		/*if (time%40==0) {
			state = !state;
		}*/
	}
	
	public void setNextLightColor() {
		controller.currentColor = controller.nextColor();
	}
	
	public double getGreenTime() {
		return greenTime;
	}
	
	public double getYellowTime() {
		return yellowTime;
	}
	
	public void setGreenTime() {
		greenTime = MP.getRandomGreenTime();
	}
	
	public void setYellowTime() {
		yellowTime = MP.getRandomYellowTime();
	}

	@Override
	public CarHolder getNextHorizontalCarHolder() {
		return nextHorizontalRoad;
	}
	@Override
	public CarHolder getNextVerticalCarHolder() {
		return nextVerticalRoad;
	}
	@Override
	public void setNextVerticalCarHolder(CarHolder nextVCH) {
		nextVerticalRoad = nextVCH;
	}
	
	@Override
	public void setNextHorizontalCarHolder(CarHolder nextHCH) {
		nextHorizontalRoad = nextHCH;
	}
	
	@Override
	public double getCarHolderLength() {
		return intersectionLength;
	}
	
	@Override
	public void holdOntoCar(Car car, double frontPosition) {
		carList.remove(car);
		
		if (frontPosition > getCarHolderLength() == true) {
			setToNextRoad(car, frontPosition);
		} else {
			resetToThis(car, frontPosition);
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
	public double lengthUntilStopRequired(Car car, double frontPosition) {
		double obstacleDistance = controller.getDistanceToNextObstacle(car, frontPosition, this);
		double obstaclePosition = ObstacleDistanceHelper.distanceToNextCar(carList, car, frontPosition, (CarHolder) this);
		double temp = Math.min(obstaclePosition, obstacleDistance);
		obstaclePosition = temp;
		return obstaclePosition;
	}
	
	@Override
	public CarHolder getNextCarHolder(Car car) {
		if (car.getDirectionOfCar().equals("eastwest")) {
			return nextHorizontalRoad;
		}
		
		if (car.getDirectionOfCar().equals("northsouth")) {
			return nextVerticalRoad;
		}
		
		return null;
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
		intersectionLength = d;
	}
}