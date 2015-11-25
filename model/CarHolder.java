package myproject.model;

import java.util.List;

public interface CarHolder {
	
	public CarHolder getNextHorizontalCarHolder();

	public CarHolder getNextVerticalCarHolder();
	
	public void setNextVerticalCarHolder(CarHolder nextVCH);

	public void setNextHorizontalCarHolder(CarHolder nextHCH);

	public double getCarHolderLength();
	
	public void setCarHolderLength(double d);

	public void holdOntoCar(Car car, double frontPosition);
	
	public void deleteCar(Car car);

	public List<Car> getAllCars();
	
	public void setAllCars(List<Car> l);

	public double lengthUntilStopRequired(Car car, double fromPosition);

	public CarHolder getNextCarHolder(Car car);

	public void setNextCarHolder(CarHolder next);
	
}