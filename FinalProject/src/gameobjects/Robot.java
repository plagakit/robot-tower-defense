package gameobjects;

import java.awt.Graphics2D;

import components.RenderComponent;
import general.Vector2;
import scenes.GameScene;

public class Robot extends Tower {

	RenderComponent renderComponent;
	
	public Robot(GameScene scene, Vector2 pos) {
		super(scene, "Robot", pos);
		sprite = scene.getGame().getSpriteManager().getSprite("smile.png");
		renderComponent = new RenderComponent(this);
	}

	@Override
	public void update() {
		super.update();
	}

	@Override
	public void render(Graphics2D g) {
		renderComponent.render(g, sprite);		
	}

}
