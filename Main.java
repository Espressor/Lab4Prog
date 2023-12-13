import java.io.Console;
import java.lang.reflect.Field;
import java.time.Duration;

public class Main {


    public static void main(String[] args) {

        Console cli = System.console();
        cli.readPassword();
        cli.readLine();
        cli.printf("Welcome");

        Location street = new Location("Street");
        Location roof = new Location("Roof"){
            @Override
            public void specialEvent() {
                ActionQueue.add(this, "falls");
                for (Person p : people)
                    p.fall(street);
            }
        };
        Location.Home home = new Location.Home();

        Adult mother = new Adult("Mother", Gender.FEMALE);
        Adult father = new Adult("Father", Gender.MALE);

        Hallucination Karlson = new Hallucination("Karlson");
        Child theKid = new Child("Kid", Gender.MALE);
        Child Bosse = new Child("Bosse", Gender.MALE);
        Child Betan = new Child("Betan", Gender.MALE);

        Adult stranger = new Adult("many other strangers", Gender.THEY);
        Crowd gangstas = new Crowd(new Person[]{theKid, mother, father, Bosse, Betan, stranger});

        Adult firefighter = new Adult("Firefighter", Gender.MALE);
        firefighter.setJob(new Job("Main firefighter", 4, "Firefighting agency №1"));


        Item request = new Item("request", Karlson);
        Karlson.addItem(request);
        theKid.addMemory(request.toString(), "request");
        Item nerves = new Item("nerves");
        mother.addItem(nerves);

        String FfPhrase = "Stay right there and don't move! Listen, do not move! I'm climbing and taking you out of the roof";
        theKid.setLocation(roof);
        firefighter.setLocation(roof);

        firefighter.order(FfPhrase, theKid);
        theKid.addThought("it was very cute but unnecessary for firefighter to warn him", Person.Thought.ThoughtType.PHILOSOPHY);
        theKid.walk(roof, Duration.ofHours(3));
        theKid.able(new Action(null, "make some steps to the ladder"));
        ActionQueue.complete();
        theKid.ask("Mom has called you?", firefighter);
        firefighter.say("Well, yeah, mom. Of course. But... It has seemed two little boys were here");

        try {
            theKid.remember("requ3est");
        } catch (MemoryNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("No memories with keyword: " + e.getKeyword());
        } finally {
            System.out.println("123");
        }

        try {
            Field field = Character.class.getDeclaredField("name");
            field.setAccessible(true);

            System.out.println("Имя: " + field.get(theKid));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }


        theKid.say("There is no other boy", Emotion.SERIOUS);

        theKid.setLocation(street);
        firefighter.setLocation(street);

        mother.has(nerves);
        gangstas.stayAt(street);
        mother.jumpTo(theKid);
        mother.hug(theKid);
        mother.cry();
        mother.laugh();
        father.take(theKid);
        father.goTo(home);

        Bosse.say("You've scared us so much!");
        Betan.cry();
        Betan.say("Never do this again. Remember, never again!", Emotion.SAD);


    }
}