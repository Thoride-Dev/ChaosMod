package com.thoride.chaosmod.core.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.chaosevents.changeTime;
import com.thoride.chaosmod.chaosevents.dropItem;
import com.thoride.chaosmod.chaosevents.enchant;
import com.thoride.chaosmod.chaosevents.fire;
import com.thoride.chaosmod.chaosevents.flying;
import com.thoride.chaosmod.chaosevents.freeze;
import com.thoride.chaosmod.chaosevents.handSwap;
import com.thoride.chaosmod.chaosevents.headRotation;
import com.thoride.chaosmod.chaosevents.itemSpawn;
import com.thoride.chaosmod.chaosevents.jump;
import com.thoride.chaosmod.chaosevents.jumpscare;
import com.thoride.chaosmod.chaosevents.mobSpawn;
import com.thoride.chaosmod.chaosevents.movePlayer;
import com.thoride.chaosmod.chaosevents.playSound;
import com.thoride.chaosmod.chaosevents.playerSwap;
import com.thoride.chaosmod.chaosevents.potionEffect;
import com.thoride.chaosmod.chaosevents.teleport;
import com.thoride.chaosmod.chaosevents.weatherChange;
import com.thoride.chaosmod.chaosevents.xp;
import com.thoride.chaosmod.chaosevents.quests.obtainItem;
import com.thoride.chaosmod.chaosevents.quests.pacifistQuest;
import com.thoride.chaosmod.chaosevents.quests.yLevel;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

@EventBusSubscriber(modid = ChaosMod.MODID, bus = Bus.FORGE)
public class EventHandler {
	public static boolean enabled = false;
	public static int i = 0;
	public static Map<UUID, Boolean> flyingMap = new TreeMap<>();
	public static List<String> activeModules = new ArrayList<String>();
    public static List<String> modules = new ArrayList<String>();
    public static List<PlayerEntity> playerList = new ArrayList<PlayerEntity>();

	@SubscribeEvent
	public static void onTimeChange(final PlayerTickEvent event) {
		if (enabled) {

			i++;
			if (i >= ChaosMod.timeBetweenChaos * 80 - 800) {

				chaosTimer.countdown(event.player, i);
			}

		}

		// System.out.println(i);
		// timer reached, cause event
		if (i >= ChaosMod.timeBetweenChaos * 80) {
			i = 0;

			for (PlayerEntity player : event.player.world.getPlayers()) {
				System.out.println("Causing Chaos on: " + player.getUniqueID());
				causeChaos(event.player);
			}

		}

	}
	
	@SubscribeEvent
	public static void onPlayerJoin(final PlayerLoggedInEvent event) {
		if(!playerList.contains(event.getPlayer())) {
			playerList.add(event.getPlayer());
		}
	}
	@SubscribeEvent
	public static void onPlayerLeave(final PlayerLoggedOutEvent event) {
		if(playerList.contains(event.getPlayer())) {
			playerList.remove(event.getPlayer());
		}
	}
	
	@SubscribeEvent
	public static void onStart(final FMLServerStartedEvent event) {
		setModules();
		//activeModules.addAll(modules);
	}

	@SubscribeEvent
	public static void onRespawn(final PlayerRespawnEvent event) {

		if (event.getEntity() instanceof PlayerEntity) {
			if (enabled) {
				enabled = false;
				i = 0;
				System.out.println("DISABLED");
				enabled = true;
				System.out.println("ENABLED");
			}

		}

	}

	private static void causeChaos(PlayerEntity player) {

		TextFormatting[] chaosColor = { TextFormatting.RED, TextFormatting.GREEN, TextFormatting.YELLOW,
				TextFormatting.GOLD, TextFormatting.BLACK };
		Random r = new Random();
		TextFormatting randomChaosColor = chaosColor[r.nextInt(chaosColor.length)];

		player.sendMessage(new StringTextComponent(randomChaosColor + "CHAOS INCOMING!"), player.getUniqueID());
		player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 1.0F, 1.0F);

