package turnip.example.tscribe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class MichelleActivity extends AppCompatActivity {

    ListView messages;
    EditText editText;
    ImageButton send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messages = (ListView) findViewById(R.id.messages_view);
        editText = (EditText) findViewById(R.id.editText);
        send = (ImageButton) findViewById(R.id.send);
    }

    public void sendMessage(View view) {
        String message = editText.getText().toString();
        if (message.length() > 0) {
            // send out message
            editText.getText().clear();
        }
    }

    private View.OnClickListener sendButtonClick = new View.OnClickListener(){
        public void onClick(View v){
            sendMessage(v);
        }
    };

}
