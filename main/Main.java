package myproject.main;

import myproject.ui.UI;
import myproject.ui.UIFactory;

/**
 * A static class to demonstrate the visualization aspect of
 * simulation.
 */

// Terry Schmidt, SE450 Final Project, Fall 2015

public class Main {
	private Main() {}
	public static void main(String[] args) {
		// Terry defined main code:
		UI initialUI = UIFactory.popupUI();
		Control controller = new Control(initialUI);
		controller.run();
		
		
		// original main code:
		/*{
			Model m = new Model(new TextAnimatorBuilder(), 0, 1);
			m.run(10);
			m.dispose();
		}
		{
			Model m = new Model(new SwingAnimatorBuilder(), 0, 1);
			m.run(10);
			m.dispose();
		}
		{
			Model m = new Model(new TextAnimatorBuilder(), 1, 1);
			m.run(10);
			m.dispose();
		}
		{
			Model m = new Model(new SwingAnimatorBuilder(), 1, 1);
			m.run(10);
			m.dispose();
		}
		{
			Model m = new Model(new SwingAnimatorBuilder(), 2, 3);
			m.run(500);
			m.run(500);
			m.dispose();
		}
		
		System.exit(0);*/
	}
}