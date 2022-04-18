package com.wandy.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.wandy.api.utils.SkullManagerII;

public class SuporteCommand
  implements CommandExecutor
{
  Inventory manager = Bukkit.createInventory(null, 27, "Suporte");
  
  public void openManager(Player target)
  {
    this.manager.setItem(10, SkullManagerII.getSkullNovidades("http://textures.minecraft.net/texture/b6be4952d6e15fc5f3fcee5d310aff723dc5ddf8abd7814401fe2e19c0e81d47"));
    this.manager.setItem(11, SkullManagerII.getSkullLoja("http://textures.minecraft.net/texture/8eeb18e6695c39b441d04f7ca53eda1346a5a947d4fe8ba37b33b923288b4e30"));
    this.manager.setItem(12, SkullManagerII.getSkullTwitter("http://textures.minecraft.net/texture/ee15611d0682c13b0c6df8b81cef032bb03d0494d37f420a424147d9ad185145"));
    this.manager.setItem(13, SkullManagerII.getSkullDiscord("http://textures.minecraft.net/texture/7873c12bffb5251a0b88d5ae75c7247cb39a75ff1a81cbe4c8a39b311ddeda"));
    this.manager.setItem(15, SkullManagerII.getSkullRegras("http://textures.minecraft.net/texture/53ff804dd06e71e2adec9506afd5d2349302553e0183c2008c7a6ead7edffebb"));
    this.manager.setItem(16, SkullManagerII.getSkullInfo("http://textures.minecraft.net/texture/3fdfd1d694c2b4368fec021b954f2e899c6c5d308e402a6a197bf4057d6da50"));
    target.openInventory(this.manager);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player playerSender = (Player)sender;
    if (!(sender instanceof Player)) {
      return true;
    }
    if (cmd.getName().equalsIgnoreCase("suporte")) {
      openManager(playerSender);
    }
    return false;
  }
}
