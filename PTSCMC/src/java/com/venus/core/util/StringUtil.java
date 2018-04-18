package com.venus.core.util;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;

public class StringUtil
{
  public static boolean isBlankOrNull(String str)
  {
    if ((str == null) || (str.trim().length() == 0)) {
      return true;
    }
    return false;
  }
  
  public static String concatPrefix(boolean bFirst, String str, String prefixStr)
  {
    return prefixStr + str;
  }
  
  public static String[] toTokensArray(String value, String token_sign)
  {
    if (value == null) {
      value = "";
    }
    StringTokenizer st = new StringTokenizer(value, token_sign);
    
    int token_num = st.countTokens();
    String[] shared_path_full = new String[token_num];
    int k = 0;
    while (st.hasMoreTokens())
    {
      shared_path_full[k] = st.nextToken().trim();
      k++;
    }
    return shared_path_full;
  }
  
  public static String replace(String str, String pattern, String replace)
  {
    if (replace == null) {
      replace = "";
    }
    int s = 0;int e = 0;
    StringBuffer result = new StringBuffer(str.length() * 2);
    while ((e = str.indexOf(pattern, s)) >= 0)
    {
      result.append(str.substring(s, e));
      result.append(replace);
      s = e + pattern.length();
    }
    result.append(str.substring(s));
    return result.toString();
  }
  
  public static String wordWrap(String str)
  {
    return wordWrap(str, 80, "\n", "-", true);
  }
  
  public static String wordWrap(String str, int width)
  {
    return wordWrap(str, width, "\n", "-", true);
  }
  
  public static String wordWrap(String str, int width, String delim, String split, boolean delimInside)
  {
    int sz = str.length();
    
    width++;
    
    StringBuffer buffer = new StringBuffer(sz / width * delim.length() + sz);
    if (delimInside) {
      width -= delim.length();
    } else {
      width--;
    }
    int idx = -1;
    String substr = null;
    for (int i = 0; i < sz; i += width)
    {
      if (i > sz - width)
      {
        buffer.append(str.substring(i));
        break;
      }
      substr = str.substring(i, i + width);
      
      idx = substr.indexOf(delim);
      if (idx != -1)
      {
        buffer.append(substr.substring(0, idx));
        buffer.append(delim);
        i -= width - idx - delim.length();
        if ((substr.length() > idx + 1) && 
          (substr.charAt(idx + 1) != '\n') && 
          (Character.isWhitespace(substr.charAt(idx + 1)))) {
          i++;
        }
      }
      else
      {
        idx = -1;
        
        char[] chrs = substr.toCharArray();
        for (int j = width; j > 0; j--) {
          if (Character.isWhitespace(chrs[(j - 1)]))
          {
            idx = j;
            break;
          }
        }
        if (idx == -1)
        {
          for (int j = width; j > 0; j--) {
            if (chrs[(j - 1)] == '-')
            {
              idx = j;
              break;
            }
          }
          if (idx == -1)
          {
            buffer.append(substr);
            buffer.append(delim);
          }
          else
          {
            if (idx != width) {
              idx++;
            }
            buffer.append(substr.substring(0, idx));
            buffer.append(delim);
            i -= width - idx;
          }
        }
        else
        {
          buffer.append(substr.substring(0, idx));
          buffer.append(StringUtils.repeat(" ", width - idx));
          buffer.append(delim);
          i -= width - idx;
        }
      }
    }
    return buffer.toString();
  }
  
  public static String encodeHTML(String s)
  {
    StringBuffer str = new StringBuffer();
    
    int len = s != null ? s.length() : 0;
    for (int i = 0; i < len; i++)
    {
      char ch = s.charAt(i);
      switch (ch)
      {
      case '<': 
        str.append("&lt;");
        break;
      case '>': 
        str.append("&gt;");
        break;
      case '&': 
        str.append("&amp;");
        break;
      case '"': 
        str.append("&quot;");
        break;
      case '�': 
        str.append("&quot;");
        break;
      case '\'': 
        str.append("&apos;");
        break;
      case '\n': 
      case '\r': 
        str.append("&#");
        str.append(Integer.toString(ch));
        str.append(';');
        break;
      default: 
        str.append(ch);
      }
    }
    return str.toString();
  }
  
  public static String encodeString(String s)
  {
    StringBuffer str = new StringBuffer();
    
    int len = s != null ? s.length() : 0;
    for (int i = 0; i < len; i++)
    {
      char ch = s.charAt(i);
      switch (ch)
      {
      case '"': 
        str.append("\"");
        break;
      default: 
        str.append(ch);
      }
    }
    return str.toString();
  }
  
  public static String decodeString(String s)
  {
    try
    {
      s = replaceAllWords(s, "&lt;", "<");
      s = replaceAllWords(s, "&gt;", ">");
      s = replaceAllWords(s, "&amp;", "&");
      s = replaceAllWords(s, "&quot;", '"');
      s = replaceAllWords(s, "&apos;", "'");
      s = replaceAllWords(s, "&#10;", "\n");
      s = replaceAllWords(s, "?", "?");
      s = s.replaceAll("null", "...");
    }
    catch (Exception e)
    {
      s = "";
    }
    return s;
  }
  
  public static String replaceAllWords(String original, String find, String replacement)
  {
    String result = "";
    String delimiters = "+-*/(),. ";
    StringTokenizer st = new StringTokenizer(original, delimiters, true);
    while (st.hasMoreTokens())
    {
      String w = st.nextToken();
      if (w.contains(find))
      {
        if (w.split(find).length == 0) {
          result = result + replacement;
        } else if (w.split(find).length == 1) {
          result = result + w.split(find)[0] + replacement;
        } else {
          result = result + w.split(find)[0] + replacement + w.split(find)[1];
        }
      }
      else {
        result = result + w;
      }
    }
    return result;
  }
  
  public static String replaceAllWords(String original, String find, char replacement)
  {
    String result = "";
    String delimiters = "+-*/(),. ";
    StringTokenizer st = new StringTokenizer(original, delimiters, true);
    while (st.hasMoreTokens())
    {
      String w = st.nextToken();
      if (w.contains(find))
      {
        if (w.split(find).length == 0) {
          result = result + replacement;
        } else if (w.split(find).length == 1) {
          result = result + w.split(find)[0] + replacement;
        } else {
          result = result + w.split(find)[0] + replacement + w.split(find)[1];
        }
      }
      else {
        result = result + w;
      }
    }
    return result;
  }
  
  public static String makeShortName(String str, int nWorld)
  {
    if (isBlankOrNull(str)) {
      return "";
    }
    StringTokenizer token = new StringTokenizer(str, " ");
    int i = 0;
    StringBuffer strBuff = new StringBuffer();
    while (token.hasMoreTokens())
    {
      strBuff.append(token.nextToken());
      i++;
      if (i == nWorld) {
        break;
      }
      strBuff.append(" ");
    }
    if (strBuff.length() < str.length()) {
      strBuff.append("...");
    }
    return strBuff.toString();
  }
  
  public static String getString(String str)
  {
    if (isBlankOrNull(str)) {
      return "";
    }
    return str;
  }
  
  public static String decodeHTML(String s)
  {
    s = s.replaceAll("&#43;", "+");
    return s;
  }
  
  public static String generatePrefix(String content, int length)
  {
    String temp = content;
    int l = temp.length();
    temp = "";
    for (int i = 0; i < length - l; i++) {
      temp = "0";
    }
    temp = temp + content;
    return temp;
  }
  
  public static String decompose(String s)
  {
    return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
  }
}
