package server.model;

import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;

//Item class describes an item on the scene
public class PickUpObj {
	public static final double touchDistance = 1.5; // How close must the player
													// approach to the item to
													// take it

	private int id; // The unique id of the item
	private Transform transform; // The position and rotation of the item in the
									// world

	public PickUpObj(int id, Transform transform) {
		this.id = id;
		this.transform = transform;
	}

	// Put the item info to JSON object to send it to client
	public void toSFSObject(ISFSObject data) {
		ISFSObject itemData = new SFSObject();

		itemData.putInt("id", id);
		this.transform.toSFSObject(itemData);
		data.putSFSObject("item", itemData);
	}

	public boolean isClose(Transform newTransform) {
		double d = newTransform.distanceTo(this.transform);
		return (d <= touchDistance);
	}

	@Override
	public String toString() {
		String res = this.getClass().toString();
		res += "_id:" + id;
		res += "_Transform"+":" + transform.getX() + "_" + transform.getY() + "_"
				+ transform.getZ();
		return res;
	}
}
