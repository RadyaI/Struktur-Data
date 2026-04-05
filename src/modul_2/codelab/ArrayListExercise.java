package modul_2.codelab;

import java.util.ArrayList;

public class ArrayListExercise {

    public static void main(String[] args) {

        // ==================================================================================
        // 1. Definition & Characteristics of ArrayList (ANIME EDITION: JUJUTSU KAISEN)
        // ==================================================================================

        // 1.1 Definition
        // Imagine an ArrayList as Megumi Fushiguro's Shadow. He can store an unlimited
        // number
        // of Cursed Tools and Shikigami inside it!
        // Just like his shadow, an ArrayList expands dynamically as you add more items.

        // Constructors (The Summoning Rituals):
        // 1) ArrayList(): Creates an empty Shadow storage (Energy cost: low).
        // 2) ArrayList(Collection c): Steals someone else's inventory (like Yuta
        // copying techniques).
        // 3) ArrayList(int capacity): Prepares a specific amount of space (Binding
        // Vow).

        // 1.2 Characteristics
        // - Dynamic Sizing: Expands like a Domain Expansion when more space is needed.
        // - Ordered: Maintains the order of weapons/spirits exactly as you put them in.
        // - Index-based Access: You can summon a specific weapon instantly (Speed:
        // Infinite/O(1)).

        // ==================================================================================
        // 1.5. Example Use Cases (MISSION: MANAGE THE TOKYO STUDENT ROSTER)
        // ==================================================================================
        // INSTRUCTIONS: Help Gojo Sensei manage the students! Replace '__________' with
        // the correct code.

        ArrayList<String> jujutsuSorcerers = new ArrayList<>();

        jujutsuSorcerers.add("Itadori");
        jujutsuSorcerers.add("Fushiguro");
        jujutsuSorcerers.add("Kugisaki");

        System.out.println("First Years Assembled: " + jujutsuSorcerers);

        jujutsuSorcerers.add(0, "Okkotsu");

        System.out.println("After Yuta joins: " + jujutsuSorcerers);

        String student = jujutsuSorcerers.get(2);
        System.out.println("Student at index 2 is: " + student);

        jujutsuSorcerers.set(1, "Ryomen Sukuna");

        System.out.println("Oh no, Itadori switched!: " + jujutsuSorcerers);

        jujutsuSorcerers.remove(3);

        System.out.println("After the Shibuya Incident: " + jujutsuSorcerers);

        System.out.println("Remaining students: " + jujutsuSorcerers.size());

        if (jujutsuSorcerers.isEmpty()) {
            System.out.println("No sorcerers left to fight curses...");
        } else {
            System.out.println("The fight continues!");
        }

        jujutsuSorcerers.clear();

        System.out.println("Post-Culling Game Status: " + jujutsuSorcerers);
    }
}