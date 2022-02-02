package com.thoride.chaosmod.core.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class CommandCMDisable implements Command<CommandSource> {


private static final CommandCMDisable CMD = new CommandCMDisable();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("disable").requires(cs -> cs.hasPermissionLevel(0)).executes(CMD);
    }
  
  
  
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		
        if (!EventHandler.enabled) {
  context.getSource().sendFeedback(new StringTextComponent(TextFormatting.DARK_RED + "Chaos is already disabled!"), false);

        }else if (EventHandler.enabled){
 context.getSource().sendFeedback(new StringTextComponent(TextFormatting.RED + "Chaos has been disabled!"), false);
        context.getSource().asPlayer().playSound(SoundEvents.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER,
		 1.0F, 1.0F);
		EventHandler.enabled = false;
		context.getSource().asPlayer().abilities.allowFlying = false;
		context.getSource().asPlayer().abilities.isFlying = false;
		context.getSource().asPlayer().sendPlayerAbilities();
        EventHandler.i = 0;
        }
        
        
        return 0;

    }



}