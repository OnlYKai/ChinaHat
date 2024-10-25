package com.kai.chinahat.utils;

import java.util.ArrayList;
import java.util.List;

public class PartyGetter {

	/*
	[MVP+] Player: Message

	[MVP+] Player joined the party.
	[MVP+] Player has left the party.

	-----------------------------------------------------
	Party Members (3)
	Party Leader: [MVP+] Player ●
	Party Members: [MVP+] Player ● [MVP+] Player ●
	-----------------------------------------------------

	The party was transferred to [MVP+] Player because [MVP+] Player left

	[MVP+] Player has disbanded the party!

	The party was disbanded because all invites expired and the party was empty.

	-----------------------------------------------------
	You have joined [MVP+] Player's party!
	You'll be partying with: [MVP+] Player, [MVP+] Player
	-----------------------------------------------------

	You left the party.

	You have been kicked from the party by [MVP+] Player

	[MVP+] Player has been removed from the party.
	*/

	public static List<String> inParty = new ArrayList<>();


	private static final String PLAYER_MESSAGE = "^(\\[.*?\\] )?([a-zA-Z0-9_]+): (.*)$";

	private static final String PLAYER_PATTERN = "^(\\[.*?\\] )?([a-zA-Z0-9_]+)$";

	private static final String PARTY_JOINED_PATTERN = "^(\\[.*?\\] )?([a-zA-Z0-9_]+) joined the party\\.$";
	private static final String PARTY_LEFT_PATTERN = "^(\\[.*?\\] )?([a-zA-Z0-9_]+) has left the party\\.$";

	private static final String PARTY_LEADER_PATTERN = "^Party Leader: (\\[.*?\\] )?([a-zA-Z0-9_]+) ●$";
	private static final String PARTY_MEMBERS_PATTERN = "^Party Members: (.*)$";

	private static final String PARTY_TRANSFERRED_PATTERN = "^The party was transferred to (\\[.*?\\] )?([a-zA-Z0-9_]+) because (\\[.*?\\] )?([a-zA-Z0-9_]+) left$";

	private static final String PARTY_DISBANDED_PATTERN = "^(\\[.*?\\] )?([a-zA-Z0-9_]+) has disbanded the party!$";
	private static final String PARTY_EMPTY_PATTERN = "^The party was disbanded because all invites expired and the party was empty\\.$";

	private static final String PARTY_JOINED_LEADER_PATTERN = "^You have joined (\\[.*?\\] )?([a-zA-Z0-9_]+)'s party!$";
	private static final String PARTY_JOINED_MEMBERS_PATTERN = "^You'll be partying with: (.*)$";

	private static final String PARTY_YOU_LEFT_PATTERN = "^You left the party\\.$";

	private static final String PARTY_YOU_KICKED_PATTERN = "^You have been kicked from the party by (\\[.*?\\] )?([a-zA-Z0-9_]+)$";

	private static final String PARTY_KICKED_PATTERN = "^(\\[.*?\\] )?([a-zA-Z0-9_]+) has been removed from the party\\.$";


	public void onChatEvent(String message) {
		// Remove formatting (even though I assume it should already be removed by getUnformattedText(), it's not)
		message = message.replaceAll("(?i)§[0-9a-fklmnor]", "");
		// Filter out player messages
		if (!message.matches(PLAYER_MESSAGE)) {
			// Player joined/left (add/remove)
			if (message.matches(PARTY_JOINED_PATTERN)) {
				message = message.replaceAll(PARTY_JOINED_PATTERN, "$2");
				if (!inParty.contains(message))
					inParty.add(message);
			}
			else if (message.matches(PARTY_LEFT_PATTERN)) {
				message = message.replaceAll(PARTY_LEFT_PATTERN, "$2");
				if (inParty.contains(message))
					inParty.remove(message);
			}

			// Party list Leader/Members (add)
			else if (message.matches(PARTY_LEADER_PATTERN)) {
				inParty.clear();
				message = message.replaceAll(PARTY_LEADER_PATTERN, "$2");
				inParty.add(message);
			}
			else if (message.matches(PARTY_MEMBERS_PATTERN)) {
				message = message.replaceAll(PARTY_MEMBERS_PATTERN, "$1");
				String[] players = message.split("●");
				for (String player : players) {
					player = player.trim();
					if (player.matches(PLAYER_PATTERN)) {
						player = player.replaceAll(PLAYER_PATTERN, "$2");
						if (!inParty.contains(player))
							inParty.add(player);
					}
				}
			}

			// Transferred because player left (remove)
			else if (message.matches(PARTY_TRANSFERRED_PATTERN)) {
				message = message.replaceAll(PARTY_TRANSFERRED_PATTERN, "$4");
				if (inParty.contains(message))
					inParty.remove(message);
			}

			// Disbanded (clear)
			else if (message.matches(PARTY_DISBANDED_PATTERN))
				inParty.clear();
			else if (message.matches(PARTY_EMPTY_PATTERN))
				inParty.clear();

			// You joined Leader/Members (add)
			else if (message.matches(PARTY_JOINED_LEADER_PATTERN)) {
				message = message.replaceAll(PARTY_JOINED_LEADER_PATTERN, "$2");
				if (!inParty.contains(message))
					inParty.add(message);
			}
			else if (message.matches(PARTY_JOINED_MEMBERS_PATTERN)) {
				message = message.replaceAll(PARTY_JOINED_MEMBERS_PATTERN, "$1");
				String[] players = message.split(",");
				for (String player : players) {
					player = player.trim();
					if (player.matches(PLAYER_PATTERN)) {
						player = player.replaceAll(PLAYER_PATTERN, "$2");
						if (!inParty.contains(player))
							inParty.add(player);
					}
				}
			}

			// You left (clear)
			else if (message.matches(PARTY_YOU_LEFT_PATTERN))
				inParty.clear();

			// You got kicked (clear)
			else if (message.matches(PARTY_YOU_KICKED_PATTERN))
				inParty.clear();

			// Player got kicked (remove)
			else if (message.matches(PARTY_KICKED_PATTERN)) {
				message = message.replaceAll(PARTY_KICKED_PATTERN, "$2");
				if (inParty.contains(message))
					inParty.remove(message);
			}
		}
	}

}