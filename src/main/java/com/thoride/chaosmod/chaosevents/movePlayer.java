package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public class movePlayer {
	public static void move(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "You got Bamboozled"), p.getUniqueID());
			//p.moveForced(p.getPosX() + 5, p.getPosY(), p.getPosZ() + 5);
			Random r = new Random();
			int x = r.nextInt(11) -5;
			int z = r.nextInt(11) -5;

			p.setPositionAndUpdate(p.getPosX() + x, p.getPosY(), p.getPosZ() + z);
		}
	}
}
