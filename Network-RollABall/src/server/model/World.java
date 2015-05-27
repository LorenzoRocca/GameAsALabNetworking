package server.model;

import java.util.ArrayList;
import java.util.List;

import server.interfaces.IRollExtension;

import com.smartfoxserver.v2.entities.User;

public class World {
	// Players
	private List<Player> players = new ArrayList<Player>();
	// PickupAble objects
	private List<PickUpObj> items = new ArrayList<PickUpObj>();
	private IRollExtension extension;

	public World(IRollExtension extension) {
		this.extension = extension;
		spawnItems();
	}

	private void spawnItems() {
		// Spawn some pickup object, adding them to items
		int numItems = 13;
		float r = 5.5f; // items are disposed on a circle having a radius of 5.5
		for (int i = 0; i < numItems; i++) {
			items.add(new PickUpObj(i, new Transform(r
					* Math.cos(i * 2 * Math.PI / 13), 0.5, r
					* Math.sin(i * 2 * Math.PI / 13), Math.PI / 4, Math.PI / 4,
					Math.PI / 4)));
		}
		// A real approach would be read a config-pickUp.xml file, shared
		// between
		// server and client side installation
		// but never sent or transmitted, always a priori defined
	}

	// Gets the player corresponding to the specified SFS user
	private Player getPlayer(User u) {
		for (Player player : players) {
			if (player.getSfsUser().getId() == u.getId()) {
				return player;
			}
		}
		return null;
	}

	private boolean isValidNewTransform(Player player, Transform newTransform) {

		// Check if the given transform is valid in terms of collisions, speed
		// hacks etc
		// In this example, the server will always accept a new transform from
		// the client

		return true;
	}

	// Add new player if he doesn't exist, or resurrect him if he already added
	public boolean addPlayer(User user) {
		Player player = getPlayer(user);

		if (player == null) {
			player = new Player(user);
			players.add(player);
			extension.clientInstantiatePlayer(player);
			return true;
		} else {
			extension.clientInstantiatePlayer(player);
			return false;
		}
	}

	// When user lefts the room or disconnects - removing him from the players
	// list
	public void userLeft(User user) {
		Player player = this.getPlayer(user);
		if (player == null) {
			return;
		}
		players.remove(player);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Transform getTransform(User u) {
		return this.getPlayer(u).getTransform();
	}
}
