package com.clueless.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.clueless.domain.Card;
import com.clueless.domain.Hallway;
import com.clueless.domain.Player;
import com.clueless.domain.Room;
import com.clueless.domain.Weapon;
import com.clueless.model.ClueLessModel;
import com.clueless.model.SolutionModel;
import com.clueless.model.SuggestionModel;

/**
 * The ClueLessServiceImpl implements the ClueLessService interface. All the actual game logic code is here.
 * @author nshih
 */
@Service
public class ClueLessServiceImpl implements ClueLessService {

	ClueLessModel clueLessModel;
	SolutionModel solutionModel;
	SuggestionModel suggestionModel;
	ArrayList<Card> deck;
	HashMap<String, Player> players;
	private HashMap<String, Room> rooms;
	private HashMap<String, Hallway> hallways;
	ArrayList<String> roomKeys;
	ArrayList<String> weaponList;
	
	private boolean cardsDealt;
	
	public ClueLessServiceImpl() {
		clueLessModel = ClueLessModel.getInstance();
		solutionModel = SolutionModel.getInstance();
		suggestionModel = SuggestionModel.getInstance();
		deck = new ArrayList<Card>(Card.TOTAL);
		players = new HashMap<String, Player>(Player.TOTAL);
		rooms = new HashMap<String, Room>(Room.TOTAL);
		hallways = new HashMap<String, Hallway>(Hallway.TOTAL);
		roomKeys = new ArrayList<String>(rooms.keySet());
		weaponList = new Weapon().getWeaponList();
		
		// initialize rooms
		initRooms();

		// initialize hallways
		initHallways();
		
		// randomly put 6 weapons in 9 rooms no more than one per room
		distributeWeapons();
		
		//create deck
		createDeck();
		
		// generate solution using 3 cards one of each type: room, suspect, weapon
		generateSolution();
		
		cardsDealt = false;
	}
	
