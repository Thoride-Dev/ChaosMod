package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

public class changeTime {
	public static void setTime(PlayerEntity p) {
		if (!p.world.isRemote) {
			Random r = new Random();
			int time = r.nextInt(24000);	
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Time set to: " + time), p.getUniqueID());
			for (ServerWorld server : p.getServer().getWorlds()) {
				server.setDayTime((long) time);
			}
			

			

		}	
	}
}


