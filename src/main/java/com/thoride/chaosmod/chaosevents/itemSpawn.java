package com.thoride.chaosmod.chaosevents;

import java.util.Collection;
import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.EquipmentSlotType.Group;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class itemSpawn {


	public static void giveItem(PlayerEntity p) {
		if(!p.world.isRemote) {
			Item item = getRandomItem();
			Random x = new Random();
			int quantity = x.nextInt(10) + 1;
			PlayerInventory inventory = p.inventory;
			String itemName = ForgeRegistries.ITEMS.getValue(item.getRegistryName()).toString();
			itemName = itemName.replaceAll("_", " ");
			
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "You got " + quantity + " " + itemName), p.getUniqueID());
			if(inventory.getFirstEmptyStack() == -1) {
				p.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(item, quantity));
			}else {
				p.addItemStackToInventory(new ItemStack(item, quantity));
			
			}
		}	
	}
	public static Item[] itemRegCache;

	public static Item getRandomItem() {
		if(itemRegCache == null) {
			itemRegCache = ForgeRegistries.ITEMS.getValues().toArray(new Item[ForgeRegistries.ITEMS.getValues().size()]);
		}
		Random r = new Random();
		Item i = itemRegCache[r.nextInt(itemRegCache.length)];
		System.out.println(i.toString());
		return i;
	}

}

/**
 * 		//int randomVal = r.nextInt(values.getKeys().size() + 1);

 * for (Item item : values) {
			j++;
			if(j == randomVal) {
				i = item;
			}
		}
 */


//world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(Blah item here)));