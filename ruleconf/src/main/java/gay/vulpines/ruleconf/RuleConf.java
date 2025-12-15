package gay.vulpines.ruleconf;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;

public class RuleConf implements ModInitializer {
    private static final Gson GSON = new Gson();

    public static JsonObject getRuleMap() {
        var path = FabricLoader.getInstance().getConfigDir().resolve("ruleconf.json").toFile();

        if (path.exists()) {
            try (var reader = new FileReader(path)) {
                JsonObject raw = GSON.fromJson(reader, JsonObject.class);
                JsonObject result = new JsonObject();

                for (var entry : raw.entrySet()) {
                    var value = entry.getValue();

                    if (value.isJsonPrimitive())
                        result.addProperty(entry.getKey(), value.getAsString());
                    else
                        result.addProperty(entry.getKey(), value.toString());
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new JsonObject();
    }

    @Override
    public void onInitialize() {
    }
}
