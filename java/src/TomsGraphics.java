/**
 * File:	TomsGraphics.java
 * 
 * Author:		Date:			Version:		Changes:
 * Tom White	7 March 1997	Version 0.0		Basic applet.
 * "			12 March 1997	Version 0.1		Threads added, mouse events.
 *             
 * The drawParabola method draws a parabola through the three points specified.
 *
 *
**/


import java.awt.*;

public class TomsGraphics {
	
	Graphics m_g;

	public TomsGraphics (Graphics g) {

	m_g = g;

	}

	public void drawParabola (int x1,int y1,int x2,int y2,int x3,int y3) {

		int x,y,oldx,oldy;
		float denom,a,b,c;

		oldx=x1;
		oldy=y1;
		denom=x1*x1*(x2-x3)+x1*(x3*x3-x2*x2)+x2*x2*x3-x3*x3*x2;
		a=(y1*(x2-x3)+x1*(y3-y2)+y2*x3-y3*x2)/denom;
		b=(x1*x1*(y2-y3)+y1*(x3*x3-x2*x2)+x2*x2*y3-x3*x3*y2)/denom;
		c=(x1*x1*(x2*y3-x3*y2)+x1*(x3*x3*y2-x2*x2*y3)+y1*(x2*x2*x3-x3*x3*x2))/denom;
		
		for (x=x1;x<=x2;++x) {
			y = (int)(a*x*x+b*x+c);
			m_g.drawLine(oldx,oldy,x,y);
			oldx=x;
			oldy=y;
		}
	}
}



