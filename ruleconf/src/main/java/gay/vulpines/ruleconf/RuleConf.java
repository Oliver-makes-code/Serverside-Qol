package gay.vulpines.ruleconf;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.IOException;

public class RuleConf implements ModInitializer {
    private static final Gson GSON = new Gson();

    public static JsonObject getRuleMap() {
        var path = FabricLoader.getInstance().getConfigDir().resolve("ruleconf.json").toFile();

        if (path.exists()) {
            try (var reader = new FileReader(path)) {
                return GSON.fromJson(reader, JsonObject.class);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (IOException ignored) {}
        }

        return new JsonObject();
    }

    @Override
    public void onInitialize() {}
}
