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
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;

// My Imports
import java.awt.*;
import  java.util.*;
import java.util.List;

public class Events extends ListenerAdapter {
    private final Dotenv readerCommands;
    private final ArrayList<SlashCommandData> ListCommands;
    public Events(){
        ListCommands = new ArrayList<>();
        readerCommands = Dotenv.load();
    }
    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        User user = event.getUser();
        //List<Role> role = new ArrayList<>();
        String valueToTake = readerCommands.get("ROLE" + event.getInteraction().getValues().get(0));
        //role.add().queue();
        event.reply("seu cargo" + valueToTake).queue();
        event.getGuild().addRoleToMember(user,event.getGuild().getRolesByName(valueToTake,false).get(0)).queue();
        event.getGuild().addRoleToMember(user,event.getGuild().getRolesByName("ᴍᴇᴍʙʀᴏ",false).get(0)).queue();
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
            .setDescription("Quem voce prefere? Resposta certa: Question :D").build();
    }
    private StringSelectMenu createPlaceHolder(){
        return StringSelectMenu.create("levels").setPlaceholder("Quem você prefere?")
            .addOption("Question","1")
            .addOption("Zeddron","2")
            .addOption("Danone","3").build();
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
