/**
 * Copyright (c) 2010 Dvidea
 * This source code is the property of Dvidea.
 */
package com.cineapps.mediablock.core.utils;

import java.io.Serializable;

/**
 * A class representing a pair of objects
 */
public class Pair<T, E> implements Serializable {
	private static final long serialVersionUID = 2967526818555166082L;
	private T firstObject;
	private E secondObject;

	public Pair(T firstObject, E secondObject) {
		this.firstObject = firstObject;
		this.secondObject = secondObject;
	}

	public T getFirst() {
		return firstObject;
	}

	public E getSecond() {
		return secondObject;
	}

	public void setFirst(T object) {
		firstObject = object;
	}

	public void setSecond(E object) {
		secondObject = object;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair<?, ?>)) {
			return false;
		}
		Pair<?, ?> other = (Pair<?, ?>) obj;
		return same(other.firstObject, this.firstObject) && same(other.secondObject, this.secondObject);
	}

	@Override
	public int hashCode() {
		if (firstObject == null) {
			return (secondObject == null) ? 0 : secondObject.hashCode() + 1;
		}
		else if (secondObject == null) {
			return firstObject.hashCode() + 2;
		}
		else {
			return firstObject.hashCode() * 17 + secondObject.hashCode();
		}
	}

	private boolean same(Object o1, Object o2) {
		// the classes must be the same and the objects must be equal
		return (o1 == null && o2 == null) || (o1 != null && o2 != null && o1.getClass().equals(o2.getClass()) && o1.equals(o2));
	}

	@Override
	public String toString() {
		return "Pair{" + firstObject + ", " + secondObject + "}";
	}

	/**
	 * Returns a {@link Pair} made of the two given objects
	 * @param <T> the class of the first object
	 * @param <E> the class of the second object
	 * @param firstObject the first object of the pair
	 * @param secondObject the second object of the pair
	 * @return a new {@link Pair} with the two objects.
	 */
	public static <T, E> Pair<T, E> of(T firstObject, E secondObject) {
		return new Pair<T, E>(firstObject, secondObject);
	}
}
