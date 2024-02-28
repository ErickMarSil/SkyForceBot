package com.erick.SkyForce.Listener;

// JDA Imports
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenuInteraction;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

// My Imports
import java.awt.*;
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
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        User user = event.getUser();
        ArrayList<Role> role = new ArrayList<Role>();


        switch (event.getInteraction().getValues().get(0)){
            case "quest":
                // COde
                break;
            case "zedd":
                //code
                break;
            case "danone":

                break;
            case "membros":

        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        mySlashFunction(event);
    }
    private void mySlashFunction(SlashCommandInteractionEvent  event){
        String actCommand = event.getName();
        ArrayList<MessageEmbed> EmbedsToReply = new ArrayList<MessageEmbed>();
        if (actCommand.equals("registrar")) {
            event.replyEmbeds(createEmbed(event)).addActionRow(createPlaceHolder()).queue();
        }
    }
    private MessageEmbed createEmbed(SlashCommandInteractionEvent event){
        EmbedBuilder eb = new EmbedBuilder();

        return eb.setAuthor("SkyForce Bot")
            .setTitle("Só uma pergunta!")
            .setColor(new Color(255, 255, 255))
            .setDescription("Qual level voce é no mush?").build();
    }
    private StringSelectMenu createPlaceHolder(){
        return StringSelectMenu.create("levels").setPlaceholder("Quem você prefere?")
            .addOption("Question","quest")
            .addOption("Zeddron","zedd")
            .addOption("Danone","danone")
            .addOption("Nenhum", "membros").build();
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
