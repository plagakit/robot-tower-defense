package components;

import gameobjects.GameObject;

public class PhysicsComponent extends Component {

	public PhysicsComponent(GameObject parent) {
		super(parent);
	}
	
	public void update() {
		// TODO adjust for fps
		parent.getPos().x += parent.getVel().x;
		parent.getPos().y += parent.getVel().y;
	}

}
