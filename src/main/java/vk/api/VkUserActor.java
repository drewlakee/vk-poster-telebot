package vk.api;

import app.Environment;
import com.vk.api.sdk.client.actors.UserActor;

public class VkUserActor {

    private static UserActor actor;

    public static UserActor instance() {
        if (isEmpty())
            actor = new UserActor(
                    Integer.parseInt(Environment.PROPERTIES.getProperty("vk_user_id")),
                    Environment.PROPERTIES.getProperty("vk_token")
            );

        return actor;
    }

    private static boolean isEmpty() {
        return actor == null;
    }
}
