package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;

public class mobSpawn {
	public static void spawnMob(PlayerEntity p) {
		if (!p.world.isRemote) {
			ServerWorld server = p.getServer().getWorld(p.world.getDimensionKey());			
			getRandomEntity().spawn(server, null, p, p.getPosition(), SpawnReason.MOB_SUMMONED, false, false);
			p.sendMessage(new StringTextComponent(EventHandler.getRandomColor() + "Mob spawned!"), p.getUniqueID());

		}

		/**
		 * if(!p.world.isRemote) { EntityType<?> type = getRandomEntity();
		 * 
		 * entity.setPosition(p.getPosX(), p.getPosY() + 2, p.getPosZ());
		 * System.out.println("Entity: " + entity); p.world.addEntity(entity); }
		 **/
	}

	public static EntityType<?>[] entityRegCache;

	public static EntityType<?> getRandomEntity() {
		if (entityRegCache == null) {
			entityRegCache = ForgeRegistries.ENTITIES.getValues()
					.toArray(new EntityType[ForgeRegistries.ENTITIES.getValues().size()]);
		}

		Random r = new Random();
		EntityType<?> i = entityRegCache[r.nextInt(entityRegCache.length)];
		System.out.println(i.toString());
		return i;
	}

}
