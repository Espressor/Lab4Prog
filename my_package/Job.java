public class Job {
    private Location location;
    private int position;
    private String positionName;
    private String companyName;

    public Job(String positionName, int position, String companyName, Location location) {
        this.positionName = positionName;
        this.position = position;
        this.companyName = companyName;
        this.location = location;
    }

    public Job(String positionName, int position, String companyName) {
        this(positionName, position, companyName, new Location("Home"));
    }

    public void promote(String newPositionName) {
        positionName = newPositionName;
        position++;
    }

    public void setPosition(String positionName, int position) {
        this.positionName = positionName;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setCompanyName(String newCompanyName) {
        companyName = newCompanyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return positionName + " at " + companyName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Job jb = (Job) obj;
        return companyName.equals(jb.getCompanyName()) && positionName.equals(jb.getPositionName()) && position ==
                jb.getPosition() && location.equals(jb.getLocation());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total * prime + companyName.hashCode();
        total = total * prime + positionName.hashCode();
        total = total * prime + position;
        total = total * prime + location.hashCode();
        return total;
    }
}
