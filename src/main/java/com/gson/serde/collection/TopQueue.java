package com.gson.serde.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
/*
 * The wrapper class implements a fixed-size priority queue. When the maximum 
 * number of elements is reached the lowest/highest element will be removed.
 */
public class TopQueue<T> {

    private TreeSet<T> set;
    private int maxSize;

    public TopQueue(Comparator<T> comparator, int maxSize) {
        this.set = new TreeSet<>(comparator);
        this.maxSize = maxSize;
    }

    public TopQueue<T> add(T element) {
        set.add(element);
        if (set.size() > maxSize) {
            set.pollLast(); // 	pollFirst();
        }
        return this;
    }

	public TopQueue<T> remove(T element) {
        if (set.contains(element)) {
            set.remove(element);
        }
        return this;
    }

    public Iterator<T> iterator() {
        return set.iterator();
    }

    public int size() {
    	return set.size();
    }
	public void printQueue() {
    	Iterator<T> iterator = iterator();
    	while (iterator.hasNext()) {
    		T t = iterator.next();
    		if(t != null) {
    			System.out.println(t.toString());
    		}
    	}
	}
	
}
