package turnip.example.tscribe;

public class Message {

    private String text;
    private boolean isMine;

    public Message(String text, boolean isMine){
        this.text = text;
        this.isMine = isMine;
    }

    public String getText(){
        return text;
    }

    public boolean isMine(){
        return isMine;
    }

}
