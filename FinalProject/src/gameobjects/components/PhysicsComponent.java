package gameobjects.components;

import gameobjects.GameObject;

public class PhysicsComponent extends Component {

	public PhysicsComponent(GameObject parent) {
		super(parent);
	}
	
	public void update() {
		double timeScale = parent.getGameScene().getGame().getTimeScale();
		parent.getPos().x += parent.getVel().x * timeScale;
		parent.getPos().y += parent.getVel().y * timeScale;
	}

}
