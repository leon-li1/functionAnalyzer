import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

//This class is the "main screen" in the program, this is where the user can input a function
public class Results extends JFrame implements ActionListener {

	//Constants for the frame width and height
	public static final int FRAME_WIDTH = 1300;
	public static final int FRAME_HEIGHT = 800;
	
	//Static fields for the user-defined function
	public static Function[] smallFunction;
	public static Function[] smallFunction2;
	public static Function[] smallFunction3;
	public static double constant;
	public static double constant2;
	public static double constant3;

	//Instance fields
	private int degree;
	private String temp;
	private JPanel panel = new JPanel();
	private JLabel lblTitle = new JLabel();
	private JLabel lblAnimation = new JLabel();
	private JLabel[] lblFunction = new JLabel[3];
	private JButton[] optionButtons = new JButton[2];
	private JButton[] navigationButtons = new JButton[2];
	private Font font = new Font ("Euclid", Font.BOLD, 75);
	private Font font2 = new Font ("Euclid", Font.BOLD, 32);
	private Font font3 = new Font ("Euclid", Font.BOLD, 26);
	private Clip clip;

	public Results() {

		//Call other methods
		frameSetup();
		GUISetup();
		repaint();
		
		//Play the "calculating" sound
		try {

			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("Sounds/loading.wav")));
			clip.start();

		} catch (Exception e) {

			System.out.println(e);

		}
		
		//Show instructions
		JOptionPane.showMessageDialog(this, "Welcome to Leon's function analyzer program!\nTo begin, click the \"Enter Function\" button to input your desired function.\nThen, you can click the other 2 buttons on the right to either sketch the function and its derivatives or to create an interval chart.\nDisclaimer: the interval chart feature is not currently finished due to time constraints.\nEnjoy!");

	}

	private void getFunction() {

		//Initialize the degree from the user
		degree = Integer.parseInt(JOptionPane.showInputDialog(this, "What is the degree of the function?"));

		//Initialize array length
		smallFunction = new Function[degree];

		//Create small functions with user input
		for (int i = 0; i < smallFunction.length; i++)

			smallFunction[i] = new Function(Double.parseDouble(JOptionPane.showInputDialog(this, "What is the coefficient of term x^" + (degree - i) + "?")), degree - i);

		//Create the constant
		constant = Double.parseDouble(JOptionPane.showInputDialog(this, "What is the constant?"));

		//Initialize the temporary string
		temp = " f(x) = ";

		//Add each small function to the temporary string
		for (int i = 0; i < smallFunction.length; i++)

			if (smallFunction[i].getLc() != 0)

				temp += smallFunction[i].toString();

		//Add the constant to the temporary string
		if (constant != 0)

			temp += String.format("%+.0f", constant);

		//Update the respective function label
		lblFunction[0].setText(temp.substring(0, 8) + temp.substring(9));

		//Call the find derivative method
		findDerivative();

	}

	private void findDerivative() {

		//Initialize array length
		smallFunction2 = new Function[degree - 1];

		//Create small functions with the derivatives of the original
		for (int i = 0; i < smallFunction2.length; i++)

			smallFunction2[i] = new Function(smallFunction[i].getLc() * smallFunction[i].getExp(), smallFunction[i].getExp() - 1);

		//Create the constant
		constant2 = smallFunction[degree - 1].getLc();

		//Initialize the temporary string
		temp = " f'(x) = ";

		//Add each small function to the temporary string
		for (int i = 0; i < smallFunction2.length; i++)

			if (smallFunction2[i].getLc() != 0)

				temp += smallFunction2[i].toString();

		//Add the constant to the temporary string
		if (constant2 != 0)

			temp += String.format("%+.0f", constant2);

		//Update the respective function label
		lblFunction[1].setText(temp.substring(0, 9) + temp.substring(10));

		//Call the find second derivative method
		findDerivative2();

	}

	private void findDerivative2() {

		//Initialize array length
		smallFunction3 = new Function[degree - 2];

		//Create small functions with the derivatives of the original
		for (int i = 0; i < smallFunction3.length; i++)

			smallFunction3[i] = new Function(smallFunction2[i].getLc() * smallFunction2[i].getExp(), smallFunction2[i].getExp() - 1);

		//Create the constant
		constant3 = smallFunction2[degree - 2].getLc();

		//Initialize the temporary string
		temp = " f''(x) = ";

		//Add each small function to the temporary string
		for (int i = 0; i < smallFunction3.length; i++)

			if (smallFunction3[i].getLc() != 0)

				temp += smallFunction3[i].toString();

		//Add the constant to the temporary string
		if (constant3 != 0)

			temp += String.format("%+.0f", constant3);

		//Update the respective function label
		lblFunction[2].setText(temp.substring(0, 10) + temp.substring(11));

	}

	private void frameSetup() {

		//Frame setup
		setTitle("Start Screen");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color (35, 150, 230));
		setVisible(true);

		//Panel setup
		panel.setBounds(0, 0, FRAME_WIDTH - 5, FRAME_HEIGHT - 28); 
		panel.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLUE));
		panel.setOpaque(false);
		panel.setLayout(null);
		getContentPane().add(panel);

	}

	private void GUISetup() {

		//Show the title
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setText("Results");
		lblTitle.setBounds(550, 25, 300, 80);
		panel.add(lblTitle);

		//Create function labels
		for (int i = 0; i < lblFunction.length; i++) {

			lblFunction[i] = new JLabel();
			lblFunction[i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
			lblFunction[i].setBounds(75, 390 + i * 100, 880, 55);
			lblFunction[i].setForeground(Color.BLUE);
			lblFunction[i].setFont(font2);
			panel.add(lblFunction[i]);

		}

		//Create option buttons
		for (int i = 0; i < optionButtons.length; i++) {

			optionButtons[i] = new JButton();
			optionButtons[i].setBounds(1060, 420 + i * 125, 165, 70);
			optionButtons[i].setForeground(Color.BLUE);
			optionButtons[i].setFont(font2);
			optionButtons[i].setFocusPainted(false);
			optionButtons[i].addActionListener(this);
			panel.add(optionButtons[i]);

		}

		//Create navigation buttons
		for (int i = 0; i < navigationButtons.length; i++) {

			navigationButtons[i] = new JButton();
			navigationButtons[i].setBounds(120 + i * 750, 710, 250, 40);
			navigationButtons[i].setForeground(Color.BLUE);
			navigationButtons[i].setFont(font3);
			navigationButtons[i].setFocusPainted(false);
			navigationButtons[i].addActionListener(this);
			panel.add(navigationButtons[i]);

		}

		//Create the animation label
		lblAnimation.setBounds(380, 130, 580, 230);
		lblAnimation.setIcon(new ImageIcon(new ImageIcon("Images/graph2.png").getImage().getScaledInstance(580, 230, 0)));
		lblAnimation.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
		panel.add(lblAnimation);

		//Add text to GUI objects
		lblFunction[0].setText(" f(x) =");
		lblFunction[1].setText(" f'(x) =");
		lblFunction[2].setText(" f''(x) =");
		optionButtons[0].setText("Sketch!");
		optionButtons[1].setText("Chart!");
		navigationButtons[0].setText("Enter Function");
		navigationButtons[1].setText("Finish");

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//If enter function button is pressed
		if (e.getSource() == navigationButtons[0])

			//Call the get function method
			getFunction();

		//If the exit button is pressed
		else if (e.getSource() == navigationButtons[1])

			//Exit the program
			System.exit(0);

		//If the sketch button is pressed
		else if (e.getSource() == optionButtons[0])

			//Call the labels class
			new Labels();

		//If the chart button is pressed
		else if (e.getSource() == optionButtons[1])

			//Call the interval chart class
			new IntervalChart();


	}

}
