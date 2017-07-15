import java.awt.*;
import java.awt.event.*;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	int x= 50,y= 50;
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
		Color c = g.getColor();
		g.setColor(Color.red);
		g.fillOval(x,y,30,30);
		g.setColor(c);
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
			int key = e.getKeyCode();
		    switch(key) {
		    case KeyEvent.VK_LEFT:
		    	x -= 5;
		    	break;
		    case KeyEvent.VK_RIGHT:
		    	x += 5;
		    	break;
		    case KeyEvent.VK_UP:
		    	y -= 5;
		    	break;
		    case KeyEvent.VK_DOWN:
		    	y += 5;
		    	break;
		    }
		}
	}
}
