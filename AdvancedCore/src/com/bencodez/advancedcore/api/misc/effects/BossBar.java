package com.bencodez.advancedcore.api.misc.effects;

import java.util.List;

import com.bencodez.advancedcore.scheduler.BukkitScheduler;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

import com.bencodez.advancedcore.AdvancedCorePlugin;
import com.bencodez.advancedcore.api.messages.StringParser;

import lombok.Getter;

// TODO: Auto-generated Javadoc
/**
 * The Class BossBar.
 */
public class BossBar {

	@Getter
	private org.bukkit.boss.BossBar bossBar;

	/**
	 * Instantiates a new boss bar.
	 *
	 * @param msg      the msg
	 * @param barColor the bar color
	 * @param barStyle the bar style
	 * @param progress the progress
	 */
	public BossBar(String msg, String barColor, String barStyle, double progress) {
		bossBar = Bukkit.createBossBar(StringParser.getInstance().colorize(msg), BarColor.valueOf(barColor),
				BarStyle.valueOf(barStyle), BarFlag.DARKEN_SKY);
		bossBar.setProgress(progress);
	}

	public void addPlayer(Player player) {
		bossBar.addPlayer(player);
	}

	public void addPlayer(final Player player, int delay) {
		try {
			if (player == null) {
				return;
			}
			bossBar.addPlayer(player);

			if (delay > 0) {
				BukkitScheduler.runTaskLater(AdvancedCorePlugin.getInstance(), new Runnable() {

					@Override
					public void run() {
						if (bossBar != null && player != null) {
							bossBar.removePlayer(player);
						}
					}
				}, delay * 20);
			}
		} catch (Exception e) {
			AdvancedCorePlugin.getInstance().debug(e);
		}
	}

	public List<Player> getPlayers() {
		return bossBar.getPlayers();
	}

	public void hide() {
		if (bossBar != null) {
			bossBar.setVisible(false);
			bossBar.removeAll();
		}
	}

	private void hideInDelay(int delay) {
		BukkitScheduler.runTaskLater(AdvancedCorePlugin.getInstance(), new Runnable() {

			@Override
			public void run() {
				hide();
			}
		}, delay);
	}

	public void removePlayer(Player player) {
		bossBar.removePlayer(player);
	}

	public void send() {
		bossBar.setVisible(true);
	}

	public void send(int delay) {
		bossBar.setVisible(true);

		hideInDelay(delay);
	}

	/**
	 * Send.
	 *
	 * @param player the player
	 * @param delay  the delay
	 */
	public void send(Player player, int delay) {
		bossBar.addPlayer(player);
		bossBar.setVisible(true);
		hideInDelay(delay);
	}

	public void setColor(String barColor) {
		if (barColor != null) {
			bossBar.setColor(BarColor.valueOf(barColor));
		}
	}

	public void setProgress(double progress) {
		if (progress > 1) {
			progress = 1;
		}
		if (progress < 0) {
			progress = 0;
		}
		bossBar.setProgress(progress);
	}

	public void setStyle(String barStyle) {
		if (barStyle != null) {
			bossBar.setStyle(BarStyle.valueOf(barStyle));
		}
	}

	public void setTitle(String title) {
		if (title != null) {
			bossBar.setTitle(StringParser.getInstance().colorize(title));
		}

	}

	public void setVisible(boolean visible) {
		bossBar.setVisible(visible);
	}
}