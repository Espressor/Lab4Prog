import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Crowd extends Character {

    private final Set<Person> people = new HashSet<>();
    private Location currentLocation;

    public Crowd(Person[] people) {
        setGender(Gender.THEY);
        Collections.addAll(this.people, people);
    }

    public void addPerson(Person person) {
        people.add(person);
    }
    public boolean removePerson(Person person) {
        return people.remove(person);
    }

    public void addPeople(Person[] people) {
        for (Person person : people)
            addPerson(person);
    }

    public boolean removePeople(Person[] people) {
        for (Person person : people)
            if (!removePerson(person))
                return false;
        return true;
    }

    public Person[] getPeople() {
        return people.toArray(new Person[0]);
    }

    @Override
    public Location getLocation() {
        return currentLocation;
    }

    @Override
    public void setLocation(Location location) {
        currentLocation = location;
        location.people.addAll(people);
    }

    @Override
    public void goTo(Location location) {
        currentLocation = location;
        location.people.addAll(people);
        ActionQueue.add(new Action(this, location, "go to location"));
    }

    @Override
    public void stayAt(Location location) {
        ActionQueue.add(new Action(this, location, "stay at location"));
    }

    @Override
    public String toString() {
        if (people.isEmpty())
            return "";

        int n = people.size();
        Person[] ppl = getPeople();

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Crowd crowd = (Crowd) obj;
        if (!people.equals(crowd.people)) return false;
        if (currentLocation == null) return crowd.currentLocation == null;
        return currentLocation.equals(crowd.currentLocation);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total * prime + people.hashCode();
        total = total * prime + currentLocation.hashCode();
        return total;
    }
}
