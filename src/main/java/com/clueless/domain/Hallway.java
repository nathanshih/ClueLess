package com.clueless.domain;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class Hallway {
	
	public static final int TOTAL = 12; // 12 hallways
	
	public static final String HALLWAY1 = "Hallway1";
	public static final String HALLWAY2 = "Hallway2";
	public static final String HALLWAY3 = "Hallway3";
	public static final String HALLWAY4 = "Hallway4";
	public static final String HALLWAY5 = "Hallway5";
	public static final String HALLWAY6 = "Hallway6";
	public static final String HALLWAY7 = "Hallway7";
	public static final String HALLWAY8 = "Hallway8";
	public static final String HALLWAY9 = "Hallway9";
	public static final String HALLWAY10 = "Hallway10";
	public static final String HALLWAY11 = "Hallway11";
	public static final String HALLWAY12 = "Hallway12";
	
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

		adjacentTo.add(adjacentTo1);
		adjacentTo.add(adjacentTo2);
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

	public void setAdjacentTo(ArrayList<String> adjacentTo) {
		this.adjacentTo = adjacentTo;
	}
}
