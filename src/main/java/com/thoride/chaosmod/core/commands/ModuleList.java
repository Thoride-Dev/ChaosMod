package com.thoride.chaosmod.core.commands;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.thoride.chaosmod.core.event.EventHandler;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class ModuleList implements Command<CommandSource> {

    private static final ModuleList CMD = new ModuleList();

    public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
        return Commands.literal("ListModules")
                .requires(cs -> cs.hasPermissionLevel(0)).executes(CMD);
    }
    @Override
    public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
    	String disabledModuleString = "";
    	String activeModuleString = "";
    	for (String string : EventHandler.activeModules) {
			activeModuleString += string + ", ";
		}
        for (String string : EventHandler.modules) {
        	if(!EventHandler.activeModules.contains(string))
        		disabledModuleString += string + ", ";
		}
        context.getSource().sendFeedback(new StringTextComponent(TextFormatting.GREEN + "Active Modules: " + TextFormatting.DARK_GREEN + activeModuleString), false);
        context.getSource().sendFeedback(new StringTextComponent(TextFormatting.RED + "\nDisabled Modules: " + TextFormatting.DARK_RED + disabledModuleString), false);
        context.getSource().sendFeedback(new StringTextComponent(TextFormatting.DARK_GRAY + "\n\nTip: " + TextFormatting.GRAY + "You can use 'all' as a moduleName to disable/enable all modules at once!"), false);

        return 0;
    }
    
    
}

	
 
	/**
 public static final List<Class<?>> getClassesInPackage(String packageName) {
    String path = packageName.replaceAll("\\.", File.separator);
    List<Class<?>> classes = new ArrayList<>();
    String[] classPathEntries = System.getProperty("java.class.path").split(
            System.getProperty("path.separator")
    );

    String name;
    for (String classpathEntry : classPathEntries) {
        if (classpathEntry.endsWith(".jar")) {
            File jar = new File(classpathEntry);
            try {
                JarInputStream is = new JarInputStream(new FileInputStream(jar));
                JarEntry entry;
                while((entry = is.getNextJarEntry()) != null) {
                    name = entry.getName();
                    if (name.endsWith(".class")) {
                        if (name.contains(path) && name.endsWith(".class")) {
                            String classPath = name.substring(0, entry.getName().length() - 6);
                            classPath = classPath.replaceAll("[\\|/]", ".");
                            classes.add(Class.forName(classPath));
                        }
                    }
                }
            } catch (Exception ex) {
                // no error pls
            }
        } else {
            try {
                File base = new File(classpathEntry + File.separatorChar + path);
                for (File file : base.listFiles()) {
                    name = file.getName();
                    if (name.endsWith(".class")) {
                        name = name.substring(0, name.length() - 6);
                        classes.add(Class.forName(packageName + "." + name));
                    }
                }
            } catch (Exception ex) {
                // pls no error
            }
        }
    }

    return classes;
}**/




