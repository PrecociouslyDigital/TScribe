package turnip.example.tscribe;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RachelActivity extends Activity{

    Button call;
    EditText mEdit;
    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dial);

        call = (Button) findViewById(R.id.CallButton);
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mEdit   = (EditText)findViewById(R.id.phoneNum);
                mText = (TextView)findViewById(R.id.text);
                String output = mEdit.getText().toString();
            }
        });
    }
}
