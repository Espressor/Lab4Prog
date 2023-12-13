public class Memory extends Thought{
    private String keyword;

    public Memory(){
        this("they can't remember anything", "empty");
    }

    public Memory(String description, String keyword) {
        super(ThoughtType.PSYCHE, description);
        this.keyword = keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword(){
        return keyword;
    }

    @Override
    public  int hashCode() {
        final int prime = 31;
        int total = prime;

        total = total*prime + keyword.hashCode();
        total = total*prime + getDescription().hashCode();
        return total;
    }
}
