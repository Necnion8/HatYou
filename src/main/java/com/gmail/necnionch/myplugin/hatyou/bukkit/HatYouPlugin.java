package com.gmail.necnionch.myplugin.hatyou.bukkit;

import com.gmail.necnionch.myplugin.hatyou.bukkit.event.HatYouEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class HatYouPlugin extends JavaPlugin implements Listener {
    
    private static final String CLICK_PERMISSION = "hatyou.click";
    private final HatYouConfig mainConfig = new HatYouConfig(this);

    @Override
    public void onEnable() {
        mainConfig.load();
        getServer().getPluginManager().registerEvents(this, this);

    }

    @EventHandler(ignoreCancelled = true)
    public void onRightClick(PlayerInteractAtEntityEvent event) {
        Player srcPlayer = event.getPlayer();
        if (!srcPlayer.hasPermission(CLICK_PERMISSION))
            return;

        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }

        PlayerInventory srcInventory = srcPlayer.getInventory();
        ItemStack itemStack = srcInventory.getItemInMainHand();
        if (!mainConfig.getHandItemTypes().contains(itemStack.getType()))
            return;

        Player target = (Player) event.getRightClicked();
        PlayerInventory dstInventory = target.getInventory();
        if (dstInventory.getHelmet() != null)
            return;

        HatYouEvent newEvent = new HatYouEvent(srcPlayer, target, event);
        getServer().getPluginManager().callEvent(newEvent);
        if (newEvent.isCancelled())
            return;

        ItemStack copyItemStack = itemStack.clone();
        copyItemStack.setAmount(1);
        itemStack.setAmount(itemStack.getAmount() - 1);

        dstInventory.setHelmet(copyItemStack);
        target.updateInventory();
        srcPlayer.updateInventory();
        event.setCancelled(true);
    }

}
