package com.thoride.chaosmod.chaosevents;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class dropItem {
	public static void drop(PlayerEntity p) {
		if (!p.world.isRemote) {
			
			if (p.getHeldItemMainhand() != ItemStack.EMPTY) {
				ItemStack itemInMainHand = p.getHeldItemMainhand();
				p.dropItem(itemInMainHand, false);
				PlayerInventory inventory = p.inventory;
				inventory.deleteStack(itemInMainHand);
				p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Oops it slipped"), p.getUniqueID());
			}else {
				p.sendMessage(new StringTextComponent(TextFormatting.DARK_RED + "" + TextFormatting.BOLD + "Oops it ALL slipped >:)"), p.getUniqueID());
				p.inventory.dropAllItems();
			}

		}
	}
}
