package database;

public class Word {
    private String word_target;
    private String word_explain;
    private String type;
    private String ipa;

    /**
     * Constructor.
     */
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    /**
     * Constructor.
     */
    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    /**
     * Constructor.
     */
    public Word(String word_target, String word_explain, String type, String ipa) {
        this.word_target = word_target;
        this.word_explain = word_explain;
        this.type = type;
        this.ipa = ipa;
    }

    /**
     * get word_target.
     * @return word_target
     */
    public String getWord_target() {
        return word_target;
    }

    /**
     * set word_target.
     * @param word_target
     */
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    /**
     * get word_explain.
     * @return word_explain
     */
    public String getWord_explain() {
        return word_explain;
    }

    /**
     * set word_explain.
     * @param word_explain
     */
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    /**
     * get type.
     * @return type of word
     */
    public String getType() {
        return type;
    }

    /**
     * set type of word.
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * get pronounce.
     * @return ipa
     */
    public String getIpa() {
        return ipa;
    }

    /**
     * set pronounce.
     * @param ipa
     */
    public void setIpa(String ipa) {
        this.ipa = ipa;
    }


}
