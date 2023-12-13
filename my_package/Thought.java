import java.lang.reflect.Type;
import java.util.ArrayList;

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
