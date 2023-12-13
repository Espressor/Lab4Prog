public class Hallucination extends Person {
    public Hallucination(String name) {
        super(name);
        setHappinessPercentage(100);
    }

    public void scare(Character character) {
        ActionQueue.add(this, character, "scares");
    }

    @Override
    public void shareThoughts(){
        say("My thoughts are your thoughts..");
        laugh();
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
