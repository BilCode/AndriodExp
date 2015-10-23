package com.example.bilal_pc.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;


public class MainActivity extends Activity {

    public static final String API_URL = "https://api.github.com";
    public static final String API_URL_POST = "http://127.0.0.1";

    String txtShow="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       ImageView imgtest = (ImageView)findViewById(R.id.imageView);
       Picasso.with(MainActivity.this).load("http://www.gurit.com/files/images/new-makkah-clock-tower-inaugurated-featuring-gurit-frp-composite-materials-2jpg.jpg").into(imgtest);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here
        }

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_POST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        GitHub github = retrofit.create(GitHub.class);

        // Create a call instance for looking up Retrofit contributors.
       // Call<List<Contributor>> call = github.contributors("square", "retrofit");
        User  usr;
        try {
           usr= github.login("Btest").execute().body();
            ((TextView)findViewById(R.id.textView)).setText(usr.user+" "+usr.login_status);
            Log.d("Response","Post Response "+usr.user+" "+usr.login_status);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        // Fetch and print a list of the contributors to the library.
        List<Contributor> contributors = null;
        try {
            contributors = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
            txtShow+=contributor.login + " (" + contributor.contributions + ")\n";
        }*/

    }

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);

        @FormUrlEncoded
        @POST("/test/jsontest.php")
        public Call<User> login(@Field("username") String username);

    }


}
class User {
    public String user;
    public String login_status;
    // ... etc.
}