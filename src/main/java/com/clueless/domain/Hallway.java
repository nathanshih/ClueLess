package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Hallway {
	
	public static final int TOTAL = 12; // 12 hallways
	
	public static final String HALLWAY1 = "hallway1";
	public static final String HALLWAY2 = "hallway2";
	public static final String HALLWAY3 = "hallway3";
	public static final String HALLWAY4 = "hallway4";
	public static final String HALLWAY5 = "hallway5";
	public static final String HALLWAY6 = "hallway6";
	public static final String HALLWAY7 = "hallway7";
	public static final String HALLWAY8 = "hallway8";
	public static final String HALLWAY9 = "hallway9";
	public static final String HALLWAY10 = "hallway10";
	public static final String HALLWAY11 = "hallway11";
	public static final String HALLWAY12 = "hallway12";
	
	private String token;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@JsonIgnore
	public ArrayList<String> getAdjacentTo() {
		return adjacentTo;
	}

	public void setAdjacentTo(ArrayList<String> adjacentRooms) {
		this.adjacentTo = adjacentRooms;
	}
}
