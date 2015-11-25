package myproject.model;

import java.util.Random;

/**
 * Static class for model parameters.
 */

//Terry Schmidt, SE450 Final Project, Fall 2015

public class MP {
	private MP() {}
	
	/** Length of cars, in meters */
	 //public static double carLength = 10;
	/** Length of roads, in meters */
	// public static double roadLength = 200;
	/** Maximum car velocity, in meters/second */
	 //public static double maxVelocity = 6;
	
	public static double timeStep = 0.1;
	public static double runtimeDuration = 2000.0;
	public static int rows = 5; public static int cols = 5; 
	public static String traffic = "alternating";
	public static double carGenDelayMin = 10.0; public static double carGenDelayMax = 12.0;
	public static double carEntryRate = 7.0; public static double carEntryRateMin = 7.0; public static double carEntryRateMax = 10.0; 
	public static double roadLength = 206; public static double roadLengthMin = 220; public static double roadLengthMax = 250;
	public static double intersectionLength = 10.0; public static double intersectionLengthMin = 10.0; public static double intersectionLengthMax = 15.0;
	public static double carLength = 10; public static double carLengthMin = 5.0; public static double carLengthMax = 10.0; 
	public static double maxVelocity = 40; public static double lowerBoundMaxVelocity = 60; public static double highBoundMaxVelocity = 80;
	public static double stopDistance = 2.0; public static double minStopDistance = 0.5;  public static double maxStopDistance = 5.0;
	public static double brakeDistance = 9.0; public static double minBrakeDistance = 9.0; public static double maxBrakeDistance = 10.0;
	public static double greenLightTime = 50.0; public static double minGreenLightTime = 10.0; public static double maxGreenLightTime = 80.0;
	public static double yellowLightTime = 4.5; public static double minYellowLightTime = 4.0; public static double maxYellowLightTime = 5.0;
	public static Random RNG = new Random();
	public static double inf = Double.POSITIVE_INFINITY;
	
	// CAR GENERATION METHODS
	
	public static double getRandomCarGenDelay() {
		return randomize(carGenDelayMin, carGenDelayMax);
	}
	
	public static double getCarGenDelayMin() {
		return carGenDelayMin;
	}

	public static double getCarGenDelayMax() {
		return carGenDelayMax;
	}

	public static void setCarGenDelayMin(double min) {
		carGenDelayMin = min;
	}

	public static void setCarGenDelayMax(double max) {
		carGenDelayMax = max;
	}
	
	// VELOCITY METHODS
	
	public static double getRandomCarVelocity() {
		return randomize(lowerBoundMaxVelocity, highBoundMaxVelocity);
	}
	
	public static void setMaxHighBoundVelocity(double newMaxVelocity) {
		highBoundMaxVelocity = newMaxVelocity;
	}
	
	public static void setMaxLowBoundVelocity(double newMinVelocity) {
		lowerBoundMaxVelocity = newMinVelocity;
	}
	
	public static double getMinVelocity() {
		return lowerBoundMaxVelocity;
	}
	
	public static double getMaxVelocity() {
		return highBoundMaxVelocity;
	}
	
	// CAR LENGTH METHODS
	
	public static double getRandomCarLength() {
		return randomize(carLengthMin, carLengthMax);
	}
	
	public static double getCarLengthMin() {
		return carLengthMin;
	}
	
	public static double getCarLengthMax() {
		return carLengthMax;
	}
	
	public static void setCarLengthMin(double newMinLength) {
		carLengthMin = newMinLength;
	}
	
	public static void setCarLengthMax(double newCarLength) {
		carLengthMax = newCarLength;
	}
	
	// GRID METHODS
	
	public static int getNumRows() {
		return rows;
	}
	
	public static int getNumCols() {
		return cols;
	}
	
	public static void setNumRows(int rowNum) {
		rows = rowNum;
	}
	
	public static void setNumCols(int colNum) {
		cols = colNum;
	}
	
	// RUNTIME METHODS
	
	public static double getRuntimeDuration() {
		return runtimeDuration;
	}
	
	public static void setRuntimeDuration(double durationNum) {
		runtimeDuration = durationNum;
	}
	
	// BREAK DISTANCE METHODS
	
	public static double getRandomBrakeDistance() {
		return randomize(minBrakeDistance, maxBrakeDistance);
	}
	
	public static double getMinBrakeDistance() {
		return minBrakeDistance;
	}
	
	public static double getMaxBrakeDistance() {
		return maxBrakeDistance;
	}
	
	public static void setMinBrakeDistance(double min) {
		minBrakeDistance = min;
	}
	
	public static void setMaxBrakeDistance(double max) {
		maxBrakeDistance = max;
	}
	
	// STOP DISTANCE METHODS
	
	public static double getRandomStopDistance() {
		return randomize(minStopDistance, maxStopDistance);
	}
	
	public static Double getMinStopDistance() {
		return minStopDistance;
	}
	
	public static Double getMaxStopDistance() {
		return maxStopDistance;
	}
	
	public static void setMinStopDistance(double newStopDistance) {
		minStopDistance = newStopDistance;
	}
	
	public static void setMaxStopDistance(double newStopDistance) {
		maxStopDistance = newStopDistance;
	}
	
	// TIMESTEP METHODS
	
	public static double getTimeStep() {
		return timeStep;
	}
	
	public static void setTimeStep(double newTimeStep) {
		timeStep = newTimeStep;
	}
	
	// TRAFFIC PATTERN METHODS
	
