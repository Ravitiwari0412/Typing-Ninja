package May10Game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class typingtutor extends JFrame implements ActionListener {
	JLabel lbltimer;
	JLabel lblscore;
	JLabel lblword;
	JTextArea textword;
	JButton btnstart;
	JButton btnstop;

	int score;
	int timeLeft;
	boolean isRunning;
	Timer timer;
	String[] data;

	public typingtutor(String feeder) {
		data = feeder.split(" ");
		super.setTitle("Typing Ninja");
		super.setSize(1000, 1000);
		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);
		lbltimer = new JLabel("");
		this.addComponent(lbltimer);

		lblscore = new JLabel("");
		this.addComponent(lblscore);

		lblword = new JLabel("");
		this.addComponent(lblword);

		textword = new JTextArea();
		this.addComponent(textword);

		btnstart = new JButton("Start");
		btnstart.addActionListener(this);
		this.addComponent(btnstart);

		btnstop = new JButton("Stop");
		btnstop.addActionListener(this);
		this.addComponent(btnstop);

		initGame();
		super.setVisible(true);

	}

	private void addComponent(JComponent comp) {
		Font font = new Font("Comic Sans MS", 1, 100);
		comp.setFont(font);
		super.add(comp);

	}

	private void initGame() {
		timer = new Timer(2000, this);
		score = 0;
		timeLeft = 50;
		isRunning = false;

		lbltimer.setText("Timer:" + timeLeft);
		lblscore.setText("Score" + score);
		lblword.setText("");
		textword.setText("");
		
		btnstart.setText("Start");
		btnstop.setText("stop");

		btnstop.setEnabled(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnstart) {
			handleStart();

		} else if (e.getSource() == btnstop) {
			handleStop();

		} else if (e.getSource() == timer) {
			handleTimer();
		}

		// TODO Auto-generated method stub

	}

	private void handleStart() {
		if (isRunning) {
			isRunning = false;
			timer.stop();
			btnstart.setText("resume");

		} else {

			timer.start();
			isRunning = true;
			btnstart.setText("pause");
			btnstop.setEnabled(true);
		}

	}

	private void handleStop() {
		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Game over. Restart?");
		if (choice == JOptionPane.YES_OPTION) {
			initGame();
		} else {
			super.dispose();
		}

	}

	private void handleTimer() {
		if (timeLeft > 0) {

			String expected = lblword.getText();
			String actual = textword.getText();
			if (timeLeft != 50 && expected.equals(actual)) {
				score++;
				lblscore.setText("Score:" + score);

			}
			timeLeft--;
			lbltimer.setText("Timer:" + timeLeft);
			int idx = (int) ((int) data.length * Math.random());

			lblword.setText(data[idx]);

			textword.setText("");
			textword.setFocusable(true);

		}

		else {
			this.handleStop();
		}

	}
}
