package com.thoride.chaosmod.chaosevents.quests;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;


@EventBusSubscriber(modid = ChaosMod.MODID, bus = Bus.FORGE)

public class yLevel {
	private static int i = 0;
	public static int y = 0;
	// player and if they are questing
	public static Map<UUID, Boolean> playerMap = new TreeMap<>();

	@SubscribeEvent
	public static void onPlayerTick(final PlayerTickEvent event) {
		if (!playerMap.containsKey(event.player.getUniqueID())) {
			playerMap.put(event.player.getUniqueID(), false);
		}
		if (playerMap.get(event.player.getUniqueID()) == true && EventHandler.enabled) {
			// System.out.println(isQuesting);
			i++;
			if (playerMap.get(event.player.getUniqueID()) == true) {
				//System.out.println((int)event.player.getPosY());
				if ((int)event.player.getPosY() == y) {
					event.player.sendMessage(
							new StringTextComponent(
									EventHandler.getRandomColor() + "Quest complete! You got to y level: " + y),
							event.player.getUniqueID());
					i = 0;
					event.player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0F, 1.0F);
					playerMap.replace(event.player.getUniqueID(), false);
				}
			}

			if (i >= (60 * 80) - 20 && playerMap.get(event.player.getUniqueID()) == true) {

				event.player.sendMessage(
						new StringTextComponent(
								EventHandler.getRandomColor() + "You couldn't get to y level: " + y + " in time. :("),
						event.player.getUniqueID());
				kill(event.player);
				i = 0;
				playerMap.replace(event.player.getUniqueID(), false);

			}
		} else if (EventHandler.enabled == false) {
			i = 0;
		}
	}

	public static void getRandomYPlayer(PlayerEntity p) {
		if (!playerMap.containsKey(p.getUniqueID())) {
			playerMap.put(p.getUniqueID(), false);
		}
		if (playerMap.get(p.getUniqueID()) == false) {
			i = 0;
			playerMap.replace(p.getUniqueID(), true);
			Random randomY = new Random();
			y = randomY.nextInt(255) + 1;
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Get to Y-level: " + y), p.getUniqueID());
		}else if (playerMap.get(p.getUniqueID()) == true) {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "keep going to the y lvl my mans"),
					p.getUniqueID());
		}


	}

	public static void kill(PlayerEntity p) {
		if (!p.world.isRemote) {
			System.out.println("killing");
			p.onKillCommand();
		}
	}
}