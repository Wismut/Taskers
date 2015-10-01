package com.tasker.database;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;


public class RunPrograms {
    Timer timer;

    public RunPrograms(String date, String path) {
        Timestamp timestamp = Timestamp.valueOf(date);
        timer = new Timer();
        timer.schedule(new AlarmTask(path), timestamp);
    }

    public class AlarmTask extends TimerTask {
        String path;

        public AlarmTask(String path) {
            this.path = path;
        }

        public void run() {
            try {
                Runtime.getRuntime().exec(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            timer.cancel();
        }
    }
}

