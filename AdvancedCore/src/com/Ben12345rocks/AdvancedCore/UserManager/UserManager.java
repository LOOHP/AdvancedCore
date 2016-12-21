package com.Ben12345rocks.AdvancedCore.UserManager;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.Ben12345rocks.AdvancedCore.AdvancedCoreHook;
import com.Ben12345rocks.AdvancedCore.Objects.UUID;
import com.Ben12345rocks.AdvancedCore.Objects.User;
import com.Ben12345rocks.AdvancedCore.Util.Misc.PlayerUtils;

/**
 * The Class UserManager.
 */
public class UserManager {

	/** The instance. */
	static UserManager instance = new UserManager();

	/** The plugin. */
	AdvancedCoreHook plugin = AdvancedCoreHook.getInstance();

	/**
	 * Gets the single instance of UserManager.
	 *
	 * @return single instance of UserManager
	 */
	public static UserManager getInstance() {
		return instance;
	}

	/**
	 * Instantiates a new user manager.
	 */
	public UserManager() {
	}

	/**
	 * Gets the user.
	 *
	 * @param player
	 *            the player
	 * @return the user
	 */
	public synchronized User getUser(OfflinePlayer player) {
		return getUser(player.getName());
	}

	/**
	 * Gets the user.
	 *
	 * @param player
	 *            the player
	 * @return the user
	 */
	public synchronized User getUser(Player player) {
		return getUser(player.getName());
	}

	/**
	 * Gets the user.
	 *
	 * @param playerName
	 *            the player name
	 * @return the user
	 */
	public synchronized User getUser(String playerName) {
		return getUser(new UUID(PlayerUtils.getInstance().getUUID(playerName)));
	}

	/**
	 * Gets the user.
	 *
	 * @param uuid
	 *            the uuid
	 * @return the user
	 */
	@SuppressWarnings("deprecation")
	public synchronized User getUser(UUID uuid) {
		return new User(plugin.getPlugin(), uuid);
	}

	public synchronized ArrayList<String> getAllUUIDs() {
		File folder = new File(plugin.getPlugin().getDataFolder() + File.separator + "Data");
		String[] fileNames = folder.list();
		ArrayList<String> uuids = new ArrayList<String>();
		if (fileNames != null) {
			for (String playerFile : fileNames) {
				if (!playerFile.equals("null") && !playerFile.equals("")) {
					String uuid = playerFile.replace(".yml", "");
					uuids.add(uuid);
				}
			}
		}
		return uuids;
	}
}
