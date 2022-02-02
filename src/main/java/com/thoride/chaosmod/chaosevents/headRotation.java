package com.thoride.chaosmod.chaosevents;

import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;

public class headRotation {
	public static void changeRotation(PlayerEntity p) {
		if (!p.world.isRemote) {
			Random r = new Random();
			int yaw = r.nextInt(360) - 180;
			int pitch = r.nextInt(180) - 90;
			System.out.println(pitch + " " + yaw);
			// p.rotationPitch = pitch;
			// p.rotationYawHead = yaw;
			p.rotateTowards(yaw, pitch);
		}

	}
}
