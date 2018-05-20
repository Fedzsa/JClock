import java.awt.Dimension;

import javax.swing.JFrame;


public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	private Panel p;
	private Thread t;
	
	public Gui() {
		p = new Panel();
		t = new Thread(p);
		
		add(p);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		setLayout(null);
		setVisible(true);
		
		t.start();
	}
}
