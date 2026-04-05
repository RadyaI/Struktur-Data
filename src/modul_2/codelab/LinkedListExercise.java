package modul_2.codelab;

public class LinkedListExercise {

    // ==================================================================================
    // 2. Linked List (ANIME EDITION: ONE PIECE LOG POSE)
    // ==================================================================================

    // 2.1 Definition
    // Imagine the Grand Line. You cannot fly straight to the end. You must travel
    // from
    // Island to Island. Use a Log Pose (Pointer) to find the next island.
    // Each Island (Node) contains adventure (Data) and points to the next
    // destination (Next).

    // 2.2 Basic Structure
    // 1) Node: An Island (e.g., Drum Kingdom, Alabasta).
    // 2) Data: The name of the Island or the Villain there.
    // 3) Next: The Log Pose needle pointing to the next island.
    // 6) Tail: The furthest island you have reached so far.

    // ==================================================================================
    // IMPLEMENTATION (MISSION: NAVIGATE THE GRAND LINE)
    // ==================================================================================
    // INSTRUCTIONS: Help Nami navigate! Replace '__________' with the correct code.

    static class Island {
        String name;
        Island next;

        public Island(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Island startIsland;
    private Island lastIsland;

    public void addIsland(String name) {
        Island newIsland = new Island(name);

        if (startIsland == null) {
            startIsland = newIsland;
            lastIsland = newIsland;
        } else {
            lastIsland.next = newIsland;
            lastIsland = newIsland;
        }
    }

    public void busterCall(String keyName) {
        Island current = startIsland;
        Island prev = null;

        if (current != null && current.name.equals(keyName)) {
            startIsland = current.next;
            return;
        }

        while (current != null && !current.name.equals(keyName)) {
            prev = current;
            current = current.next;
        }

        if (current == null)
            return;

        prev.next = current.next;
    }

    public void printLogbook() {
        Island current = startIsland;
        System.out.print("Grand Line Route: ");
        while (current != null) {
            System.out.print(current.name + " -> ");
            current = current.next;
        }
        System.out.println("Laugh Tale (End)");
    }

    public boolean isIslandOnRoute(String keyName) {
        Island current = startIsland;
        while (current != null) {
            if (current.name.equals(keyName)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int countIslands() {
        int count = 0;
        Island current = startIsland;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public static void main(String[] args) {
        LinkedListExercise grandLine = new LinkedListExercise();

        grandLine.addIsland("Romance Dawn");
        grandLine.addIsland("Skypiea");
        grandLine.addIsland("Water 7");
        grandLine.addIsland("Wano Kuni");

        grandLine.printLogbook();

        System.out.println("Visited Fishman Island? " + grandLine.isIslandOnRoute("Fishman Island"));

        System.out.println("Buster Call initiated on Skypiea!");
        grandLine.busterCall("Skypiea");

        grandLine.printLogbook();

        System.out.println("Total Islands visited: " + grandLine.countIslands());
    }
}