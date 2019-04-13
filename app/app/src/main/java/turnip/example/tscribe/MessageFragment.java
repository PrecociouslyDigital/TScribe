package turnip.example.tscribe;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MessageFragment extends Fragment {
    private static final String TAG = "CallFragment";
    private Main main;


    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        this.main = (Main) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "MessageFragment: OnCreate called");
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        Button disconnectButton = view.findViewById(R.id.disconnectButton);
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                main.disconnect();
            }
        });

        return view;
    }
}
