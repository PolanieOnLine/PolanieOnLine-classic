/***************************************************************************
 *                   (C) Copyright 2003-2019 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.pol.server.maps.wieliczka.townhall;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.SellerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.SellerBehaviour;

/**
 * @author KarajuSs
 */
public class ZbigniewNPC implements ZoneConfigurator {
	private final ShopList shops = SingletonRepository.getShopList();

	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	@Override
	public void configureZone(final StendhalRPZone zone, final Map<String, String> attributes) {
		buildNPC(zone);
	}

	private void buildNPC(final StendhalRPZone zone) {
		final SpeakerNPC npc = new SpeakerNPC("Zbigniew") {
			@Override
			protected void createPath() {
				final List<Node> nodes = new LinkedList<Node>();
				nodes.add(new Node(13, 4));
				nodes.add(new Node(19, 4));
				setPath(new FixedPath(nodes, true));
			}

			@Override
			protected void createDialog() {
				addGreeting("Witam obywatelu! Potrzebujesz #pomocy?");
				addJob("Jestem tutejszym burmistrzem.");
				addHelp("W Wieliczce spotkasz wielu ludzi, którzy zaoferują Tobie pomoc w różnych dziedzinach.");
				addOffer("Spójrz na karteczkę, która leży przed Twoim nosem!");
				new SellerAdder().addSeller(this, new SellerBehaviour(shops.get("zwojewieliczka")));
				addGoodbye("Życzę miłego dnia i miłego pobytu!");
			}
		};

		npc.setEntityClass("badmayornpc");
		npc.setDescription("Oto burmistrz Wieliczki, Zbigniew, zdaje się, że idzie w twoją stronę.");
		npc.setPosition(13, 4);
		npc.initHP(100);
		zone.add(npc);
	}
}
