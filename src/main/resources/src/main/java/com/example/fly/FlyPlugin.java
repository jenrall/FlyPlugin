package com.example.fly;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlyPlugin extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(this);
        getCommand("ob").setExecutor(this);
        getLogger().info("FlyPlugin enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("فقط بازیکن‌ها می‌تونن از این دستور استفاده کنن.");
            return true;
        }

        // بررسی permission
        if (!player.hasPermission("fly.use")) {
            player.sendMessage(Component.text("شما دسترسی به این دستور ندارید.", NamedTextColor.RED));
            return true;
        }

        // toggle fly
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(Component.text("❌ پرواز غیرفعال شد.", NamedTextColor.RED));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(Component.text("✅ پرواز فعال شد! از Space و Shift استفاده کن.", NamedTextColor.GREEN));
        }

        return true;
    }
}
