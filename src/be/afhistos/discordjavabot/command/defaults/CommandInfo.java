package be.afhistos.discordjavabot.command.defaults;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import be.afhistos.discordjavabot.Main;
import be.afhistos.discordjavabot.command.Command;
import be.afhistos.discordjavabot.command.Command.ExecutorType;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class CommandInfo {

	private final Main botDiscord;
	
	public CommandInfo(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
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
	
	@Command(name="info",type=ExecutorType.USER)
	private void info(User user, MessageChannel channel) {
		if(channel instanceof TextChannel) {
			TextChannel textChannel = (TextChannel)channel;
			if(!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) return;
		}
		TextChannel textChannel = (TextChannel)channel;
		//Récupérer la date d'envoi et la formatter
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
		String formatDateTime = time.format(formatter);
		String uCT = user.getCreationTime().format(formatter);
		
		//récupérer la couleur du role pour la couleur de l'embed
		Color userColor = textChannel.getGuild().getMember(user).getColor();
		
		//Création de l'embed
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor(user.getName(), null, user.getAvatarUrl()+"?size=256");
		builder.setTitle("Informations de l'utilisateur:");
		builder.setColor(userColor);
		builder.addField("ID", user.getId(), true);
		builder.addBlankField(true);
		builder.addField("Date de création du compte", uCT, true);
		builder.addField("Mention", user.getAsMention(), true);
		builder.setFooter("Message envoyé dans la guilde " + textChannel.getGuild().getName() + ", channel " + channel.getName() + " le " + formatDateTime, textChannel.getGuild().getIconUrl()+"?size=256");
		
		channel.sendMessage(builder.build()).queue();
		
	}
	
	@Command(name="serveur",type=ExecutorType.USER)
	private void serveur(User user, MessageChannel channel) throws IOException {
		TextChannel textChannel = (TextChannel)channel;
		if(channel instanceof TextChannel) {
			if(!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_EMBED_LINKS)) return;
		}
		Color userColor = textChannel.getGuild().getMember(user).getColor();
		
		//Récupérer le nombre de joueurs en ligne
		String cp = GetIpFrom("https://minecraft-api.com/api/ping/playeronline.php?ip=94.23.221.162&port=26815");
		//Date d'envoi
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
		String formatDateTime = time.format(formatter);
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor("HellRealm", "https://hell-realm-17.webself.net/", "https://image.noelshack.com/fichiers/2018/19/7/1526229260-telechargement.jpg");
		embed.setTitle("Informations du serveur");
		embed.setColor(userColor);
		embed.addField("Obtenir les mods", "http://www.dropbox.com/s/ysp7iupm6alh1s9/1.7.10.rar?dl=1", true);
		embed.addField("Nombre de joueurs connectés", cp, true);
		embed.addBlankField(true);
		embed.addField("BOT: aide", "&aide", true);
		embed.addField("IP", "hell.mcraft.fr", true);
		embed.addField("Inviter des joueurs sur le discord", "https://discord.gg/tyVjVfD", true);
		embed.setThumbnail("https://is1-ssl.mzstatic.com/image/thumb/Purple118/v4/5c/85/62/5c856242-06b4-0e3d-68b9-8ca06fc46b8e/mzl.maxjogzo.png/246x0w.jpg"+"?size=256");
		embed.setFooter(user.getName() + " | Message envoyé dans le salon " + channel.getName() + " le " + formatDateTime, user.getAvatarUrl());
		channel.sendMessage(embed.build()).queue();
	}
}



