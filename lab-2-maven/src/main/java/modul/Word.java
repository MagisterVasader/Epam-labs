package modul;

public class Word implements Component{

    private StringBuffer word;

    public Word(StringBuffer word) {
        this.word = word;
    }

    public StringBuffer getWord() {
        return word;
    }

    public void setWord(StringBuffer word) {
        this.word = word;
    }

    @Override
    public int inclusion(char symbol) {
        int counter = 0;
        for (int i = 0; i < word.length();i++) {
            if (word.charAt(i) == symbol){
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word=" + word +
                '}';
    }
}
