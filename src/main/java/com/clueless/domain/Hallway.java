package com.clueless.domain;

import java.util.ArrayList;

public class Hallway {
	
	private String occupiedBy;
	private ArrayList<String> adjacentTo;
	
	public Hallway() {
		adjacentTo = new ArrayList<String>(2); // at most 2 adjacent areas
	}
	
	/**
	 * Set adjacent areas for new hallway object.
	 * @param adjacentTo1
	 * @param adjacentTo2
	 */
	public Hallway(String adjacentTo1, String adjacentTo2) {
		adjacentTo = new ArrayList<String>(2);

		this.adjacentTo.add(adjacentTo1);
		this.adjacentTo.add(adjacentTo2);
	}

	public String getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(String occupiedBy) {
		this.occupiedBy = occupiedBy;
	}
	
	public ArrayList<String> getAdjacentTo() {
		return adjacentTo;
	}

	public void setAdjacentTo(ArrayList<String> adjacentRooms) {
		this.adjacentTo = adjacentRooms;
	}
}
