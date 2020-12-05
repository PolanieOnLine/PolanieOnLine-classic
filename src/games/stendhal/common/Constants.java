/***************************************************************************
 *                   (C) Copyright 2003-2020 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.common;

import java.util.Arrays;
import java.util.List;

/**
 * Constants about slots
 */
public final class Constants {
	/**
	 * All the slots considered to be "with" the entity. Listed in priority
	 * order (i.e. bag first).
	 */
	// TODO: let the slots decide that themselves
	public static final String[] CARRYING_SLOTS = {
			"bag", "money", "keyring", "back", "neck", "head", "belt",
			"cloak", "lhand", "armor", "rhand", "finger", "fingerb",
			"glove", "legs", "feet" };

	/**
	 * Modes that can be used for setting combat karma.
	 */
	public final static List<String> KARMA_SETTINGS = Arrays.asList(
			"nigdy", "normalny", "zawsze");

	public static final String[] CARRYING_SLOTS_BANKS = { "bag", "neck", "head", "cloak",
			"lhand", "armor", "rhand", "finger", "legs", "keyring", "back", "belt",
			"fingerb", "feet", "glove", "money", "bank", "bank_ados", "bank_deniran", "bank_fado",
			"bank_nalwor", "zaras_chest_ados", "bank_zakopane", "bank_krakow", "bank_gdansk" };
}
