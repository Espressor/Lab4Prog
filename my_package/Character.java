public abstract class Character implements Characterable {
    private String name;
    private Gender gender;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Gender getGender() {
        return gender;
    }

    public void setLocation(Location location) {
    }

    public Location getLocation() {
        return null;
    }

    public void goTo(Location location) {
    }

    public void stayAt(Location location) {
    }
}
