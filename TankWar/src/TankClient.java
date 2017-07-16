
import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	Tank myTank = new Tank(50, 50);
	public void lauchFrame() {
		this.setLocation(200,100);
		this.setSize(GAME_WIDTH,GAME_HEIGHT);
		this.setBackground(Color.GREEN);
		this.setTitle("TankWar");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		setVisible(true);
		new Thread(new PaintThread()).start();
	}
	public void paint(Graphics g) {
		myTank.draw(g);
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
	private class KeyMonitor extends KeyAdapter{
		public void keyPressed (KeyEvent e) {
			myTank.keyPressed(e);
		}
	}
}
