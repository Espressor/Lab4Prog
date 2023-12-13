@Holdable
public class Item implements Characterable {

    private Characterable owner;
    private final String name;
    private final Gender gender = Gender.NONE;

    public Item(String name) {
        this(name, null);
    }

    public Item(String name, Characterable owner) {
        this.name = name;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Characterable owner) {
        this.owner = owner;
    }

    public Characterable getOwner() {
        return owner;
    }

    @Override
    public void setGender(Gender gender) {
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        if (owner == null)
            return name;
        return owner + "'s " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Item itm = (Item) obj;
        boolean result = itm.name.equals(name);
        if (owner == null) result = itm.owner == null && result;
        else result = owner.equals(itm.owner) && result;
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total * prime + name.hashCode();
        if (owner == null) total *= 31;
        else total = total * prime + owner.hashCode();
        return total;
    }
}
