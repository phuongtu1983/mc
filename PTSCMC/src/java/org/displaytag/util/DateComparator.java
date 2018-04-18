/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.displaytag.util;

import com.venus.core.util.DateUtil;
import com.venus.core.util.StringUtil;
import java.util.Date;
import org.displaytag.model.DefaultComparator;

/**
 *
 * @author thcao
 */
public class DateComparator extends DefaultComparator {
    
    
    public static final String DEFAULT_DATE = "01/01/1970";
    
    @Override
    public int compare(Object dateOne, Object dateTwo) {
      int result = 0;
      String strDate1 = (String)dateOne;
      String strDate2 = (String)dateTwo;
      if (StringUtil.isBlankOrNull(strDate2)) {
          strDate2 = DEFAULT_DATE;
      }
      if (StringUtil.isBlankOrNull(strDate1)) {
          strDate1 = DEFAULT_DATE;
      }
      Date d1 = DateUtil.transformerStringEnDate(strDate1, "/");
      Date d2 = DateUtil.transformerStringEnDate(strDate2, "/");      
      result = d1.compareTo(d2);
      return result;
  }

}
