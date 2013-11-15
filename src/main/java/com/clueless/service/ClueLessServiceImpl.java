package com.clueless.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
	ArrayList<String> playerKeys;
	
	private boolean cardsDealt;
	
	public ClueLessServiceImpl() {
		clueLessModel = ClueLessModel.getInstance();
		solutionModel = SolutionModel.getInstance();
		suggestionModel = SuggestionModel.getInstance();
		deck = new ArrayList<Card>(Card.TOTAL);
		players = clueLessModel.getPlayers();
		rooms = clueLessModel.getRooms();
		hallways = clueLessModel.getHallways();
		playerKeys = new ArrayList<String>(Player.TOTAL);
		
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
		return clueLessModel;
	}

	@Override
	public ClueLessModel joinClueLess(String sessionId, String player) {
		Player newPlayer = new Player(player);
		
		// set each player's specific start location
		switch(player) {
			case Player.COLONEL_MUSTARD:
				newPlayer.addMovableLocation(Hallway.HALLWAY5);
				break;
			case Player.MISS_SCARLET:
				newPlayer.addMovableLocation(Hallway.HALLWAY2);
				break;
			case Player.MR_GREEN:
				newPlayer.addMovableLocation(Hallway.HALLWAY11);
				break;
			case Player.MRS_PEACOCK:
				newPlayer.addMovableLocation(Hallway.HALLWAY8);
				break;
			case Player.MRS_WHITE:
				newPlayer.addMovableLocation(Hallway.HALLWAY12);
				break;
			case Player.PROFESSOR_PLUM:
				newPlayer.addMovableLocation(Hallway.HALLWAY3);
				break;
		}
		
		players.put(sessionId, newPlayer);
		playerKeys.add(sessionId);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() >= 3 && hasMissScarletJoined) {
			clueLessModel.setGameReady(true);
		}

		return clueLessModel;
	}

	@Override
	public ClueLessModel leaveClueLess(String sessionId) {
		Player player = players.get(sessionId);
		removePlayerFromLocation(sessionId, player.getLocation());
		players.remove(sessionId);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() < 3 || hasMissScarletJoined == false) {
			clueLessModel.setGameReady(false);
		}
		
		return clueLessModel;
	}

	@Override
	public ClueLessModel dealCards() {
		
		// check to make sure other clients do not call this method twice
		if (!cardsDealt) {
			
			//deal remaining cards to all players
			int playerIndex = 0;
			for (int i = 0; i < deck.size(); i++) {
				Card card = deck.get(i);
				if (playerIndex == playerKeys.size()) {
					playerIndex = 0;
				}
				Player player = players.get(playerKeys.get(playerIndex));
				player.addCard(card);
				playerIndex++;
			}
			
			cardsDealt = true;
		}
		
		return clueLessModel;
	}

	@Override
	public ClueLessModel movePlayer(String sessionId, String location) {
		Player player = players.get(sessionId);
		String previousLocation = player.getLocation();
		
		// move player to hallway
		if (hallways.containsKey(location)) {
			Hallway hallway = hallways.get(location);
			hallway.setOccupiedBy(sessionId);
			player.setLocation(location);
				
			removePlayerFromLocation(sessionId, previousLocation);	
			updatePlayerMovableLocations();
			
			return clueLessModel;
			
		// move player to room
		} else if (rooms.containsKey(location)) {
			Room room = rooms.get(location);
			room.addPlayer(sessionId);
			player.setLocation(location);
			
			removePlayerFromLocation(sessionId, previousLocation);
			updatePlayerMovableLocations();
			
			return clueLessModel;
		} else {
			return null;
		}
	}

	@Override
	public SuggestionModel makeSuggestion(String sessionId, String room,
			String suspect, String weapon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuggestionModel disproveSuggestion(String sessionId, Card card) {
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
		for (int i = 0; i < playerKeys.size(); i++) {
			if (sessionId == playerKeys.get(i)) {
				
				// if next player the first player in the array
				if (i == (playerKeys.size() - 1)) {
					clueLessModel.setWhoseTurn(playerKeys.get(0));
					continue;
					
				// get next player
				} else {
					i++;
					clueLessModel.setWhoseTurn(playerKeys.get(i));
					continue;
				}
			}
		}
		return clueLessModel;
	}
	
	/**
	 * Sets the sessionId associated with Miss Scarlet to the whoseTurn boolean in the ClueLessModel 
	 * since miss Scarlet always starts first.
	 * @return true if Miss Scarlet has joined otherwise false 
	 */
	private boolean checkMissScarletJoinStatus() {
		for (Map.Entry<String, Player> entry : players.entrySet()) {
			if (entry.getValue().getName().equals(Player.MISS_SCARLET)) {
				clueLessModel.setWhoseTurn(entry.getKey());
				return true;
			}
		}
		clueLessModel.setWhoseTurn(null);
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
		
		roomKeys = new ArrayList<String>(rooms.keySet());
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
	}
	
	private void distributeWeapons() {
		weaponList = new ArrayList<String>(Weapon.TOTAL); // at most 6 weapons
		weaponList.add(Weapon.ROPE);
		weaponList.add(Weapon.LEAD_PIPE);
		weaponList.add(Weapon.KNIFE);
		weaponList.add(Weapon.WRENCH);
		weaponList.add(Weapon.CANDLESTICK);
		weaponList.add(Weapon.REVOLVER);		
		Collections.shuffle(weaponList);
		Collections.shuffle(roomKeys);		
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
			
			// remove weapon card for solution
			if (card.getType() == weapon.getType() && card.getValue() == weapon.getValue()) {
				deck.remove(card);
				continue;
			}
			
			// remove suspect card for solution
			if (card.getType() == suspect.getType() && card.getValue() == suspect.getValue()) {
				deck.remove(card);
				continue;
			}
			
			// remove room card for solution
			if (card.getType() == room.getType() && card.getValue() == room.getValue()) {
				deck.remove(card);
				continue;
			}
			deck.trimToSize();
		}
			
		solutionModel.setWeapon(weapon.getDesc());
		solutionModel.setSuspect(suspect.getDesc());
		solutionModel.setRoom(room.getDesc());
	}
	
	private void removePlayerFromLocation(String sessionId, String location) {
		// remove previous location if player was previously in a room
		if (rooms.containsKey(location)) {
			Room room = rooms.get(location);
			room.removePlayer(sessionId);
			rooms.put(location, room);
		}

		// remove previous location if player was previously in a hallway
		if (hallways.containsKey(location)) {
			Hallway hallway = hallways.get(location);
			hallway.setOccupiedBy(null);
		}
	}
	
	private void updatePlayerMovableLocations() {
		Collection<Player> allPlayers = players.values();
		String location;
		ArrayList<String> adjacentAreas;
		for (Player player : allPlayers) {
			player.clearMovableLocations();			
			location = player.getLocation();

			// player in hallway
			if (hallways.containsKey(location)) {
				Hallway hallway = hallways.get(location);
				adjacentAreas = hallway.getAdjacentTo();
				for (String area : adjacentAreas) {
					player.addMovableLocation(area);
				}
				continue;
				
			// player in room
			} else if (rooms.containsKey(location)) {
				Room room = rooms.get(location);
				adjacentAreas = room.getAdjacentTo();
				for (String area : adjacentAreas) {
					if (hallways.containsKey(area)) {
						Hallway adjacentHallway = hallways.get(area);
						if (adjacentHallway.getOccupiedBy() == null) {
							player.addMovableLocation(area);
						}
					} else {
						player.addMovableLocation(area);
					}
				}
				continue;
			}
		}
	}
}