	public static void setTraffic(String pattern) {
		if (pattern.equals("simple")) {
			traffic = "simple";
		} else if (pattern.equals("alternating")) {
			traffic = "alternating";
		} else {
			
		}
	}
	
	public static String getTraffic() {
		return traffic;
	}
	
	// ROAD LENGTH METHODS
	
	public static double getRandomRoadSegmentLength() {
		//return randomize(roadLengthMin, roadLengthMax);
		
		return 206.0;
	}
	
	public static void setRoadSegmentLengthMin(double newRoadLength) {
		roadLengthMin = newRoadLength;
	}
	
	public static void setRoadSegmentLengthMax(double newRoadLength) {
		roadLengthMax = newRoadLength;
	}
	
	public static double getRoadSegmentLengthMin() {
		return roadLengthMin;
	}
	
	public static double getRoadSegmentLengthMax() {
		return roadLengthMax;
	}
	
	// INTERSECTION LENGTH METHODS
	
	public static double getRandomIntersectionLength() {
		return randomize(intersectionLengthMin, intersectionLengthMax);
	}
	
	public static void setIntersectionLengthMin(double newLength) {
		intersectionLengthMin = newLength;
	}
	
	public static void setIntersectionLengthMax(double newLength) {
		intersectionLengthMax = newLength;
	}
	
	public static Double getIntersectionLengthMin() {
		return intersectionLengthMin;
	}
	
	public static double getIntersectionLengthMax() {
		return intersectionLengthMax;
	}
	
	// PRINT ALL VALUES
	
	public static String showAllValues() {
		String allVals = "";
		
		allVals += "Simulation time step (seconds) " + "                    " + "[" + getTimeStep() + "]" + "\n";
		allVals += "Simulation run time (seconds) " + "                      " + "[" + getRuntimeDuration() + "]" + "\n";
		allVals += "Grid size (number of roads) " + "                          " + "[row=" + getNumRows() + ",column=" + getNumCols() + "]" + "\n";
		allVals += "Traffic pattern " + "                                              " + "[" + getTraffic() + "]" + "\n";
		allVals += "Car entry rate (seconds/car) " + "                         " + "[min=" + getCarEntryRateMin() + ",max=" + getCarEntryRateMax() + "]" + "\n";
		allVals += "Road segment length (meters) " + "                      " + "[min=" + getRoadSegmentLengthMin() + ",max=" + getRoadSegmentLengthMax() + "]" + "\n";
		allVals += "Intersection length (meters) " + "                         " + "[min=" + getIntersectionLengthMin() + ",max=" + getIntersectionLengthMax() + "]" + "\n";
		allVals += "Car length (meters) " + "                                       " + "[min=" + getCarLengthMin() + ",max=" + getCarLengthMax() + "]" + "\n";
		allVals += "Car maximum velocity (meters/second) " + "       " + "[min=" + getMinVelocity() + ",max=" + getMaxVelocity() + "]" + "\n";
		allVals += "Car stop distance (meters) " + "                            " + "[min=" + getMinStopDistance() + ",max=" + getMaxStopDistance() + "]" + "\n";
		allVals += "Car brake distance (meters) " + "                          " + "[min=" + getMinBrakeDistance() + ",max=" + getMaxBrakeDistance() + "]" + "\n";
		allVals += "Traffic light green time (seconds) " + "                 " + "[min=" + getMinGreenLightTime() + ",max=" + getMaxGreenLightTime() + "]" + "\n";
		allVals += "Traffic light yellow time (seconds) " + "                " + "[min=" + getMinYellowLightTime() + ",max=" + getMaxYellowLightTime() + "]";
		
		return allVals;
	}
	
	// GREEN LIGHT METHODS
	
	public static double getRandomGreenTime() {
		return randomize(minGreenLightTime, maxGreenLightTime);
	}
	
	public static double getMinGreenLightTime() {
		return minGreenLightTime;
	}
	
	public static double getMaxGreenLightTime() {
		return maxGreenLightTime;
	}
	
	public static void setMinGreenLightTime(double min) {
		minGreenLightTime = min;
	}
	
	public static void setMaxGreenLightTime(double max) {
		maxGreenLightTime = max;
	}
	
	// YELLOW LIGHT METHODS
	
	public static double getRandomYellowTime() {
		return randomize(minYellowLightTime, maxYellowLightTime);
	}
	
	public static double getMinYellowLightTime() {
		return minYellowLightTime;
	}
	
	public static double getMaxYellowLightTime() {
		return maxYellowLightTime;
	}
	
	public static void setMinYellowLightTime(double minny) {
		minYellowLightTime = minny;
	}
	
	public static void setMaxYellowLightTime(double maxxy) {
		maxYellowLightTime = maxxy;
	}
	
	// CAR ENTRY RATE METHODS
	
	public static double getRandomCarEntryRate() {
		return randomize(carEntryRateMin, carEntryRateMax);
	}
	
	public static void setCarEntryRateMax(double max) {
		carEntryRateMax = max;
	}
	
	public static void setCarEntryRateMin(double min) {
		carEntryRateMin = min;
	}
	
	public static double getCarEntryRateMax() {
		return carEntryRateMax;
	}
	
	public static double getCarEntryRateMin() {
		return carEntryRateMin;
	}
	
	// RANDOM
	
	public static double randomize (double minVal, double maxVal) {
		return (RNG.nextDouble() * (maxVal - minVal)) + minVal;
	}
}