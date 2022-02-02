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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class CommandCMEnable implements Command<CommandSource> {


private static final CommandCMEnable CMD = new CommandCMEnable();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("enable").requires(cs -> cs.hasPermissionLevel(0))
                .executes(CMD);
    }
  
  
  
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		
        if (EventHandler.enabled) {
  context.getSource().sendFeedback(new StringTextComponent(TextFormatting.RED + "Chaos is already enabled!"), false);
        
        
        }else if (!EventHandler.enabled){
 context.getSource().sendFeedback(new StringTextComponent(TextFormatting.GREEN + "Chaos has been enabled!"), false);
 		context.getSource().asPlayer().playSound(SoundEvents.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER,
		 1.0F, 1.0F);
         
         EventHandler.enabled = true;
        }
        
        
        return 0;

    }





}