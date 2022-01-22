package gameobjects.components;

import gameobjects.GameObject;

/** An abstract class for defining components of gameobjects
 * that have their own behaviour. If the game was bigger and
 * there were more components, this class would be more
 * fleshed out.
 * @see https://gameprogrammingpatterns.com/component.html */
public abstract class Component {

	protected GameObject parent;
	
	public Component(GameObject parent) {
		this.parent = parent;
	}
	
	public GameObject getParent() { return parent; }
	
}
