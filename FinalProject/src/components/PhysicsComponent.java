package components;

import gameobjects.GameObject;

public class PhysicsComponent extends Component {

	public PhysicsComponent(GameObject parent) {
		super(parent);
	}
	
	public void update() {
		double timeAdjust = parent.getGameScene().getGame().getTimeAdjust();
		parent.getPos().x += parent.getVel().x * timeAdjust;
		parent.getPos().y += parent.getVel().y * timeAdjust;
	}

}
