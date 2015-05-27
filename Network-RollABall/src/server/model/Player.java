package server.model;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class Player {

	private User sfsUser; // SFS user that corresponds to this player
	private int score = 0;
	private Transform transform; // Transform of the player that is synchronized
									// with clients

	public Player(User sfsUser) {
		this.sfsUser = sfsUser;
		this.transform = Transform.random();
	}

	public void toSFSObject(ISFSObject data) {
		ISFSObject playerData = new SFSObject();

		playerData.putInt("id", sfsUser.getId());
		playerData.putInt("score", this.score);

		transform.toSFSObject(playerData);
		data.putSFSObject("player", playerData);
	}

	public User getSfsUser() {
		return sfsUser;
	}

	public Transform getTransform() {
		return transform;
	}

	public void incScore() {
		score++;
	}

	public int getScore() {
		return score;
	}
}
