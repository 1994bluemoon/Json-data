package loginwithfacebook.dminhhoang.com.jsondata.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import loginwithfacebook.dminhhoang.com.jsondata.Adapters.UserAdapter;
import loginwithfacebook.dminhhoang.com.jsondata.Models.Accounts;
import loginwithfacebook.dminhhoang.com.jsondata.Models.UserResult;
import loginwithfacebook.dminhhoang.com.jsondata.R;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String SERVER_URL = "https://lit-basin-20477.herokuapp.com/users?page=0&limit=10";
    @BindView(R.id.lv_main)
    ListView lvMain;

    List<Accounts> accounts;
    public UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        accounts = new ArrayList<Accounts>();

        adapter = new UserAdapter(accounts, this);

        new GetUserTask(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        lvMain.setAdapter(adapter);
    }


    static class GetUserTask extends AsyncTask<Void, Void, List<Accounts>> {
        private MainActivity activity;

        GetUserTask(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        protected List<Accounts> doInBackground(Void... voids) {
            String jsonResult = getJSON(SERVER_URL, 10 * 1000);
            Log.d(TAG, jsonResult);
            UserResult result = new Gson().fromJson(jsonResult, UserResult.class);
            return result.getUsers();
        }

        @Override
        protected void onPostExecute(List<Accounts> users) {
            super.onPostExecute(users);
            activity.adapter.setAccounts(users);
            activity.adapter.notifyDataSetChanged();
        }
    }

    public static String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

