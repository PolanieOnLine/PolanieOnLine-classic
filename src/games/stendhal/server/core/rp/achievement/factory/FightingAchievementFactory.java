/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.core.rp.achievement.factory;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import games.stendhal.common.parser.Sentence;
import games.stendhal.server.constants.KillType;
import games.stendhal.server.core.rp.achievement.Achievement;
import games.stendhal.server.core.rp.achievement.Category;
import games.stendhal.server.core.rp.achievement.condition.KilledRareCreatureCondition;
import games.stendhal.server.core.rp.achievement.condition.KilledSharedAllCreaturesCondition;
import games.stendhal.server.core.rp.achievement.condition.KilledSoloAllCreaturesCondition;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.npc.ChatCondition;
import games.stendhal.server.entity.npc.condition.AndCondition;
import games.stendhal.server.entity.npc.condition.PlayerHasKilledNumberOfCreaturesCondition;
import games.stendhal.server.entity.player.Player;
/**
 * Factory for fighting achievements
 *
 * @author madmetzger
 */
public class FightingAchievementFactory extends AbstractAchievementFactory {

	public static final String ID_RATS = "fight.general.rats";
	public static final String ID_EXTERMINATOR = "fight.general.exterminator";
	public static final String ID_DEER = "fight.general.deer";
	public static final String ID_BOARS = "fight.general.boars";
	public static final String ID_BEARS = "fight.general.bears";
	public static final String ID_FOXES = "fight.general.foxes";
	public static final String ID_SAFARI = "fight.general.safari";
	public static final String ID_ENTS = "fight.general.ents";
	public static final String ID_POACHER = "fight.special.rare";
	public static final String ID_LEGEND = "fight.special.all";
	public static final String ID_TEAM_PLAYER = "fight.special.allshared";
	public static final String ID_GIANTS = "fight.solo.giant";
	public static final String ID_ANGELS = "fight.general.angels";
	public static final String ID_WEREWOLF = "fight.general.werewolf";
	public static final String ID_MERMAIDS = "fight.general.mermaids";
	public static final String ID_DEEPSEA = "fight.general.deepsea";
	public static final String ID_ZOMBIES = "fight.general.zombies";
	public static final String ID_FOWL = "fight.general.fowl";
	public static final String ID_PACHYDERM = "fight.general.pachyderm";
	public static final String ID_DRAGONSLAYER = "fight.general.dragonsslayer";
	public static final String ID_SERAFINS = "fight.general.serafins";
	public static final String ID_DEATHS = "fight.general.deaths";

	public static final String[] ENEMIES_EXTERMINATOR = {
			"szczur", "szczur jaskiniowy", "wściekły szczur", "szczur zombie", "krwiożerczy szczur",
			"szczur olbrzymi", "człekoszczur", "człekoszczurzyca", "archiszczur"
	};

	public static final String[] ENEMIES_DRAGONSLAYER = {
			"szkielet smoka", "zgniły szkielet smoka", "złoty smok", "zielony smok", "błękitny smok",
			"czerwony smok", "pustynny smok", "czarny smok", "czarne smoczysko", "smok arktyczny",
			"dwugłowy zielony smok", "dwugłowy czerwony smok", "dwugłowy niebieski smok", "dwugłowy czarny smok",
			"dwugłowy lodowy smok", "lodowy smok", "latający czarny smok", "latający złoty smok", "Smok Wawelski",
			"purpurowy smok"
	};

	public static final String[] ENEMIES_SERAFINS = {
			"serafin", "azazel"
	};

	public static final String[] ENEMIES_DEATHS = {
			"śmierć", "czarna śmierć", "złota śmierć", "kostucha", "kostucha różowa", "kostucha wielka",
			"kostucha różowa wielka", "kostucha złota wielka"
	};

	// enemies required for David vs. Goliath
	public static final String[] ENEMIES_GIANTS = {
			"olbrzym", "olbrzym starszy", "amazonka olbrzymia", "olbrzym mistrz", "czarny olbrzym",
			"imperialny generał gigant", "kasarkutominubat", "kobold olbrzymi", "gigantyczny krasnal",
			"supeczłowiek olbrzym", "lodowy olbrzym", "lodowy starszy olbrzym", "Dhohr Nuggetcutter",
			"Lord Durin"
	};

