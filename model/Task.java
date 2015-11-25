package myproject.model;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class Task {
	final double taskTime;
	final Agent a;
	Task next;

	public Task(Agent a, Task next, double wTime) {
		this.taskTime = wTime;
		this.a = a;
		this.next = next;
	}
}