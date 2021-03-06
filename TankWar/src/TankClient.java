
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class TankClient extends Frame {
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	Tank myTank = new Tank(50, 50, true, this);
	Tank enemyTank = new Tank(100, 100, false, this);
	
	Explode e = new Explode(70,70,this);
	
	List<Missile> missiles = new ArrayList<Missile>();
	
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
		g.drawString("missile count:"+missiles.size(), 10, 50);
		
		for(int i = 0; i < missiles.size(); i++) {
			Missile m = missiles.get(i);
			m.hitTank(enemyTank);
			if(!m.isLive()) missiles.remove(m);
			else m.draw(g);
		}
		e.draw(g);
		
		myTank.draw(g);	
		enemyTank.draw(g);
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
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private class KeyMonitor extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			myTank.keyReleased(e);
			
		}

		public void keyPressed (KeyEvent e) {
			myTank.keyPressed(e);
		}
	}
}
