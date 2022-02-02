package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.entity.ElderGuardianRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.registries.ForgeRegistries;

public class jumpscare {
	public static void scare(PlayerEntity p) {
		if (!p.world.isRemote) {
			//p.world.addParticle(getRandomEntity(),p.getPosX(), p.getPosY(), p.getPosZ(), 0, 1, 5);
			System.out.println(getRandomEntity());
			p.playSound(SoundEvents.ENTITY_WITHER_DEATH, SoundCategory.MASTER, 1.0F, 1.0F);
			//p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Mob spawned!"), p.getUniqueID());
		}

	}
	
	public static IParticleData[] entityRegCache;

	public static IParticleData getRandomEntity() {
		if (entityRegCache == null) {
			entityRegCache = ForgeRegistries.PARTICLE_TYPES.getValues()
					.toArray(new IParticleData[ForgeRegistries.PARTICLE_TYPES.getValues().size()]);
		}

		Random r = new Random();
		IParticleData i = entityRegCache[r.nextInt(entityRegCache.length)];
		System.out.println(i.toString());
		return i;
	}
}
