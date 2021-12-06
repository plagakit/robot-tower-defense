package gameobjects;

import java.util.ArrayList;
import java.util.List;

import graphics.Renderer;

//https://docs.oracle.com/javase/tutorial/java/generics/types.html
// composition over inheritance

public class ObjectGroup<T extends GameObject> {

	private ArrayList<T> objects;
	private ArrayList<T> addQueue;
	private ArrayList<T> removeQueue;
	
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
	
	public void render(Renderer r) {
		for (T object : objects)
			object.render(r);
	}
	
	public ArrayList<T> getList() {
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
