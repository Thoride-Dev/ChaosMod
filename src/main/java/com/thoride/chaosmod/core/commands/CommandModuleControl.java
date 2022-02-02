package com.thoride.chaosmod.core.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class CommandModuleControl implements Command<CommandSource> {

	private static final CommandModuleControl CMD = new CommandModuleControl();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {

		return Commands.literal("module").requires(cs -> cs.hasPermissionLevel(0))
				.then(Commands
						.argument("ModuleName", StringArgumentType.string())
						.suggests((c, b) -> {
							for (int i = 0; i < EventHandler.modules.size(); i++) {
								b.suggest(EventHandler.modules.get(i));
							}
							b.suggest("all");
							return b.buildFuture();					
						})					
						.then(Commands.argument("ModuleState", StringArgumentType.string())
								.suggests((c, b) -> b
										.suggest("enabled")
										.suggest("disabled")
										.buildFuture())
								.executes(CMD)));

	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		String moduleName = StringArgumentType.getString(context, "ModuleName");
		String moduleState = StringArgumentType.getString(context, "ModuleState");
		if (EventHandler.modules.contains(moduleName) || moduleName.equalsIgnoreCase("all")) {
			if (moduleState.equalsIgnoreCase("enabled")) {
				if (EventHandler.activeModules.contains(moduleName)) {
					context.getSource()
							.sendFeedback(new StringTextComponent(
									TextFormatting.RED + moduleName + TextFormatting.DARK_RED + " is already enabled!"),
									false);
					// add all modules to active
				} else if (moduleName.equalsIgnoreCase("all")) {
					for (String module : EventHandler.modules) {
						if (!EventHandler.activeModules.contains(module)) {
							EventHandler.activeModules.add(module);
						}
					}
					context.getSource().sendFeedback(new StringTextComponent(
							TextFormatting.RED + "ALL MODULES" + TextFormatting.DARK_RED + " have been enabled!"),
							false);
				} else {
					EventHandler.activeModules.add(moduleName);
					context.getSource().sendFeedback(new StringTextComponent(
							TextFormatting.GREEN + moduleName + TextFormatting.DARK_GREEN + " has been enabled!"),
							false);
				}

			} else if (moduleState.equalsIgnoreCase("disabled")) {
				if (!EventHandler.activeModules.contains(moduleName) && !moduleName.equalsIgnoreCase("all")) {
					context.getSource().sendFeedback(new StringTextComponent(
							TextFormatting.RED + moduleName + TextFormatting.DARK_RED + " is already disabled!"),
							false);
					// disable all modules
				} else if (moduleName.equalsIgnoreCase("all")) {
					EventHandler.activeModules.clear();
					context.getSource().sendFeedback(new StringTextComponent(
							TextFormatting.RED + "ALL MODULES" + TextFormatting.DARK_RED + " have been disabled"),
							false);
				} else {
					EventHandler.activeModules.remove(moduleName);
					context.getSource()
							.sendFeedback(new StringTextComponent(
									TextFormatting.RED + moduleName + TextFormatting.DARK_RED + " has been disabled!"),
									false);
				}
			}
		} else {
			context.getSource()
					.sendFeedback(new StringTextComponent(TextFormatting.RED + "Error! Unknown module "
							+ TextFormatting.DARK_RED + moduleName + TextFormatting.AQUA
							+ ". Try /chaosmod ListModules to get a list of moduleNames!"), false);
		}

		return 0;
	}
}
