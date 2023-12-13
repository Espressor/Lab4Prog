public class Action {
    public final Characterable subject;
    public final Object object;
    public final String description;

    public Action(Characterable subject, Object object, String description) {
        this.subject = subject;
        this.object = object;
        this.description = description;
    }

    public Action(Characterable subject, String description) {
        this(subject, null, description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Action act = (Action) obj;
        boolean result = subject.equals(act.subject) && description.equals(act.description);
        if (object == null) result = act.object == null && result;
        else result = object.equals(act.object) && result;
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total*prime + subject.hashCode();
        total = total*prime + object.hashCode();
        total = total*prime + description.hashCode();
        return total;
    }
}
