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

public class CallFragment extends Fragment {
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
        Log.i(TAG, "CallFragment: OnCreate called");
        View view = inflater.inflate(R.layout.fragment_call, container, false);

        Button callButton = view.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                main.connect();
            }
        });

        return view;
    }
}
