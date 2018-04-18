package com.venus.mc.util;

import com.venus.core.util.TimerUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class WatchTask
  extends TimerTask
{
  private static WatchTask instance = null;
  private boolean scheduled;
  private Calendar c = Calendar.getInstance();
  
  private WatchTask()
  {
    this.scheduled = false;
  }
  
  public static synchronized WatchTask getInstance()
  {
    if (instance == null) {
      instance = new WatchTask();
    }
    return instance;
  }
  
  public boolean cancel()
  {
    return super.cancel();
  }
  
  public synchronized void schedule(Date firstTime, long period)
  {
    if (!this.scheduled)
    {
      this.scheduled = true;
      TimerUtil.getInstance().schedule(this, firstTime, period);
    }
  }
  
  public synchronized void schedule(Date time)
  {
    if (!this.scheduled)
    {
      this.scheduled = true;
      TimerUtil.getInstance().schedule(this, time);
    }
  }
  
  public synchronized void schedule(long delay)
  {
    if (!this.scheduled)
    {
      this.scheduled = true;
      TimerUtil.getInstance().schedule(this, delay);
    }
  }
  
  public synchronized void schedule(long delay, long period)
  {
    if (!this.scheduled)
    {
      this.scheduled = true;
      TimerUtil.getInstance().schedule(this, delay, period);
    }
  }
  
  public synchronized void scheduleAtFixedRate(Date firstTime, long period)
  {
    if (!this.scheduled)
    {
      this.scheduled = true;
      TimerUtil.getInstance().schedule(this, firstTime, period);
    }
  }
  
  public synchronized void scheduleAtFixedRate(long delay, long period)
  {
    if (!this.scheduled)
    {
      this.scheduled = true;
      TimerUtil.getInstance().schedule(this, delay, period);
    }
  }
  
  public void run() {}
}
