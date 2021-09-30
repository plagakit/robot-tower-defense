package components;

import gameobjects.GameObject;

public abstract class Component {

	protected GameObject parent;
	
	public Component(GameObject parent) {
		this.parent = parent;
	}
	
	public GameObject getParent() { return parent; }
	
}
