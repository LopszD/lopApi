package com.wandy.api.especiais;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.wandy.api.commands.GiveCommand;
import com.wandy.api.especiais.listeners.ImpulsaoListener;
import com.wandy.api.sql.Manager;
import com.wandy.api.utils.Heads;
import com.wandy.api.utils.ItemBuilder;
import com.wandy.api.utils.NoStack;
import com.wandy.kits.utils.Stacker;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

public class EspeciaisCommand implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("especiais")) {}
		CommandSender p = sender;
		if (!p.hasPermission("wandy.especiais")) {
			p.sendMessage("�cVoc� n�o tem permiss�o para executar este comando.");
			return true;
		}
		if (args.length == 0) {
			Player p1 = (Player) p;
			abrirMenu(p1);
			return true;
		}
		if (args.length == 1) {
			p.sendMessage("�cUtilize /especiais <usu�rio> <item> [quantidade] para enviar um item especial.");
			return true;
		}
		if (args.length == 2) {
			String jogador = args[0];
			String tipo = args[1];
			if (jogador.equals("all")) {
				int i = 0;
				for (Player p1 : Bukkit.getOnlinePlayers()) {
					if (tipo.equalsIgnoreCase("lend�ria")) {
						enviarCaixa(p1, "LENDARIA");
						p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo LEND�RIA.");
						i++;
					}
					if (tipo.equalsIgnoreCase("platina")) {
						enviarCaixa(p1, "PLATINA");
						p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo PLATINA.");
						i++;
					}
					if (tipo.equalsIgnoreCase("b�sica")) {
						enviarCaixa(p1, "BASICA");
						p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo B�SICA.");
						i++;
					}
					if (tipo.equalsIgnoreCase("astral")) {
						enviarCaixa(p1, "ASTRAL");
						p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo ASTRAL.");
						i++;
					}
					if (tipo.equalsIgnoreCase("armaduras")) {
						enviarCaixa(p1, "ARMADURAS");
						p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo ARMADURAS.");
						i++;
					}
					if (tipo.equalsIgnoreCase("spawners")) {
						enviarCaixa(p1, "SPAWNERS");
						p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo SPAWNERS.");
						i++;
					}
					if (tipo.equalsIgnoreCase("creeper")) {
						enviarCreeper(p1);
						p1.sendMessage("�aVoc� recebeu 1x super creeper.");
						i++;
					}
					if (tipo.equalsIgnoreCase("bola")) {
						enviarBola(p1);
						p1.sendMessage("�aVoc� recebeu 1x bola de fogo.");
						i++;
					}
					if (tipo.equalsIgnoreCase("booster")) {
						enviarBooster(p1);
						p1.sendMessage("�aVoc� recebeu 1x   de drops.");
						i++;
					}
					if (tipo.equalsIgnoreCase("boosterexp")) {
						enviarBoosterExp(p1);
						p1.sendMessage("�aVoc� recebeu 1x booster de experi�ncia.");
						i++;
					}
					if (tipo.equalsIgnoreCase("lan�ador")) {
						enviarLan�ador(p1);
						p1.sendMessage("�aVoc� recebeu 1x lan�ador.");
						i++;
					}
					if (tipo.equalsIgnoreCase("raiomestre")) {
						enviarRaio(p1);
						p1.sendMessage("�aVoc� recebeu 1x raio mestre.");
						i++;
					}
					if (tipo.equalsIgnoreCase("resetkdr")) {
						enviarResetKDR(p1);
						p1.sendMessage("�aVoc� recebeu 1x reset kdr.");
						i++;
					}
					if (tipo.equalsIgnoreCase("tnt")) {
						enviarTNT(p1);
						p1.sendMessage("�aVoc� recebeu 1x tnt de impuls�o.");
						i++;
					}
					if (i == 0) {
						p.sendMessage("�cItem n�o encontrado.");
					}
				}
				return true;
			}
			if (Bukkit.getPlayer(jogador) == null) {
				p.sendMessage("�cEste usu�rio n�o est� on-line.");
				return true;
			}
			Player p1 = Bukkit.getPlayer(jogador);
			if (tipo.equalsIgnoreCase("spawners")) {
				enviarCaixa(p1, "SPAWNERS");
				p.sendMessage("�aEnviando 1x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
				return true;
			}
			if (tipo.equalsIgnoreCase("astral")) {
				enviarCaixa(p1, "ASTRAL");
				p.sendMessage("�aEnviando 1x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
				return true;
			}
			if (tipo.equalsIgnoreCase("lend�ria")) {
				enviarCaixa(p1, "LENDARIA");
				p.sendMessage("�aEnviando 1x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
				return true;
			}
			if (tipo.equalsIgnoreCase("platina")) {
				enviarCaixa(p1, "PLATINA");
				p.sendMessage("�aEnviando 1x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
				return true;
			}
			if (tipo.equalsIgnoreCase("armaduras")) {
				enviarCaixa(p1, "ARMADURAS");
				p.sendMessage("�aEnviando 1x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
				return true;
			}
			if (tipo.equalsIgnoreCase("b�sica")) {
				enviarCaixa(p1, "BASICA");
				p.sendMessage("�aEnviando 1x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
				return true;
			}
			if (tipo.equalsIgnoreCase("creeper")) {
				enviarCreeper(p1);
				p.sendMessage("�aEnviando 1x super creeper para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x super creeper.");
				return true;
			}
			if (tipo.equalsIgnoreCase("bola")) {
				enviarBola(p1);
				p.sendMessage("�aEnviando 1x bola de fogo para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x bola de fogo.");
				return true;
			}
			if (tipo.equalsIgnoreCase("booster")) {
				enviarBooster(p1);
				p.sendMessage("�aEnviando 1x booster de drops para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x booster de drops.");
				return true;
			}
			if (tipo.equalsIgnoreCase("boosterexp")) {
				enviarBoosterExp(p1);
				p.sendMessage("�aEnviando 1x booster de experi�ncia para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x booster de experi�ncia.");
				return true;
			}
			if (tipo.equalsIgnoreCase("lan�ador")) {
				enviarLan�ador(p1);
				p.sendMessage("�aEnviando 1x lan�ador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x lan�ador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("raiomestre")) {
				enviarRaio(p1);
				p.sendMessage("�aEnviando 1x raio mestre para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x raio mestre.");
				return true;
			}
			if (tipo.equalsIgnoreCase("resetkdr")) {
				enviarResetKDR(p1);
				p.sendMessage("�aEnviando 1x reset kdr para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x reset kdr.");
				return true;
			}
			if (tipo.equalsIgnoreCase("furadeira")) {
				enviarFufu(p1);
				p.sendMessage("�aEnviando 1x furadeira para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x furadeira.");
				return true;
			}
			if (tipo.equalsIgnoreCase("tnt")) {
				enviarTNT(p1);
				p.sendMessage("�aEnviando 1x tnt de impuls�o para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x tnt de impuls�o.");
				return true;
			}
			if (tipo.equalsIgnoreCase("runa")) {
				enviarRuna(p1);
				p.sendMessage("�aEnviando 1x runa de extra��o para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x runa de extra��o.");
				return true;
			}
			if (tipo.equalsIgnoreCase("moeda")) {
				enviarMoeda(p1);
				p.sendMessage("�aEnviando 1x moeda de repara��o para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x moeda de repara��o.");
				return true;
			}
			if (tipo.equalsIgnoreCase("encantador")) {
				enviarEncantador(p1);
				p.sendMessage("�aEnviando 1x encantador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x encantador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("dimpulsao")) {
				enviarDispenserImpulsao(p1);
				p.sendMessage("�aEnviando 1x dispenser de impuls�o para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x dispenser de impuls�o.");
				return true;
			}
			if (tipo.equalsIgnoreCase("dtnt")) {
				enviarDispenserSimples(p1);
				p.sendMessage("�aEnviando 1x dispenser de tnt para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x dispenser de tnt.");
				return true;
			}
			if (tipo.equalsIgnoreCase("nuke")) {
				enviarNuke(p1);
				p.sendMessage("�aEnviando 1x tnt nuke para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x tnt nuke.");
				return true;
			}
			if (tipo.equalsIgnoreCase("canhao")) {
				enviarCanhao(p1);
				p.sendMessage("�aEnviando 1x canh�o port�til para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x canh�o port�til.");
				return true;
			}
			if (tipo.equalsIgnoreCase("totem")) {
				enviarTotem(p1);
				p.sendMessage("�aEnviando 1x totem da morte para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x totem da morte.");
				return true;
			}
			if (tipo.equalsIgnoreCase("slime")) {
				enviarSlime(p1);
				p.sendMessage("�aEnviando 1x detector de slime chunk para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x detector de slime chunk.");
				return true;
			}
			if (tipo.equalsIgnoreCase("incinerador")) {
				enviarIncinerador(p1);
				p.sendMessage("�aEnviando 1x incinerador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x incinerador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("armadilha")) {
				enviarTrap(p1);
				p.sendMessage("�aEnviando 1x armadilha para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x armadilha.");
				return true;
			}
			if (tipo.equalsIgnoreCase("eletrizado")) {
				enviarEletrizado(p1);
				p.sendMessage("�aEnviando 1x creeper eletrizado para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x creeper eletrizado.");
				return true;
			}
			if (tipo.equalsIgnoreCase("retroceder")) {
				enviarRetrocer(p1);
				p.sendMessage("�aEnviando 1x retroceder para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x retroceder.");
				return true;
			}
			if (tipo.equalsIgnoreCase("superlancador")) {
				enviarSuperLancador(p1);
				p.sendMessage("�aEnviando 1x super lan�ador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x super lan�ador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("silk")) {
				enviarSilk(p1);
				p.sendMessage("�aEnviando 1x silk touch para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x silk touch.");
				return true;
			}
			if (tipo.equalsIgnoreCase("devastacao")) {
				enviarSuperArea(p1);
				p.sendMessage("�aEnviando 1x devasta��o para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x devasta��o.");
				return true;
			}
			if (tipo.equalsIgnoreCase("agrupador")) {
				enviarAgrupador(p1);
				p.sendMessage("�aEnviando 1x agrupador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x agrupador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("reparador")) {
				enviarReparador(p1);
				p.sendMessage("�aEnviando 1x reparador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x reparador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("witherhead")) {
				enviarWitherHead(p1);
				p.sendMessage("�aEnviando 1x cabe�a de wither para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x cabe�a de wither.");
				return true;
			}
			if (tipo.equalsIgnoreCase("potionfood")) {
				enviarPotionFood(p1);
				p.sendMessage("�aEnviando 1x potionfood para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x potionfood.");
				return true;
			}
			if (tipo.equalsIgnoreCase("defensor")) {
				enviarDefensor(p1);
				p.sendMessage("�aEnviando 1x defensor secreto para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x defensor secreto.");
				return true;
			}
			if (tipo.equalsIgnoreCase("purificador")) {
				enviarPurificador(p1);
				p.sendMessage("�aEnviando 1x purificador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x purificador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("fragmento")) {
				enviarFragmentos(p1);
				p.sendMessage("�aEnviando 1x fragmento para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x fragmento.");
				return true;
			}
			if (tipo.equalsIgnoreCase("c4")) {
				enviarC4(p1);
				p.sendMessage("�aEnviando 1x c4 para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x c4.");
				return true;
			}
			if (tipo.equalsIgnoreCase("eletromagnetico")) {
				enviarMagnetico(p1);
				p.sendMessage("�aEnviando 1x pulso eletromagn�tico para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x pulso eletromagn�tico.");
				return true;
			}
			if (tipo.equalsIgnoreCase("rastreador")) {
				enviarRastreador(p1);
				p.sendMessage("�aEnviando 1x pulso rastreador para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x pulso rastreador.");
				return true;
			}
			if (tipo.equalsIgnoreCase("vida")) {
				enviarRege(p1);
				p.sendMessage("�aEnviando 1x vida+ para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x vida+.");
				return true;
			}
			if (tipo.equalsIgnoreCase("vipbetther")) {
				enviarVip1(p1);
				p.sendMessage("�aEnviando 1x papel betther para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x papel betther.");
				return true;
			}
			if (tipo.equalsIgnoreCase("vipghuenon")) {
				enviarVip2(p1);
				p.sendMessage("�aEnviando 1x papel ghuenon para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x papel ghuenon.");
				return true;
			}
			if (tipo.equalsIgnoreCase("divina")) {
				enviarDivina(p1);
				p.sendMessage("�aEnviando 1x espada divina para " + p1.getName() + ".");
				p1.sendMessage("�aVoc� recebeu 1x espada divina.");
				return true;
			}
			p.sendMessage("�cItem n�o encontrado.");
			return true;
		}
		if (args.length == 3) {
			String jogador = args[0];
			String tipo = args[1];
			if (GiveCommand.isInt(args[2])) {
				int qnt = Integer.valueOf(args[2]).intValue();
				if (Bukkit.getPlayer(jogador) == null) {
					p.sendMessage("�cEste usu�rio n�o est� on-line.");
					return true;
				}
				Player p1 = Bukkit.getPlayer(jogador);
				if (tipo.equalsIgnoreCase("spawners")) {
					for (int i = 0; i < qnt; i++) {
						enviarCaixa(p1, "SPAWNERS");
					}
					p.sendMessage("�aEnviando " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
					return true;
				}
				if (tipo.equalsIgnoreCase("astral")) {
					for (int i = 0; i < qnt; i++) {
						enviarCaixa(p1, "ASTRAL");
					}
					p.sendMessage("�aEnviando " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
					return true;
				}
				if (tipo.equalsIgnoreCase("lend�ria")) {
					for (int i = 0; i < qnt; i++) {
						enviarCaixa(p1, "LENDARIA");
					}
					p.sendMessage("�aEnviando " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
					return true;
				}
				if (tipo.equalsIgnoreCase("platina")) {
					for (int i = 0; i < qnt; i++) {
						enviarCaixa(p1, "PLATINA");
					}
					p.sendMessage("�aEnviando " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
					return true;
				}
				if (tipo.equalsIgnoreCase("armaduras")) {
					for (int i = 0; i < qnt; i++) {
						enviarCaixa(p1, "ARMADURAS");
					}
					p.sendMessage("�aEnviando " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
					return true;
				}
				if (tipo.equalsIgnoreCase("b�sica")) {
					for (int i = 0; i < qnt; i++) {
						enviarCaixa(p1, "BASICA");
					}
					p.sendMessage("�aEnviando " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + " para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x caixa misteriosa do tipo " + tipo.toUpperCase() + ".");
					return true;
				}
				if (tipo.equalsIgnoreCase("creeper")) {
					for (int i = 0; i < qnt; i++) {
						enviarCreeper(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x super creeper para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x super creeper.");
					return true;
				}
				if (tipo.equalsIgnoreCase("bola")) {
					for (int i = 0; i < qnt; i++) {
						enviarBola(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x bola de fogo para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x bola de fogo.");
					return true;
				}
				if (tipo.equalsIgnoreCase("booster")) {
					for (int i = 0; i < qnt; i++) {
						enviarBooster(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x booster de drops para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x booster de drops.");
					return true;
				}
				if (tipo.equalsIgnoreCase("boosterexp")) {
					for (int i = 0; i < qnt; i++) {
						enviarBoosterExp(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x booster de experi�ncia para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x booster de experi�ncia.");
					return true;
				}
				if (tipo.equalsIgnoreCase("lan�ador")) {
					for (int i = 0; i < qnt; i++) {
						enviarLan�ador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x lan�ador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x lan�ador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("raiomestre")) {
					for (int i = 0; i < qnt; i++) {
						enviarRaio(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x raio mestre para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x raio mestre.");
					return true;
				}
				if (tipo.equalsIgnoreCase("resetkdr")) {
					for (int i = 0; i < qnt; i++) {
						enviarResetKDR(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x resetkdr para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x resetkdr.");
					return true;
				}
				if (tipo.equalsIgnoreCase("tnt")) {
					for (int i = 0; i < qnt; i++) {
						enviarTNT(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x tnt de impuls�o para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x tnt de impuls�o.");
					return true;
				}
				if (tipo.equalsIgnoreCase("runa")) {
					for (int i = 0; i < qnt; i++) {
						enviarRuna(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x runa de extra��o para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x runa de extra��o.");
					return true;
				}
				if (tipo.equalsIgnoreCase("moeda")) {
					for (int i = 0; i < qnt; i++) {
						enviarMoeda(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x moeda de repara��o para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x moeda de repara��o.");
					return true;
				}
				if (tipo.equalsIgnoreCase("encantador")) {
					for (int i = 0; i < qnt; i++) {
						enviarEncantador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x encantador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x encantador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("dimpulsao")) {
					for (int i = 0; i < qnt; i++) {
						enviarDispenserImpulsao(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x dispenser de impuls�o para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x dispenser de impuls�o.");
					return true;
				}
				if (tipo.equalsIgnoreCase("dtnt")) {
					for (int i = 0; i < qnt; i++) {
						enviarDispenserSimples(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x tnt nuke para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x dispenser de tnt.");
					return true;
				}
				if (tipo.equalsIgnoreCase("nuke")) {
					for (int i = 0; i < qnt; i++) {
						enviarNuke(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x tnt nuke para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x tnt nuke.");
					return true;
				}
				if (tipo.equalsIgnoreCase("canhao")) {
					for (int i = 0; i < qnt; i++) {
						enviarCanhao(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x canh�o port�til para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x canh�o port�til.");
					return true;
				}
				if (tipo.equalsIgnoreCase("totem")) {
					for (int i = 0; i < qnt; i++) {
						enviarTotem(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x totem da morte para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x totem da morte.");
					return true;
				}
				if (tipo.equalsIgnoreCase("slime")) {
					for (int i = 0; i < qnt; i++) {
						enviarSlime(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x detector de slime chunk para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x detector de slime chunk.");
					return true;
				}
				if (tipo.equalsIgnoreCase("incinerador")) {
					for (int i = 0; i < qnt; i++) {
						enviarIncinerador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x incinerador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x incinerador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("armadilha")) {
					for (int i = 0; i < qnt; i++) {
						enviarTrap(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x armadilha para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x armadilha.");
					return true;
				}
				if (tipo.equalsIgnoreCase("eletrizado")) {
					for (int i = 0; i < qnt; i++) {
						enviarEletrizado(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x creeper eletrizado para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x creeper eletrizado.");
					return true;
				}
				if (tipo.equalsIgnoreCase("retroceder")) {
					for (int i = 0; i < qnt; i++) {
						enviarRetrocer(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x retroceder para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x retroceder.");
					return true;
				}
				if (tipo.equalsIgnoreCase("superlancador")) {
					for (int i = 0; i < qnt; i++) {
						enviarSuperLancador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x super lan�ador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x super lan�ador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("silk")) {
					for (int i = 0; i < qnt; i++) {
						enviarSilk(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x silk touch para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x silk touch.");
					return true;
				}
				if (tipo.equalsIgnoreCase("devastacao")) {
					for (int i = 0; i < qnt; i++) {
						enviarSuperArea(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x devasta��o para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x devasta��o.");
					return true;
				}
				if (tipo.equalsIgnoreCase("agrupador")) {
					for (int i = 0; i < qnt; i++) {
						enviarAgrupador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x agrupador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x agrupador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("reparador")) {
					for (int i = 0; i < qnt; i++) {
						enviarReparador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x reparador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x reparador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("witherhead")) {
					for (int i = 0; i < qnt; i++) {
						enviarWitherHead(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x cabe�a de wither para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x cabe�a de wither.");
					return true;
				}
				if (tipo.equalsIgnoreCase("potionfood")) {
					for (int i = 0; i < qnt; i++) {
						enviarPotionFood(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x potionfood para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x potionfood.");
					return true;
				}
				if (tipo.equalsIgnoreCase("defensor")) {
					for (int i = 0; i < qnt; i++) {
						enviarDefensor(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x defensor secreto para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x defensor secreto.");
					return true;
				}
				if (tipo.equalsIgnoreCase("purificador")) {
					for (int i = 0; i < qnt; i++) {
						enviarPurificador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x purificador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x purificador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("fragmento")) {
					for (int i = 0; i < qnt; i++) {
						enviarFragmentos(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x fragmento para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x fragmento.");
					return true;
				}
				if (tipo.equalsIgnoreCase("c4")) {
					for (int i = 0; i < qnt; i++) {
						enviarC4(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x c4 para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x c4.");
					return true;
				}
				if (tipo.equalsIgnoreCase("eletromagnetico")) {
					for (int i = 0; i < qnt; i++) {
						enviarMagnetico(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x pulso eletromagn�tico para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x pulso eletromagn�tico.");
					return true;
				}
				if (tipo.equalsIgnoreCase("rastreador")) {
					for (int i = 0; i < qnt; i++) {
						enviarRastreador(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x rastreador para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x rastreador.");
					return true;
				}
				if (tipo.equalsIgnoreCase("furadeira")) {
					for (int i = 0; i < qnt; i++) {
						enviarFufu(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x furadeira para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x furadeira.");
					return true;
				}
				if (tipo.equalsIgnoreCase("vida")) {
					for (int i = 0; i < qnt; i++) {
						enviarRege(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x vida+ para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x vida+.");
					return true;
				}
				if (tipo.equalsIgnoreCase("vipbetther")) {
					for (int i = 0; i < qnt; i++) {
						enviarVip1(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x papel betther para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x papel betther.");
					return true;
				}
				if (tipo.equalsIgnoreCase("vipghuenon")) {
					for (int i = 0; i < qnt; i++) {
						enviarVip2(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x papel ghuenon para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x papel ghuenon.");
					return true;
				}
				if (tipo.equalsIgnoreCase("divina")) {
					for (int i = 0; i < qnt; i++) {
						enviarDivina(p1);
					}
					p.sendMessage("�aEnviando " + qnt + "x espada divina para " + p1.getName() + ".");
					p1.sendMessage("�aVoc� recebeu " + qnt + "x espada divina.");
					return true;
				}
				p.sendMessage("�cItem n�o encontrado.");
				return true;
			}
			p.sendMessage("�cQuantidade inv�lida.");
			return true;
		}
		return false;
	}

	public static void abrirMenuCaixas(Player p) {
		Inventory inv = Bukkit.createInventory(null, 36, "�8Itens Especiais - Caixas");
		ItemStack h = new ItemStack(Material.CHEST);
		ItemMeta hm = h.getItemMeta();
		hm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		hm.setDisplayName("�eCaixa Misteriosa");
		List<String> lh = new ArrayList<String>();
		lh.add("�1");
		lh.add("�7Abra est� caixa e receba um item");
		lh.add("�7incr�vel que ser� sorteado.");
		lh.add("�2");
		lh.add("�fTipo da caixa: �eB�sica");
		hm.setLore(lh);
		h.setItemMeta(hm);

		ItemStack g = new ItemStack(Material.ENDER_CHEST);
		ItemMeta gm = g.getItemMeta();
		gm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		gm.setDisplayName("�3Caixa Misteriosa");
		List<String> lg = new ArrayList<String>();
		lg.add("�1");
		lg.add("�7Abra est� caixa e receba um item");
		lg.add("�7incr�vel que ser� sorteado.");
		lg.add("�2");
		lg.add("�fTipo da caixa: �3Platina");
		gm.setLore(lg);
		g.setItemMeta(gm);

		ItemStack f = new ItemStack(Material.ENDER_PORTAL_FRAME);
		ItemMeta fm = f.getItemMeta();
		fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fm.setDisplayName("�6Caixa Misteriosa");
		List<String> lf = new ArrayList<String>();
		lf.add("�1");
		lf.add("�7Abra est� caixa e receba um item");
		lf.add("�7incr�vel que ser� sorteado.");
		lf.add("�2");
		lf.add("�fTipo da caixa: �6Lend�ria");
		fm.setLore(lf);
		f.setItemMeta(fm);

		ItemStack u = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta um = u.getItemMeta();
		um.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		um.setDisplayName("�bCaixa Misteriosa");
		List<String> lu = new ArrayList<String>();
		lu.add("�1");
		lu.add("�7Abra est� caixa e receba um item");
		lu.add("�7incr�vel que ser� sorteado.");
		lu.add("�2");
		lu.add("�fTipo da caixa: �bArmaduras");
		um.setLore(lu);
		u.setItemMeta(um);
		
		ItemStack z = new ItemStack(Material.MOB_SPAWNER);
		ItemMeta zm = z.getItemMeta();
		zm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		zm.setDisplayName("�9Caixa Misteriosa");
		List<String> zu = new ArrayList<String>();
		zu.add("�1");
		zu.add("�7Abra est� caixa e receba um item");
		zu.add("�7incr�vel que ser� sorteado.");
		zu.add("�2");
		zu.add("�fTipo da caixa: �9Spawners");
		zm.setLore(zu);
		z.setItemMeta(zm);
		
		ItemStack x = new ItemStack(Material.BEACON);
		ItemMeta xm = x.getItemMeta();
		xm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		xm.setDisplayName("�5Caixa Misteriosa");
		List<String> xu = new ArrayList<String>();
		xu.add("�1");
		xu.add("�7Abra est� caixa e receba um item");
		xu.add("�7incr�vel que ser� sorteado.");
		xu.add("�2");
		xu.add("�fTipo da caixa: �5Astral");
		xm.setLore(xu);
		x.setItemMeta(xm);

		ItemStack v = new ItemStack(Material.ARROW);
		ItemMeta vm = v.getItemMeta();
		vm.setDisplayName("�aVoltar");
		v.setItemMeta(vm);
		
		inv.setItem(10, h);
		inv.setItem(11, g);
		inv.setItem(12, f);
		inv.setItem(13, u);
		inv.setItem(14, z);
		inv.setItem(15, x);
		inv.setItem(31, v);
		p.openInventory(inv);
	}

	public static void abrirMenuItens(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "�8Itens Especiais - Itens");

		ItemStack a = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am.setDisplayName("�aSuper Creeper");
		am.setLore(Arrays.asList("�7Use para invocar um creeper eletrizado", "�7que causar� 5 de dano em blocos."));
		a.setItemMeta(am);

		ItemStack a1 = new ItemStack(Material.FIREBALL);
		ItemMeta am1 = a1.getItemMeta();
		am1.setDisplayName("�cBola de fogo");
		am1.setLore(Arrays.asList("�7Use este item para disparar", "�7uma poderosa bola de fogo que", "�7causar� 5 de dano em blocos."));
		a1.setItemMeta(am1);

		ItemStack a3 = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta am3 = a3.getItemMeta();
		am3.setDisplayName("�6Booster de Drop");
		am3.setLore(Arrays.asList("�7Use para receber o dobro de drops do tipo", "�7de mob que voc� selecionar."));
		a3.setItemMeta(am3);

		ItemStack a4 = new ItemStack(Material.FIREWORK);
		ItemMeta am4 = a4.getItemMeta();
		am4.setDisplayName("�eLan�ador");
		am4.setLore(Arrays.asList("�7Ao usar voc� ser� lan�ado 40 blocos para cima."));
		a4.setItemMeta(am4);

		ItemStack a5 = new ItemStack(Material.BLAZE_ROD);
		a5.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am5 = a5.getItemMeta();
		am5.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am5.setDisplayName("�6Raio Mestre");
		am5.setLore(Arrays.asList("�7Use para lan�ar um raio aonde voc�", "�7estiver olhando."));
		a5.setItemMeta(am5);

		ItemStack a6 = new ItemStack(Material.FIREWORK_CHARGE);
		ItemMeta am6 = a6.getItemMeta();
		am6.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		am6.setDisplayName(Manager.randomString() + "�6Reset KDR");
		am6.setLore(Arrays.asList("�7Use este item para resetar seu KDR."));
		a6.setItemMeta(am6);

		ItemStack a8 = new ItemStack(Material.STICK);
		ItemMeta am8 = a8.getItemMeta();
		am8.setDisplayName("�eFuradeira");
		am8.setLore(Arrays.asList("�7Utilize para quebrar qualquer bloco", "�7de prote��o, cuidado com a sua vida �til."));
		a8.setItemMeta(am8);

		ItemStack a7 = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta am7 = a7.getItemMeta();
		am7.setDisplayName("�aBooster de Experi�ncia");
		am7.setLore(Arrays.asList("�7Use para receber o dobro de experi�ncia da", "�7habilidade que voc� selecionar."));
		a7.setItemMeta(am7);

		ItemStack a71 = new ItemStack(Material.REDSTONE);
		a71.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am71 = a71.getItemMeta();
		am71.setDisplayName("�eVida+");
		am71.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am71.setLore(Arrays.asList("�7Ao utilizar este item, voc� ir�", "�7receber +3 cora��es."));
		a71.setItemMeta(am71);

		ItemStack a2 = new ItemStack(Material.NETHER_STAR);
		ItemMeta am2 = a2.getItemMeta();
		am2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		am2.setDisplayName(Manager.randomString() + "�eMembros+");
		am2.setLore(Arrays.asList("�7Ao utilizar este item, o n�mero m�x�mo de", "�7membros da fac��o ser� aumentado."));
		a2.setItemMeta(am2);

		ItemStack a9 = new ItemStack(Material.BOOK);
		a9.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am9 = a9.getItemMeta();
		am9.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am9.setDisplayName(Manager.randomString() + "�aRuna de Extra��o");
		am9.setLore(Arrays.asList("�7Ao utilizar este item, os encantamentos do item escolhido", "�7ser�o removidos e transformados em livro."));
		a9.setItemMeta(am9);

		ItemStack a10 = new ItemStack(Material.IRON_INGOT);
		a10.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am10 = a10.getItemMeta();
		am10.addItemFlags(ItemFlag.HIDE_ENCHANTS );
		am10.setDisplayName(Manager.randomString() + "�aMoeda de Repara��o");
		am10.setLore(Arrays.asList("�7Ao utilizar este item, o item escolhido ser� totalmente reparado."));
		a10.setItemMeta(am10);

		ItemStack a11 = new ItemStack(Material.EYE_OF_ENDER);
		a11.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am11 = a11.getItemMeta();
		am11.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am11.setDisplayName(Manager.randomString() + "�eOlho Espi�o");
		am11.setLore(Arrays.asList("�7Ao usar este item, voc� entrar�", "�7em modo espi�o por 10 segundos.", "", " �e* Utilizado em invas�es."));
		a11.setItemMeta(am11);
		
		ItemStack a12 = new ItemStack(Material.ARROW);
		a12.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am12 = a12.getItemMeta();
		am12.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am12.setDisplayName(Manager.randomString() + "�aRetroceder");
		am12.setLore(Arrays.asList("�7Ao utilizar este item, voc� ir�", "�7recuar todos os membros de sua fac��o", "�7que estiver perto de voc�."));
		a12.setItemMeta(am12);
		
		ItemStack a13 = new ItemStack(Material.ARMOR_STAND);
		a13.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am13 = a13.getItemMeta();
		am13.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am13.setDisplayName(Manager.randomString() + "�eTotem da morte");
		am13.setLore(Arrays.asList("�7Caso voc� morra com este item no", "�7seu invent�rio voc� n�o perdera seus itens"));
		a13.setItemMeta(am13);
		
		ItemStack a14 = new ItemStack(Heads.CANHAO.clone());
		a14.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am14 = a14.getItemMeta();
		am14.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am14.setDisplayName(Manager.randomString() + "�bCanh�o Port�til");
		am14.setLore(Arrays.asList("�7Ao colocar este item no ch�o,", "�7ir� gerar um canh�o pronto.", "", " �e* Utilizada na cria��o de canh�es."));
		a14.setItemMeta(am14);
		
		ItemStack a15 = new ItemStack(Material.TNT);
		a15.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am15 = a15.getItemMeta();
		am15.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am15.setDisplayName(Manager.randomString() + "�cTNT Nuke");
		am15.setLore(Arrays.asList("�7Ao colocar este bloco no ch�o,", "�7ele se transformar� em uma TnT", "�7que destroi todos os tipos de bloco", "�7em um raio de 10x10.", "", " �e * Utilizada na cria��o de canh�es."));
		a15.setItemMeta(am15);
		
		ItemStack a16 = new ItemStack(Material.QUARTZ);
		a16.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am16 = a16.getItemMeta();
		am16.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am16.setDisplayName(Manager.randomString() + "�dFragmento");
		am16.setLore(Arrays.asList("�7Com este item, voc� possuindo", "�74 do mesmo tipo ser� poss�vel", "�7criar um poder m�ximo"));
		a16.setItemMeta(am16);

		ItemStack a17 = new ItemStack(Material.DISPENSER);
		a17.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am17 = a17.getItemMeta();
		am17.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am17.setDisplayName(Manager.randomString() + "�cDispenser de TNT");
		am17.setLore(Arrays.asList("�7Ao ser colocado, voc� n�o precisar�", "�7abastec�-lo com TNTs.", "", " �e * Utilizada na cria��o de canh�es."));
		a17.setItemMeta(am17);
		
		ItemStack a18 = new ItemStack(Material.DISPENSER);
		a18.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am18 = a18.getItemMeta();
		am18.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am18.setDisplayName(Manager.randomString() + "�cDispenser de Impuls�o");
		am18.setLore(Arrays.asList("�7Ao ser colocado, voc� n�o precisar�", "�7abastec�-lo com TNTs de impuls�o.", "", " �e * Utilizada na cria��o de canh�es."));
		a18.setItemMeta(am18);
		
		ItemStack v = new ItemStack(Material.ARROW);
		ItemMeta vm = v.getItemMeta();
		vm.setDisplayName("�aVoltar");
		v.setItemMeta(vm);

		inv.setItem(10, a);
		inv.setItem(11, a1);
		inv.setItem(12, a3);
		inv.setItem(13, a4);
		inv.setItem(14, a5);
		inv.setItem(15, a6);
		inv.setItem(16, a8);
		inv.setItem(19, ImpulsaoListener.getItem());
		inv.setItem(20, a2);
		inv.setItem(21, a9);
		inv.setItem(22, a10);
		inv.setItem(23, a11);
		inv.setItem(24, a7);
		inv.setItem(25, a71);
		inv.setItem(28, a12);
		inv.setItem(29, a13);
		inv.setItem(30, a14);
		inv.setItem(31, a15);
		inv.setItem(32, a16);
		inv.setItem(33, a17);
		inv.setItem(34, a18);
		inv.setItem(40, v);
		p.openInventory(inv);
	}

	public static void abrirMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "�8Itens Especiais");

		ItemStack m1 = new ItemStack(Material.CHEST);
		ItemMeta mm1 = m1.getItemMeta();
		mm1.setDisplayName("�aCaixas Misteriosas");
		m1.setItemMeta(mm1);
		mm1.setLore(Arrays.asList("�7Clique para ver as caixas misteriosas."));
		m1.setItemMeta(mm1);

		ItemStack m = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta mm = m.getItemMeta();
		mm.setDisplayName("�aItens");
		m.setItemMeta(mm);
		mm.setLore(Arrays.asList("�7Clique para ver todos os itens especiais."));
		m.setItemMeta(mm);

		inv.setItem(12, m1);
		inv.setItem(14, m);

		p.openInventory(inv);
	}

	public static void enviarCreeper(Player p) {
		ItemStack a = new ItemStack(Material.MONSTER_EGG, 1, (short) 50);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am.setDisplayName("�aSuper Creeper");
		am.setLore(Arrays.asList("�7Use para invocar um creeper eletrizado", "�7que causar� 5 de dano em blocos."));
		a.setItemMeta(am);
		p.getInventory().addItem(a);
	}

	public static void enviarBola(Player p) {
		ItemStack a = new ItemStack(Material.FIREBALL);
		ItemMeta am = a.getItemMeta();
		am.setDisplayName("�cBola de fogo");
		am.setLore(Arrays.asList("�7Use este item para disparar", "�7uma poderosa bola de fogo que", "�7causar� 5 de dano em blocos."));
		a.setItemMeta(am);
		p.getInventory().addItem(a);
	}

	public static void enviarBooster(Player p) {
		ItemStack a3 = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta am3 = a3.getItemMeta();
		am3.setDisplayName("�6Booster de Drop" + NoStack.randomString());
		am3.setLore(Arrays.asList("�7Use para receber o dobro de drops do tipo", "�7de mob que voc� selecionar."));
		a3.setItemMeta(am3);
		p.getInventory().addItem(a3);
	}

	public static void enviarBoosterExp(Player p) {
		ItemStack a3 = new ItemStack(Material.EXP_BOTTLE);
		ItemMeta am3 = a3.getItemMeta();
		am3.setDisplayName("�aBooster de Experi�ncia" + NoStack.randomString());
		am3.setLore(Arrays.asList("�7Use para receber o dobro de experi�ncia da", "�7habilidade que voc� selecionar."));
		a3.setItemMeta(am3);
		p.getInventory().addItem(a3);
	}

	public static void enviarLan�ador(Player p) {
		ItemStack a4 = new ItemStack(Material.FIREWORK);
		ItemMeta am4 = a4.getItemMeta();
		am4.setDisplayName("�eLan�ador");
		am4.setLore(Arrays.asList("�7Ao usar voc� ser� lan�ado 40 blocos para cima."));
		a4.setItemMeta(am4);
		p.getInventory().addItem(a4);
	}

	public static void enviarRaio(Player p) {
		ItemStack a5 = new ItemStack(Material.BLAZE_ROD);
		a5.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am5 = a5.getItemMeta();
		am5.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am5.setDisplayName("�6Raio Mestre");
		am5.setLore(Arrays.asList("�7Use para lan�ar um raio aonde voc�", "�7estiver olhando."));
		a5.setItemMeta(am5);
		p.getInventory().addItem(a5);
	}

	public static void enviarMembros(Player p) {
		ItemStack a = new ItemStack(Material.NETHER_STAR);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		am.setDisplayName("�eMembros+");
		am.setLore(Arrays.asList("�7Ao utilizar este item, o n�mero m�x�mo de", "�7membros da fac��o ser� aumentado."));
		a.setItemMeta(am);
		p.getInventory().addItem(a);
	}

	public static void enviarRege(Player p) {
		ItemStack a = new ItemStack(Material.REDSTONE);
		a.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am = a.getItemMeta();
		am.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am.setDisplayName("�eVida+");
		am.setLore(Arrays.asList("�7Ao utilizar este item, voc� ir�", "�7receber +3 cora��es."));
		a.setItemMeta(am);
		p.getInventory().addItem(a);
	}

	public static void enviarFufu(Player p) {
		ItemStack a8 = new ItemStack(Material.STICK);
		ItemMeta am8 = a8.getItemMeta();
		am8.setDisplayName("�eFuradeira");
		am8.setLore(Arrays.asList("�7Utilize para quebrar qualquer bloco", "�7de prote��o, cuidado com a sua vida �til."));
		a8.setItemMeta(am8);
		p.getInventory().addItem(a8);
	}

	public static void enviarTNT(Player p) {
		p.getInventory().addItem(ImpulsaoListener.getItem());
	}

	public static void enviarRuna(Player p) {
		ItemStack a9 = new ItemStack(Material.BOOK);
		a9.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am9 = a9.getItemMeta();
		am9.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am9.setDisplayName(Manager.randomString() + "�aRuna de Extra��o");
		am9.setLore(Arrays.asList("�7Ao utilizar este item, os encantamentos do item escolhido", "�7ser�o removidos e transformados em livro."));
		a9.setItemMeta(am9);
		p.getInventory().addItem(a9);
	}

	public static void enviarMoeda(Player p) {
		ItemStack a9 = new ItemStack(Material.IRON_INGOT);
		a9.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am9 = a9.getItemMeta();
		am9.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am9.setDisplayName(Manager.randomString() + "�aMoeda de Repara��o");
		am9.setLore(Arrays.asList("�7Ao utilizar este item, o item escolhido ser� totalmente reparado."));
		a9.setItemMeta(am9);
		p.getInventory().addItem(a9);
	}

	public static void enviarEncantador(Player p) {
		ItemStack a11 = new ItemStack(Material.EYE_OF_ENDER);
		a11.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta am11 = a11.getItemMeta();
		am11.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		am11.setDisplayName("�aEncantador");
		am11.setLore(Arrays.asList("�7Ao utilizar este item, o item escolhido ter� seus encantamentos", "�7evolu�dos em +1. Limite m�ximo 6."));
		a11.setItemMeta(am11);
		p.getInventory().addItem(a11 );
	}
	
	public static void enviarResetKDR(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.FIREWORK_CHARGE).removeAttributes().name("�6Reset KDR").lore("�7Use este item para resetar seu KDR.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarVip1(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.PAPER).removeAttributes().name("�eVIP �9Extra").lore("�7Ativando este item, voc�","�7receber� um VIP �9Betther", "�7com dura��o de 7 dias.", "", "�e * Voc� pode utilizar para si mesmo", "�e ou doar para um amigo.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarVip2(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.PAPER).removeAttributes().name("�eVIP �2Extra").lore("�7Ativando este item, voc�","�7receber� um VIP �2Ghuenon", "�7com dura��o de 7 dias.", "", "�e * Voc� pode utilizar para si mesmo", "�e ou doar para um amigo.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarRastreador(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Heads.AMARELO.clone()).removeAttributes().name(Manager.randomString() + "�aRastreador").lore("�7Utilize este item para localizar", "�7usu�rios de fac��es pelo mundo.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarMagnetico(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.REDSTONE_LAMP_OFF).removeAttributes().name(Manager.randomString() + "�aPulso Eletromagn�tico").lore("�7Ao ativar este item, o funcionamento da Redstone �", "�7temporariamente desativado por 1 minuto.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarC4(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Heads.C4.clone()).removeAttributes().name(Manager.randomString() + "�bC4 Explosive").lore("�7Ao colocar este item no ch�o,","�7ir� plantar uma bomba explosiva", "que vai explodir em 5 segundos.", "", "�e * Utilizada na cria��o de canh�es.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarSuperLancador(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.FIREWORK).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�eSuper Lan�ador").lore("�7Ao usar voc� ir� lan�ar", "�7todos os usu�rios em um raio", "�7de 10 blocos para cima.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarRetrocer(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.ARROW).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�aRetroceder").lore("�7Ao usar este item, voc� ir�", "�7recuar todos membros de sua fac��o", "�7que estiver perto de voc�.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarReparador(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.ANVIL).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�eReparador Instant�neo").lore("�7Ao usar este item, voc� ir�", "�7reparar toda a armadura que", "�7estiver equipada no seu corpo.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarAgrupador(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.HOPPER).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�dAgrupador de Po��es").lore("�7Agrupe at� 16 po��es consum�veis do", "�7mesmo tipo para que voc� tenha", "�7vantagem em suas batalhas.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarIncinerador(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.POTION).durability(16422).removeAttributes().name(Manager.randomString() + "�eIncinerador").lore("�7Est� po��o fara com que todos os", "�7usu�rios que forem atingidos por", "�7ela fiquem pegando fogo eternamente.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarSlime(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.SLIME_BALL).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�aDetector de Slime Chunk").lore("�7Ao clicar com este item","�7ele informar� se voc� est�", "�7em uma Slime Chunk.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarTotem(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.ARMOR_STAND).removeAttributes().name(Manager.randomString() + "�eTotem da Morte").lore("�7Caso voc� morra com este item no","�7invent�rio voc� n�o perdera seus itens.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarCanhao(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Heads.CANHAO.clone()).removeAttributes().name(Manager.randomString() + "�bCanh�o Port�til").lore("�7Ao colocar este item no ch�o,","�7ir� gerar um canh�o pronto.", "", "�e * Utilizada na cria��o de canh�es.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarNuke(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.TNT).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�cTNT Nuke").lore("�7Ao colocar este bloco no ch�o,", "�7ele se transformar� em uma TNT", "�7que destroi todos os tipos de blocos", "�7em um raio de 10x10.", "", "�e * Utilizada na cria��o de canh�es.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarDispenserSimples(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.DISPENSER).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�cDispenser de TNT").lore("�7Ao ser colocado, voc� n�o precisar�", "�7abastec�-lo com TNTs.", "", "�e * Utilizada na cria��o de canh�es.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarDispenserImpulsao(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.DISPENSER).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�cDispenser de Impuls�o").lore("�7Ao ser colocado, voc� n�o precisar�", "�7abastec�-lo com TNTs de impus�o.", "", "�e * Utilizada na cria��o de canh�es.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarWitherHead(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.SKULL_ITEM).durability(1).name("�5Cabe�a de Wither").lore("�7Utilize este item para soltar uma", "�7cabe�a de wither onde voc� est� olhando", "�7", "�7 * A cabe�a de wither causa ", "�74 de dano em blocos.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarPotionFood(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.POTION).durability(8236).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).name("�aPo��o Contra Fome").lore("�7Ao beber est� po��o, voc� fica", "�7sem fome durante um periodo de 30 minutos.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarFragmentos(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.QUARTZ).name("�dFragmento").enchant(Enchantment.DURABILITY, 1).removeAttributes().lore("�7Com este item, voc� possuindo", "�74 do mesmo tipo ser� poss�vel", "�7criar um poder m�ximo.").amount(1).toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarPurificador(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.POTION).durability(16422).removeAttributes().name(Manager.randomString() + "�9Purificador").lore("�7Est� po��o remover� todos os", "�7efeitos dos usu�rios que", "�7forem atingidos por ela.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarTrap(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.SNOW_BALL).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�cArmadilha").lore("�7Durante 12 segundos", "�7um quadrado de teia de 3x3", "�7ser� formado no local onde", "�7a bola de neve atingir.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarEletrizado(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.MONSTER_EGG).enchant(Enchantment.DURABILITY, 1).removeAttributes().durability(50).name("�aCreeper Eletrizado").lore("�7Use para invocar um creeper", "�7eletrizado que causar� um", "�7raio de explos�o 2x maior.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarSuperArea(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).enchant(Enchantment.DURABILITY, 1).removeAttributes().name(Manager.randomString() + "�bPicareta de Diamante").lore("�7Devasta��o I").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarSilk(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_PICKAXE).enchant(Enchantment.SILK_TOUCH, 2).name("�bPicareta de Diamante" + Stacker.randomString()).toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarDefensor(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.POTION).durability(8236).addItemFlag(ItemFlag.HIDE_POTION_EFFECTS).name("�aDefensor Secreto").lore("�7Ao beber est� po��o, voc� ficar�", "�7com vanish durante um periodo de tempo.").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}
	
	public static void enviarDivina(Player p) {
		if (p != null) {
			p.getInventory().addItem(new ItemBuilder(Material.DIAMOND_SWORD).enchant(Enchantment.DURABILITY, 1).removeAttributes().name("�cEspada Divina" + Stacker.randomString()).lore("�7Ao utilizar est� espada voc� receber�", "�fFor�a II �7por 10 segundos.", "", "�7Propriet�rio: �f" + p.getName(), "", "�eClique com o direito para ativar efeitos!").toItemStack());
		} else {
			System.out.print("[ESPECIAIS] Usu�rio n�o encontrado.");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void enviarCaixa(Player p, String tipo) {
		if (tipo.equals("LENDARIA")) {
			ItemStack f = new ItemStack(Material.ENDER_PORTAL_FRAME);
			ItemMeta fm = f.getItemMeta();
			fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fm.setDisplayName("�6Caixa Misteriosa");
			List<String> lf = new ArrayList();
			lf.add("�1");
			lf.add("�7Abra est� caixa e receba um item");
			lf.add("�7incr�vel que ser� sorteado.");
			lf.add("�2");
			lf.add("�fTipo da caixa: �6Lend�ria");
			fm.setLore(lf);
			f.setItemMeta(fm);
			if (taCheio(p)) {
				p.getWorld().dropItem(p.getLocation(), f);
				p.sendMessage("�aItem dropado.");
				return;
			}
			p.getInventory().addItem(f);
		}
		if (tipo.equals("PLATINA")) {
			ItemStack f = new ItemStack(Material.ENDER_CHEST);
			ItemMeta fm = f.getItemMeta();
			fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fm.setDisplayName("�3Caixa Misteriosa");
			List<String> lf = new ArrayList();
			lf.add("�1");
			lf.add("�7Abra esta caixa e receba um item");
			lf.add("�7incr�vel que ser� sorteado.");
			lf.add("�2");
			lf.add("�fTipo da caixa: �3Platina");
			fm.setLore(lf);
			f.setItemMeta(fm);
			if (taCheio(p)) {
				p.getWorld().dropItem(p.getLocation(), f);
				p.sendMessage("�aItem dropado.");
				return;
			}
			p.getInventory().addItem(f);
		}
		if (tipo.equals("BASICA")) {
			ItemStack f = new ItemStack(Material.CHEST);
			ItemMeta fm = f.getItemMeta();
			fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fm.setDisplayName("�eCaixa Misteriosa");
			List<String> lf = new ArrayList();
			lf.add("�1");
			lf.add("�7Abra est� caixa e receba um item");
			lf.add("�7incr�vel que ser� sorteado.");
			lf.add("�2");
			lf.add("�fTipo da caixa: �eB�sica");
			fm.setLore(lf);
			f.setItemMeta(fm);
			if (taCheio(p)) {
				p.getWorld().dropItem(p.getLocation(), f);
				p.sendMessage("�aItem dropado.");
				return;
			}
			p.getInventory().addItem(f);
		}
		if (tipo.equals("ASTRAL")) {
			ItemStack f = new ItemStack(Material.BEACON);
			ItemMeta fm = f.getItemMeta();
			fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fm.setDisplayName("�5Caixa Misteriosa");
			List<String> lf = new ArrayList();
			lf.add("�1");
			lf.add("�7Abra est� caixa e receba um item");
			lf.add("�7incr�vel que ser� sorteado.");
			lf.add("�2");
			lf.add("�fTipo da caixa: �5Astral");
			fm.setLore(lf);
			f.setItemMeta(fm);
			if (taCheio(p)) {
				p.getWorld().dropItem(p.getLocation(), f);
				p.sendMessage("�aItem dropado.");
				return;
			}
			p.getInventory().addItem(f);
		}
		if (tipo.equals("ARMADURAS")) {
			ItemStack f = new ItemStack(Material.DIAMOND_CHESTPLATE);
			ItemMeta fm = f.getItemMeta();
			fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fm.setDisplayName("�bCaixa Misteriosa");
			List<String> lf = new ArrayList();
			lf.add("�1");
			lf.add("�7Abra est� caixa e receba um item");
			lf.add("�7incr�vel que ser� sorteado.");
			lf.add("�2");
			lf.add("�fTipo da caixa: �bArmaduras");
			fm.setLore(lf);
			f.setItemMeta(fm);
			if (taCheio(p)) {
				p.getWorld().dropItem(p.getLocation(), f);
				p.sendMessage("�aItem dropado.");
				return;
			}
			p.getInventory().addItem(f);
		}
		if (tipo.equals("SPAWNERS")) {
			ItemStack f = new ItemStack(Material.MOB_SPAWNER);
			ItemMeta fm = f.getItemMeta();
			fm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			fm.setDisplayName("�9Caixa Misteriosa");
			List<String> lf = new ArrayList();
			lf.add("�1");
			lf.add("�7Abra est� caixa e receba um item");
			lf.add("�7incr�vel que ser� sorteado.");
			lf.add("�2");
			lf.add("�fTipo da caixa: �9Spawners");
			fm.setLore(lf);
			f.setItemMeta(fm);
			if (taCheio(p)) {
				p.getWorld().dropItem(p.getLocation(), f);
				p.sendMessage("�aItem dropado.");
				return;
			}
			p.getInventory().addItem(f);
		}
		if (tipo.equals("BOOSTER")) {
			ItemStack a3 = new ItemStack(Material.BLAZE_POWDER);
			ItemMeta am3 = a3.getItemMeta();
			am3.setDisplayName("�6Booster de Drop" + NoStack.randomString());
			am3.setLore(Arrays.asList("�7Use para receber o dobro de drops do tipo", "�7de mob que voc� selecionar."));
			a3.setItemMeta(am3);
			p.getInventory().addItem(a3);
		}
	}

	@EventHandler
	public void onInventoryClickEvent(InventoryClickEvent e) {
		if (e.getInventory().getName().equals("�8Itens Especiais")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if (e.getSlot() == 12) {
				abrirMenuCaixas(p);
			}
			if (e.getSlot() == 14) {
				abrirMenuItens(p);
			}
		}
		if (e.getInventory().getName().equals("�8Itens Especiais - Caixas")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if (e.getSlot() == 10) {
				enviarCaixa(p, "BASICA");
			}
			if (e.getSlot() == 11) {
				enviarCaixa(p, "PLATINA");
			}
			if (e.getSlot() == 12) {
				enviarCaixa(p, "LENDARIA");
			}
			if (e.getSlot() == 13) {
				enviarCaixa(p, "ARMADURAS");
			}
			if (e.getSlot() == 14) {
				enviarCaixa(p, "SPAWNERS");
			}
			if (e.getSlot() == 15) {
				enviarCaixa(p, "ASTRAL");
			}
			if (e.getSlot() == 31) {
				abrirMenu(p);
			}
		}
		if (e.getInventory().getName().equals("�8Itens Especiais - Itens")) {
			if (e.getCurrentItem() == null) {
				return;
			}
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if (e.getSlot() == 10) {
				enviarCreeper(p);
			}
			if (e.getSlot() == 11) {
				enviarBola(p);
			}
			if (e.getSlot() == 12) {
				enviarBooster(p);
			}
			if (e.getSlot() == 13) {
				enviarLan�ador(p);
			}
			if (e.getSlot() == 14) {
				enviarRaio(p);
			}
			if (e.getSlot() == 15) {
				enviarResetKDR(p);
			}
			if (e.getSlot() == 16) {
				enviarFufu(p);
			}
			if (e.getSlot() == 19) {
				enviarTNT(p);
			}
			if (e.getSlot() == 20) {
				enviarMembros(p);
			}
			if (e.getSlot() == 21) {
				enviarRuna(p);
			}
			if (e.getSlot() == 22) {
				enviarMoeda(p);
			}
			if (e.getSlot() == 23) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "giveeye "+ p.getName() + " 1");
			}
			if (e.getSlot() == 24) {
				enviarBoosterExp(p);
			}
			if (e.getSlot() == 25) {
				enviarRege(p);
			}
			if (e.getSlot() == 28) {
				enviarRetrocer(p);
			}
			if (e.getSlot() == 29) {
				enviarTotem(p);
			}
			if (e.getSlot() == 30) {
				enviarCanhao(p);
			}
			if (e.getSlot() == 31) {
				enviarNuke(p);
			}
			if (e.getSlot() == 32) {
				enviarFragmentos(p);
			}
			if (e.getSlot() == 33) {
				enviarDispenserSimples(p);
			}
			if (e.getSlot() == 34) {
				enviarDispenserImpulsao(p);
			}
			if (e.getSlot() == 40) {
				abrirMenu(p);
			}
		}
	}

	public static boolean taCheio(final Player p) {
		if (p.getInventory().getContents().length > 0) {
			int i = 0;
			ItemStack[] contents;
			for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
				final ItemStack it = contents[j];
				if (it != null && !it.getType().equals((Object) Material.AIR)) {
					++i;
				}
			}
			if (i >= 36) {
				return true;
			}
		}
		return false;
	}
	
	public static ItemStack addGlow(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null) {
			tag = nmsStack.getTag();
		}
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		return CraftItemStack.asCraftMirror(nmsStack);
	}
}
