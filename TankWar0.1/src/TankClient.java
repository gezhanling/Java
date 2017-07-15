import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {

	int x= 50,y= 50;
	public void lauchFrame() {
		this.setLocation(200,100);
		this.setSize(800,600);
		this.setBackground(Color.GREEN);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		setVisible(true);
		new Thread(new PaintThread()).start();
	}
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x,y,30,30);
		g.setColor(c);
		y= y+5;
	}
	public static void main(String[] args) {
	TankClient tc = new TankClient();
	tc.lauchFrame();                 
	}
	
	private class PaintThread implements Runnable{
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
