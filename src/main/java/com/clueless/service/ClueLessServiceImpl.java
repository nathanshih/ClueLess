package com.clueless.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.clueless.domain.Card;
import com.clueless.domain.Hallway;
import com.clueless.domain.Player;
import com.clueless.domain.SuspectToken;
import com.clueless.domain.Room;
import com.clueless.domain.Token;
import com.clueless.domain.WeaponToken;
import com.clueless.model.ClueLessModel;
import com.clueless.model.SolutionModel;
import com.clueless.model.SuggestionModel;

/* TODO If all of the exits are blocked (i.e., there are people in all of the hallways) and you are not in
one of the corner rooms (with a secret passage), and you weren't moved to the room by
another player making a suggestion, you lose your turn (except for maybe making an
accusation).
*/

/**
 * The ClueLessServiceImpl implements the ClueLessService interface. All the game logic code is here.
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
	ArrayList<WeaponToken> weaponTokens;
	ArrayList<SuspectToken> suspectTokens;
	
	private boolean isClueLessInitialized;
	
	public ClueLessServiceImpl() {
		clueLessModel = ClueLessModel.getInstance();
		solutionModel = SolutionModel.getInstance();
		suggestionModel = SuggestionModel.getInstance();
		deck = new ArrayList<Card>(Card.TOTAL);
		players = clueLessModel.getPlayers();
		rooms = clueLessModel.getRooms();
		hallways = clueLessModel.getHallways();
		
		// initialize rooms
		createRooms();

		// initialize hallways
		createHallways();
			
		// randomly put 6 weapons in 9 rooms no more than one per room
		distributeWeaponTokens();
		
		// initialize suspect tokens
		createSuspectTokens();
		
		//create deck
		createDeck();
		
		// generate solution using 3 cards one of each type: room, suspect, weapon
		generateSolution();
		
		isClueLessInitialized = false;
	}
	
	@Override
	public ClueLessModel getClueLess() {
		return clueLessModel;
	}

	@Override
	public ClueLessModel joinClueLess(String playerName, String suspect) {
		Player newPlayer = new Player(suspect);
		
		// set each player's specific start location
		switch(suspect) {
			case SuspectToken.COLONEL_MUSTARD:
				newPlayer.addMovableLocation(Hallway.HALLWAY5);
				break;
			case SuspectToken.MISS_SCARLET:
				newPlayer.addMovableLocation(Hallway.HALLWAY2);
				break;
			case SuspectToken.MR_GREEN:
				newPlayer.addMovableLocation(Hallway.HALLWAY11);
				break;
			case SuspectToken.MRS_PEACOCK:
				newPlayer.addMovableLocation(Hallway.HALLWAY8);
				break;
			case SuspectToken.MRS_WHITE:
				newPlayer.addMovableLocation(Hallway.HALLWAY12);
				break;
			case SuspectToken.PROFESSOR_PLUM:
				newPlayer.addMovableLocation(Hallway.HALLWAY3);
				break;
		}
		
		players.put(playerName, newPlayer);
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() >= 3 && hasMissScarletJoined) {
			clueLessModel.setGameReady(true);
		}

		return clueLessModel;
	}

	@Override
	public ClueLessModel leaveClueLess(String playerName) {
		// TODO handle player's deck as well? Would also need to handle re-dealing the cards
		players.remove(playerName);
		for (SuspectToken suspectToken : suspectTokens) {
			if (suspectToken.getPlayedBy() != null) {
				if (suspectToken.getPlayedBy().equals(playerName)) {
					suspectToken.setPlayedBy(null);
					break;
				}
			}
		}
		boolean hasMissScarletJoined = checkMissScarletJoinStatus();
		if (players.size() < 3 || hasMissScarletJoined == false) {
			clueLessModel.setGameReady(false);
		}
		
		return clueLessModel;
	}

	@Override
	public ClueLessModel initClueLess() {
		
		// check to make sure other clients do not call this method twice
		if (!isClueLessInitialized) {
			ArrayList<String> playerKeys = new ArrayList<String>(players.keySet());
			
			// deal remaining cards to all players
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
			
			// sets suspect tokens to their players
			for (Map.Entry<String, Player> entry : players.entrySet()) {
				Player player = entry.getValue();
				findMatchingToken: {
					for (SuspectToken suspectToken : suspectTokens) {
						if (suspectToken.getTokenName().equals(player.getSuspect())) {
							suspectToken.setPlayedBy(entry.getKey());
							break findMatchingToken;
						}
					}
				}
			}

			
			isClueLessInitialized = true;
		}
		
		return clueLessModel;
	}

	@Override
	public ClueLessModel movePlayer(String playerName, String location) {
		Player player = players.get(playerName);
		player.setLocation(location);
		
		for (SuspectToken suspectToken : suspectTokens) {
			if (suspectToken.getTokenName().equals(player.getSuspect())) {
				moveToken(suspectToken, location);
				break;
			}
		}
		
		updatePlayerMovableLocations();
		
		return clueLessModel;
	}

	@Override
	public SuggestionModel makeSuggestion(String playerName, String room,
			String suspect, String weapon) {
		// TODO Auto-generated method stub
		
		// TODO If you were moved to the room by another player making a suggestion, you may, if
		// you wish, stay in that room and make a suggestion. Otherwise you may move
		// through a doorway or take a secret passage as described above.
		
		return null;
	}

	@Override
	public SuggestionModel disproveSuggestion(String playerName, Card card) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SolutionModel makeAccusation(String playerName, String room,
			String suspect, String weapon) {
		if (room.equals(solutionModel.getRoom()) 
			&& suspect.equals(solutionModel.getSuspect()) 
			&& weapon.equals(solutionModel.getWeapon())) {
			solutionModel.setSolvedBy(playerName);
		} else {
			Player player = players.get(playerName);
			player.setFailedAccusation(true);
		}
		
		return solutionModel;
	}

	@Override
	public ClueLessModel endTurn(String playerName) {
		ArrayList<String> playerKeys = new ArrayList<String>(players.keySet());
		int i = playerKeys.indexOf(playerName);
		Player player;
		boolean isWhoseTurnSet = false;
		while (!isWhoseTurnSet) {
			// if whose turn is the last player in the list then get the first player
			if (i == (playerKeys.size() - 1)) {
				i = 0;				
			// else get next player
			} else {
				i++;
			}
			player = players.get(playerKeys.get(i));
			if (!player.isFailedAccusation()) {
				clueLessModel.setWhoseTurn(playerKeys.get(i));
				isWhoseTurnSet = true;
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
			if (entry.getValue().getSuspect().equals(SuspectToken.MISS_SCARLET)) {
				clueLessModel.setWhoseTurn(entry.getKey());
				return true;
			}
		}
		clueLessModel.setWhoseTurn(null);
		return false;
	}
	
	private void createRooms() {
		rooms.put(Room.STUDY, new Room(Hallway.HALLWAY1, Hallway.HALLWAY3, Room.KITCHEN));
		rooms.put(Room.HALL, new Room(Hallway.HALLWAY1, Hallway.HALLWAY2, Hallway.HALLWAY4));
		rooms.put(Room.LOUNGE, new Room(Hallway.HALLWAY2, Room.CONSERVATORY, Hallway.HALLWAY5));
		rooms.put(Room.LIBRARY, new Room(Hallway.HALLWAY3, Hallway.HALLWAY6, Hallway.HALLWAY8));
		rooms.put(Room.BILLIARD_ROOM, new Room(Hallway.HALLWAY4, Hallway.HALLWAY6, Hallway.HALLWAY7, Hallway.HALLWAY9));
		rooms.put(Room.DINING_ROOM, new Room(Hallway.HALLWAY5, Hallway.HALLWAY7, Hallway.HALLWAY10));
		rooms.put(Room.CONSERVATORY, new Room(Hallway.HALLWAY8, Room.LOUNGE, Hallway.HALLWAY11));
		rooms.put(Room.BALLROOM, new Room(Hallway.HALLWAY11, Hallway.HALLWAY9, Hallway.HALLWAY12));
		rooms.put(Room.KITCHEN, new Room(Hallway.HALLWAY12, Room.STUDY, Hallway.HALLWAY10));
	}
	
	private void createHallways() {
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
	
	private void distributeWeaponTokens() {
		ArrayList<String> roomKeys = new ArrayList<String>(rooms.keySet());
		
		weaponTokens = new ArrayList<WeaponToken>(WeaponToken.TOTAL); // at most 6 weapons
		weaponTokens.add(new WeaponToken(WeaponToken.ROPE));
		weaponTokens.add(new WeaponToken(WeaponToken.LEAD_PIPE));
		weaponTokens.add(new WeaponToken(WeaponToken.KNIFE));
		weaponTokens.add(new WeaponToken(WeaponToken.WRENCH));
		weaponTokens.add(new WeaponToken(WeaponToken.CANDLESTICK));
		weaponTokens.add(new WeaponToken(WeaponToken.REVOLVER));	
		
		Collections.shuffle(weaponTokens);
		Collections.shuffle(roomKeys);	
		
		for (int i = 0; i < weaponTokens.size(); i++) {
			Room room = rooms.get(roomKeys.get(i));
			room.addToken(weaponTokens.get(i).getTokenName());
		}
	}
	
	private void createSuspectTokens() {
		suspectTokens = new ArrayList<SuspectToken>(SuspectToken.TOTAL);
		suspectTokens.add(new SuspectToken(SuspectToken.COLONEL_MUSTARD));
		suspectTokens.add(new SuspectToken(SuspectToken.MISS_SCARLET));
		suspectTokens.add(new SuspectToken(SuspectToken.MR_GREEN));
		suspectTokens.add(new SuspectToken(SuspectToken.MRS_PEACOCK));
		suspectTokens.add(new SuspectToken(SuspectToken.MRS_WHITE));
		suspectTokens.add(new SuspectToken(SuspectToken.PROFESSOR_PLUM));
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
	
	private void updatePlayerMovableLocations() {
		String location;
		ArrayList<String> adjacentAreas;
		for (Map.Entry<String, Player> entry : players.entrySet()) {
			Player player = entry.getValue();		
			location = player.getLocation();

			// player in hallway
			if (hallways.containsKey(location)) {
				Hallway hallway = hallways.get(location);
				adjacentAreas = hallway.getAdjacentTo();
				player.clearMovableLocations();
				for (String area : adjacentAreas) {
					player.addMovableLocation(area);
				}
				continue;
				
			// player in room
			} else if (rooms.containsKey(location)) {
				Room room = rooms.get(location);
				adjacentAreas = room.getAdjacentTo();
				player.clearMovableLocations();	
				for (String area : adjacentAreas) {
					if (hallways.containsKey(area)) {
						Hallway adjacentHallway = hallways.get(area);
						if (adjacentHallway.getToken() == null) {
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
	
	private void moveToken(Token token, String location) {
		String previousLocation = token.getLocation();
		token.setLocation(location);
		
		// moving to hallway	
		if (hallways.containsKey(location)) {
			Hallway hallway = hallways.get(location);
			hallway.setToken(token.getTokenName());		
		} 
	
		// moving to a room
		if (rooms.containsKey(location)) {
			Room room = rooms.get(location);
			room.addToken(token.getTokenName());
 		}	
		
		// remove token from previous location
		if (previousLocation != null) {
			if (rooms.containsKey(previousLocation)) {
				Room previousRoom = rooms.get(previousLocation);
				previousRoom.removeToken(token.getTokenName());
			} else {
				Hallway previousHallway = hallways.get(previousLocation);
				previousHallway.setToken(null);
			}
		}
	}
}
