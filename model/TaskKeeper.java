package myproject.model;

//Terry Schmidt, SE450 Final Project, Fall 2015

public interface TaskKeeper {
	public void runTask(double time);
	
	public double getTime();
	
	public void setCurrentTime(double t);

	public void addTask(double wTime, Agent a);
	
	public Agent removeTask();
	
	public void clearTaskKeeper();
}
