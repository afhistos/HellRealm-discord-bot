package be.afhistos.discordjavabot.command.defaults;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class CommandInfo {

	private final Main botDiscord;
	
	public CommandInfo(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
	
	@Command(name="info",type=ExecutorType.USER)
	private void info(User user, MessageChannel channel) {
		
		channel.sendMessage(user.getAsMention() + "'s informations ").queue();
		channel.sendMessage("à rejoint discord le " + user.getCreationTime()).queue();
		channel.sendMessage("URL de son avatar " + user.getAvatarUrl()).queue();
		channel.sendMessage("ID: " + user.getId()).queue();
		
	}
}
