package myproject.model;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class ObjFactory {
	private ObjFactory() {}
	
	public static Car newCarInstance() {
		return new Car();
	}
	
	public static Light newLightInstance() {
		return new Light();
	}
	
	public static CarDestroyer newCarDestroyerInstance() {
		return new CarDestroyer();
	}
	
	public static CarCreator newCarCreatorInstance() {
		return new CarCreator();
	}
	
	public static LightControl newLightControlInstance(double greenTime, double yellowTime, double redTime) {
		return new LightControl(greenTime, yellowTime, redTime);
	}
	
	public static RoadVertical newVerticalRoadInstance() {
		return new RoadVertical();
	}
	
	public static RoadHorizontal newHorizontalRoadInstance() {
		return new RoadHorizontal();
	}
	
	public static Task newTaskInstance(Agent a, Task t, double d) {
		return new Task(a, t, d);
	}
	
	public static TaskKeeperImpl newTaskKeeperInstance() {
		return new TaskKeeperImpl();
	}
}