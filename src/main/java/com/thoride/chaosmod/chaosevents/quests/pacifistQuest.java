package com.thoride.chaosmod.chaosevents.quests;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.datafix.fixes.PlayerUUID;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(modid = ChaosMod.MODID, bus = Bus.FORGE)

public class pacifistQuest {
	// public static boolean isQuesting = false;
	private static int i = 0;
	// player and if they are questing
	public static Map<UUID, Boolean> playerMap = new TreeMap<>();

	@SubscribeEvent
	public static void onPlayerTick(final PlayerTickEvent event) {
		if (!playerMap.containsKey(event.player.getUniqueID())) {
			playerMap.put(event.player.getUniqueID(), false);
		}
		if (playerMap.get(event.player.getUniqueID()) == true && EventHandler.enabled) {
			// System.out.println(isQuesting);
			i++;
			if (i >= (300 * 40) - 20 && playerMap.get(event.player.getUniqueID()) == true) {
				event.player.sendMessage(
						new StringTextComponent(TextFormatting.GREEN + "Ok PETA doesn't care anymore."),
						event.player.getUniqueID());
				i = 0;
				event.player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 1.0F, 1.0F);
				playerMap.replace(event.player.getUniqueID(), false);
			}
		} else if (EventHandler.enabled == false) {
			i = 0;
		}
	}

	@SubscribeEvent
	public static void onPlayerAttack(final AttackEntityEvent event) {
		if (playerMap.get(event.getPlayer().getUniqueID()) == true) {
			PlayerEntity p  = event.getPlayer();
			if (p.getHealth() <= 1) {
				kill(p);
			} else {
				System.out.println("taking damage");
				p.attackEntityFrom(DamageSource.MAGIC, 4.0f);
				// mainPlayer.setHealth(mainPlayer.getHealth() -1);
			}

		}
	}

	public static void startQuest(PlayerEntity p) {

		if (playerMap.get(p.getUniqueID()) == false) {
			i = 0;
			playerMap.replace(p.getUniqueID(), true);

			p.sendMessage(
					new StringTextComponent(
							EventHandler.getRandomColor() + "Be kind to the animals or i'm gonna call PETA."),
					p.getUniqueID());

		} else if (playerMap.get(p.getUniqueID()) == true) {
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Keep being kind :)"),
					p.getUniqueID());
		}
		

	}

	public static void kill(PlayerEntity p) {
		if (!p.world.isRemote) {
			System.out.println("killing");
			p.onKillCommand();
			playerMap.replace(p.getUniqueID(), false);
		}
	}

}
