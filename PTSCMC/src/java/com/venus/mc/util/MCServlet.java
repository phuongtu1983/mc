package com.venus.mc.util;

import com.venus.core.util.LogUtil;
import java.io.PrintStream;
import javax.servlet.http.HttpServlet;

public class MCServlet
  extends HttpServlet
{
  WatchTask watchTask = null;
  
  public void init()
  {
    try
    {
      LogUtil.initialize(MCServlet.class.getResource("/log4j.properties"));
      
      System.out.println("Start WatchTask");
      this.watchTask = WatchTask.getInstance();
      this.watchTask.schedule(10000L, 360000L);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void destroy()
  {
    if (this.watchTask != null)
    {
      System.out.println("Stop WatchTask");
      this.watchTask.cancel();
    }
    super.destroy();
  }
}
