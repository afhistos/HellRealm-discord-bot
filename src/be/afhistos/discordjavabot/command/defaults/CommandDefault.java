package be.afhistos.discordjavabot.command.defaults;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;

public class CommandDefault {

	private final Main botDiscord;
	
	public CommandDefault(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
	
	@Command(name="stop",type=ExecutorType.CONSOLE)
	private void stop() {
		botDiscord.setRunning(false);
	}

}
