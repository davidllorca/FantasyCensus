package me.test.davidllorca.fantasycensus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.test.davidllorca.fantasycensus.data.CitizensRepository;
import me.test.davidllorca.fantasycensus.data.model.Citizen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CitizensRepository repository = Injection.provideMoviesRepository();
        repository.getCitizens()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(citizens -> {
                            for (Citizen c : citizens) {
                                Log.i("citizens", c.toString());
                            }
                        },
                        throwable -> Log.e("citizens", throwable.getMessage()));
    }
}