	// enemies required for Heavenly Wrath
	public static final String[] ENEMIES_ANGELS = {
			"anioł", "archanioł", "anioł ciemności", "archanioł ciemności", "upadły anioł",
			"aniołek"
	};

	// enemies required for Serenade the Siren
	public static final String[] ENEMIES_MERMAIDS = {
			"syrena ametystowa", "syrena szmaragdowa", "syrena rubinowa", "syrena szafirowa"
	};

	// enemies required for Deep Sea Fisherman
	public static final String[] ENEMIES_DEEPSEA = {
			"rekin", "kraken", "neo kraken"
	};

	// enemies required for Zombie Apocalypse
	public static final String[] ENEMIES_ZOMBIES = {
			"zombi", "krwawy zombi", "bezgłowy potwór", "szczur zombie"
	};

	// enemies required for Chicken Nuggets
	public static final String[] ENEMIES_FOWL = {
			"pisklak", "kogucik", "dodo", "kokoszka", "pingwin", "gołąb"
	};

	// enemies required for Pachyderm Mayhem
	public static final String[] ENEMIES_PACHYDERM = {
			"słoń", "słoń z kłami", "słoń musth", "mamut włochaty"
	};

	@Override
	public Collection<Achievement> createAchievements() {
		List<Achievement> fightingAchievements = new LinkedList<Achievement>();
		fightingAchievements.add(createAchievement(
				ID_RATS, "Łowca Szczurów", "Zabił 15 szczurów",
				Achievement.EASY_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition("szczur", 15)));

		fightingAchievements.add(createAchievement(
				ID_EXTERMINATOR, "Eksterminator", "Zabił po 10 szczurów z każdego rodzaju",
				Achievement.MEDIUM_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(10, ENEMIES_EXTERMINATOR)));

		fightingAchievements.add(createAchievement(
				ID_DEER, "Łowca Jeleni", "Zabił 25 jeleni",
				Achievement.EASY_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition("jeleń", 25)));

		fightingAchievements.add(createAchievement(
				ID_BOARS, "Łowca Dzików", "Zabił 50 dzików",
				Achievement.EASY_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition("dzik", 50)));

		fightingAchievements.add(createAchievement(
				ID_BEARS, "Łowca Niedźwiedzi", "Zabił po 25 niedźwiedzi grizli, niedźwiedzi i misi",
				Achievement.EASY_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(25, "niedźwiedź", "niedźwiedź grizli", "miś")));

		fightingAchievements.add(createAchievement(
				ID_FOXES, "Łowca Lisic", "Zabił 20 lisic",
				Achievement.EASY_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition("lisica", 20)));

		fightingAchievements.add(createAchievement(
				ID_SAFARI, "Safari", "Zabił po 30 tygrysów, lwów i 50 słoni",
				Achievement.EASY_BASE_SCORE, true,
				new AndCondition(
								new PlayerHasKilledNumberOfCreaturesCondition("tygrys", 30),
								new PlayerHasKilledNumberOfCreaturesCondition("lew", 30),
								new PlayerHasKilledNumberOfCreaturesCondition("słoń", 50))));

		fightingAchievements.add(createAchievement(
				ID_ENTS, "Drwal", "Zabił po 10 drzewców, drzewcowych i uschłych drzewców",
				Achievement.MEDIUM_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(10, "drzewiec", "drzewcowa", "uschły drzewiec")));

		fightingAchievements.add(createAchievement(
				ID_POACHER, "Kłusownik", "Zabił każdego rzadkiego potwora",
				Achievement.HARD_BASE_SCORE, true,
				new KilledRareCreatureCondition()));

		fightingAchievements.add(createAchievement(
				ID_LEGEND, "Legenda", "Zabił sam wszystkie potwory",
				Achievement.LEGENDARY_BASE_SCORE, true,
				new KilledSoloAllCreaturesCondition()));

		fightingAchievements.add(createAchievement(
				ID_TEAM_PLAYER, "Wojownik Drużyny", "Zabił z drużyną wszystkie potwory",
				Achievement.LEGENDARY_BASE_SCORE, true,
				new KilledSharedAllCreaturesCondition()));

		fightingAchievements.add(createAchievement(
				ID_DRAGONSLAYER, "Pogromca Smoków", "Zabił razem 1,000 różnych smoków",
				Achievement.HARD_BASE_SCORE, true,
				new ChatCondition() {
					@Override
					public boolean fire(Player player, Sentence sentence, Entity npc) {
						int kills = 0;
						for (final String dragon: ENEMIES_DRAGONSLAYER) {
							kills += player.getSoloKill(dragon) + player.getSharedKill(dragon);
						}
						return kills >= 1000;
					}
				}));

		fightingAchievements.add(createAchievement(
				ID_SERAFINS, "Serafiny mu Niestraszne", "Zabił po 10 serafinów i azazeli",
				Achievement.HARD_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(10, ENEMIES_SERAFINS)));

		fightingAchievements.add(createAchievement(
				ID_DEATHS, "Władca Śmierci", "Zabił po 10 śmierci, czarnej śmierci, złotej śmierci, kostuch, kostuch różowych, kostuch wielkich, kostuch różowych wielkich i kostuch złotych wielkich w pojedynkę",
				Achievement.HARD_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(10, KillType.SOLO, ENEMIES_DEATHS)));

		fightingAchievements.add(createAchievement(
				ID_GIANTS, "Dawid kontra Goliat", "Zabił po 20 olbrzymów każdego rodzaju w pojedynkę",
				Achievement.MEDIUM_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(20, KillType.SOLO, ENEMIES_GIANTS)));

		fightingAchievements.add(createAchievement(
				ID_ANGELS, "Niebiański Gniew", "Zabił po 100 aniołów każdego rodzaju",
				Achievement.HARD_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(100, ENEMIES_ANGELS)));

		fightingAchievements.add(createAchievement(
				ID_WEREWOLF, "Srebrny Pocisk", "Zabił 500 wilkołaków",
				Achievement.MEDIUM_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(500, "wilkołak")));

		fightingAchievements.add(createAchievement(
				ID_MERMAIDS, "Serenada Syren", "Zabił 10,000 klejnotowych rodzai syren",
				Achievement.HARD_BASE_SCORE, true,
				new ChatCondition() {
					@Override
					public boolean fire(final Player player, final Sentence sentence, final Entity npc) {
						int kills = 0;

						for (final String mermaid: ENEMIES_MERMAIDS) {
							kills += player.getSoloKill(mermaid) + player.getSharedKill(mermaid);
						}

						return kills >= 10000;
					}
				}));

		fightingAchievements.add(createAchievement(
				ID_DEEPSEA, "Głębinowy Rybak", "Zabił po 500 rekinów, krakenów oraz neo krakenów",
				Achievement.MEDIUM_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(500, ENEMIES_DEEPSEA)));

		fightingAchievements.add(createAchievement(
				ID_ZOMBIES, "Apokalipsa Zombi", "Zabił razem 500 różnych zombi",
				Achievement.EASY_BASE_SCORE, true,
				new ChatCondition() {
					@Override
					public boolean fire(Player player, Sentence sentence, Entity npc) {
						int kills = 0;
						for (final String zombie: ENEMIES_ZOMBIES) {
							kills += player.getSoloKill(zombie) + player.getSharedKill(zombie);
						}
						return kills >= 500;
					}
				}));

		fightingAchievements.add(createAchievement(
				ID_FOWL, "Nuggetsy z Kurczaka", "Zabił po 100 każdego rodzaju ptactwa",
				Achievement.EASY_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(100, ENEMIES_FOWL)));

		fightingAchievements.add(createAchievement(
				ID_PACHYDERM, "Gruboskórny Zamęt", "Zabił po 100 słoni, słoni z kłami, słoni musth oraz mamutów włochatych",
				Achievement.MEDIUM_BASE_SCORE, true,
				new PlayerHasKilledNumberOfCreaturesCondition(100, ENEMIES_PACHYDERM)));

		return fightingAchievements;
	}

	@Override
	protected Category getCategory() {
		return Category.FIGHTING;
	}
}
