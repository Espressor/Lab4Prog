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
        ActionQueue.add(this, "says" + Emotion.getString(emotion) + ": " + text);
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

    public void remember(String keyword) {
        for (Memory memory : memories)
            if (memory.getKeyword().equals(keyword)) {
                ActionQueue.add(this, memory, "remembers");
                return;
            }
        ActionQueue.add(this, new Memory(), "remembers");
    }

    public void addThought(Thought thought) {
        thoughts.add(thought);
    }

    public boolean removeThought(Thought thought) {
        return thoughts.remove(thought);
    }

    public void addMemory(Memory memory) {
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

}
