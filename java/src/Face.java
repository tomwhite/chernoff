/**
 * File:	Face.java
 * 
 * Author:		Date:			Version:		Changes:
 * Tom White	7 March 1997	Version 0.0		Basic applet.
 * "			12 March 1997	Version 0.1		Threads added, mouse events.
 *             
 * The Face class constructs facial measurements, from the raw p[] parameters.
 * The drawFace(Graphics g) method draws a Face instance in the Graphics context g.
 *
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
**/


import java.awt.*;

import TomsGraphics;

class Face {

	int head_x,head_y,head_widthx,head_widthy;
	
	int eyebrow1_x[] = new int[2];
	int eyebrow1_y[] = new int[2];
	int eyebrow2_x[] = new int[2];
	int eyebrow2_y[] = new int[2];

	int nose_x[] = new int[3];
	int nose_y[] = new int[3];

	int mouth_x[] = new int[4];
	int mouth_y[] = new int[4];

	int eye1_x,eye1_y,eye2_x,eye2_y,eye_widthx,eye_widthy;

	int pupil1_x,pupil1_y,pupil2_x,pupil2_y,pupil_radius;

	public Face (int p[]) {

		//Head outline
		int head_eccenx,head_ecceny;
		if (p[6]>=5) {
			head_eccenx = 2*(p[6]-5);
			head_ecceny = 0;
		}
		else {
			head_eccenx = 0;
			head_ecceny = 2*(5-p[6]);
		}
		head_x = 20-head_eccenx;
		head_y = 20-head_ecceny;
		head_widthx = 60+2*head_eccenx;
		head_widthy = 60+2*head_ecceny;

		//Eyebrows
		eyebrow1_x[0] = 35;
		eyebrow1_x[1] = 45;
		eyebrow1_y[0] = 35-p[0];
		eyebrow1_y[1] = 25+p[0];
		eyebrow2_x[0] = 55;
		eyebrow2_x[1] = 65;
		eyebrow2_y[0] = 25+p[0];
		eyebrow2_y[1] = 35-p[0];

		//Nose
		nose_x[0] = 46;
		nose_x[1] = 50;
		nose_x[2] = 54;
		nose_y[0] = nose_y[2] = 55-(int)(p[8]/2);
		nose_y[1] = 45;

		//Mouth
		mouth_x[0] = 45-p[5];
		mouth_x[1] = 55+p[5];
		mouth_x[2] = mouth_x[3] = 50;
		mouth_y[0] = mouth_y[1] =65;
		mouth_y[2] = 70-p[2];
		mouth_y[3] = 70-p[2]-(int)(p[7]/2);

		//Eyes
		int eye_eccenx,eye_ecceny,eye_size;
		if (p[1]>=5) {
			eye_eccenx = 2*(p[1]-5);
			eye_ecceny = 0;
		}
		else {
			eye_eccenx = 0;
			eye_ecceny = 2*(5-p[1]);
		}
		eye_size =(int) ((p[4]-5)/2);
		eye_widthx = 2*(5+eye_size+eye_eccenx);
		eye_widthy = 2*(5+eye_size+eye_ecceny);
		eye1_x = 40-p[3]-eye_size-eye_eccenx;
		eye1_y = 35-eye_size-eye_ecceny;
		eye2_x = 50+p[3]-eye_size-eye_eccenx;
		eye2_y = 35-eye_size-eye_ecceny;

		//Pupils
		pupil_radius=(int)(p[9]/5+1);
		pupil1_x = 45-p[3]-pupil_radius;
		pupil1_y = 40-pupil_radius;
		pupil2_x = 55+p[3]-pupil_radius;
		pupil2_y = 40-pupil_radius;
	}

	public void drawFace (Graphics g) {
		//Head outline
		g.drawOval(head_x,head_y,head_widthx,head_widthy);
		
		//Eyebrows
		g.drawLine(eyebrow1_x[0],eyebrow1_y[0],eyebrow1_x[1],eyebrow1_y[1]);
		g.drawLine(eyebrow2_x[0],eyebrow2_y[0],eyebrow2_x[1],eyebrow2_y[1]);

		//Nose
/*		g.drawPolygon(nose_x,nose_y,3); //Does not give the same result!
 */
		g.drawLine(nose_x[0],nose_y[0],nose_x[1],nose_y[1]);
		g.drawLine(nose_x[1],nose_y[1],nose_x[2],nose_y[2]);
		g.drawLine(nose_x[2],nose_y[2],nose_x[0],nose_y[0]);

		//Eyes
		g.drawOval(eye1_x,eye1_y,eye_widthx,eye_widthy);
		g.drawOval(eye2_x,eye2_y,eye_widthx,eye_widthy);

		//Pupils
		if (pupil_radius>1) {
			g.fillOval(pupil1_x,pupil1_y,2*pupil_radius,2*pupil_radius);
			g.fillOval(pupil2_x,pupil2_y,2*pupil_radius,2*pupil_radius);
		}
		else {
			g.drawOval(pupil1_x,pupil1_y,2*pupil_radius,2*pupil_radius);
			g.drawOval(pupil2_x,pupil2_y,2*pupil_radius,2*pupil_radius);
		}

		//Mouth
		TomsGraphics tg = new TomsGraphics (g);
		tg.drawParabola(mouth_x[0],mouth_y[0],mouth_x[1],mouth_y[1],mouth_x[2],mouth_y[2]);
		tg.drawParabola(mouth_x[0],mouth_y[0],mouth_x[1],mouth_y[1],mouth_x[3],mouth_y[3]);

	}
}

