package myproject.model;

import static org.junit.Assert.*;
import org.junit.Test;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class ObjFactoryTEST {
	
	@Test
	public void test1() {
		Car testCar = ObjFactory.newCarInstance();
		assertTrue(testCar.getBrakeDist() >= MP.getMinBrakeDistance() && testCar.getBrakeDist() <= MP.getMaxBrakeDistance());
		assertTrue(testCar.getStopDist() >= MP.getMinStopDistance() && testCar.getStopDist() <= MP.getMaxStopDistance());
		assertTrue(testCar.getMaxVeloc() >= MP.getMinVelocity() && testCar.getMaxVeloc() <= MP.getMaxVelocity());
		assertTrue(testCar.getLength() >= MP.getCarLengthMin() && testCar.getLength() <= MP.getCarLengthMax());
		
		Light testLight = ObjFactory.newLightInstance();
		assertTrue(testLight.getCarHolderLength() >= MP.getIntersectionLengthMin() && testLight.getCarHolderLength() <= MP.getIntersectionLengthMax());
		assertTrue(testLight.greenTime >= MP.getMinGreenLightTime() && testLight.greenTime <= MP.getMaxGreenLightTime());
		assertTrue(testLight.yellowTime >= MP.getMinYellowLightTime() && testLight.yellowTime <= MP.getMaxYellowLightTime());
		
		CarDestroyer testDestroyer = ObjFactory.newCarDestroyerInstance();
		CarHolder testCH = ObjFactory.newHorizontalRoadInstance();
		CarHolder testCH2 = ObjFactory.newVerticalRoadInstance();
		try {
			testDestroyer.setNextHorizontalCarHolder(testCH);
			fail();
		} catch (IllegalStateException s) { }
		
		try {
			testDestroyer.setNextVerticalCarHolder(testCH2);
			fail();
		} catch (IllegalStateException s) { }
		
		try {
			testDestroyer.setNextCarHolder(testCH);
			fail();
		} catch (IllegalStateException s) { }
		
		testDestroyer.holdOntoCar(testCar, 4.0);
		assertTrue(testDestroyer.getAllCars().isEmpty());
		
		CarCreator testCreator = ObjFactory.newCarCreatorInstance();
		assertTrue(testCreator.getRandomCarGenDelay() >= MP.getCarGenDelayMin() && testCreator.getRandomCarGenDelay() <= MP.getCarGenDelayMax());
		
		RoadVertical testVertRoad = ObjFactory.newVerticalRoadInstance();
		RoadVertical testVertRoad2 = ObjFactory.newVerticalRoadInstance();
		testVertRoad.setNextVerticalCarHolder(testVertRoad2);
		assertTrue(testVertRoad.getNextVerticalCarHolder() == testVertRoad2);
		
		try {
			testVertRoad.setNextHorizontalCarHolder(testVertRoad2);
			fail();
		} catch (IllegalStateException s) { }
		
		RoadHorizontal testHorizRoad = ObjFactory.newHorizontalRoadInstance();
		RoadHorizontal testHorizRoad2 = ObjFactory.newHorizontalRoadInstance();
		testHorizRoad.setNextHorizontalCarHolder(testHorizRoad2);
		assertTrue(testHorizRoad.getNextHorizontalCarHolder() == testHorizRoad2);
		
		try {
			testHorizRoad.setNextVerticalCarHolder(testHorizRoad2);
			fail();
		} catch (IllegalStateException s) { }
		
		//assertTrue(testVertRoad.getCarHolderLength() >= MP.getRoadSegmentLengthMin() && testVertRoad.getCarHolderLength() <= MP.getRoadSegmentLengthMax());
		//assertTrue(testHorizRoad.getCarHolderLength() >= MP.getRoadSegmentLengthMin() && testHorizRoad.getCarHolderLength() <= MP.getRoadSegmentLengthMax());
		
		
		
		
	}
}
