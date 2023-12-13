import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Holdable
public abstract class Person extends Character {

    private int happinessPercentage;
    private Location currentLocation;
    private Object holdingObject = null;

    protected Set<Thought> thoughts = new HashSet<>();
    protected Set<Memory> memories = new HashSet<>();
    protected Set<Item> inventory = new HashSet<>();


    public Person(String name) {
        this(name, Gender.MALE);
    }

    public Person(String name, Gender gender) {
        setName(name);
        setGender(gender);
    }

    public void setHappinessPercentage(int happinessPercentage) {
        this.happinessPercentage = happinessPercentage;
    }

    public int getHappinessPercentage() {
        return happinessPercentage;
    }

    public boolean setHoldingObject(Object object) {
        if (!(object.getClass().isAnnotationPresent(Holdable.class)))
            return false;
        holdingObject = object;
        return true;
    }

    public Object getHoldingObject() {
        return holdingObject;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void take(Object object) {
        if (setHoldingObject(object))
            ActionQueue.add(this, object, "takes");
    }


    public void has(Item item) {
        ActionQueue.add(this, item, "literally " + (inventory.contains(item) ? "has" : "hasn't"));
    }

    public void say(String text) {
        say(text, Emotion.NONE);
    }

    public void say(String text, Emotion emotion) {
        ActionQueue.add(this, "says" + emotion + ": " + text);
    }

    public void order(String text, Character character) {
        ActionQueue.add(this, "orders " + character + ": " + text);
    }

    public void ask(String text, Character character) {
        ActionQueue.add(this, "asks " + character + ": " + text);
    }

    public void jumpTo(Characterable character) {
        ActionQueue.add(this, character, "jumps to");
    }

    public void laugh() {
        ActionQueue.add(this, "laughs");
    }

    public void cry() {
        ActionQueue.add(this, "cries");
    }

    public void hug(Characterable character) {
        ActionQueue.add(this, character, "hugs");
    }

    public void remember(String keyword) throws MemoryNotFoundException {
        for (Memory memory : memories)
            if (memory.getKeyword().equals(keyword)) {
                ActionQueue.add(this, memory, "remembers");
                return;
            }
        throw new MemoryNotFoundException("Memory not found.", keyword);
    }

    public void addThought(String description, Thought.ThoughtType type) {
        Thought thought = new Thought(type, description);
        thoughts.add(thought);
        ActionQueue.add(this, "thinks that " + thought);
    }

    public boolean removeThought(Thought thought) {
        return thoughts.remove(thought);
    }

    public void addMemory(String description, String keyword) {
        Memory memory = new Memory(description, keyword);
        memories.add(memory);
    }

    public boolean removeMemory(Memory memory) {
        return memories.remove(memory);
    }

    public void shareThoughts() {
    }

    public String getStringThoughts() {
        if (thoughts == null) return "";

        Thought[] arr = thoughts.toArray(new Thought[0]);
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = arr[i].toString();
        return getArrString(result);
    }

    public String getStringMemories() {
        if (memories == null) return "";

        Memory[] arr = memories.toArray(new Memory[0]);
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
            result[i] = arr[i].toString();
        return getArrString(result);
    }

    private String getArrString(String[] arr) {
        if (arr.length == 0) return "";
        if (arr.length == 1) return arr[0];

        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length - 1; i++)
            sb.append(", ").append(arr[i]);
        sb.append(" and ").append(arr[arr.length - 1]);
        return sb.toString();
    }

    public void fall(Location ground){
        setLocation(ground);
        ActionQueue.add(this, ground, "falls to");
    }

    @Override
    public void setLocation(Location location) {
        currentLocation = location;
        location.people.add(this);
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public void goTo(Location location) {
        setLocation(location);
        ActionQueue.add(this, location, "goes to location");
    }

    @Override
    public void stayAt(Location location) {
        ActionQueue.add(this, location, "stays at location");
    }

    @Override
    public void walk(Location location, Duration duration) {
        ActionQueue.add(this, location, "walked for " + duration.toMillis() + " millis at location");
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Person prs = (Person) obj;
        boolean result = prs.inventory.equals(inventory) && prs.thoughts.equals(thoughts) &&
                prs.memories.equals(memories) && prs.getName().equals(getName()) && prs.happinessPercentage == happinessPercentage;
        if (holdingObject == null) result = prs.holdingObject == null && result;
        else result = holdingObject.equals(prs.holdingObject) && result;
        if (currentLocation == null) result = prs.currentLocation == null && result;
        else result = currentLocation.equals(prs.currentLocation) && result;
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total * prime + inventory.hashCode();
        total = total * prime + thoughts.hashCode();
        total = total * prime + memories.hashCode();
        total = total * prime + getName().hashCode();
        total = total * prime + happinessPercentage;
        if (holdingObject == null) total *= 31;
        else total = total * prime + holdingObject.hashCode();
        if (currentLocation == null) total *= 31;
        else total = total * prime + currentLocation.hashCode();
        return total;
    }


    public class Thought {
        protected ArrayList<Object> associations = new ArrayList<>();
        private String description;
        private final ThoughtType type;

        public Thought(ThoughtType type) {
            this(type, "emptiness...");
        }

        public Thought(ThoughtType type, String description) {
            this.type = type;
            this.description = description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public ThoughtType getType() {
            return type;
        }

        @Override
        public String toString() {
            return description;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;

            Thought thght = (Thought) obj;
            return associations.equals(thght.associations) && description.equals(thght.description) && type == thght.type;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int total = prime;

            total = total * 31 + associations.hashCode();
            total = total * 31 + description.hashCode();
            total = total * 31 + type.hashCode();
            return total;
        }

        public enum ThoughtType {
            NONE,
            INTELLECT,
            PSYCHE,
            PHILOSOPHY,
            EGO
        }
    }

    public class Memory extends Thought {
        private String keyword;

        public Memory() {
            this("they can't remember anything", "empty");
        }

        public Memory(String description, String keyword) {
            super(ThoughtType.PSYCHE, description);
            this.keyword = keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int total = prime;

            total = total * prime + keyword.hashCode();
            total = total * prime + getDescription().hashCode();
            return total;
        }
    }
}

class MemoryNotFoundException extends Exception {
    private final String keyword;

    public String getKeyword() {
        return keyword;
    }

    public MemoryNotFoundException(String message, String keyword) {
        super(message);
        this.keyword = keyword;
    }
}

// локальный класс +
// (про классы) +
// Залить на хелиос +
// рефлексия +
// uml -