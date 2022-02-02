package com.thoride.chaosmod.chaosevents.quests;

import java.util.Random;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = ChaosMod.MODID, bus = Bus.FORGE)

public class obtainItem {
	public static boolean isQuesting = false;
	public static Item itemToObtain = null;
	private static int i = 0;

	@SubscribeEvent
	public static void onInventoryUpdate(final PlayerTickEvent event) {
		if (isQuesting && EventHandler.enabled) {
			// System.out.println(isQuesting);
			if (event.player.inventory.hasItemStack(new ItemStack(itemToObtain))) {
				for (PlayerEntity player : event.player.world.getPlayers()) {
					player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Congrats! You survived this time!"),
							player.getUniqueID());
					player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0F, 1.0F);
				}
				i = 0;
				isQuesting = false;
			} else {
				i++;
			}
			if (i >= (300 * 80) - 20 && isQuesting == true) {
				for (PlayerEntity player : event.player.world.getPlayers()) {
					player.sendMessage(new StringTextComponent(TextFormatting.DARK_RED + "You failed! D:"),
							player.getUniqueID());
					kill(player);
				}
				i = 0;
				isQuesting = false;
			}

		} else if (EventHandler.enabled == false) {
			i = 0;
		}
	}

	public static void startQuest(PlayerEntity p) {
		if (!isQuesting) {
			i = 0;
			itemToObtain = getRandomItem();
			isQuesting = true;
			String itemName = ForgeRegistries.ITEMS.getValue(itemToObtain.getRegistryName()).toString();
			itemName = itemName.replaceAll("_", " ");
			for (PlayerEntity player : p.world.getPlayers()) {
				player.sendMessage(new StringTextComponent(
						EventHandler.getRandomColor() + "You have 5 minutes to get: " + TextFormatting.GOLD + itemName),
						player.getUniqueID());

			}
			
		} else {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Keep searching for that item"), p.getUniqueID());

		}

	}

	public static Item[] itemRegCache;

	public static Item getRandomItem() {
		if (itemRegCache == null) {
			itemRegCache = ForgeRegistries.ITEMS.getValues()
					.toArray(new Item[ForgeRegistries.ITEMS.getValues().size()]);
		}
		Random r = new Random();
		Item i = itemRegCache[r.nextInt(itemRegCache.length)];
		System.out.println(i.toString());
		return i;
	}

	public static void kill(PlayerEntity p) {
		if (!p.world.isRemote) {
			System.out.println("killing");
			p.onKillCommand();
		}
	}
}
