package game.view;

import game.view.panels.WelcomeScreen;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

import game.controller.Features;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WorldView extends JFrame implements View {
	
	private static final long serialVersionUID = 1L;
	private WelcomeScreen ws;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem1;
	private JMenuItem menuItem2;
	private JMenuItem menuItem3;

	public WorldView(String caption) {
		if(caption==null||"".equals(caption.trim())) {
			throw new IllegalArgumentException("Caption shouldn't be empty");
		}
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menuBar.add(menu);
		menuItem1=new JMenuItem("New Game");
		menu.add(menuItem1);
		menuItem3=new JMenuItem("Upload file");
		menu.add(menuItem3);
		menuItem2=new JMenuItem("Quit Game");
		menu.add(menuItem2);
		this.setJMenuBar(menuBar);
		ws=new WelcomeScreen();
		this.add(ws);
		this.setSize(500, 500);
	    this.setLocation(200, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.pack();
	    this.setVisible(true);
	}
	@Override
	public void setFeatures(Features f) {
		if(f==null) {
			throw new IllegalArgumentException("Features object cannot be null");
		}
	}

}
