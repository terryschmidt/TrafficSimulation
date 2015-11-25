package myproject.model;

import java.util.ArrayList;

import myproject.model.Task;

//Terry Schmidt, SE450 Final Project, Fall 2015

public class TaskKeeperImpl implements TaskKeeper, Agent {
	
	private static int listLength;
	private static double timeDouble;
	private static Task headTask;
	private ArrayList<Task> taskList;
	private volatile static TaskKeeper keeper;
	
	TaskKeeperImpl() {
		listLength = 0;
		timeDouble = 0.0;
		keeper = null;
		headTask = ObjFactory.newTaskInstance(null, null, 0);
		taskList = new ArrayList<Task>();
	}
	
	public static TaskKeeper getKeeper() {
		if (keeper == null) {
			synchronized (TaskKeeperImpl.class) {
				if (keeper == null) {
					keeper = ObjFactory.newTaskKeeperInstance();
				}
			}
		}
		return keeper;
	}

	@Override
	public void runTask(double time) {
		double ending = time + timeDouble;
		while (listLength > 0 == true && headTask.next.taskTime <= ending == true) {
			run();
		}
		timeDouble = ending;
	}
	
	public void run() {
		timeDouble = headTask.next.taskTime;
		Agent agentToRun = removeTask();
		agentToRun.run();
	}
	
	public Agent removeTask() {
		if (listLength == 0) {
			return null;
		} else {
			return remove();
		}
	}
	
	public Agent remove() {
		Agent ag = headTask.next.a;
		headTask.next = headTask.next.next;
		listLength = listLength - 1;
		return ag;
	}

	@Override
	public double getTime() {
		return timeDouble;
	}
	
	@Override
	public void setCurrentTime(double t) {
		timeDouble = t;
	}

	@Override
	public void addTask(double waketime, Agent a) {
		Task prevTask = headTask;
		while (prevTask.next == null == false && prevTask.next.taskTime <= waketime == true) {
			prevTask = prevTask.next;
		}
		Task newTask = new Task(a, prevTask.next, waketime);
		prevTask.next = newTask;
		listLength = listLength + 1;
	}

	@Override
	public void clearTaskKeeper() {
		keeper = null;
		headTask = ObjFactory.newTaskInstance(null, null, 0);
		listLength = 0;
		timeDouble = 0;
	}
}
