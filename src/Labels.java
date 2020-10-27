import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

//This class is a side screen with labels that calls the sketch functions class
public class Labels extends JFrame implements ActionListener{

	//Instance fields
	private SketchFunctions sketch = new SketchFunctions();
	private JPanel panel = new JPanel();
	private JLabel[] lblFunction = new JLabel[3];
	private JButton exitButton = new JButton();
	private Font font = new Font ("Euclid", Font.BOLD, 26);

	public Labels() {

		//Call other methods
		frameSetup();
		GUISetup();
		repaint();

	}

	private void GUISetup() {

		//Create function labels
		for (int i = 0; i < lblFunction.length; i++) {

			lblFunction[i] = new JLabel();
			lblFunction[i].setBounds(10, 50 + i * 75, 105, 55);
			lblFunction[i].setFont(font);
			lblFunction[i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLUE));
			panel.add(lblFunction[i]);

		}

		//Set text for labels
		lblFunction[0].setForeground(Color.RED);
		lblFunction[0].setText(" f(x)");
		lblFunction[1].setForeground(Color.BLUE);
		lblFunction[1].setText(" f'(x)");
		lblFunction[2].setForeground(Color.GREEN);
		lblFunction[2].setText(" f''(x)");

		//Create exit button
		exitButton.setBounds(10, 620, 105, 55);
		exitButton.setFont(font);
		exitButton.setText("Back");
		exitButton.setFocusPainted(false);
		exitButton.addActionListener(this);
		panel.add(exitButton);

	}

	private void frameSetup() {

		//Frame setup
		setTitle("Labels");
		setSize(85, Results.FRAME_HEIGHT);
		setLocation(Results.FRAME_WIDTH + 5, 50);
		setResizable(false);
		getContentPane().setBackground(new Color (35, 150, 230));
		setVisible(true);

		//Panel setup
		panel.setBounds(0, 0, Results.FRAME_WIDTH - 5, Results.FRAME_HEIGHT - 28); 
		panel.setBorder(new MatteBorder(5, 5, 5, 5, Color.BLUE));
		panel.setOpaque(false);
		panel.setLayout(null);
		getContentPane().add(panel);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		//Close the sketching frame and this frame
		sketch.dispose();
		dispose();

	}

}
