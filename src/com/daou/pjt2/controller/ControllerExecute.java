package com.daou.pjt2.controller;

public class ControllerExecute {

	private AbstractController controller;
	
	public ControllerExecute(AbstractController controller) { 
		setController( controller );
	}
	
	public void setController(AbstractController newController) { 
		this.controller = newController; 
	}
	
	public void execute() { 
		controller.run(); 
	}
}