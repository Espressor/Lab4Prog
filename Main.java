import java.lang.reflect.Field;
import java.time.Duration;

public class Main {

    public static void main(String[] args) {
        
        Location street = new Location("street");
        Location home = new Location("home");

        Adult mother = new Adult("Mother", Character.Gender.FEMALE);
        Adult father = new Adult("Father", Character.Gender.MALE);

        Hallucination Karlson = new Hallucination("Karlson");
        Child theKid = new Child("Kid", Character.Gender.MALE);
        Child Bosse = new Child("Bosse", Character.Gender.MALE);
        Child Betan = new Child("Betan", Character.Gender.MALE);

        Adult stranger = new Adult("many other strangers", Character.Gender.THEY);
        Crowd gangstas = new Crowd(new Person[]{theKid, mother, father, Bosse, Betan, stranger});

        Item request = new Item("request", Karlson);
        Karlson.addItem(request);
        theKid.addMemory(new Memory(request.toString(), "request"));
        Item nerves = new Item("nerves");
        mother.addItem(nerves);

        theKid.remember("request");
        theKid.say("There is no other boy", Emotion.SERIOUS);
        mother.has(nerves);
        gangstas.stayAt(street);
        mother.jumpTo(theKid);
        mother.hug(theKid);
        mother.cry();
        mother.laugh();
        father.take(theKid);
        father.goTo(home);

        ActionQueue.complete();
    }
}
