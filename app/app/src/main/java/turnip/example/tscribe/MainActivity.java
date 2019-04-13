package turnip.example.tscribe;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity implements Main{
    private static final String TAG = "Main";
    private static final String SERVER_URL = "http://tscribe-precociouslydigital.c9users.io:8080/call";
    private CallFragment callFragment;
    private MessageFragment messageFragment;


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

    public void connectToServer(String userNum, String callNum, String opening) throws Exception{
        /*HttpClient client = new DefaultHttpClient();
        HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
        JSONObject json = new JSONObject();

            //URI uri = new URI("http", null, SERVER_URL, 3000, "", "", "");
            HttpPost post = new HttpPost(SERVER_URL);
            Log.i(TAG, "Posted");
            json.put("callNumber", callNum);
            json.put("userNumber", "userNum");
            json.put("opening", opening);
            String send = json.toString();
            StringEntity se = new StringEntity( json.toString());
            se.setContentType(new BasicHeader("Content-Type", "application/json"));
            post.setEntity(se);
            Log.i(TAG, "Created JSON");

            String response = getStringFromStream(client.execute(post).getEntity().getContent());

            Log.i("Response", response);*/

            HttpClient httpClient = new DefaultHttpClient();
            JSONObject object = new JSONObject();
            try {
                object.put("callNumber", callNum);
                object.put("userNumber", userNum);
                object.put("opening", opening);
                HttpPost postMethod = new HttpPost(SERVER_URL);
                postMethod.setEntity(new StringEntity(object.toString()));
                postMethod.setHeader("Accept", "application/json");
                postMethod.setHeader("Content-type", "application/json");
                HttpResponse response = httpClient.execute(postMethod);
                HttpEntity entity = response.getEntity();
                String result = getStringFromStream(entity.getContent());


                Log.i(TAG, "Result: " + result );
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                Log.e(TAG, "ClientProtocol");
                throw new Exception();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "IOException");
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "why");
                throw new Exception();
            }



    }

    public String getStringFromStream(InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
    }



    public void connect() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    connectToServer("+14254999057", "+14254350494", "My tooth hurts");
                    MessageFragment messageFragment = new MessageFragment();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.contentFragment, messageFragment);
                    ft.commit();
                } catch (Exception e) {
                    Log.e(TAG,"Error connecting to server");
                    Toast.makeText(getApplicationContext(), "Error connecting", Toast.LENGTH_SHORT).show();
                }
            }
        };
        new Thread(runnable).start();
    }

    public void disconnect() {
        CallFragment callFragment = new CallFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentFragment, callFragment);
        ft.commit();
    }

}
