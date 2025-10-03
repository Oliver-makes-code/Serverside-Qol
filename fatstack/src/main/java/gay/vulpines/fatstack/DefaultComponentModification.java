package gay.vulpines.fatstack;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.component.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DefaultComponentModification {
    public static final Gson GSON = new Gson();

    public static final Map<ResourceKey<Item>, DataComponentPatch> PATCH_MAP = new HashMap<>();

    public static final File STACK_SIZE_FILE = FabricLoader.getInstance().getConfigDir().resolve("fatstack").resolve("stack_sizes.json").toFile();

    public static Map<String, Integer> stackSizeMap = null;

    private static Map<String, Integer> load() throws IOException {
        if (stackSizeMap != null)
            return stackSizeMap;

        if (!STACK_SIZE_FILE.exists()) {
            STACK_SIZE_FILE.getParentFile().mkdirs();

            try (InputStream in = DefaultComponentModification.class.getResourceAsStream("/fatstack/stack_sizes.json")) {
                try (OutputStream out = new FileOutputStream(STACK_SIZE_FILE)) {
                    out.write(in.readAllBytes());
                }
            }
        }

        var object = GSON.fromJson(new FileReader(STACK_SIZE_FILE), JsonObject.class);

        stackSizeMap = new HashMap<>();

        for (var entry : object.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();

            if (!value.isJsonPrimitive())
                continue;

            var primitive = value.getAsJsonPrimitive();

            if (!primitive.isNumber())
                continue;

            stackSizeMap.put(key, primitive.getAsNumber().intValue());
        }

        return stackSizeMap;
    }

    public static DataComponentPatch applyPatch(ResourceKey<Item> itemKey, DataComponentPatch other) {
        if (!PATCH_MAP.containsKey(itemKey))
            return other;

        var prototype = PATCH_MAP.get(itemKey);

        var patch = DataComponentPatch.builder();

        for (var entry : prototype.entrySet()) {
            var type = entry.getKey();
            var component = entry.getValue();

            component.ifPresent(it -> patch.set((DataComponentType<? super Object>) type, (Object)it));

            if (component.isEmpty())
                patch.remove(type);
        }

        for (var entry : other.entrySet()) {
            var type = entry.getKey();
            var component = entry.getValue();

            component.ifPresent(it -> patch.set((DataComponentType<? super Object>) type, (Object)it));

            if (component.isEmpty())
                patch.remove(type);
        }

        return patch.build();
    }

    public static void modify(ResourceKey<Item> itemKey, Builder builder) {
        try {
            var map = load();

            var fullKey = itemKey.location().toString();
            var pathKey = itemKey.location().getPath();

            if (map.containsKey(fullKey))
                builder.set(DataComponents.MAX_STACK_SIZE, map.get(fullKey));
            else if (itemKey.location().getNamespace().equals("minecraft") && map.containsKey(pathKey))
                builder.set(DataComponents.MAX_STACK_SIZE, map.get(pathKey));

            PATCH_MAP.put(itemKey, builder.toPatch());
        } catch (IOException ignored) {}
    }

    public record Builder(DataComponentPatch.Builder patch, DataComponentMap.Builder components) {
        <T> Builder set(DataComponentType<T> dataComponentType, T object) {
            patch.set(dataComponentType, object);
            components.set(dataComponentType, object);
            return this;
        }

        DataComponentPatch toPatch() {
            return patch.build();
        }
    }
}
