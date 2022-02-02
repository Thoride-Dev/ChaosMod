package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class fire {
	public static void setFire(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.sendMessage(new StringTextComponent(TextFormatting.RED + "" + TextFormatting.BOLD + "BURN!!!"), p.getUniqueID());
			Random r = new Random();
			int time = r.nextInt(20);
			p.setFire(time);
		}
	}
}
