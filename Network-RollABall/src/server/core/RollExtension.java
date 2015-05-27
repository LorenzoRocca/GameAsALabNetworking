package server.core;

import java.util.Date;
import java.util.List;

import server.common.*;
import server.handlers.*;
import server.interfaces.IRollExtension;
import server.model.PickUpObj;
import server.model.Player;
import server.model.Transform;
import server.model.World;

import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

//The extension main class. Used to handle requests from clients and send messages back to them
//All communication is done with json objects
//
//Requests that can be send from clients:
//- sendTransform
//- spawnMe
//- getTime

//
//Responses send from the extention to clients:
//- time
//- spawnPlayer
//- transform
//- notransform
//- gameOver
//- removeItem
//
public class RollExtension extends SFSExtension implements IRollExtension {

	private World world; // Reference to World simulation model

	public World getWorld() {
		return world;
	}

	@Override
	public void init() {
		world = new World(this);
		trace("Roll Extension initialized");
		// TODO: Subscribe here the request handlers

		// Basic Handlers for game synchronization
		addRequestHandler("getTime", GetTimeHandler.class);
	}

	public void clientInstantiatePlayer(Player player, User targetUser) {
		ISFSObject data = new SFSObject();

		player.toSFSObject(data);
		Room currentRoom = RoomHelper.getCurrentRoom(this);
		if (targetUser == null) {
			// Sending to all the users
			List<User> userList = UserHelper.getRecipientsList(currentRoom);
			this.send("spawnPlayer", data, userList, false);
		} else {
			// Sending to the specified user
			this.send("spawnPlayer", data, targetUser, false);
		}
	}

	public void clientInstantiatePlayer(Player player) {
		clientInstantiatePlayer(player, null);
	}

	@Override
	public void clientSendTransform(User fromUser, Transform resultTransform) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientSendRejectTransform(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientRemoveItem(PickUpObj item, Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clientGameOver(Player winner) {
		// TODO Auto-generated method stub
		
	}

}
