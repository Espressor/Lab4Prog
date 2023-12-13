public enum Emotion {
    NONE(""),
    EMOTIONLESS(" emotionless"),
    SERIOUS (" seriously"),
    JOKING(" joking"),
    SAD(" sadly"),
    HAPPY(" happily"),
    TIRED( " tired"),
    BORED(" bored"),
    INTERESTED(" interested"),
    THOUGHTFUL(" thoughtfully"),
    FRIGHTENED(" frightened");

    private final String title;

    Emotion(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
