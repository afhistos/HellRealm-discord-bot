package be.afhistos.discordjavabot;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import be.afhistos.discordjavabot.command.CommandMap;
import be.afhistos.discordjavabot.event.BotListener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;

public class Main implements Runnable{

	private final JDA jda;
	private final CommandMap commandMap = new CommandMap(this);
	private final Scanner scanner= new Scanner(System.in);
	
	private boolean running;
	
	public Main() throws LoginException {
		jda = new JDABuilder(AccountType.BOT).setToken("TOKEN").buildAsync();
		jda.addEventListener(new BotListener(commandMap));
		System.out.println("Bot started");
		jda.getPresence().setGame(Game.of(GameType.WATCHING, "afhistos galérer"));
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
	public static String getIpFrom(String adresse) { 
		String toreturn = null;
		  try {
		    URL url = new URL(adresse); 
		   URLConnection uc = url.openConnection(); 
		   InputStream in = uc.getInputStream(); 
		   int c = in.read(); 
		   StringBuilder build = new StringBuilder();  
		   while (c != -1) { 
		    build.append((char) c); 
		    c = in.read(); 
		   } 
		   toreturn = build.toString(); 
		   
		  } catch (MalformedURLException e) { 
		  
		   e.printStackTrace(); 
		  } catch (IOException e) { 
		  
		   e.printStackTrace(); 
		  }
		   return toreturn;
		 }


}

