package be.afhistos.discordjavabot.command.defaults;

import java.awt.Color;
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

public class CommandAide {

	private final Main botDiscord;
	
	public CommandAide(Main botDiscord) {
		this.botDiscord = botDiscord;
	}
	
	@Command(name="aide",type=ExecutorType.USER)
	private void aide(User user, MessageChannel channel, String[] args) {
		TextChannel textChannel = (TextChannel)channel;
		if(channel instanceof TextChannel) {
			if(!textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) return;
		}
		EmbedBuilder embed = new EmbedBuilder();
		Color cembed = Color.YELLOW;
		LocalDateTime time = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
		String formatDateTime = time.format(formatter);
		embed.setAuthor("HellRealm: Page d'aide", "https://hell-realm-17.webself.net/", "https://image.noelshack.com/fichiers/2018/19/7/1526229260-telechargement.jpg");
		embed.setColor(cembed);
		embed.setTitle("N'oublie pas de voter pour le serveur ! (Clique)", "https://github.com/afhistos/HellRealm-discord-bot");
		embed.addField("__**Commandes minecraft:**__", "**  &serveur** - Donne toutes les informations du serveur\n  **&stats <pseudo>** - Donnes différentes statistiques à propos du joueur.__*** :warning: LE JOUEUR DOIT ÊTRE PREMIUM***__\n  **&skin <pseudo>** - Donne le skin du joueur spécifié.__*** :warning: LE JOUEUR DOIT ÊTRE PREMIUM***__", true);
		embed.addField("__**Commandes discord**__", "**  &info [utilisateur]** - Donnes différentes informations à propos de l'utilisateur spécifié/vous\n  **&guilde** - Donne différentes informations à propos du serveur discord (guilde)\n  **&bot** - Donne des informations à propos du bot.\n**  &setgame <texte>** - Change le jeu du bot :warning: ADMINS ONLY :warning:\n**  &question** Vous envoie le formulaire à faire si vous avez une question", true);
		embed.setFooter(user.getName() + " | Message envoyé dans le salon " + channel.getName() + " le " + formatDateTime, user.getAvatarUrl());
		channel.sendMessage(embed.build()).queue();
		
		
	}
}
