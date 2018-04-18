package com.venus.core.util;

import java.util.*;

public final class TimerUtil {
    
    private static TimerUtil instance = null;
    private static boolean isCanceled = false;
    private Timer timer;
    
    private TimerUtil() {
        timer = null;        
        timer = new Timer();
    }
    
    private void reloadTimer() {        
        if(!isCanceled) {
            timer.cancel();
            timer = new Timer();
        }
    }
    
    public static synchronized TimerUtil getInstance() {
        if(instance == null)
            instance = new TimerUtil();
        return instance;
    }
    
    public void cancel() {
        isCanceled = true;
        timer.cancel();
    }
    
    public void schedule(TimerTask task, Date firstTime, long period) {
        if(!isCanceled)
            try {
                timer.schedule(task, firstTime, period);
            } catch(IllegalStateException ex) {                
                reloadTimer();
            }
    }
    
    public void schedule(TimerTask task, Date time) {
        if(!isCanceled)
            try {
                timer.schedule(task, time);
            } catch(IllegalStateException ex) {                
                reloadTimer();
            }
    }
    
    public void schedule(TimerTask task, long delay) {
        if(!isCanceled)
            try {
                timer.schedule(task, delay);
            } catch(IllegalStateException ex) {                
                reloadTimer();
            }
    }
    
    public void schedule(TimerTask task, long delay, long period) {
        if(!isCanceled)
            try {
                timer.schedule(task, delay, period);
            } catch(IllegalStateException ex) {                
                reloadTimer();
            }
    }
    
    public void scheduleAtFixedRate(TimerTask task, Date firstTime, long period) {
        if(!isCanceled)
            try {
                timer.schedule(task, firstTime, period);
            } catch(IllegalStateException ex) {                
                reloadTimer();
            }
    }
    
    public void scheduleAtFixedRate(TimerTask task, long delay, long period) {
        if(!isCanceled)
            try {
                timer.schedule(task, delay, period);
            } catch(IllegalStateException ex) {                
                reloadTimer();
            }
    }
    
}
