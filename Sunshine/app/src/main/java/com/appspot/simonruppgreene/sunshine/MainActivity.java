package com.appspot.simonruppgreene.sunshine;

import android.os.AsyncTask;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        private String getUrlData(String url){
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                String output = EntityUtils.toString(httpEntity);

                return output;
            } catch(Exception e) {
                return "Something went wrong, "+e;
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String[] fakeData = {
                    "Today - Sunny - 88 / 63",
                    "Tomorrow - Sunny - 40 / 23",
                    "Wednesday - Partly Cloudy - 40 / 20",
                    "Thursday - kfdsaklfdasf",
                    "Friday - kfdsaklfdasf",
                    "Saturday - kfdsaklfdasf",
                    "Tomorrow - Sunny - 40 / 23",
                    "Wednesday - Partly Cloudy - 40 / 20",
                    "Thursday - kfdsaklfdasf",
                    "Friday - kfdsaklfdasf",
                    "Saturday - kfdsaklfdasf",
                    "Tomorrow - Sunny - 40 / 23",
                    "Wednesday - Partly Cloudy - 40 / 20",
                    "Thursday - kfdsaklfdasf",
                    "Friday - kfdsaklfdasf",
                    "Saturday - kfdsaklfdasf",
                    "Sunday - kfdsaklfdasf"
            };

            ArrayAdapter Adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forecast,R.id.list_item_forecast_textview, fakeData);
            String urlDataTest = getUrlData("http://api.openweathermap.org/data/2.5/weather?lat=35&lon=138");
            Log.d("myTag",urlDataTest);
            ListView forecastList = (ListView) rootView.findViewById(R.id.listview_forecast);
            forecastList.setAdapter(Adapter);

            return rootView;
        }
    }
}
