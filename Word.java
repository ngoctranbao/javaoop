public class Word {
    //english word
    private String word_target;
    //vietnamese word
    private String word_explain;

    /**
     * Constructor co tham so
     * @param word_target english word
     * @param word_explain giai nghia tieng Viet
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    /**
     * Constructor khong co tham so
     */
    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
}
