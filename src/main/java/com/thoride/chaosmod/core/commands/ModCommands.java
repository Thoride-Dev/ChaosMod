package com.thoride.chaosmod.core.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.thoride.chaosmod.ChaosMod;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraftforge.common.MinecraftForge;

public class ModCommands {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(
                Commands.literal(ChaosMod.MODID)
                        .then(CommandSetTime.register(dispatcher))
                        .then(CommandCMDisable.register(dispatcher))
                        .then(CommandCMEnable.register(dispatcher))
                        .then(CommandModuleControl.register(dispatcher))
                        .then(ModuleList.register(dispatcher))

        );

        
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandlers());
    }

}