package com.thoride.chaosmod.core.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.thoride.chaosmod.ChaosMod;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class CommandSetTime implements Command<CommandSource> {

    private static final CommandSetTime CMD = new CommandSetTime();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("time")
                .requires(cs -> cs.hasPermissionLevel(0)).then(Commands.argument("Seconds", IntegerArgumentType.integer())
                                    .executes(CMD));
    }
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
    	boolean wasEnabled = false;
    	if(EventHandler.enabled == true) {
    		wasEnabled = true;
    	}
        EventHandler.enabled = false;       
        ChaosMod.timeBetweenChaos = Math.abs(IntegerArgumentType.getInteger(context, "Seconds"));
        EventHandler.i = 0;
        if(wasEnabled == true)
        	EventHandler.enabled = true;
        context.getSource().sendFeedback(new StringTextComponent(TextFormatting.GREEN + "Event time set to: " + 
        TextFormatting.DARK_GREEN + IntegerArgumentType.getInteger(context, "Seconds") + " Seconds"), false);
        return 0;
    }
}
