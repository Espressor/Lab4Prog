import java.util.HashSet;
import java.util.Set;

public class Location {
    private final String name;
    private String description = "";
    protected Set<Person> people = new HashSet<>();


    public Location(String name) {
        this.name = name;
    }

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getPeople() {
        if (people.isEmpty())
            return "";

        int n = people.size();
        Person[] ppl = people.toArray(new Person[n]);

        if (n == 1)
            return ppl[0].toString();

        StringBuilder sb = new StringBuilder();
        sb.append(ppl[0]);
        for (int i = 1; i < n - 1; i++)
            sb.append(", ").append(ppl[i]);

        sb.append(" and ").append(ppl[n - 1]);
        return sb.toString();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Location loc = (Location) obj;
        boolean result = name.equals(loc.name) && people.equals(loc.people);
        if (description == null) result = loc.description == null && result;
        else result = description.equals(loc.description) && result;
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total * prime + name.hashCode();
        total = total * prime + people.hashCode();
        total = total * prime + description.hashCode();
        return total;
    }
}
