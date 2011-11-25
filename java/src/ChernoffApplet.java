
/**
 * File:	ChernoffApplet.java
 * 
 * Author:		Date:			Version:		Changes:
 * Tom White	7 March 1997	Version 0.0		Basic applet.
 * "			12 March 1997	Version 0.1		Threads added, mouse events.
 *             
 * Chernoff faces are cartoon faces with parameters which control
 * facial characteristics such as nose width and head eccentricity.
 * This program is based on Pickover (1990), 'Computers, Pattern, 
 * Chaos, and Beauty', pp47-55, and Appendix B. They have potential in
 * summarising large dimensions of information in a sketch of a face.
 * 
 * In order of decreasing visual impact	(roughly), the parameters are:
 * p[0]	eyebrow slant	
 * p[1]	eye shape
 * p[2]	mouth straightness
 * p[3]	eye spacing
 * p[4]	eye size
 * p[5]	mouth width
 * p[6]	head shape
 * p[7]	lip height
 * p[8]	nose length
 * p[9]	pupil size
 *
 * These parameters are passed from the HTML page by means of a comma
 * delimited list called "p":
 * 
 * <APPLET CODE=ChernoffApplet.class WIDTH=100 HEIGHT=100>
 * <PARAM NAME="p" VALUE="5,5,5,5,5,5,5,5,5,5">
 * </APPLET>
 *
 * To do:  Add gizmos: e.g. widgets to control parameters in the applet.
 *		   Document.
 *		   Coloured pupils.
**/


import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.util.StringTokenizer;

import Face;

public class ChernoffApplet extends Applet implements Runnable{
	int p[] = new int[11];	// p[] holds ten facial parameters, range 1 to 10.
	boolean regen = false;
	Face myface;
	Thread mainthread=null;
	 
	//Thread methods
	
	public void run() {
				
		while (true) {
			if (regen == true) {	// only regenerate if the mouse button's been pressed
				Random r = new Random();
				for (int i=0;i<10;i++) {
					p[i] = (int)(r.nextFloat()*10+1);
				}
				repaint();
				regen = false;
			}
			try {
				Thread.sleep(50);
			}	catch (java.lang.InterruptedException e) {}
		}
	}


	//Applet methods

	public void start() {
		if (mainthread == null) {
			mainthread = new Thread(this);
			mainthread.start();
		}
	}
	
	public void stop() {
		if (mainthread != null) {
			mainthread.stop();
			mainthread=null;
		}
	}
	 	
	public void init()	{
		resize(100,100);
		setBackground(Color.white);

		Random r = new Random();
		for (int i=0;i<10;i++) {	//Fill p[] with (default) random integers between 1 and 10
			p[i] = (int)(r.nextFloat()*10+1);
		}
		String pString  = getParameter("p");	//Get the p string from the HTML page
		if (pString != null) {	

			StringTokenizer t = new StringTokenizer(pString, ",");
			int ct = t.countTokens();

			for (int i=0;i<ct;i++) {	//Fill p[] with the p string parameters (if any)
				String temp = t.nextToken();
				try {
					  p[i] = Integer.parseInt(temp);
					} catch (NumberFormatException e) {
                           //Use default (random) parameter.
                     }

			}
		}

		

	}

	public void paint(Graphics g) {
		myface = new Face(p);
		myface.drawFace(g);
	}

	public boolean mouseDown(Event e,int x,int y) {
		regen = true;
		return true;
	}
	
}