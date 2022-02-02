package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.registries.ForgeRegistries;

public class potionEffect {

	public static void giveEffect(PlayerEntity p) {
		if (!p.world.isRemote) {
			Effect test = getRandomEffect();
			Random randStr = new Random();
			int pStr = randStr.nextInt(6);
			Random randLen = new Random();
			int pLen = randLen.nextInt(1000);
			
			String efName = test.getName();
			efName = efName.replaceAll("effect.minecraft.", "");
			System.out.println("Effect: " + efName + " for: " + pLen + " ticks with strength: " + pStr);
			p.addPotionEffect(new EffectInstance(test, pLen, pStr));
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "You've been effected with: " + efName), p.getUniqueID());

		}
	}

	public static Effect[] potionRegCache;

	public static Effect getRandomEffect() {
		if (potionRegCache == null) {
			potionRegCache = ForgeRegistries.POTIONS.getValues()
					.toArray(new Effect[ForgeRegistries.POTIONS.getValues().size()]);
		}
		Random r = new Random();
		Effect i = potionRegCache[r.nextInt(potionRegCache.length)];
		System.out.println(i.toString());
		return i;

	}
}
