package turnip.example.tscribe;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.Random;

public class MessageFragment extends Fragment {
    private static final String TAG = "CallFragment";
    private Main main;
    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private Context context;
    private ImageButton send;
    private Button disconnect;

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.main = (Main) activity;
        context = activity.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "MessageFragment: OnCreate called");
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        editText = view.findViewById(R.id.editText);

        messageAdapter = new MessageAdapter(context);
        messagesView = view.findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);

        send = view.findViewById(R.id.send);
        send.setOnClickListener(sendButtonClick);

        disconnect = view.findViewById(R.id.disconnect);
//        disconnect.setOnClickListener(disconnectClick);

        return view;
    }

    public void sendMessage() {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            // send message to server
            editText.getText().clear();
            messageAdapter.add(new Message(message, true));
        }
    }

    // connect to server
    public void onOpen() {
        System.out.println("Connected to room");
    }

    // failed to connect to server
    public void onOpenFailure(Exception ex) {
        System.err.println(ex);
    }

    // what happens when message is received
    public void onMessage(Message receivedMessage) {
        // get stuff from server
        final Message message = new Message(receivedMessage.getText(), false);
        messageAdapter.add(message);
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                messageAdapter.add(message);
//                messagesView.setSelection(messagesView.getCount() - 1);
//            }
//        });
    }

    private String getRandomName() {
        String[] adjs = {"autumn", "hidden", "bitter", "misty", "silent", "empty", "dry", "dark", "summer", "icy", "delicate", "quiet", "white", "cool", "spring", "winter", "patient", "twilight", "dawn", "crimson", "wispy", "weathered", "blue", "billowing", "broken", "cold", "damp", "falling", "frosty", "green", "long", "late", "lingering", "bold", "little", "morning", "muddy", "old", "red", "rough", "still", "small", "sparkling", "throbbing", "shy", "wandering", "withered", "wild", "black", "young", "holy", "solitary", "fragrant", "aged", "snowy", "proud", "floral", "restless", "divine", "polished", "ancient", "purple", "lively", "nameless"};
        String[] nouns = {"waterfall", "river", "breeze", "moon", "rain", "wind", "sea", "morning", "snow", "lake", "sunset", "pine", "shadow", "leaf", "dawn", "glitter", "forest", "hill", "cloud", "meadow", "sun", "glade", "bird", "brook", "butterfly", "bush", "dew", "dust", "field", "fire", "flower", "firefly", "feather", "grass", "haze", "mountain", "night", "pond", "darkness", "snowflake", "silence", "sound", "sky", "shape", "surf", "thunder", "violet", "water", "wildflower", "wave", "water", "resonance", "sun", "wood", "dream", "cherry", "tree", "fog", "frost", "voice", "paper", "frog", "smoke", "star"};
        return (
                adjs[(int) Math.floor(Math.random() * adjs.length)] +
                        "_" +
                        nouns[(int) Math.floor(Math.random() * nouns.length)]
        );
    }

    private String getRandomColor() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer("#");
        while(sb.length() < 7){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, 7);
    }

    private View.OnClickListener sendButtonClick = new View.OnClickListener() {
        public void onClick(View v){
            sendMessage();
        }
    };

    private View.OnClickListener disconnectClick = new View.OnClickListener(){
        public void onClick(View v){
            main.disconnect();
        }
    };
}

