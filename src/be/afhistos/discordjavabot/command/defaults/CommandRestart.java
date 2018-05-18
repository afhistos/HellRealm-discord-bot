package be.afhistos.discordjavabot.command.defaults;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;
import net.dv8tion.jda.core.JDA;

public class CommandRestart {

private final Main botDiscord;
	
	public CommandRestart(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
	@Command(name="stops",type=ExecutorType.USER)
	private void restartConsole() {
		botDiscord.setRunning(false);
		if(JDA.Status.DISCONNECTED != null) {
			botDiscord.setRunning(true);
		}
	}
}
