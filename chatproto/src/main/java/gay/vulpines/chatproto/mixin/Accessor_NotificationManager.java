package gay.vulpines.chatproto.mixin;

import net.minecraft.server.notifications.NotificationManager;
import net.minecraft.server.notifications.NotificationService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(NotificationManager.class)
public interface Accessor_NotificationManager {
    @Accessor
    List<NotificationService> getNotificationServices();
}
