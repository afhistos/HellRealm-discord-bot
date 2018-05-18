package be.afhistos.discordjavabot.command.defaults;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;

public class CommandSetGame {
	private final Main botDiscord;

	public CommandSetGame(Main botDiscord) {
		this.botDiscord= botDiscord;
	}
	@Command(name="setgame",type=ExecutorType.ALL)
	private void setGame(JDA jda, String[] args) {
		StringBuilder builder = new StringBuilder();
		String GameTypes = args[0];
		String Link = args[1];
		for(String str : args) {
			if(builder.length() > 0) builder.append(" ");
			builder.append(str);
		}
		String Jeu = builder.toString().replace(GameTypes, "");
		if(GameTypes.equalsIgnoreCase("playing")) jda.getPresence().setGame(Game.of(GameType.DEFAULT, Jeu));
		else if(GameTypes.equalsIgnoreCase("listening")) jda.getPresence().setGame(Game.of(GameType.LISTENING, Jeu));
		else if(GameTypes.equalsIgnoreCase("watching")) jda.getPresence().setGame(Game.of(GameType.WATCHING, Jeu));
		else if(GameTypes.equalsIgnoreCase("streaming")) {
			String stream = builder.toString().replace(GameTypes, "").replace(Link, "");
			jda.getPresence().setGame(Game.of(GameType.STREAMING, stream, Link));			
		}
		else jda.getPresence().setGame(Game.of(GameType.DEFAULT, Jeu));
		
	}
}
