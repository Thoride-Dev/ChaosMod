package com.thoride.chaosmod.core.event;

import java.text.DecimalFormat;

import com.thoride.chaosmod.ChaosMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class chaosTimer {

	public static void countdown(PlayerEntity p, int tick) {
		double timeLeft = ChaosMod.timeBetweenChaos - (tick / 80.0);
		String result = String.format("%.2f", timeLeft);
        p.sendStatusMessage(new StringTextComponent(TextFormatting.DARK_RED + "" + result + TextFormatting.RED + " Seconds until the next event!"), true);
	
    
        
    }

}