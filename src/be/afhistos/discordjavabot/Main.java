package be.afhistos.discordjavabot;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import be.afhistos.discordjavabot.command.CommandMap;
import be.afhistos.discordjavabot.event.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main implements Runnable{

	private final JDA jda;
	private final CommandMap commandMap = new CommandMap(this);
	private final Scanner scanner= new Scanner(System.in);
	
	private boolean running;
	
	public Main() throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("NDQ0ODU4MTIxNjAxNjc5MzYx.DdiFNQ.6AeJMsOk8gNp2La44gqKkp0pxzE").buildAsync();
		jda.addEventListener(new BotListener(commandMap));
		System.out.println("Bot started");
		
	}
	
	public JDA getJda() {
		return jda;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	@Override
	public void run() {
		running = true;
		
		while (running) {
			if(scanner.hasNextLine()) commandMap.commandConsole(scanner.nextLine());
		}
		
		scanner.close();
		System.out.println("Bot stopped");
		jda.shutdown();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		try {
			Main botDiscord = new Main();
			new Thread(botDiscord, "bot").start();
		} catch (LoginException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
