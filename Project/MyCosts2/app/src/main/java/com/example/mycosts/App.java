package com.example.mycosts;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.mycosts.db.MyCostsDatabase;

public class App extends Application {

    public static App instance;
    private MyCostsDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, MyCostsDatabase.class, "mycosts.db").build();
    }

    public static App getInstance() {
        return instance;
    }

    public MyCostsDatabase getDatabase() {
        return database;
    }
}
