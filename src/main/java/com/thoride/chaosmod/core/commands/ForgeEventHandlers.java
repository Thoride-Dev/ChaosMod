package com.thoride.chaosmod.core.commands;

import org.jline.reader.impl.history.DefaultHistory;

import com.thoride.chaosmod.ChaosMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = ChaosMod.MODID, bus = Bus.FORGE)
public class ForgeEventHandlers {
	

	@SubscribeEvent
	public static void registerCommands(final RegisterCommandsEvent event) {	
		ModCommands.register(event.getDispatcher());
		
	}
}