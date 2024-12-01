package data_access;

import use_case.character_creation.CharacterCreationDataAccessInterface;
import entity.Player;

import okhttp3.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class PlayerDataAccessObject implements CharacterCreationDataAccessInterface {
    private Player player = Player.getInstance();

    /**
     * Setters
     */
    public void setPclass(String pclass) {
        player.setPclass(pclass);
    }
    public void setPrace(String prace) {
        player.setPrace(prace);
    }

    /**
     * Getters
     */
    public String getPclass() {
        return player.getPclass();
    }
    public String getPrace() {
        return player.getPrace();
    }
    public Player getInstance() {
        return player;
    }
    public int getRoomNumber() { return player.getCurrentRoom().getRoomNumber(); }

    /**
     * Description Creators, accessing and analysing API data.
     */
    public String getPClassDescription() {
        // Early return if class is not selected yet
        if (player.getPclass() == null) {
            return "";
        }
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url("https://www.dnd5eapi.co/api/classes/" + player.getPclass())
                    .get() // Use the `.get()` method for GET requests
                    .addHeader("Accept", "application/json")
                    .build();

            try(Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    // Analyze JSON and update player entity
                    Map<String, Integer> stats = analyzeClassJSON(response);
                    player.setClassDamage(stats.get("Damage"));
                    player.setClassArmor(stats.get("Armor"));
                    return "Damage: " + player.getClassDamage() + ", Armor: " + player.getClassArmor();
                }
                else {
                    throw new IOException("Unexpected code " + response);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e); // Rethrow as an unchecked exception
        }
    }

    private static Map<String, Integer> analyzeClassJSON(Response response) throws IOException {
        // No need to check response.body() != null here since it's done in getPClassDescription()
        JSONObject json = new JSONObject(response.body().string());

        // Find armor proficiencies and get a value using regex
        JSONArray proficiencies = json.getJSONArray("proficiencies");
        int armorProf = 0;
        for (int i = 0; i < proficiencies.length(); i++) {
            JSONObject proficiency = proficiencies.getJSONObject(i);
            if (proficiency.getString("name").matches(".*Armor")) {
                // No .* at start because string should be Light/Medium Armor exactly
                armorProf += proficiency.getString("name").matches("Light.*") ? 1 : 0;
                armorProf += proficiency.getString("name").matches("Medium.*") ? 2 : 0;
            }
        }

        // Get damage (translating hit_die directly)
        int damage = json.getInt("hit_die");

        // Put stats in a HashMap and return
        Map<String, Integer> stats = new HashMap<>();
        stats.put("Damage", damage);
        stats.put("Armor", armorProf);
        return stats;
    }

    public String getPRaceDescription() {
        // Early return if race is not selected yet
        if (player.getPrace() == null) {
            return "";
        }
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url("https://www.dnd5eapi.co/api/races/" + player.getPrace())
                    .get() // Use the `.get()` method for GET requests
                    .addHeader("Accept", "application/json")
                    .build();

            try(Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    // Analyze JSON and update player entity
                    Map<String, Integer> bonuses = analyzeRaceJSON(response);
                    player.setRaceDamage(bonuses.get("Damage"));
                    player.setRaceArmor(bonuses.get("Armor"));
                    return "Damage: " + player.getRaceDamage() + ", Armor: " + player.getRaceArmor();
                }
                else {
                    throw new IOException("Unexpected code " + response);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e); // Rethrow as an unchecked exception
        }
    }

    private Map<String, Integer> analyzeRaceJSON(Response response) throws IOException {
        // No need to check response.body() != null here since it's done in getPRaceDescription()
        JSONObject json = new JSONObject(response.body().string());

        // Find ability bonuses and parse values into bonuses Map
        JSONArray abilityBonuses = json.getJSONArray("ability_bonuses");
        Map<String, Integer> bonuses = new HashMap<>();
        bonuses.put("Damage", 0);
        bonuses.put("Armor", 0);
        for (int i = 0; i < abilityBonuses.length(); i++) {
            JSONObject abilityBonus = abilityBonuses.getJSONObject(i);
            String abilityName = abilityBonus.getJSONObject("ability_score").getString("name");
            if (abilityName.equals("STR")) {
                bonuses.put("Damage", abilityBonus.getInt("bonus"));
            }
            if (abilityName.equals("CON")) {
                bonuses.put("Armor", abilityBonus.getInt("bonus"));
            }
        }

        return bonuses;
    }
}
