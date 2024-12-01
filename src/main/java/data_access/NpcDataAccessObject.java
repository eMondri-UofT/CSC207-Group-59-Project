package data_access;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import entity.Npc;
import use_case.room_default.NpcRoomDataAccessInterface;
import use_case.talk_to_npc.TalkToNpcDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing NPC data. This implementation does
 * NOT persist data between runs of the program.
 */
public class NpcDataAccessObject implements TalkToNpcDataAccessInterface, NpcRoomDataAccessInterface {
    private final Map<String, Npc> npcs = new HashMap<>();
    private Npc currentNpc;

    @Override
    public Npc getCurrentNpc() {
        return currentNpc;
    }

    @Override
    public void setCurrentNpc(Npc currentNpc) {
        this.currentNpc = currentNpc;
    }

    @Override
    public Npc generateRandomNpc() {
        if (npcs.isEmpty()) {
            currentNpc = null;
        }
        else {
            final String[] keys = npcs.keySet().toArray(new String[0]);
            final String randomKey = keys[(int) (Math.random() * keys.length)];
            currentNpc = npcs.remove(randomKey);
        }
        return currentNpc;
    }

    @Override
    public void loadNpcs() {
        npcs.put("ALCHEMIST", new Npc("ALCHEMIST", "Medium humanoid (Artificer), any alignment",
                Arrays.asList(
                        "Careful there, I just cleaned this place.",
                        "This? Just a little something I'm experimenting with.",
                        "Sometimes I wonder if all this work is worth it.",
                        "The right formula can change everything - or ruin it.",
                        "My best ideas come when I'm not even trying.",
                        "Don't touch that! Oh, wait... never mind.",
                        "If this works, I might finally get some respect around here."
                )));

        npcs.put("BEASTMASTER", new Npc("BEASTMASTER", "Medium humanoid (Ranger), any alignment",
                Arrays.asList(
                        "The animals trust me because I trust them.",
                        "You'd be amazed how much you can learn by watching.",
                        "Some days, the woods feel like a second home.",
                        "This scar? Got it from a boar. He's my friend now.",
                        "I don't tame them; I just listen.",
                        "There's nothing like the quiet of the wild."
                )));

        npcs.put("ARCANE WARRIOR", new Npc("ARCANE WARRIOR", "Medium humanoid (Fighter/Sorcerer), any alignment",
                Arrays.asList(
                        "People think magic makes things easier - it doesn't.",
                        "My first spell? It fizzled out before anything happened.",
                        "Steel and spells together take years to master.",
                        "The hardest fights are the ones in your head.",
                        "I've learned that power means responsibility, not glory.",
                        "I'll admit it - I've made mistakes, big ones.",
                        "At the end of the day, I just want to protect what matters."
                )));

        npcs.put("ARCHPRIEST", new Npc("ARCHPRIEST", "Medium humanoid (Cleric), usually any good alignment",
                Arrays.asList(
                        "Sometimes faith feels like walking through fog.",
                        "I've seen miracles happen when all seemed lost.",
                        "Prayer isn't just words - it's action too.",
                        "The world's pain doesn't disappear overnight.",
                        "May your path be clear, even when it feels impossible."
                )));

        npcs.put("BATTLE SHAMAN", new Npc("BATTLE SHAMAN", "Medium humanoid (Barbarian/Druid), any alignment",
                Arrays.asList(
                        "The spirits speak in ways most people can't hear.",
                        "A storm doesn't rage - it dances.",
                        "Balance isn't something you find; it's something you fight for.",
                        "The wild doesn't forgive carelessness.",
                        "Each strike I make is guided by something greater.",
                        "The land remembers. It always does."
                )));

        npcs.put("BODYGUARD", new Npc("BODYGUARD", "Medium humanoid (Fighter/Guardian), any alignment",
                Arrays.asList(
                        "I keep people safe - it's simple, but not easy.",
                        "Most days, it's quiet. That's how I like it.",
                        "The hardest part of this job is knowing when to act.",
                        "I've seen things I'll never forget. That's part of the deal.",
                        "People put their lives in my hands. I don't take that lightly.",
                        "I don't talk much. My actions say enough."
                )));

        npcs.put("CANNONEER", new Npc("CANNONEER", "Medium humanoid, any alignment",
                Arrays.asList(
                        "People think cannons are all noise. They're wrong.",
                        "Every shot is calculated; nothing's left to chance.",
                        "I've spent years perfecting my aim.",
                        "The sea teaches you patience - or you don't last long.",
                        "Sometimes I miss the quiet before battle.",
                        "You'd be surprised how heavy these things are.",
                        "No matter what, I always fire with purpose."
                )));

        npcs.put("GUNSLINGER", new Npc("GUNSLINGER", "Medium humanoid, usually non-lawful alignment",
                Arrays.asList(
                        "I've never been one for second chances.",
                        "A duel? It's more about reading people than shooting.",
                        "I've walked away from fights I knew I couldn't win.",
                        "Every shot I take, I make sure it counts.",
                        "People think this life is glamorous - it's not.",
                        "You don't get used to the sound of a gun. Not really.",
                        "I'm fast, but sometimes fast isn't enough."
                )));

        npcs.put("MYSTIC PUGILIST", new Npc("MYSTIC PUGILIST", "Medium humanoid (Monk), any alignment",
                Arrays.asList(
                        "Every movement has a purpose.",
                        "I've trained for years to find balance in chaos.",
                        "The strongest weapon is a focused mind.",
                        "Even in a fight, you can learn about yourself.",
                        "Sometimes, a single strike says more than words.",
                        "I don't fight for glory - I fight for understanding.",
                        "Each step I take is part of the journey."
                )));

        npcs.put("SAMURAI", new Npc("SAMURAI", "Medium humanoid (Fighter), any alignment",
                Arrays.asList(
                        "My blade reflects my soul.",
                        "Discipline is my foundation; honor is my path.",
                        "A duel isn't just a fight - it's a conversation.",
                        "Even in defeat, there's dignity.",
                        "Every battle teaches something new.",
                        "The way of the sword is endless.",
                        "I strive for perfection, though I know I'll never reach it."
                )));

        npcs.put("TACTICIAN", new Npc("TACTICIAN", "Medium humanoid (Rogue/Guide), any alignment",
                Arrays.asList(
                        "A good plan can change everything.",
                        "Every move has a purpose, even if it's not obvious.",
                        "I've made mistakes, but I've learned from every one.",
                        "Wars aren't won by strength alone.",
                        "The smallest detail can turn the tide of battle.",
                        "I don't believe in luck - just preparation.",
                        "Watch closely. You might learn something."
                )));

        npcs.put("SEEKER", new Npc("SEEKER", "Medium humanoid (Ranger), any alignment",
                Arrays.asList(
                        "I've spent years tracking shadows.",
                        "Every path has its secrets.",
                        "You can tell a lot from the way someone walks.",
                        "The forest doesn't lie. People do.",
                        "I don't just hunt - I listen.",
                        "Some trails are harder to follow than others.",
                        "I'll find what I'm looking for, no matter how long it takes."
                )));

        npcs.put("SWORDMAGE", new Npc("SWORDMAGE", "Medium humanoid (Fighter/Wizard), any alignment",
                Arrays.asList(
                        "The sword and the spell - they're one to me.",
                        "People think magic is chaotic, but it's precision.",
                        "Every strike carries intention.",
                        "I've spent my life mastering the balance of power.",
                        "A perfect spell is as beautiful as a perfect blade.",
                        "Magic sharpens my edge in more ways than one.",
                        "This is more than a craft - it's who I am."
                )));

        npcs.put("TEMPLAR", new Npc("TEMPLAR", "Medium humanoid (Paladin), usually non-evil alignment",
                Arrays.asList(
                        "Justice isn't about strength - it's about conviction.",
                        "I swore an oath, and I intend to keep it.",
                        "Faith has carried me through darker times than this.",
                        "I fight not for myself, but for others.",
                        "The righteous path isn't always easy to walk.",
                        "Every step forward is a step closer to peace.",
                        "I will not falter, no matter what stands in my way."
                )));
    }
}
