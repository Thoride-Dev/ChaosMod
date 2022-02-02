package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;

public class playerSwap {
	public static void swap(PlayerEntity p1) {
		if (!p1.world.isRemote) {
			if (EventHandler.playerList.size() >= 2) {
				double p1x, p1y, p1z;
				Random rand = new Random();
				PlayerEntity p2 = EventHandler.playerList.get(rand.nextInt(EventHandler.playerList.size()));
				p1x = p1.getPosX();
				p1y = p1.getPosY();
				p1z = p1.getPosZ();
				p1.setPositionAndUpdate(p2.getPosX(), p2.getPosY(), p2.getPosZ());
				p2.setPositionAndUpdate(p1x, p1y, p1z);
			}
		}
	}
}
