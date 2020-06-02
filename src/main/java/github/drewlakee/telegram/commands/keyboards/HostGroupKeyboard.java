package github.drewlakee.telegram.commands.keyboards;

import github.drewlakee.telegram.commands.callbacks.HandlerBotCallback;
import github.drewlakee.vk.domain.groups.VkCustomGroup;
import github.drewlakee.vk.domain.groups.VkGroupObjective;
import github.drewlakee.vk.singletons.VkGroupPool;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class HostGroupKeyboard {

    private final InlineKeyboardBuilder keyboard;

    public HostGroupKeyboard() {
        this.keyboard = new InlineKeyboardBuilder();
    }

    public InlineKeyboardMarkup build(String callbackCommand) {
        return build(callbackCommand, false);
    }

    public InlineKeyboardMarkup build(String callbackCommand, boolean withCancel) {
        List<VkCustomGroup> hosts = VkGroupPool.getConcreteGroups(VkGroupObjective.HOST);

        for (VkCustomGroup host : hosts) {
            this.keyboard.addButton(new InlineKeyboardButton()
                    .setText(host.getName())
                    .setCallbackData(callbackCommand + "_group_id" + host.getId()))
                    .nextLine();
        }

        if (withCancel) {
            this.keyboard
                    .addButton(new InlineKeyboardButton()
                            .setText("Cancel")
                            .setCallbackData(HandlerBotCallback.DELETE_MESSAGE.name()));
        }

        return this.keyboard.build();
    }
}