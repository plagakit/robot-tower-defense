package gameobjects.components;

import gameobjects.GameObject;

/** A component that lets game objects move with their velocity. */
public class PhysicsComponent extends Component {

	public PhysicsComponent(GameObject parent) {
		super(parent);
	}
	
	/** Moves the gameobjects using their velocity and the game's
	 * time scale. */
	public void update() {
		double timeScale = parent.getGameScene().getGame().getTimeScale();
		parent.getPos().x += parent.getVel().x * timeScale;
		parent.getPos().y += parent.getVel().y * timeScale;
	}

}
