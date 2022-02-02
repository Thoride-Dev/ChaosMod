package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.registries.ForgeRegistries;


public class teleport {
	public static void teleportPlayer(PlayerEntity p) {
		
		if(!p.world.isRemote) {
			Random xRand = new Random();
			Random zRand = new Random();
			Random directionX = new Random();
			Random directionZ = new Random();

			int x = xRand.nextInt(500);
			int z = zRand.nextInt(500);
			int directionRandX = directionX.nextInt(2);
			int directionRandZ = directionZ.nextInt(2);

			if(directionRandX == 0) {
				x = -x;
			}
			if(directionRandZ == 0) {
				z = -z;
			}
			
			x += p.getPosX();
			z += p.getPosZ();
			
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "You were teleported to: " + x + 
			", " + z), p.getUniqueID());

			p.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 100, 255));
			System.out.println("Teleporting to: " + x + ", " + z);
			p.setPositionAndUpdate(x, 90, z);
		}	
		
	}
}

