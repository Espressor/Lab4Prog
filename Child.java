public class Child extends Person {

    public Child(String name) {
        this(name, Gender.MALE);
    }

    public Child(String name, Gender gender) {
        super(name, gender);
        setHappinessPercentage(70);
    }

    @Override
    public void shareThoughts(){
        say("I think, that "+ getStringThoughts());
    }
}
