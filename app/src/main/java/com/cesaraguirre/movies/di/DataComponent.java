package com.cesaraguirre.movies.di;

import android.app.Application;

import androidx.room.Room;

import com.cesaraguirre.movies.data.room.db.MyAppRoomDatabase;


import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public class DataComponent {

    @Reusable
    @Provides
    public MyAppRoomDatabase provideDatabase(Application myApplication) {
        return Room.databaseBuilder(myApplication.getApplicationContext(), MyAppRoomDatabase.class, "mydb")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
