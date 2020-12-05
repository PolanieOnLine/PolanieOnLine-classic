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
package games.pol.server.maps.zakopane.sewinghouse;

import java.util.Map;

import games.stendhal.common.Direction;
import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.ShopList;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.adder.BuyerAdder;
import games.stendhal.server.entity.npc.behaviour.impl.BuyerBehaviour;

/**
 * @author ZEKKEQ
 */
public class GlovesBuyerNPC implements ZoneConfigurator {
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
		final SpeakerNPC npc = new SpeakerNPC("Ludwina") {

			@Override
			protected void createDialog() {
				addGreeting("Witaj w szwalni rękawic! W czym mogę #pomóc.");
				addHelp("Możesz sprzedać mi kilka starych rękawic, jeśli masz jakieś... Spójrz na książkę.");
				addHelp("Jedna z moich znajomych znajdujących się piętro wyżej, jest kolekcjonerką. Zapytaj, a może będzie potrzebować pomocy. "
						+ "Możesz również sprzedać mi kilka starych rękawic, jeśli masz jakieś... Spójrz na książkę.");
				addJob("Zajmuję się skupem starych rękawic oraz skór zwierząt.");
				new BuyerAdder().addBuyer(this, new BuyerBehaviour(shops.get("buygloves")), false);
				addOffer("Spójrz na książkę, by zaznajomić się z cenami skupu.");
				addGoodbye();
			}
		};

		npc.setDescription("Oto Ludwina. Zajmuje się skupem rękawic.");
		npc.setEntityClass("woman_002_npc");
		npc.setPosition(8, 3);
		npc.setDirection(Direction.DOWN);
		npc.initHP(100);
		zone.add(npc);
	}
}
