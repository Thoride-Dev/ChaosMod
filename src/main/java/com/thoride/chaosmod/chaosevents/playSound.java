package com.thoride.chaosmod.chaosevents;


import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.client.audio.Sound;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.registries.ForgeRegistries;

public class playSound {
	public static void play(PlayerEntity p) {
		if (!p.world.isRemote) {
			p.playSound(getRandomSound(), SoundCategory.MASTER, 1.0F, 1.0F);
			//p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Mob spawned!"), p.getUniqueID());
		}

	}

	public static SoundEvent[] soundRegCache;

	public static SoundEvent getRandomSound() {
		if (soundRegCache == null) {
			soundRegCache = ForgeRegistries.SOUND_EVENTS.getValues()
					.toArray(new SoundEvent[ForgeRegistries.SOUND_EVENTS.getValues().size()]);
		}

		Random r = new Random();
		SoundEvent i = soundRegCache[r.nextInt(soundRegCache.length)];
		System.out.println(i.toString());
		return i;
	}
	
}
