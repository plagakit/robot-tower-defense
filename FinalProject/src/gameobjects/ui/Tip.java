package gameobjects.ui;

import java.awt.Color;
import java.util.Scanner;

import game.Vector2;
import gameobjects.components.BoxBounds;
import graphics.Renderer;
import scenes.GameScene;

public class Tip extends Button {

	private final float FADE = 0.015f;
	private boolean fading = false;
	private float transparency = 0;
	
	private String[] tipData;
	private int currentRound = 0;
	
	public Tip(GameScene scene) {
		super(scene, "Tip", new Vector2(240, 310));
		
		sprite = scene.getGame().getSpriteManager().getSprite("tipbutton.png");
		bounds = new BoxBounds(this, sprite);
		
		loadTipText();
	}
	
	private void loadTipText() {
		
		final String dataPath = "tipsdata.txt";
		Scanner sc = new Scanner(ClassLoader.getSystemClassLoader().getResourceAsStream(dataPath));
		
		tipData = new String[100];
		int counter = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line.length() == 0) {
				tipData[counter] = "";
				counter++;
			} else if (line.charAt(0) == '#') {
				continue; 
			} else {
				tipData[counter] = line;
				counter++;
			}
		}
		
		sc.close();
	}
	
	public void showTip(int round) {
		currentRound = round;
		transparency = 1;
		fading = false;
	}
	
	public void startFade() {
		fading = true;
	}
	
	@Override
	protected void onClick() {
		startFade();
	}
	
	@Override
	public void update() {
		super.update();
		
		if (fading)
			transparency -= FADE;
		
		if (transparency <= 0)
			fading = false;
		
	}
	
	@Override
	public void render(Renderer r) {
		if (transparency <= 0 || tipData[currentRound] == null || tipData[currentRound].length() == 0)
			return;
		
		r.setTransparency(transparency);
		super.render(r);
		
		r.setColor(Color.BLACK);
		r.drawWrappedString(tipData[currentRound], 
				pos.x - bounds.getWidth()/2 + 15, 
				pos.y - bounds.getHeight()/2 + 20, 
				bounds.getWidth()-20);
		
		r.setTransparency(1);
	}

	@Override
	protected void onMouseEnter() {
		
	}
	
	@Override
	protected void onMouseExit() {
		
	}

}
