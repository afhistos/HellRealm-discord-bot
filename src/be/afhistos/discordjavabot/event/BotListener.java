package be.afhistos.discordjavabot.event;

import be.afhistos.discordjavabot.command.CommandMap;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

public class BotListener implements EventListener {

	public final CommandMap CommandMap;
	
	public BotListener(CommandMap commandMap) {
		this.CommandMap = commandMap;
	}
	
	@Override
	public void onEvent(Event event) {
		System.out.println(event.getClass().getSimpleName());
		if(event instanceof MessageReceivedEvent) onMessage((MessageReceivedEvent) event);
	}
	
	private void onMessage(MessageReceivedEvent event) {
		if(event.getAuthor().equals(event.getJDA().getSelfUser())) return;
		String message = event.getMessage().getContentDisplay();
		if(message.startsWith(CommandMap.getTag())) {
			message = message.replaceFirst(CommandMap.getTag(), "");
			if(CommandMap.commandUser(event.getAuthor(), message, event.getMessage())) {
				if(event.getTextChannel() != null && event.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
					event.getMessage().delete().queue();
				}
			}
		}
	}

}
