import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

//This class sketches out the user-defined function and its first and second derivative
public class SketchFunctions extends JFrame {

	//Instance fields
	private int drawPhase;
	private boolean doneDrawing;
	private double x, y, ax, by, n;

	public SketchFunctions() {

		//Frame Setup
		setTitle("Graph");
		setSize(Results.FRAME_WIDTH, Results.FRAME_HEIGHT);
		setLocation(0, 50);
		setResizable(false);
		setVisible(true);
		getContentPane().setBackground(new Color (35, 150, 230));

		//Initialize x and y
		x = 60;
		y = 400;

	}

	public void paint (Graphics g) {

		//Y-Axis
		g.drawLine(600, 0, 600, 800);

		//X-Axis
		g.drawLine(60, 400, 1200, 400);

		//		//Label points on Y-Axis
		//		g.drawString("2", 605, 200);
		//		g.drawString("1", 605, 300);
		//		g.drawString("1", 605, 500);
		//		g.drawString("2", 605, 600);
		//
		//		//Label points on X-Axis
		//		g.drawString("2", 400, 412);
		//		g.drawString("1", 500, 412);
		//		g.drawString("1", 700, 412);
		//		g.drawString("2", 800, 412);

		//Depending on the draw phase, change the draw colour and call the respective graph function
		if (drawPhase == 0) {

			g.setColor(Color.RED);
			graph();

		}
		else if (drawPhase == 1) {

			g.setColor(Color.BLUE);
			graph2();

		}
		else {

			g.setColor(Color.GREEN);
			graph3();

		}

		//Draw a filled oval at the calculated point
		g.fillOval((int) Math.round(x), (int) Math.round(y), 2, 2);


		//recall this method, if not done drawing
		if (!doneDrawing)

			repaint();

	}

	public void graph() {

		//Check if x is less than 1200
		if (x < 1200) {

			//Scale x and y down for calculations
			ax = x - 600;
			by = y - 400;

			//Initialize n to 0
			n = 0;

			//Loop through each term in the respective function 
			for (int t = 0; t < Results.smallFunction.length; t++) 

				//Accumulate the sum of the terms into n, stretch the graph out by multiplying the x by 0.02
				n += Results.smallFunction[t].getLc() * Math.pow(0.02 * ax, Results.smallFunction[t].getExp());

			//Set by to n + the constant term
			by = n + Results.constant;

			//Scale x  and y back, vertically compress the graph
			x = ax + 600;
			y = 0.97 * 400 - by;

			//Increment x by 0.2
			x += 0.2;

		}
		//If x is over 1200
		else {

			//Reset the x and y coordinate and increment the draw phase
			x = 60;
			y = 400;
			drawPhase++;

		}

	}

	public void graph2() {

		//Check if x is less than 1200
		if (x < 1200) {

			//Scale x and y down for calculations
			ax = x - 600;
			by = y - 400;

			//Initialize n to 0
			n = 0;

			//Loop through each term in the respective function 
			for (int t = 0; t < Results.smallFunction2.length; t++) 

				//Accumulate the sum of the terms into n, stretch the graph out by multiplying the x by 0.02
				n += Results.smallFunction2[t].getLc() * Math.pow(0.02 * ax, Results.smallFunction2[t].getExp());

			//Set by to n + the constant term
			by = n + Results.constant2;

			//Scale x  and y back, vertically compress the graph
			x = ax + 600;
			y = 0.96 * 400 - by;

			//Increment x by 0.2
			x += 0.2;

		}
		//If x is over 1200
		else {

			//Reset the x and y coordinate and increment the draw phase
			x = 60;
			y = 400;
			drawPhase++;

		}

	}

	public void graph3() {

		//Check if x is less than 1200
		if (x < 1200) {

			//Scale x and y down for calculations
			ax = x - 600;
			by = y - 400;

			//Initialize n to 0
			n = 0;

			//Loop through each term in the respective function 
			for (int t = 0; t < Results.smallFunction3.length; t++) 

				//Accumulate the sum of the terms into n, stretch the graph out by multiplying the x by 0.02
				n += Results.smallFunction3[t].getLc() * Math.pow(0.02 * ax, Results.smallFunction3[t].getExp());

			//Set by to n + the constant term
			by = n + Results.constant3;

			//Scale x  and y back, vertically compress the graph
			x = ax + 600;
			y = 0.95 * 400 - by;

			//Increment x by 0.2
			x += 0.2;

		}
		//If x is over 1200
		else 

			//Set done drawing to true
			doneDrawing = true;

		}
		
	}