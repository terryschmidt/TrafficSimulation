package myproject.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Iterator;
import myproject.util.Animator;
/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimatorBuilder, int, int)}.
 */

//Terry Schmidt, SE450 Final Project, Fall 2015

public class Model extends Observable {
	
	//private List<Agent> agents;
	private Animator animator;
	private boolean disposed;
	private double time;
	CarHolder[][] intersections;

	/** Creates a model to be visualized using the <code>builder</code>.
	 *  If the builder is null, no visualization is performed.
	 *  The number of <code>rows</code> and <code>columns</code>
	 *  indicate the number of {@link Light}s, organized as a 2D
	 *  matrix.  These are separated and surrounded by horizontal and
	 *  vertical {@link Road}s.  For example, calling the constructor with 1
	 *  row and 2 columns generates a model of the form:
	 *  <pre>
	 *     |  |
	 *   --@--@--
	 *     |  |
	 *  </pre>
	 *  where <code>@</code> is a {@link Light}, <code>|</code> is a
	 *  vertical {@link Road} and <code>--</code> is a horizontal {@link Road}.
	 *  Each road has one {@link Car}.
	 *
	 *  <p>
	 *  The {@link AnimatorBuilder} is used to set up an {@link
	 *  Animator}.
	 *  {@link AnimatorBuilder#getAnimator()} is registered as
	 *  an observer of this model.
	 *  <p>
	 */
	
	
	
	public Model(AnimatorBuilder builder, int rows, int columns) {
		if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
			throw new IllegalArgumentException();
		}
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		//this.agents = new ArrayList<Agent>();
		setup(builder, rows, columns);
		this.animator = builder.getAnimator();
		super.addObserver(animator);
		time = 0.0;
	}
	
	

	/**
	 * Run the simulation for <code>duration</code> model seconds.
	 */
	
	
	public void run(double duration) {
		if (disposed)
			throw new IllegalStateException();
		for (int i=0; i<duration; i++) {
			/*time++;
			// iterate through a copy because agents may change during iteration...
			for (Agent a : agents.toArray(new Agent[0])) {
				//a.run(time);
			}*/
			time = time + MP.getTimeStep();
			TaskKeeperImpl.getKeeper().runTask(MP.getTimeStep());
			super.setChanged();
			super.notifyObservers();
		}
		TaskKeeperImpl.getKeeper().clearTaskKeeper();
	} 

	/**
	 * Throw away this model.
	 */
	
	public void dispose() {
		animator.dispose();
		disposed = true;
	}

	/**
	 * Construct the model, establishing correspondences with the visualizer.
	 */
	
	
	private void setup(AnimatorBuilder builder, int rows, int columns) {
		intersections = new Light[rows][columns];

		// Add Lights
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				intersections[i][j] = ((Light) ObjFactory.newLightInstance());
				builder.addLight((Light) intersections[i][j], i, j);
				TaskKeeperImpl.getKeeper().addTask(0, (Agent) intersections[i][j]);
			}
		}

		// Add Horizontal Roads
		boolean eastToWest = false;
		for (int i=0; i<rows; i++) {
			List<CarHolder> roads = new ArrayList<CarHolder>();
			for (int j=0; j<=columns; j++) {
			//	Road l = new Road();
			//	builder.addHorizontalRoad(l, i, j, eastToWest);
			//	roads.add(l);
				
				CarHolder hRoad = ObjFactory.newHorizontalRoadInstance();
				roads.add(hRoad);
				builder.addHorizontalRoad((Road) hRoad, i, j, eastToWest);
				if (j < columns) {
					roads.add(intersections[i][j]);
				}
			}
			
			if (eastToWest == true) {
				Collections.reverse(roads);
			}
			
			Iterator<CarHolder> iterator = roads.iterator();
			CarHolder f = iterator.next();
			CarHolder fRoad = f;
			CarHolder nextE = null;
			
			while(iterator.hasNext() == true) {
				nextE = iterator.next();
				f.setNextHorizontalCarHolder(nextE);
				f = nextE;
			}
			
			CarHolder carGod = ObjFactory.newCarCreatorInstance();
			carGod.setNextHorizontalCarHolder(fRoad);
			TaskKeeperImpl.getKeeper().addTask(0, (Agent) carGod);
			CarHolder carEnder = ObjFactory.newCarDestroyerInstance();
			nextE.setNextHorizontalCarHolder(carEnder);
			
			if(MP.getTraffic().equals("alternating") == true) {
				eastToWest = !eastToWest;
			}
		}

		// Add Vertical Roads
		boolean southToNorth = false;
		for (int j=0; j<columns; j++) {
			List<CarHolder> roads = new ArrayList<CarHolder>();
			for (int i=0; i<=rows; i++) {
			//	Road l = new Road();
			//	builder.addVerticalRoad(l, i, j, southToNorth);
			//	roads.add(l);
				
				CarHolder vRoad = ObjFactory.newVerticalRoadInstance();
				roads.add(vRoad);
				builder.addVerticalRoad((Road) vRoad, i, j, southToNorth);
				if(i < rows) {
					roads.add(intersections[i][j]);
				}
			}
			
			if (southToNorth == true) {
				Collections.reverse(roads);
			}
				
			Iterator<CarHolder> iterator = roads.iterator();
			CarHolder f = iterator.next();
			CarHolder fRoad = f;
			CarHolder nextE = null;
				
			while (iterator.hasNext() == true) {
				nextE = iterator.next();
				f.setNextVerticalCarHolder(nextE);
				f = nextE;
			}
				
			CarHolder carGod = ObjFactory.newCarCreatorInstance();
			carGod.setNextVerticalCarHolder(fRoad);
			TaskKeeperImpl.getKeeper().addTask(0, (Agent) carGod);
				
			CarHolder carEnder = ObjFactory.newCarDestroyerInstance();
			nextE.setNextVerticalCarHolder(carEnder);
				
			if(MP.getTraffic().equals("alternating") == true) {
				southToNorth = !southToNorth;
			}
		}
	}
}

