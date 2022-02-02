package com.thoride.chaosmod.chaosevents;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.HandSide;
import net.minecraft.util.text.StringTextComponent;

public class handSwap {
	public static void swap(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Hmm, that doesn't feel right."), p.getUniqueID());
			if(p.getPrimaryHand() == HandSide.RIGHT) {
				p.setPrimaryHand(HandSide.LEFT);
			}else {
				p.setPrimaryHand(HandSide.RIGHT);
			}

		}
	}
}
