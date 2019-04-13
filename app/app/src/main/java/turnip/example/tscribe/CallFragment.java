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
import android.widget.EditText;
import android.widget.TextView;

public class CallFragment extends Fragment {
    private static final String TAG = "CallFragment";
    private Main main;
    private Button call;
    private EditText phone;
    private EditText message;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.main = (Main) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i(TAG, "CallFragment: OnCreate called");
        View view = inflater.inflate(R.layout.fragment_call, container, false);

        call = view.findViewById(R.id.CallButton);
        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                phone = view.findViewById(R.id.phoneNum);
                message = view.findViewById(R.id.msg);
                String number = phone.getText().toString();
                String msg = message.getText().toString();
            }
        });

        /*Button callButton = view.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                main.connect();
            }
        });*/

        return view;
    }
}
