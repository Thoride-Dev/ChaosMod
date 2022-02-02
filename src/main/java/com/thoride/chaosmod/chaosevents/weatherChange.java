package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class weatherChange {
	public static void changeWeather(PlayerEntity p) {
		if(!p.world.isRemote) {
			Random r = new Random();
			int time = r.nextInt(12000);
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Changing weather!"), p.getUniqueID());
			ServerWorld server = p.getServer().getWorld(World.OVERWORLD);
			if (p.world.isRaining()) {
				server.func_241113_a_(time, 0, false, false);
			}else if (p.world.isThundering()) {
				server.func_241113_a_(time, 0, false, false);
			}else if (!p.world.isRaining() && !p.world.isThundering()) {
				server.func_241113_a_(0, time, true, false);
			}
			

		}
	}
}
		
