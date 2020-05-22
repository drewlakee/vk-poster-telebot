package telegram.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import telegram.commands.handlers.BotCommand;
import telegram.commands.statics.Commands;
import telegram.utils.ResponseMessageDispatcher;
import telegram.commands.handlers.MessageHandler;
import vk.domain.groups.VkCustomGroup;
import vk.domain.groups.VkGroupObjective;
import vk.singletons.VkGroupPool;

public class MyGroupsCommand extends BotCommand implements MessageHandler {

    public MyGroupsCommand() {
        super(Commands.MY_GROUPS);
    }

    @Override
    public void handle(AbsSender sender, Message message) {
        SendMessage response = new SendMessage();
        response.setChatId(message.getChatId());
        response.setText("Your groups: " + buildGroupsDesk());
        response.disableWebPagePreview();

        ResponseMessageDispatcher.send(sender, response);
    }

    private String buildGroupsDesk() {
        StringBuilder desk = new StringBuilder();
        int count = 0;

        for (VkCustomGroup group : VkGroupPool.getConcreteGroups(VkGroupObjective.HOST)) {
            desk.append(String.format("\n\n%d: %s (%d)\n%s", count, group.getName(), group.getId(), group.getUrl()));
            count++;
        }
        desk.append("\n\n").append("Total - ").append(count).append(".");

        return desk.toString();
    }
}
