package com.venus.core.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class DateUtil
{
  public static String formatterDateAfficher(Date date)
  {
    if (date == null) {
      return "";
    }
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int jour = c.get(5);
    int mois = c.get(2) + 1;
    int annee_pro = c.get(1);
    NumberFormat format_number = new DecimalFormat("##00");
    return format_number.format(mois) + "/" + format_number.format(jour) + "/" + annee_pro;
  }
  
  public static Date transformerStringEnDate(String str, String token)
  {
    if (str == null) {
      return null;
    }
    StringTokenizer dateCreation_token = new StringTokenizer(str, token);
    if (dateCreation_token.countTokens() != 3) {
      return null;
    }
    try
    {
      int dateCreation_jour = Integer.parseInt(dateCreation_token.nextToken());
      int dateCreation_mois = Integer.parseInt(dateCreation_token.nextToken());
      int dateCreation_annee = Integer.parseInt(dateCreation_token.nextToken());
      if (dateCreation_annee < 2000) {
        dateCreation_annee += 2000;
      }
      Calendar c = Calendar.getInstance();
      c.set(dateCreation_annee, dateCreation_mois - 1, dateCreation_jour);
      return c.getTime();
    }
    catch (Exception ex) {}
    return null;
  }
  
  public static String formatDate(Date date, String pattern)
  {
    if (date == null) {
      return "";
    }
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(date);
  }
  
  public static String today(String pattern)
  {
    Date date_now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(date_now);
  }
  
  public static String yesterday(String pattern)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(5, -1);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(c.getTime());
  }
  
  public static String getDate(Date date, int day, String pattern)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.add(5, day);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(c.getTime());
  }
  
  public static String tomorrow(String pattern)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(new Date());
    c.add(5, 1);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(c.getTime());
  }
  
  public static String getDayOfWeek(Date date, int i, String pattern)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int day = c.get(7);
    c.set(7, i);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(c.getTime());
  }
  
  public static String getDayOfWeek(String date, int i, String pattern)
  {
    return getDayOfWeek(transformerStringEnDate(date, "-"), i, pattern);
  }
  
  public static String getNextWeek(String date, int i, String pattern)
  {
    return getNextWeek(transformerStringEnDate(date, "-"), i, pattern);
  }
  
  public static String getNextWeek(Date date, int i, String pattern)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int day = c.get(7);
    c.set(7, 2);
    c.add(5, 7);
    c.set(7, i);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(c.getTime());
  }
  
  public static String getDayLastWeek(Date date, int i, String pattern)
  {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    int day = c.get(7);
    c.set(7, 2);
    c.add(5, -7);
    c.set(7, i);
    SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    return formatter.format(c.getTime());
  }
  
  public static int getDay(Date date1, Date date2)
  {
    if ((date1 == null) || (date2 == null)) {
      return 0;
    }
    int result = 0;
    Calendar c1 = Calendar.getInstance();
    c1.setTime(date1);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(date2);
    while (c1.get(1) != c2.get(1))
    {
      result += 360;
      c2.add(6, 360);
    }
    result += c1.get(6) - c2.get(6);
    return result;
  }
  
  public static int getDay(String strDate1, String strDate2)
  {
    Date date1 = transformerStringEnDate(strDate1, "-");
    Date date2 = transformerStringEnDate(strDate2, "-");
    int result = 0;
    Calendar c1 = Calendar.getInstance();
    c1.setTime(date1);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(date2);
    while (c1.get(1) != c2.get(1))
    {
      result += 360;
      c2.add(6, 360);
    }
    result += c1.get(6) - c2.get(6);
    return result;
  }
  
  public static Date convertStringToDate(String dateStr, String format)
  {
    SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat(format);
    try
    {
      return mySimpleDateFormat.parse(dateStr);
    }
    catch (Exception e) {}
    return null;
  }
}
