package com.thoride.chaosmod.chaosevents;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;

public class jump {
	public static void disableJump(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Hmm, seems like you're glued to the ground!"), p.getUniqueID());
			p.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, ChaosMod.timeBetweenChaos * 20, 129));
			
		}
	}
}
