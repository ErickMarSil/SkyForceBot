package com.erick.SkyForce.Listener;

// JDA Imports
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;

// My Imports
import  java.util.*;
public class Events extends ListenerAdapter {
    ;
    private final Dotenv readerCommands;
    private final ArrayList<SlashCommandData> ListCommands;
    public Events(){
        ListCommands = new ArrayList<SlashCommandData>();
        readerCommands = Dotenv.load();
    }
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        mySlashFunction(event);
    }
    private void mySlashFunction(SlashCommandInteractionEvent event){
        String actCommand = event.getName();
        if (actCommand.equals("registrar")) {
            event.reply("cadastro feito com sucesso!").queue();
            event.getChannel().sendMessage("**" + event.getUser().getEffectiveName() + "** usou o comando registrar. Bem vindo!").queue();
        }
    }
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        SetAllCommands();
        event.getGuild().updateCommands().addCommands(ListCommands).queue();
    }
    private void SetAllCommands(){
        for (int i = 1; i <= 3; i++){
            String commandName = readerCommands.get("COMMAND" + i);
            String commandDesc = readerCommands.get("COMMANDESC" + i);
            ListCommands.add(Commands.slash(commandName,commandDesc));
        }
    }

}
