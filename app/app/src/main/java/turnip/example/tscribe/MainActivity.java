package turnip.example.tscribe;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Main{
    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "OnCreate called");
        setContentView(R.layout.activity_main);

        CallFragment callFragment = new CallFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentFragment, callFragment);
        ft.commit();
    }

    public void connect() {
        MessageFragment messageFragment = new MessageFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentFragment, messageFragment);
        ft.commit();
    }

    public void disconnect() {
        CallFragment callFragment = new CallFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentFragment, callFragment);
        ft.commit();
    }

}
