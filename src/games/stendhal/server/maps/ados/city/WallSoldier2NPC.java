/***************************************************************************
 *                     (C) Copyright 2017 - Stendhal                       *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.maps.ados.city;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.SpeakerNPC;

/**
 * Soldiers on the wall
 *
 * @author snowflake
 * @author hendrik
 */
public class WallSoldier2NPC implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildAdosWallSoldier(zone);
	}

	/**
	 * Creatures a soldier on the city wall
	 *
	 * @param zone StendhalRPZone
	 */
	private void buildAdosWallSoldier(final StendhalRPZone zone) {

		final SpeakerNPC npc = new SpeakerNPC("Hermius") {

			@Override
			protected void createPath() {
				final List<Node> path = new LinkedList<Node>();
				path.add(new Node(80, 73));
				path.add(new Node(55, 73));
				path.add(new Node(55, 70));
				path.add(new Node(80, 70));
				setPath(new FixedPath(path, true));
			}

			@Override
			protected void createDialog() {
				addGreeting("Cześć i witaj w mieście Ados!");
				addJob("Praca? Czy czasem nie jesteś ślepy? Jestem strażnikiem! Czy to nie jest oczywiste? Teraz ruszaj się, obywatelu. Nie mam czasu na pogawędkę.");
				addHelp("Jeżeli potrzebujesz mapy Ados oraz poprowadził Ciebie wokół miasta, to tylko żołnierz Julius, który znajduje się blisko bramy miasta.");
				addGoodbye("Mam nadzieje, że dobrze bawisz się w Ados.");
			}
		};

		npc.setEntityClass("youngsoldiernpc");
		npc.setPosition(80, 73);
		npc.initHP(100);
		npc.setDescription("Oto Hermius, żołnierz, który strzeże mury miasta Ados.");
		zone.add(npc);
	}
}
