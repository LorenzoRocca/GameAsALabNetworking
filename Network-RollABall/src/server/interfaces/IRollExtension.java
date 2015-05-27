package server.interfaces;

import server.model.PickUpObj;
import server.model.Player;
import server.model.Transform;
import server.model.World;

import com.smartfoxserver.v2.entities.User;

public interface IRollExtension {
	// Methods for sending messages to clients
	public void clientInstantiatePlayer(Player player, User targetUser);

	public void clientInstantiatePlayer(Player player);

	public void clientSendTransform(User fromUser, Transform resultTransform);

	public void clientSendRejectTransform(User u);

	public void clientRemoveItem(PickUpObj item, Player player);

	public void clientGameOver(Player winner);

	// Getter methods
	public World getWorld();
}
