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
package games.pol.server.maps.krakow.sukiennice;

import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.BuyerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.BuyerBehaviour;

/*
 * Inside Semos Tavern - Level 0 (ground floor)
 */
public class RybakMiloradNPC implements ZoneConfigurator {
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
		final SpeakerNPC npc = new SpeakerNPC("Rybak Miłorad") {
			@Override
			protected void createDialog() {
				addGreeting();
				addJob("Uprzejmie witam wielmożność.");
				addHelp("Skupuję ryby. W książce przede mną są ceny i rodzaje ryb, które kupuję.");
				new BuyerAdder().addBuyer(this, new BuyerBehaviour(shops.get("buyrybak")), false);
				addOffer("Kupię ryby, oferta moja jest w książce.");
				addGoodbye("Do widzenia. Miłego dnia życzę.");
			}
		};

		npc.setDescription("Oto Rybak Miłorad, który jest bardzo uprzejmy.");
		npc.setEntityClass("man_008_npc");
		npc.setPosition(26, 36);
		npc.setDirection(Direction.LEFT);
		npc.initHP(100);
		zone.add(npc);
	}
}
