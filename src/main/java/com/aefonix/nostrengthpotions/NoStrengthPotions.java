package com.aefonix.nostrengthpotions;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class NoStrengthPotions extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    this.getServer().getPluginManager().registerEvents(this, this);
  }

  @Override
  public void onDisable() {}

  @EventHandler
  public void onPotionBrew(BrewEvent event) {
    ItemStack firstItem = event.getContents().getContents()[0];

    if (!event.isCancelled()) {
      if (firstItem.getType() == Material.BLAZE_POWDER) {
        event.setCancelled(true);
      }
    }
  }

  @EventHandler
  public void onPlayerMoveItem(InventoryClickEvent event) {
    Player player = (Player)event.getWhoClicked();
    InventoryView inventory = player.getOpenInventory();

    if (!event.isCancelled()) {
      if (inventory.getTopInventory() instanceof BrewerInventory) {
        if (event.getCurrentItem().getType() == Material.BLAZE_POWDER) {
          event.setCancelled(true);
        }
      }
    }
  }

  @EventHandler
  public void onHopperMove(InventoryMoveItemEvent event) {
    if (!event.isCancelled()) {
      if (event.getDestination() instanceof BrewerInventory) {
        if (event.getItem().getType() == Material.BLAZE_POWDER) {
          event.setCancelled(true);
        }
      }
    }
  }
}
