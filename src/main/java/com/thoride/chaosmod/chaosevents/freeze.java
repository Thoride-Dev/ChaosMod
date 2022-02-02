package com.thoride.chaosmod.chaosevents;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;

public class freeze {
	public static void freezePlayer(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Freeze! Every body clap your hands!"), p.getUniqueID());
			p.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, ChaosMod.timeBetweenChaos * 20, 129));
			p.addPotionEffect(new EffectInstance(Effects.SLOWNESS, ChaosMod.timeBetweenChaos * 20, 129));
		}
	}
}
