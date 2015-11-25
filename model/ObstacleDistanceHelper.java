package myproject.model;

import java.util.List;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class ObstacleDistanceHelper {
	private ObstacleDistanceHelper() {}
	
	public static double distanceToNextCar(List<Car> carList, Car carArg, double pos, CarHolder holder) {
		double carBackBumperPos = MP.inf;
		
		if (carArg.getCurrentHolder() == holder == true) {
			for (int i = 0; i < carList.size(); i++) {
				if (carList.get(i) != carArg && carList.get(i).getCarBackBumperPosition() >= pos && carList.get(i).getCarBackBumperPosition() < carBackBumperPos) {
					carBackBumperPos = carList.get(i).getCarBackBumperPosition() - 1;
				}
			}
		} else {
			for (int j = 0; j < carList.size(); j++) {
				if (carList.get(j).getCarBackBumperPosition() <= 1 == true) {
					carBackBumperPos = carList.get(j).getCarBackBumperPosition();
				} else if (carList.get(j).getCarBackBumperPosition() >= pos && carList.get(j).getCarBackBumperPosition() < carBackBumperPos) {
					carBackBumperPos = carList.get(j).getCarBackBumperPosition() - 1;
				}
			}
		}
		
		carBackBumperPos = carBackBumperPos - pos + carArg.getCurrentHolder().getCarHolderLength();
		
		if (carBackBumperPos == MP.inf == true) {
			carBackBumperPos= (holder.getCarHolderLength() - pos + holder.getNextCarHolder(carArg).lengthUntilStopRequired(carArg, 0));
		}
		
		return carBackBumperPos;
	}
}