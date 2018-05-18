package be.afhistos.discordjavabot.command.defaults;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class CommandMcStats {
	static HashMap<String, String> userInfo = new HashMap<String, String>();
	public static String GetIpFrom(String adresse) throws MalformedURLException {
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
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return toreturn;
	}
	private final Main botDiscord;
	
	public CommandMcStats(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
	@Command(name="stats",type=ExecutorType.USER)
	private void mcStats(User user, MessageChannel channel, String[] args) throws IOException {
		TextChannel textChannel = (TextChannel)channel;
		if(channel instanceof TextChannel) {
			if(!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) return;
		}
		userInfo.remove(user + "name");
		userInfo.remove(user + "uuid");
		userInfo.remove(user + "LinkHead");
		userInfo.remove(user + "LinkSkin");
		channel.sendMessage(":warning: Les images n'apparaissent pas pour le moment !").queue();
		if(args.length == 0) {
			String player = user.getName();
			userInfo.put(user + "name", player);
			String pUuid = GetIpFrom("https://minecraft-api.com/api/uuid/uuid.php?pseudo=" + player);
			if(pUuid.isEmpty() == true) {
				channel.sendMessage("Erreur: ** " + player + " ** n'existe pas/n'est pas premium!").queue();
				return;
			}
			userInfo.put(user + "uuid", pUuid);
						
			String pHeadLink = "http://minecraft-api.com/api/skins/head.php?player=" + player + "&size=256";
			userInfo.put(user + "LinkHead", pHeadLink);
			String pSkinLink = "http://minecraft-api.com/api/skins/skins.php?player=" + player;
			userInfo.put(user + "LinkSkin", pSkinLink);
		}
		else{
			String player = args[0].toString();
			System.out.println("player" + player);
			userInfo.put(user + "name", player);
			String pUuid = GetIpFrom("https://minecraft-api.com/api/uuid/uuid.php?pseudo=" + player);
			if(pUuid.isEmpty() == true) {
				channel.sendMessage("Erreur: ** " + player + " ** n'existe pas/n'est pas premium!").queue();
				return;
			}
			userInfo.put(user + "uuid", pUuid);
						
			String pHeadLink = "http://minecraft-api.com/api/skins/head.php?player=" + player + "&size=256";
			userInfo.put(user + "LinkHead", pHeadLink);
			String pSkinLink = "http://minecraft-api.com/api/skins/skins.php?player=" + player;
			userInfo.put(user + "LinkSkin", pSkinLink);
		}
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
		String formatDateTime = time.format(formatter);
		Color colorE = new Color(72, 61, 139, 39);
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor("HellRealm: Page d'aide", "https://hell-realm-17.webself.net/", "https://image.noelshack.com/fichiers/2018/19/7/1526229260-telechargement.jpg");
		embed.setTitle("Informations Minecraft de: " + userInfo.get(user + "name"));
		embed.setColor(colorE);
		embed.addField("UUID:", userInfo.get(user + "uuid"), true);
		embed.addField("Skin:", userInfo.get(user + "LinkSkin"), true);
		embed.setThumbnail(userInfo.get(user + "LinkHead"));
		embed.setFooter(user.getName() + " | Message envoyé dans le salon " + channel.getName() + " le " + formatDateTime, user.getAvatarUrl());
		channel.sendMessage(embed.build()).queue();
	}

}
