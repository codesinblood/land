// Print a message every 10 seconds using TimerTask API with time, like "6:11 AM Monday, 10 September 2018: Hi I am auto runner"

package com.objectfrontier.training.java.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskApi {

    public static void main(String[] args) {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                System.out.println( LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a EEEE, dd MMMM yyyy:")) + " Hi I am auto runner");
            }
        };
            timer.schedule(task, 0, 10000);
        }
    }

