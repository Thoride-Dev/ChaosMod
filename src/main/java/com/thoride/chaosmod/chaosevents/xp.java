package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class xp {

	public static void xpMod(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.sendMessage(
					new StringTextComponent(
							EventHandler.getRandomColor() + "Lets see if you're lucky...."),
					p.getUniqueID());
			Random randAddOrRemove = new Random();
			boolean rRB = randAddOrRemove.nextBoolean();

			if (rRB) {
				Random randRemove = new Random();
				int rR = randRemove.nextInt(10) + 1;

				int removeAmount = rR * -1;
				p.addExperienceLevel(removeAmount);
                p.sendMessage(
					new StringTextComponent(
							TextFormatting.RED + "You're unlucky! You lost: " + removeAmount + " levels!"),
					p.getUniqueID());

			} else {

				Random randAdd = new Random();
				int rA = randAdd.nextInt(10) + 1;
				p.addExperienceLevel(rA);
				 p.sendMessage(
					new StringTextComponent(
							TextFormatting.GREEN + "You're lucky! You gained: " + rA + " levels!"),
					p.getUniqueID());
                
			}
		}
	}

}