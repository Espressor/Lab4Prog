public class Adult extends Person {

    private Job job;

    public Adult(String name) {
        this(name, Gender.MALE);
    }

    public Adult(String name, Gender gender) {
        super(name, gender);
        setHappinessPercentage(30);
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }

    @Override
    public void shareThoughts(){
        ActionQueue.add(new Crowd(new Person[]{this}), "Adults don't share their thoughts");
    }
}
