package turnip.example.tscribe;

public interface Main {
    public void connect(String callNum, String opening);
    public void disconnect();
    public void send(String message);
}
