package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

public class enchant {
	public static void enchantHand(PlayerEntity p) {
		if (!p.world.isRemote) {		
			Random r = new Random();
			int level = r.nextInt(7) + 1;
			p.getHeldItemMainhand().addEnchantment(getRandomEnchant(), level);
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Item in hand enchanted!"), p.getUniqueID());
		}
	}
	
	public static Enchantment[] enchantRegCache;
	
	public static Enchantment getRandomEnchant() {
		if (enchantRegCache == null) {
			enchantRegCache = ForgeRegistries.ENCHANTMENTS.getValues().toArray(new Enchantment[ForgeRegistries.ENCHANTMENTS.getValues().size()]);
		}

		Random r = new Random();
		Enchantment i = enchantRegCache[r.nextInt(enchantRegCache.length)];
		System.out.println(i.toString());
		return i;
	}
}
