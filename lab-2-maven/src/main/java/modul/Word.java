package modul;

public class Word implements Component{
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
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
        return word + " ";
    }
}