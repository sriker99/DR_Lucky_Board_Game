package game.controller;
import game.model.World;
import game.view.View;


public class ExtensibleController implements Controller, Features{
	private World model;
	private View view;
	
	public ExtensibleController(World model) {
		if(model==null) {
			throw new IllegalArgumentException("World Object cannot be nulll");
		}
		this.model=model;
	}
	@Override
	public void execute(View view) {
		if(view==null) {
			throw new IllegalArgumentException("View Object cannot be null");
		}
		this.view=view;
		view.setFeatures(this);
	}
	@Override
	public void exitProgram() {
		System.exit(0);
	}

}