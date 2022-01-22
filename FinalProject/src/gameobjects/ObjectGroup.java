package gameobjects;

import java.util.ArrayList;

import graphics.Renderer;

//https://docs.oracle.com/javase/tutorial/java/generics/types.html
// composition over inheritance

/** A custom list that works for the gamescene. Since lists cannot
 * modified while being iterated through without causing an error,
 * ObjectGroup uses a queue system. Objects that are added to the
 * list are put into an "add queue", and objects that are removed
 * are put into a "remove queue." At the start of an update, objects
 * in the add queue are added to the list before iteration, and at
 * the end of an update objects in the remove queue are removed. */
public class ObjectGroup<T extends GameObject> {

	private ArrayList<T> objects;
	private ArrayList<T> addQueue;
	private ArrayList<T> removeQueue;
	
	public ObjectGroup() {
		objects = new ArrayList<T>();
		addQueue = new ArrayList<T>();
		removeQueue = new ArrayList<T>();
	}
	
	/** Updates each object in its list. */
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
	
	/** Renders each object in its list. */
	public void render(Renderer r) {
		for (T object : objects)
			object.render(r);
	}
	
	public ArrayList<T> getList() {
		return objects;
	}
	
	/** Adds an object to the ObjectGroup's add queue. */
	public void add(T object) {
		if (!addQueue.contains(object) && !objects.contains(object))
			addQueue.add(object);
	}
	
	/** Adds an object to the ObjectGroup's remove queue. */
	public void remove(T object) {
		if (!removeQueue.contains(object) && objects.contains(object))
			removeQueue.add(object);
	}
	
}
