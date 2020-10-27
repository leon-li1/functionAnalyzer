/*
 * Name: Leon Li
 * Date: January 21, 2018
 * Course code: ICS4U1
 * Title: Function Analyzer
 * Description: A program that takes in a user-defined function and determines its first and second derivative,
 * sketches them and creates an interval chart for them.
 * Features: Splash screen, arrays and array lists, class objects, finding derivatives,
 * graphics (sketching functions), solving equations (using the quadratic formula) and sound.
 * Areas of concern: Due to time constraints (learning how to use graphics in Java took way longer than expected), I was not able to finish the
 * Interval chart class. Currently it only solves for the critical points when a user enters a 3rd degree polynomial.
 * I would also like to improve the overall aesthetics of the program.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoadingScreen extends JWindow implements ActionListener {

	//Constant
	private final int MAX = 500;

	//Instance fields
	private JPanel panel = new JPanel();
	private JProgressBar loadingBar = new  JProgressBar();
	private Timer progressBarTimer = new Timer(10, this);
	private int count;

	//Main method
	public static void main(String[] args) {

		//Runs the constructor method
		new LoadingScreen();

	}

	//Constructor method
	public LoadingScreen() {

		//Calls the set up method
		objectsSetup();

		//Start the progress bar timer
		progressBarTimer.start();
	}

	//This method sets up the objects in the class
	private void objectsSetup() {

		//Panel setup
		panel.setLayout(new BorderLayout()); 
		JLabel splashImage = new JLabel(new ImageIcon("Images/derivative.gif"));
		splashImage.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		panel.add(splashImage); 

		//Progress bar set up
		loadingBar.setMaximum(MAX);
		loadingBar.setForeground(new Color(0, 0, 255));
		loadingBar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
		panel.add(loadingBar, BorderLayout.SOUTH);

		//Frame setup
		setContentPane(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	//This method implements the action listener
	public void actionPerformed(ActionEvent event) {

		//Checks if the source was the game start button
		if (event.getSource() == progressBarTimer) {

			//Set the value of the progress bar to count
			loadingBar.setValue(count);

			//Checks if the count is equal to the max
			if (count == MAX) {

				//Close the current window, stop the timers and call the results class
				dispose();
				progressBarTimer.stop();
				new Results();

			}

			//Increase count by 2
			count += 2;
			
		}

	}

}
