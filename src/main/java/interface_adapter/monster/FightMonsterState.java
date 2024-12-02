package interface_adapter.monster;

import okhttp3.*;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.util.*;


/**
 * The state for the Fight Monster view model
 */
public class FightMonsterState {
    public int health = (int)getAPIData().get("health");
    public String name = (String)getAPIData().get("name");

    public int hit() {
        int damage = (int)(Math.random() * 10) + 1;
        this.health -= damage;
        return damage;
    }

    public int hit(int power) {
        int damage = (int)(Math.random() * 3) + power - (int)(Math.random() * 3);
        if (damage <= 0){
            damage = 1;
        }
        this.health -= damage;
        return damage;
    }

    public int damage(int armor) {
        int damTaken = (int)(Math.random() * 3) + 5 - (int)(Math.random() * 3);
        if(armor == 0){
            damTaken *= 2;
        }
        else {
            damTaken /= armor;
        }
        return damTaken;
    }

    private Map<String, Object> getAPIData() {
        Map<String, Object> data = new HashMap<>();
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url("https://www.dnd5eapi.co/api/monsters/" + getRandomMonster())
                    .get() // Use the `.get()` method for GET requests
                    .addHeader("Accept", "application/json")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    JSONObject json = new JSONObject(response.body().string());
                    data.put("health", json.getInt("hit_points"));
                    data.put("name", json.getString("name"));
                    return data;
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomMonster() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();

            Request request = new Request.Builder()
                    .url("https://www.dnd5eapi.co/api/monsters/")
                    .get() // Use the `.get()` method for GET requests
                    .addHeader("Accept", "application/json")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful() && response.body() != null) {
                    JSONObject json = new JSONObject(response.body().string());
                    JSONArray jsonArray = json.getJSONArray("results");
                    List<String> monsters = new ArrayList();
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            monsters.add(jsonArray.getJSONObject(i).getString("index"));
                        }
                    }
                    Random randomizer = new Random();
                    String random = monsters.get(randomizer.nextInt(monsters.size()));
                    return random;
                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