	@Override
	public ClueLessModel getClueLess() {
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel joinClueLess(String sessionId, String player) {
		//HashMap<String, Player> players = this.clueLessModel.getPlayers();
		players.put(sessionId, new Player(player));
		this.clueLessModel.setPlayers(players);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() >= 3 && hasMissScarletJoined) {
			this.clueLessModel.setGameReady(true);
		}
		
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel leaveClueLess(String sessionId) {
		//HashMap<String, Player> players = this.clueLessModel.getPlayers();
		players.remove(sessionId);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() < 3 || hasMissScarletJoined == false) {
			this.clueLessModel.setGameReady(false);
		}
		this.clueLessModel.setPlayers(players);
		
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel dealCards() {
		
		// check to make sure other clients do not call this method twice
		if (!cardsDealt) {
	
			//TODO: deal remaining cards to all players
			cardsDealt = true;
		}
		
		return this.clueLessModel;
	}

	@Override
	public ClueLessModel movePlayer(String sessionId, String location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuggestionModel makeSuggestion(String sessionId, String room,
			String suspect, String weapon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SolutionModel makeAccusation(String sessionId, String room,
			String suspect, String weapon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClueLessModel endTurn(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Sets the sessionId associated with Miss Scarlet to the whoseTurn boolean in the ClueLessModel 
	 * since miss Scarlet always starts first.
	 * @return true if Miss Scarlet has joined otherwise false 
	 */
	private boolean checkMissScarletJoinStatus() {
		HashMap<String, Player> players = this.clueLessModel.getPlayers();
		for (String key : players.keySet()) {
			Player player = players.get(key);
			if (player.getName().equals("Miss Scarlet")) {
				this.clueLessModel.setWhoseTurn(key);
				return true;
			}
		}
		this.clueLessModel.setWhoseTurn(null);
		return false;
	}
	
	private void initRooms() {
		rooms.put(Room.STUDY, new Room(Hallway.HALLWAY1, Hallway.HALLWAY3, Room.KITCHEN));
		rooms.put(Room.HALL, new Room(Hallway.HALLWAY1, Hallway.HALLWAY2, Hallway.HALLWAY4));
		rooms.put(Room.LOUNGE, new Room(Hallway.HALLWAY2, Room.CONSERVATORY, Hallway.HALLWAY5));
		rooms.put(Room.LIBRARY, new Room(Hallway.HALLWAY3, Hallway.HALLWAY6, Hallway.HALLWAY8));
		rooms.put(Room.BILLIARD_ROOM, new Room(Hallway.HALLWAY4, Hallway.HALLWAY6, Hallway.HALLWAY7, Hallway.HALLWAY9));
		rooms.put(Room.DINING_ROOM, new Room(Hallway.HALLWAY5, Hallway.HALLWAY7, Hallway.HALLWAY10));
		rooms.put(Room.CONSERVATORY, new Room(Hallway.HALLWAY8, Room.LOUNGE, Hallway.HALLWAY11));
		rooms.put(Room.BALLROOM, new Room(Hallway.HALLWAY11, Hallway.HALLWAY9, Hallway.HALLWAY12));
		rooms.put(Room.KITCHEN, new Room(Hallway.HALLWAY12, Room.STUDY, Hallway.HALLWAY10));
		
		this.clueLessModel.setRooms(rooms);
	}
	
	private void initHallways() {
		hallways.put(Hallway.HALLWAY1, new Hallway(Room.STUDY, Room.HALL));
		hallways.put(Hallway.HALLWAY2, new Hallway(Room.HALL, Room.LOUNGE));
		hallways.put(Hallway.HALLWAY3, new Hallway(Room.STUDY, Room.LIBRARY));
		hallways.put(Hallway.HALLWAY4, new Hallway(Room.HALL, Room.BILLIARD_ROOM));
		hallways.put(Hallway.HALLWAY5, new Hallway(Room.LOUNGE, Room.DINING_ROOM));
		hallways.put(Hallway.HALLWAY6, new Hallway(Room.LIBRARY, Room.BILLIARD_ROOM));
		hallways.put(Hallway.HALLWAY7, new Hallway(Room.BILLIARD_ROOM, Room.DINING_ROOM));
		hallways.put(Hallway.HALLWAY8, new Hallway(Room.LIBRARY, Room.CONSERVATORY));
		hallways.put(Hallway.HALLWAY9, new Hallway(Room.BILLIARD_ROOM, Room.BALLROOM));
		hallways.put(Hallway.HALLWAY10, new Hallway(Room.DINING_ROOM, Room.KITCHEN));
		hallways.put(Hallway.HALLWAY11, new Hallway(Room.CONSERVATORY, Room.BALLROOM));
		hallways.put(Hallway.HALLWAY12, new Hallway(Room.BALLROOM, Room.KITCHEN));
		
		this.clueLessModel.setHallways(hallways);
	}
	
	private void distributeWeapons() {
		Collections.shuffle(roomKeys);
		Collections.shuffle(weaponList);
		for (int i = 0; i < 6; i++) {
			Room room = rooms.get(roomKeys.get(i));
			room.addWeapon(weaponList.get(i));
		}
	}
	
	private void createDeck() {
		for (int i=0;i<Card.NUM_ROOMS;i++) deck.add(new Card(Card.TYPE_ROOM,i));
		for (int i=0;i<Card.NUM_SUSPECTS;i++) deck.add(new Card(Card.TYPE_SUSPECT,i));
		for (int i=0;i<Card.NUM_WEAPONS;i++) deck.add(new Card(Card.TYPE_WEAPON,i));
		Collections.shuffle(deck);
	}
	
	private void generateSolution() {
		Random rand = new Random();
		int w = rand.nextInt(Card.NUM_WEAPONS);
		int r = rand.nextInt(Card.NUM_ROOMS);
		int s = rand.nextInt(Card.NUM_SUSPECTS);
		Card weapon = new Card(Card.TYPE_WEAPON,w);
		Card suspect = new Card(Card.TYPE_SUSPECT,s);
		Card room = new Card(Card.TYPE_ROOM,r);
		for (int i = 0; i < deck.size(); i++) {
			Card card = deck.get(i);
			if (card.getType() == weapon.getType() && card.getValue() == weapon.getValue()) {
				deck.remove(card);
			}
			if (card.getType() == suspect.getType() && card.getValue() == suspect.getValue()) {
				deck.remove(card);
			}
			if (card.getType() == room.getType() && card.getValue() == room.getValue()) {
				deck.remove(card);
			}
			deck.trimToSize();
		}
			
		solutionModel.setWeapon(weapon.getDesc());
		solutionModel.setSuspect(suspect.getDesc());
		solutionModel.setRoom(room.getDesc());
	}
}
