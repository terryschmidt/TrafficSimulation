package myproject.main;

import myproject.model.*;
import myproject.model.swing.SwingAnimatorBuilder;
import myproject.ui.*;

// Terry Schmidt, SE450 Final Project, Fall 2015

public class Control {
	private static final int EXITED = 0;
	private static final int EXIT = 1;
	private static final int START = 2;
	private static final int EDIT = 3;
	private static final int NUMSTATES = 10;

	private int currentState;
	private UIMenu[] menus;
	private UIFormTest numTest;
	private UIFormTest intTest;
	private UIFormTest trafficPatternTest;

	private UIForm getNum;
	private UIForm getMinAndMax;
	private UIForm getRowAndCol;
	private UIForm getPattern;
	

	private UI myUI;

	Control(UI ui) {
		myUI = ui;
		menus = new UIMenu[NUMSTATES];
		currentState = START;
		addSTART(START);
		addEDIT(EDIT);
		addEXIT(EXIT);
		
		trafficPatternTest = input -> {
			if (input.equals("alternating") || input.equals("simple")) {
				return true;
			} else {
				return false;
			}
		};

		numTest = input -> {
			try {
				Double.parseDouble(input);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		};
		
		intTest = input -> {
				try {
					Integer.parseInt(input);
					return true;
				} catch (NumberFormatException e) {
					return false;
				}
		};
		
		UIFormBuilder moreDataForm = new UIFormBuilder();
		moreDataForm.add("Minimum", numTest);
		moreDataForm.add("Maximum", numTest);
		getMinAndMax = moreDataForm.toUIForm("Enter range:");
		
		UIFormBuilder moreDataForm2 = new UIFormBuilder();
		moreDataForm2.add("Value", numTest);
		getNum = moreDataForm2.toUIForm("Enter value:");
		
		UIFormBuilder moreDataForm3 = new UIFormBuilder();
		moreDataForm3.add("Pattern: (simple or alternating)", trafficPatternTest);
		getPattern = moreDataForm3.toUIForm("Enter pattern:");
		
		UIFormBuilder moreDataForm4 = new UIFormBuilder();
		moreDataForm4.add("Rows:", intTest);
		moreDataForm4.add("Columns:", intTest);
		getRowAndCol = moreDataForm4.toUIForm("Enter row and column:");
	}

	void run() {
		try {
			while (currentState != EXITED) {
				myUI.processMenu(menus[currentState]);
			}
			// user has requested exit:
			System.exit(0);
		} catch (UIError e) {
			myUI.displayError("UI error");
		}
	}

	private void addEXIT(int state) {
		UIMenuBuilder exitMenu = new UIMenuBuilder();
		
		exitMenu.add("Default", () -> {
				myUI.displayError("Invalid Selection");
		});
		
		exitMenu.add("Yes", () -> {
				currentState = EXITED;
		});
		
		exitMenu.add("No", () -> {
				currentState = START;
		});
		
		menus[state] = exitMenu.toUIMenu("Are you sure you want to exit?");
	}

	private void addSTART(int state) {
		UIMenuBuilder startMenu = new UIMenuBuilder();
		
		startMenu.add("Default", () -> {
				myUI.displayError("Invalid Selection");
		});
		
		startMenu.add("Run Simulation", () -> {
				AnimatorBuilder myBuilder = new SwingAnimatorBuilder();
				Model model = new Model(myBuilder, MP.getNumRows(), MP.getNumCols());
				model.run((int) MP.getRuntimeDuration());
				model.dispose();
				TaskKeeperImpl.getKeeper().clearTaskKeeper();
				/*
				Model myModel = new Model(myBuilder, MP.getNumRows(), MP.getNumCols());
				myModel.run(MP.getRuntimeDuration());
				myModel.dispose();
				*/
		});
		
		startMenu.add("Change simulation parameters", () -> {
				currentState = EDIT;
		});
		
		startMenu.add("Exit", () -> {
				currentState = EXIT;
		});
		
		menus[state] = startMenu.toUIMenu("Terry Schmidt's SE450 Final Project");
	}

	private void addEDIT(int state) {
		UIMenuBuilder editMenu = new UIMenuBuilder();
		
		editMenu.add("Default", () -> {
				myUI.displayError("Invalid Selection");
		});
		
		editMenu.add("Show current values", () -> {
				myUI.displayMessage(MP.showAllValues());
		});
		
		editMenu.add("Simulation time step", () -> {
				String[] inputFromUser = myUI.processForm(getNum);
				MP.setTimeStep(Double.parseDouble(inputFromUser[0]));
		});
		
		editMenu.add("Simulation run time", () -> {
				String[] inputFromUser = myUI.processForm(getNum);
				MP.setRuntimeDuration(Double.parseDouble(inputFromUser[0]));
		});
		
		editMenu.add("Grid size", () -> {
				String[] gridVals = myUI.processForm(getRowAndCol);
				MP.setNumRows(Integer.parseInt((gridVals[0])));
				MP.setNumCols(Integer.parseInt((gridVals[1])));
		});
		
		editMenu.add("Traffic pattern", () -> {
				String[] userInput = myUI.processForm(getPattern);
				MP.setTraffic(userInput[0]);
		});
		
		editMenu.add("Car entry rate", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setCarEntryRateMin(Double.parseDouble(valsFromUser[0]));
				MP.setCarEntryRateMax(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Road segment length", () -> {
				String[] getValsFromUser = myUI.processForm(getMinAndMax);
				MP.setRoadSegmentLengthMin(Double.parseDouble(getValsFromUser[0]));
				MP.setRoadSegmentLengthMax(Double.parseDouble(getValsFromUser[1]));
		});
		
		editMenu.add("Intersection length", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setIntersectionLengthMin(Double.parseDouble(valsFromUser[0]));
				MP.setIntersectionLengthMax(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Car length", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setCarLengthMin(Double.parseDouble(valsFromUser[0]));
				MP.setCarLengthMax(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Car maximum velocity", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setMaxLowBoundVelocity(Double.parseDouble(valsFromUser[0]));
				MP.setMaxHighBoundVelocity(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Car stop distance", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setMinStopDistance(Double.parseDouble(valsFromUser[0]));
				MP.setMaxStopDistance(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Car brake distance", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setMinBrakeDistance(Double.parseDouble(valsFromUser[0]));
				MP.setMaxBrakeDistance(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Traffic light green time", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setMinGreenLightTime(Double.parseDouble(valsFromUser[0]));
				MP.setMaxGreenLightTime(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Traffic light yellow time", () -> {
				String[] valsFromUser = myUI.processForm(getMinAndMax);
				MP.setMinYellowLightTime(Double.parseDouble(valsFromUser[0]));
				MP.setMaxYellowLightTime(Double.parseDouble(valsFromUser[1]));
		});
		
		editMenu.add("Reset simulation and return to the main menu", () -> {
				currentState = START; // change the state
		});
		
		menus[state] = editMenu.toUIMenu("Editable simulation parameters");
	}
}