		Random random = new Random();
		int x = random.nextInt(activeModules.size());
		String moduleCalled = activeModules.get(x);
		// System.out.println("X is: " + x);
		//x = 12; // force event
		if (moduleCalled.equalsIgnoreCase("itemSpawning")) {
			System.out.println("Giving Item!");
			itemSpawn.giveItem(player);
		} else if (moduleCalled.equalsIgnoreCase("changeTime")) {
			System.out.println("Setting time!");
			changeTime.setTime(player);
		} else if (moduleCalled.equalsIgnoreCase("teleport")) {
			System.out.println("Teleporting!");
			teleport.teleportPlayer(player);
		} else if (moduleCalled.equalsIgnoreCase("potionEffect")) {
			System.out.println("Giving effect!");
			potionEffect.giveEffect(player);
		} else if (moduleCalled.equalsIgnoreCase("weatherChange")) {
			System.out.println("Changing weather!");
			weatherChange.changeWeather(player);
		} else if (moduleCalled.equalsIgnoreCase("headRotation")) {
			System.out.println("so no head");
			headRotation.changeRotation(player);
		} else if (moduleCalled.equalsIgnoreCase("bamboozle")) {
			System.out.println("moving player");
			movePlayer.move(player);
		} else if (moduleCalled.equalsIgnoreCase("dropItem")) {
			System.out.println("dropping item");
			dropItem.drop(player);
		} else if (moduleCalled.equalsIgnoreCase("setFire")) {
			System.out.println("setting fire");
			fire.setFire(player);
		} else if (moduleCalled.equalsIgnoreCase("mobSpawning")) {
			System.out.println("Spawning mob");
			mobSpawn.spawnMob(player);
		} else if (moduleCalled.equalsIgnoreCase("enchantHand")) {
			System.out.println("Enchanting");
			enchant.enchantHand(player);
		} else if (moduleCalled.equalsIgnoreCase("noJumping")) {
			System.out.println("no jump for you");
			jump.disableJump(player);
		} else if (moduleCalled.equalsIgnoreCase("allowFly")) {
			System.out.println("fly!");
			flying.enableFlight(player);

		}else if (moduleCalled.equalsIgnoreCase("playSound")) {
			System.out.println("playing sound");
			playSound.play(player);
		}else if (moduleCalled.equalsIgnoreCase("swapHands")) {
			System.out.println("hands swapped");
			handSwap.swap(player);
		}else if (moduleCalled.equalsIgnoreCase("jumpscare")) {
				System.out.println("jump and scared");
				jumpscare.scare(player);
		}else if (moduleCalled.equalsIgnoreCase("obtainItemQuest")) {
			System.out.println("go get it");
			obtainItem.startQuest(player);
		}else if (moduleCalled.equalsIgnoreCase("pacifist")) {
			System.out.println("be nice");
			pacifistQuest.startQuest(player);
		}else if (moduleCalled.equalsIgnoreCase("reachY")) {
			System.out.println("go!");
			yLevel.getRandomYPlayer(player);
		}
		else if (moduleCalled.equalsIgnoreCase("freeze")) {
			System.out.println("hammer time");
			freeze.freezePlayer(player);
		}else if (moduleCalled.equalsIgnoreCase("xp")) {
			System.out.println("stonks!");
			xp.xpMod(player);
		}else if (moduleCalled.equalsIgnoreCase("swap")) {
			System.out.println("swapping!");
			playerSwap.swap(player);
		}

	}

	public static TextFormatting getRandomColor() {
		TextFormatting[] chaosColor = { TextFormatting.RED, TextFormatting.GREEN, TextFormatting.YELLOW,
				TextFormatting.GOLD, TextFormatting.BLACK, TextFormatting.GOLD, TextFormatting.DARK_GRAY,
				TextFormatting.DARK_BLUE, TextFormatting.DARK_AQUA, TextFormatting.DARK_PURPLE, TextFormatting.GRAY,
				TextFormatting.BLUE, TextFormatting.AQUA, TextFormatting.LIGHT_PURPLE, TextFormatting.WHITE,
				TextFormatting.ITALIC, TextFormatting.UNDERLINE, TextFormatting.BOLD };
		Random r = new Random();
		TextFormatting randomChaosColor = chaosColor[r.nextInt(chaosColor.length)];
		return randomChaosColor;
	}
	
	public static void setModules() {
		
		modules.add("itemSpawning");
		modules.add("changeTime");
		modules.add("teleport");
		modules.add("potionEffect");
		modules.add("weatherChange");
		//modules.add("headRotation");
		modules.add("bamboozle");
		modules.add("dropItem");
		modules.add("setFire");
		modules.add("mobSpawning");
		modules.add("enchantHand");
		modules.add("noJumping");
		modules.add("allowFly");
		modules.add("playSound");
		modules.add("swapHands");
		//modules.add("jumpscare");
		modules.add("obtainItemQuest");
		modules.add("pacifist");
		modules.add("reachY");
		modules.add("freeze");
		modules.add("xp");
		modules.add("swap");

	}
}
