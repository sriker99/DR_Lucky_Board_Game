package game.view;

import javax.swing.JFrame;

import game.controller.Features;

public class WorldView extends JFrame implements View {
	
	private static final long serialVersionUID = 1L;
	public WorldView(String caption) {
		if(caption==null||"".equals(caption.trim())) {
			throw new IllegalArgumentException("Caption shouldn't be empty");
		}
		setSize(500, 300);
	    setLocation(200, 200);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    pack();
	    setVisible(true);
	}
	@Override
	public void setFeatures(Features f) {
		if(f==null) {
			throw new IllegalArgumentException("Features object cannot be null");
		}
	}

}
