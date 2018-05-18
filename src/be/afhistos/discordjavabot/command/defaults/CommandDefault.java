package be.afhistos.discordjavabot.command.defaults;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class CommandDefault {

	private final Main botDiscord;
	
	public CommandDefault(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
	@Command(name="stop",type=ExecutorType.CONSOLE)
	private void stop() {
		botDiscord.setRunning(false);
	}
	
	
	@Command(name="restart",type=ExecutorType.USER)
	private void restartdiscord(User user, MessageChannel channel) throws InterruptedException {
		TextChannel textChannel = (TextChannel)channel;
		if(channel instanceof TextChannel) {
			if(!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) return;
		}
		channel.sendMessage("Redémarrage du bot").queue();
		botDiscord.setRunning(false);
		wait(3);
		botDiscord.setRunning(true);
		channel.sendTyping();
		channel.sendMessage("Bot redémarré !").queue();
		
	}
	

}
