package gameobjects;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

//https://docs.oracle.com/javase/tutorial/java/generics/types.html

public class ObjectGroup<T extends GameObject> {

	private List<T> objects;
	private List<T> addQueue;
	private List<T> removeQueue;
	
	public ObjectGroup() {
		objects = new ArrayList<T>();
		addQueue = new ArrayList<T>();
		removeQueue = new ArrayList<T>();
	}
	
	public void update() {
		if (addQueue.size() > 0) {
			for (T object : addQueue)
				objects.add(object);
			addQueue.clear();
		}
		
		for (T object : objects)
			if (object.getActive())
				object.update();
		
		if (removeQueue.size() > 0) {
			for (T object : removeQueue)
				objects.remove(object);
			removeQueue.clear();
		}
	}
	
	public void render(Graphics2D g) {
		for (T object : objects)
			object.render(g);
	}
	
	public List<T> getList() {
		return objects;
	}
	
	public void add(T object) {
		if (!addQueue.contains(object) && !objects.contains(object))
			addQueue.add(object);
	}
	
	public void remove(T object) {
		if (!removeQueue.contains(object) && objects.contains(object))
			removeQueue.add(object);
	}
	
}
