package com.example.bilal_pc.myapplication.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.bilal_pc.myapplication.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.android.view.OnClickEvent;
import rx.android.view.ViewObservable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    Button btnrxjava;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnrxjava= (Button) findViewById(R.id.btnrxjava);
        tvResult= (TextView) findViewById(R.id.tvResult);

        Observable<OnClickEvent> clicksObservable
                = ViewObservable.clicks(btnrxjava); // Create a ViewObservable for the Button

        clicksObservable
                .skip(4)    // Skip the first 4 clicks
                .subscribe(new Action1<OnClickEvent>() {
                    @Override
                    public void call(OnClickEvent onClickEvent) {
                        Log.d("Click Action", "Clicked!");
                        tvResult.append("\n Clicked");
                        // Printed from the fifth click onwards
                    }
                });

       /* Observable<String> myObservable
                = Observable.just("Hello"); // Emits "Hello
        Subscription mySubscription = myObservable.subscribe(myObserver);
        Subscription mySubscription1 = myObservable.subscribe(myAction);
        Observable<Integer> myArrayObservable
                = Observable.from(new Integer[]{1, 2, 3, 4, 5, 6}); // Emits each item of the array, one at a time

        myArrayObservable = myArrayObservable.map(new Func1<Integer, Integer>() { // Input and Output are both Integer
            @Override
            public Integer call(Integer integer) {
                return integer * integer; // Square the number
            }
        });

        myArrayObservable =  myArrayObservable
                .skip(1) // Skip the first two items
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) { // Ignores any item that returns false
                        return integer % 2 == 0;
                    }
                });

        myArrayObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                Log.d("My Action", String.valueOf(i)); // Prints the number received
            }
        });

        Observable<String> fetchFromGoogle = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String data = fectchData("http://www.google.com");
                    subscriber.onNext(data); // Emit the contents of the URL
                    subscriber.onCompleted(); // Nothing more to emit
                }catch(Exception e){
                    subscriber.onError(e); // In case there are network errors
                }
            }
        });

        Observable<String> fetchFromYahoo = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    String data = fectchData("http://square.github.io/okhttp/");
                    subscriber.onNext(data); // Emit the contents of the URL
                    subscriber.onCompleted(); // Nothing more to emit
                }catch(Exception e){
                    subscriber.onError(e); // In case there are network errors
                }
            }
        });

        fetchFromGoogle = fetchFromGoogle.subscribeOn(Schedulers.newThread());
        fetchFromYahoo = fetchFromYahoo.subscribeOn(Schedulers.newThread());

       /* fetchFromGoogle
                .subscribeOn(Schedulers.newThread()) // Create a new Thread
                .observeOn(AndroidSchedulers.mainThread()) // Use the UI thread
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                       Log.d("My Action"," Google : "+s);
                    }
                });*/

      /*  Observable<String> zipped
                = Observable.zip(fetchFromGoogle, fetchFromYahoo, new Func2<String, String, String>() {
            @Override
            public String call(String google, String yahoo) {
                // Do something with the results of both threads
                return "Google "+google.substring(0,1000) + "\n" + "okhttp "+yahoo.substring(0,1000);
            }
        }).observeOn(AndroidSchedulers.mainThread()); // Use the UI thread;

        zipped.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("My Action"," Google and okhttp : "+s);
            }
        });

        Observable<String> concatenated = Observable.concat(fetchFromGoogle, fetchFromYahoo);// Emit the results one after another
        concatenated.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("My Action"," Google and okhttp : "+s);
            }
        });*/
    }

    Observer<String> myObserver = new Observer<String>() {
        @Override
        public void onCompleted() {
            // Called when the observable has no more data to emit
            Log.d("MY OBSERVER", "brx onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
        }

        @Override
        public void onNext(String s) {
            // Called each time the observable emits data
            Log.d("MY OBSERVER", "brx " +s);
        }
    };

    Action1<String> myAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Log.d("My Action","brx "+ s);
        }
    };

    private String fectchData(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
    }
}
