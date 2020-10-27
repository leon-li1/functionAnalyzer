import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

//This class creates an interval chart for the user-defined function (INCOMPLETE)
public class IntervalChart extends JFrame implements ActionListener {

	//Instance fields
	private JPanel panel = new JPanel();
	private JLabel lblTitle = new JLabel();
	private JLabel lblAnimation = new JLabel();
	private JTextArea chart = new JTextArea();
	private JButton exitButton = new JButton();
	private Font font = new Font ("Euclid", Font.BOLD, 75);
	private Font font2 = new Font ("Euclid", Font.BOLD, 32);
	private Font font3 = new Font ("Euclid", Font.BOLD, 26);
	private ArrayList <Double> cp = new ArrayList <Double> ();
	private String temp;
	private double a, b, c, discriminant;

	public IntervalChart() {

		//Call other methods
		frameSetup();
		GUISetup();
		determineCP();
		printChart();
		repaint();
		
	}

	private void determineCP() {

		//Find the roots of f prime (using the quadratic formula)
		a = Results.smallFunction2[0].getLc();
		b = Results.smallFunction2[1].getLc();
		c = Results.constant2;

		//Calculate the discriminant
		discriminant = b * b - 4 * a * c;

		//If discriminant > 0, there are 2 real roots, if discrimant equals 0, there is 1 real root
		if (discriminant > 0) {

			cp.add((-b + Math.sqrt(discriminant))  / (2 * a));
			cp.add((-b - Math.sqrt(discriminant))  / (2 * a));

		}
		else if (discriminant == 0)

			cp.add(-b / (2 * a));

		//System.out.printf("%.4f\n", cp.get(0));
		//System.out.printf("%.4f\n", cp.get(1));

		//Solve f double prime to get the point of inflection
		a = Results.smallFunction3[0].getLc();
		b = Results.constant3;

		cp.add(-b / a);

		//System.out.printf("%.4f", cp.get(2));

	}

	private void printChart() {

		//Display header row
		System.out.printf("x\t\tf(x)\t\tf'(x)\t\tf''(x)\n");

		//Print another row for each critical point
		for (int r = 0; r < cp.size(); r++) {

			System.out.printf("%.4f\t\t+\t\t+\t\t+", cp.get(r));
			System.out.println();

		}

		//Update the chart text area
		if (cp.isEmpty())
			
			chart.setText("Critical points at x = 0");
		
		else {
			
			temp = "Critical points at x = ";
			
			//Loop through each critical point and add it to the temporary string
			for (int i = 0; i < cp.size(); i++)
				
				temp += cp.get(i) + " ";
			
			chart.setText(temp);
			
		}

	}

	private void frameSetup() {

		//Frame setup
		setTitle("Interval Chart");
		setSize(Results.FRAME_WIDTH, Results.FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		getContentPane().setBackground(new Color (35, 150, 230));

		//Panel setup
		panel.setBounds(0, 0, Results.FRAME_WIDTH - 5, Results.FRAME_HEIGHT - 28); 
		panel.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLUE));
		panel.setOpaque(false);
		panel.setLayout(null);
		getContentPane().add(panel);

	}

	private void GUISetup() {

		//Show the title
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.BLUE);
		lblTitle.setText("Interval Chart");
		lblTitle.setBounds(450, 25, 550, 80);
		panel.add(lblTitle);

		//Create the exitButton
		exitButton.setBounds(1120, 660, 105, 65);
		exitButton.setFont(font3);
		exitButton.setForeground(Color.BLUE);
		exitButton.setText("Back");
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(this);
		panel.add(exitButton);

		//Create the chart
		chart.setBounds(255, 430, 800, 250);
		chart.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
		chart.setFont(font2);
		chart.setLineWrap(true);
		chart.setWrapStyleWord(true);
		chart.setEditable(false);
		chart.setOpaque(false);
		chart.setForeground(Color.BLUE);
		panel.add(chart);

		//Create the animation label
		lblAnimation.setBounds(450, 170, 460, 230);
		lblAnimation.setIcon(new ImageIcon(new ImageIcon("Images/construction.png").getImage().getScaledInstance(460, 230, 0)));
		lblAnimation.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
		panel.add(lblAnimation);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		//Close current frame
		dispose();

	}
	
}
