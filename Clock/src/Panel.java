import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable {
	final int circleX;
	final int circleY;
	int x = 0, y = 0;
	int x2 = 0, y2 = 0;
	int secondx = 0, secondy = 0;
	int minutex = 0, minutey = 0;
	int hourx = 0, houry = 0;
	Date date;
	LocalTime t;
	SimpleDateFormat f;
	SimpleDateFormat f2;
	String time;
	String d;
	
	
	public Panel() {
		t = LocalTime.now();
		date = new Date();
		f = new SimpleDateFormat("HH:mm:ss a");
		f2 = new SimpleDateFormat("yyyy.MM.dd");
		circleX = 200;
		circleY = 200;
		time = f.format(date);
		d = f2.format(date);
		
		analogTime();
		
		//setBackground(Color.BLUE);
		setBounds(0, 0, 400, 400);
		setLayout(null);
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i = 0; i <= 360; i += 6) {
			elforgat(i);
			if(i % 30 == 0) {
				g.setColor(Color.BLACK);
				if(i != 0) {
					g.drawString(String.valueOf(i / 30), x2, y2);
				}
				
			} else {
				g.setColor(Color.RED);
				g.drawLine(x2, y2, x2, y2);
			}
		}
		
		g.drawString(d, 180, 260);
		g.drawString(time, 180, 280);
		
		g.setColor(Color.RED);
		g.drawLine(circleX, circleY, hourx, houry);
		g.setColor(Color.GREEN);
		g.drawLine(circleX, circleY, minutex, minutey);
		g.setColor(Color.BLACK);
		g.drawLine(circleX, circleY, secondx, secondy);
	}
	
	public void elforgat(int i) {
		x2 = (int)((circleX) + (150 * (Math.sin(i * (Math.PI / 180)))));
		y2 = (int)((circleY) - (150 * (Math.cos(i * (Math.PI / 180)))));
	}
	
	public void elforgatM(int s, int m, int h) {		
		secondx = (int)((circleX) + (140 * (Math.sin(s * (Math.PI / 180)))));
		secondy = (int)((circleY) - (140 * (Math.cos(s * (Math.PI / 180)))));
		minutex = (int)((circleX) + (140 * (Math.sin(m * (Math.PI / 180)))));
		minutey = (int)((circleY) - (140 * (Math.cos(m * (Math.PI / 180)))));
		hourx = (int)((circleX) + (80 * (Math.sin(h * (Math.PI / 180)))));
		houry = (int)((circleY) - (80 * (Math.cos(h * (Math.PI / 180)))));
	}
	
	public synchronized void analogTime() {
		date = new Date();
		t = LocalTime.now();
		time = f.format(date);
		d = f2.format(date);
		
		if(t.getMinute() >= 0 && t.getMinute() <= 12) {
			elforgatM(6 * t.getSecond(), 6 * t.getMinute(), 30 * date.getHours());
		} else if(t.getMinute() > 12 && t.getMinute() <= 24) {
			elforgatM(6 * t.getSecond(), 6 * t.getMinute(), (30 * date.getHours()) + 6);
		} else if(t.getMinute() > 24 && t.getMinute() <= 36) {
			elforgatM(6 * t.getSecond(), 6 * t.getMinute(), (30 * date.getHours()) + 12);
		} else if(t.getMinute() > 36 && t.getMinute() <= 48) {
			elforgatM(6 * t.getSecond(), 6 * t.getMinute(), (30 * date.getHours()) + 18);
		} else if(t.getMinute() > 48 && t.getMinute() <= 59) {
			elforgatM(6 * t.getSecond(), 6 * t.getMinute(), (30 * date.getHours()) + 24);
		}
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				
				analogTime();
				
				repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
