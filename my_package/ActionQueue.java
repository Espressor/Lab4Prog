import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public abstract class ActionQueue {

    private ActionQueue() {
    }

    private final static Queue<Action> queue = new ArrayDeque<>();

    public static void add(Action action) {
        queue.add(action);
    }

    public static void add(Characterable subject, Object object, String description) {
        add(new Action(subject, object, description));
    }

    public static void add(Characterable subject, String description) {
        try {
            add(new Action(subject, description));
        } catch (NullActionException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void complete() {
        if (queue.isEmpty())
            return;

        Action previousAction = queue.poll();
        ArrayList<String> actions = new ArrayList<>();
        actions.add(previousAction.description + (previousAction.object == null ? "" : " " + previousAction.object));

        while (!queue.isEmpty()) {
            Action nextAction = queue.poll();

            if (nextAction.subject.equals(previousAction.subject)) {
                if (!nextAction.equals(previousAction))
                    if (nextAction.object != null && nextAction.object.equals(previousAction.object)) {
                        if (nextAction.object instanceof Characterable)
                            switch (((Characterable) nextAction.object).getGender()) {
                                case NONE -> actions.add(nextAction.description + " it");
                                case MALE -> actions.add(nextAction.description + " him");
                                case FEMALE -> actions.add(nextAction.description + " her");
                                case THEY -> actions.add(nextAction.description + " them");
                            }

                    } else if (nextAction.object != null) {
                        actions.add(nextAction.description + " " + nextAction.object);
                    } else
                        actions.add(nextAction.description);
            } else {
                System.out.println(previousAction.subject + " " + join(actions) + ". ");
                actions = new ArrayList<>();
                actions.add(nextAction.description + (nextAction.object == null ? "" : " " + nextAction.object));
            }

            previousAction = nextAction;
        }
        System.out.println(previousAction.subject + " " + join(actions) + ".");
    }

    private static String join(ArrayList<String> descriptions) {
        if (descriptions.isEmpty())
            return "";
        if (descriptions.size() == 1)
            return descriptions.get(0);

        int n = descriptions.size();
        StringBuilder sb = new StringBuilder();
        sb.append(descriptions.get(0));
        for (String description : descriptions.subList(1, n - 1))
            sb.append(", ").append(description);
        sb.append(" and ").append(descriptions.get(n - 1));
        return sb.toString();
    }
}
