package com.thoride.chaosmod.chaosevents;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ChaosMod.MODID, bus = Bus.FORGE)

public class flying {

	private static int i = 0;
	public static int y = 0;
	// player and if they can fly
	public static Map<UUID, Boolean> playerMap = new TreeMap<>();

	@SubscribeEvent
	public static void onPlayerTick(final PlayerTickEvent event) {
		if (!playerMap.containsKey(event.player.getUniqueID())) {
			playerMap.put(event.player.getUniqueID(), false);
		}

		if (playerMap.get(event.player.getUniqueID()) == true && EventHandler.enabled) {
			// System.out.println(isQuesting);
			i++;

			if (i >= (ChaosMod.timeBetweenChaos * 40) - 20 && playerMap.get(event.player.getUniqueID()) == true) {
				if (!event.player.world.isRemote) {
					event.player.abilities.allowFlying = false;
					event.player.abilities.isFlying = false;
					event.player.sendPlayerAbilities();
					System.out.println(event.player.abilities.allowFlying);
				}
				i = 0;
				playerMap.replace(event.player.getUniqueID(), false);
			}
		} else if (EventHandler.enabled == false) {
			i = 0;
		}
	}

	public static void enableFlight(PlayerEntity p) {
		if (!playerMap.containsKey(p.getUniqueID())) {
			playerMap.put(p.getUniqueID(), false);
		}
		if (playerMap.get(p.getUniqueID()) == false) { // if their flight is disabled
			i = 0;
			playerMap.replace(p.getUniqueID(), true);
			if (!p.world.isRemote) {
				p.abilities.allowFlying = true;
				p.sendPlayerAbilities();
				p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Fly me to the moon!"),
						p.getUniqueID());
			}
		}

	}

}
