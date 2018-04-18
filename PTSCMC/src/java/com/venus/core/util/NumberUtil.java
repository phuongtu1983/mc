/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.venus.core.util;

import java.text.DecimalFormat;

/**
 *
 * @author phuongtu
 */
public class NumberUtil {

    public static int round(double x) {
        return (int) (Math.floor(x + 0.5));
    }

    public static double roundDouble(double x) {
        return (double) (Math.floor(x + 0.5));
    }

    public static int parseInt(String value, int defaultValue) {
        int ret = defaultValue;
        try {
            ret = Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static long parseLong(String value, long defaultValue) {
        long ret = defaultValue;
        try {
            ret = Long.parseLong(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static float parseFloat(String value, float defaultValue) {
        float ret = defaultValue;
        try {
            ret = Float.parseFloat(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static double parseFloat(String value, double defaultValue) {
        double ret = defaultValue;
        try {
            ret = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static boolean parseBool(String value, boolean defaultValue) {
        boolean ret = defaultValue;
        try {
            ret = Boolean.parseBoolean(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static int decode(String value, int defaultValue) {
        int ret = defaultValue;
        if (value.indexOf("0x") == -1) {
            value = "0x" + value;
        }
        try {
            ret = Integer.decode(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static long decode(String value, long defaultValue) {
        long ret = defaultValue;
        if (value.indexOf("0x") == -1) {
            value = "0x" + value;
        }
        try {
            ret = Long.decode(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static double parseDouble(String value, double defaultValue) {
        double ret = defaultValue;
        try {
            ret = Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            ret = defaultValue;
        }
        return ret;
    }

    public static boolean isHex(String str) {
        boolean result = true;
        try {
            Long.decode(str);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static boolean isInteger(String strNum) {
        boolean result = true;
        try {
            Integer.parseInt(strNum);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public static boolean isFloat(String strNum) {
        boolean result = true;
        try {
            Float.parseFloat(strNum);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public static boolean isDouble(String strNum) {
        boolean result = true;
        try {
            Double.parseDouble(strNum);
        } catch (Exception ex) {
            result = false;
        }
        return result;
    }

    public static String formatNumberDefault(double number) {
        return formatNumber(number, "#,##0");
    }

    public static String formatDoubleDefault(double number) {
//        return formatNumber(number, "#,##0.##");
        return formatNumber(number, "#,##0.00");
    }

//    public static String formatMoneyDefault(double number, String currency) {
//        if (GenericValidator.isBlankOrNull(currency)) {
//            currency = "VND";
//        }
//        if (currency.startsWith("VN")) {
//            return formatNumber(number, "#,##0");
//        } else {
//            return formatNumber(number, "#,##0.00");
//        }
//    }
    public static String reformatMoneyDefault(double number, String currency) {
        if (GenericValidator.isBlankOrNull(currency)) {
            currency = "VND";
        }
        if (currency.startsWith("VN")) {
            return formatNumber(number, "###0");
        } else {
            return formatNumber(number, "###0.00");
        }
    }

    public static String formatMoneyDefault(Double number) {
//        double temp = number - round(number);
        long l = number.longValue();
        double temp = number - l;
        if (temp < 0.01 && temp >= 0) {
            return formatNumber(number, "#,##0");
        } else {
            return formatNumber(number, "#,##0.00");
        }
//        if (round(number) == number ) {
//            return formatNumber(number, "#,##0");
//        } else {
//            return formatNumber(number, "#,##0.00");
//        }
    }

    public static String formatMoneyDefault(double number, String currency) {
        if (GenericValidator.isBlankOrNull(currency)) {
            currency = "VND";
        }
        long l = (long) number;
        double temp = number - l;
        if (currency.startsWith("VN")) {
            if (temp >= 0.5 && temp < 1) {
                return formatNumber(roundDouble(number), "#,##0");
            } else {
                return formatNumber(number, "#,##0");
            }
        } else {
            return formatNumber(number, "#,##0.00");
        }
    }

    public static String formatNumber(double number, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(number);
    }

    public static String textMoney(Double money, String currency) {
		/*
        String s = "";
        if (money < 0) {
            money = -money;
        }
        try {
            String s1 = "";
//            String m = String.valueOf(round(money - (float) ((money * 100) % 100) / 100));
//            long l = (long) (money - (float) ((money * 100) % 100) / 100);

            long l = money.longValue();
            int m2 = 0;
            if (!currency.startsWith("VN")) {
                //phuongtu added
                Double temp = (money * 100);
                temp = Double.parseDouble(formatNumber(temp, "###0.00"));
                m2 = (int) (temp % 100);

            } else {
                int t = (int) ((money * 100) % 100);
                if (t > 50) {
                    l += 1;
                }
            }
            String m = String.valueOf(l);
            for (int i = 0; i < m.length(); i++) {
                switch (m.charAt(i)) {
                    case '0':
                        s1 = s1 + "kh\u00F4ng";
                        break;
                    case '1':
                        s1 = s1 + "m\u1ED9t";
                        break;
                    case '2':
                        s1 = s1 + "hai";
                        break;
                    case '3':
                        s1 = s1 + "ba";
                        break;
                    case '4':
                        s1 = s1 + "b\u1ED1n";
                        break;
                    case '5':
                        s1 = s1 + "n\u0103m";
                        break;
                    case '6':
                        s1 = s1 + "s\u00E1u";
                        break;
                    case '7':
                        s1 = s1 + "b\u1EA3y";
                        break;
                    case '8':
                        s1 = s1 + "t\u00E1m";
                        break;
                    case '9':
                        s1 = s1 + "ch\u00EDn";
                        break;
                }
                switch (m.length() - i) {
                    case 2:
                        s1 = s1 + " m\u01B0\u01A1i ";
                        break;
                    case 5:
                        s1 = s1 + " m\u01B0\u01A1i ";
                        break;
                    case 8:
                        s1 = s1 + " m\u01B0\u01A1i ";
                        break;
                    case 11:
                        s1 = s1 + " m\u01B0\u01A1i ";
                        break;

                    case 3:
                        s1 = s1 + " tr\u0103m ";
                        break;
                    case 6:
                        s1 = s1 + " tr\u0103m ";
                        break;
                    case 9:
                        s1 = s1 + " tr\u0103m ";
                        break;
                    case 12:
                        s1 = s1 + " tr\u0103m ";
                        break;

                    case 4:
                        s1 = s1 + " ng\u00E0n ";
                        break;
                    case 7:
                        s1 = s1 + " tri\u1EC7u, ";
                        break;
                    case 10:
                        s1 = s1 + " t\u1EF7, ";
                        break;

                }
            }
            s = s1.trim();

            s = s.replaceAll(" kh\u00F4ng m\u01B0\u01A1i kh\u00F4ng", "");
            s = s.replaceAll(" kh\u00F4ng tr\u0103m", "");
            s = s.replaceAll("kh\u00F4ng ng\u00E0n", "ng\u00E0n");
            s = s.replaceAll(" kh\u00F4ngkh\u00F4ngkh\u00F4ng", "");
            s = s.replaceAll(", tri\u1EC7u", "");
            s = s.replaceAll(", ng\u00E0n", "");
            s = s.replaceAll("m\u01B0\u01A1i m\u1ED9t", "m\u01B0\u01A1i m\u1ED1t");
            s = s.replaceAll("kh\u00F4ng m\u01B0\u01A1i", "l\u1EBB");
            s = s.replaceAll("m\u1ED9t m\u01B0\u01A1i", "m\u01B0\u1EDDi");
            s = s.replaceAll("m\u01B0\u1EDDi m\u1ED1t", "m\u01B0\u1EDDi m\u1ED9t");
            s = s.replaceAll("m\u01B0\u1EDDi n\u0103m", "m\u01B0\u1EDDi l\u0103m");
            s = s.replaceAll("m\u01B0\u01A1i n\u0103m", "m\u01B0\u01A1i l\u0103m");
            s = s.replaceAll(" kh\u00F4ngkh\u00F4ng", "");
            s = s.replaceAll("Kh\u00F4ngkh\u00F4ng", "Kh\u00F4ng");
            s = s.replaceAll("kh\u00F4ng tri\u1EC7u", "tri\u1EC7u");
            s = s.replaceAll("kh\u00F4ng t\u1EF7", "t\u1EF7");
            s = s.replaceAll("kh\u00F4ng", "m\u01B0\u01A1i");
            if (s.equals(" m\u01B0\u01A1i")) {
                s = "Kh\u00F4ng";
            }
            if (s.equals("m\u01B0\u01A1i")) {
                s = "Kh\u00F4ng";
            }
            s = s.replaceAll("m\u01B0\u1EDDi m\u01B0\u01A1i", "m\u01B0\u1EDDi");
            s = s.replaceAll("m\u01B0\u01A1i m\u01B0\u01A1i", "m\u01B0\u01A1i");
            s = s.replaceAll("l\u1EBB m\u1ED1t", "l\u1EBB m\u1ED9t");

            if (currency.startsWith("VN")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u1ED3ng";
            }
            if (currency.equals("USD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la M\u1EF9";
            }
            if (currency.equals("AUD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la \u00DAc";
            }
            if (currency.equals("CAD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la Canada";
            }
            if (currency.equals("CHF")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " SWISS FRANCE";
            }
            if (currency.equals("DKK")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " DANISH KRONE";
            }
            if (currency.equals("EUR")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u1ED3ng Euro";
            }
            if (currency.equals("GBP")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " BRITISH POUND";
            }
            if (currency.equals("HKD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la H\u1ED3ng K\u00F4ng";
            }
            if (currency.equals("INR")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " INDIAN RUPEE";
            }
            if (currency.equals("JPY")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " Y\u00EAn Nh\u1EADt";
            }
            if (currency.equals("KRW")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " SOUTH KOREAN WON	";
            }
            if (currency.equals("KWD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " KUWAITI DINAR";
            }
            if (currency.equals("MYR")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " MALAYSIAN RINGGIT";
            }
            if (currency.equals("NOK")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " NORWEGIAN KRONER";
            }
            if (currency.equals("RUB")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " RUSSIAN RUBLE";
            }
            if (currency.equals("SEK")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " SWEDISH KRONA";
            }
            if (currency.equals("SGD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la Singapore";
            }
            if (currency.equals("THB")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " THAI BAHT";
            }

            if (m2 > 0) {
                s = s + " và " + textMoneyUSD(m2, currency);
            }

            if (s.equals("m\u01B0\u01A1i")) {
                s = "kh\u00F4ng";
            }
        } catch (Exception ex) {
            s = "";
        }

        return s;
		*/
		
		String s = "";
    if (money.doubleValue() < 0.0D) {
      money = Double.valueOf(-money.doubleValue());
    }
    try
    {
      String s1 = "";
      
      long l = money.longValue();
      int m2 = 0;
      if (!currency.startsWith("VN"))
      {
        Double temp = Double.valueOf(money.doubleValue() * 100.0D);
        temp = Double.valueOf(Double.parseDouble(formatNumber(temp.doubleValue(), "###0.00")));
        m2 = (int)(temp.doubleValue() % 100.0D);
      }
      else
      {
        int t = (int)(money.doubleValue() * 100.0D % 100.0D);
        if (t > 50) {
          l += 1L;
        }
      }
      String m = String.valueOf(l);
      for (int i = 0; i < m.length(); i++)
      {
        switch (m.charAt(i))
        {
        case '0': 
          s1 = s1 + "kh\u00F4ng";
          break;
        case '1': 
          s1 = s1 + "m\u1ED9t";
          break;
        case '2': 
          s1 = s1 + "hai";
          break;
        case '3': 
          s1 = s1 + "ba";
          break;
        case '4': 
          s1 = s1 + "b\u1ED1n";
          break;
        case '5': 
          s1 = s1 + "n\u0103m";
          break;
        case '6': 
          s1 = s1 + "s\u00E1u";
          break;
        case '7': 
          s1 = s1 + "b\u1EA3y";
          break;
        case '8': 
          s1 = s1 + "t\u00E1m";
          break;
        case '9': 
          s1 = s1 + "ch\u00EDn";
        }
        switch (m.length() - i)
        {
        case 2: 
          s1 = s1 + " m\u01B0\u01A1i ";
          break;
        case 5: 
          s1 = s1 + " m\u01B0\u01A1i ";
          break;
        case 8: 
          s1 = s1 + " m\u01B0\u01A1i ";
          break;
        case 11: 
          s1 = s1 + " m\u01B0\u01A1i ";
          break;
        case 3: 
          s1 = s1 + " tr\u0103m ";
          break;
        case 6: 
          s1 = s1 + " tr\u0103m ";
          break;
        case 9: 
          s1 = s1 + " tr\u0103m ";
          break;
        case 12: 
          s1 = s1 + " tr\u0103m ";
          break;
        case 4: 
          s1 = s1 + " ng\u00E0n ";
          break;
        case 7: 
          s1 = s1 + " tri\u1EC7u, ";
          break;
        case 10: 
          s1 = s1 + " t\u1EF7, ";
        }
      }
      s = s1.trim();
      
      s = s.replaceAll(" kh\u00F4ng m\u01B0\u01A1i kh\u00F4ng", "");
      s = s.replaceAll(" kh\u00F4ng tr\u0103m", "");
      s = s.replaceAll("kh\u00F4ng ng\u00E0n", "ng\u00E0n");
      s = s.replaceAll(" kh\u00F4ngkh\u00F4ngkh\u00F4ng", "");
      s = s.replaceAll(", tri\u1EC7u", "");
      s = s.replaceAll(", ng\u00E0n", "");
      s = s.replaceAll("m\u01B0\u01A1i m\u1ED9t", "m\u01B0\u01A1i m\u1ED1t");
      s = s.replaceAll("kh\u00F4ng m\u01B0\u01A1i", "l\u1EBB");
            s = s.replaceAll("m\u1ED9t m\u01B0\u01A1i", "m\u01B0\u1EDDi");
            s = s.replaceAll("m\u01B0\u1EDDi m\u1ED1t", "m\u01B0\u1EDDi m\u1ED9t");
            s = s.replaceAll("m\u01B0\u1EDDi n\u0103m", "m\u01B0\u1EDDi l\u0103m");
            s = s.replaceAll("m\u01B0\u01A1i n\u0103m", "m\u01B0\u01A1i l\u0103m");
            s = s.replaceAll(" kh\u00F4ngkh\u00F4ng", "");
            s = s.replaceAll("Kh\u00F4ngkh\u00F4ng", "Kh\u00F4ng");
            s = s.replaceAll("kh\u00F4ng tri\u1EC7u", "tri\u1EC7u");
            s = s.replaceAll("kh\u00F4ng t\u1EF7", "t\u1EF7");
            s = s.replaceAll("kh\u00F4ng", "m\u01B0\u01A1i");
            if (s.equals(" m\u01B0\u01A1i")) {
                s = "Kh\u00F4ng";
            }
            if (s.equals("m\u01B0\u01A1i")) {
                s = "Kh\u00F4ng";
            }
            s = s.replaceAll("m\u01B0\u1EDDi m\u01B0\u01A1i", "m\u01B0\u1EDDi");
            s = s.replaceAll("m\u01B0\u01A1i m\u01B0\u01A1i", "m\u01B0\u01A1i");
            s = s.replaceAll("l\u1EBB m\u1ED1t", "l\u1EBB m\u1ED9t");
      if (currency.startsWith("VN")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u1ED3ng";
            }
            if (currency.equals("USD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la M\u1EF9";
            }
            if (currency.equals("AUD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la \u00DAc";
            }
            if (currency.equals("CAD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la Canada";
            }
            if (currency.equals("CHF")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " SWISS FRANCE";
            }
            if (currency.equals("DKK")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " DANISH KRONE";
            }
            if (currency.equals("EUR")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u1ED3ng Euro";
            }
            if (currency.equals("GBP")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " BRITISH POUND";
            }
            if (currency.equals("HKD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la H\u1ED3ng K\u00F4ng";
            }
            if (currency.equals("INR")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " INDIAN RUPEE";
            }
            if (currency.equals("JPY")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " Y\u00EAn Nh\u1EADt";
            }
            if (currency.equals("KRW")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " SOUTH KOREAN WON	";
            }
            if (currency.equals("KWD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " KUWAITI DINAR";
            }
            if (currency.equals("MYR")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " MALAYSIAN RINGGIT";
            }
            if (currency.equals("NOK")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " NORWEGIAN KRONER";
            }
            if (currency.equals("RUB")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " RUSSIAN RUBLE";
            }
            if (currency.equals("SEK")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " SWEDISH KRONA";
            }
            if (currency.equals("SGD")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la Singapore";
            }
            if (currency.equals("THB")) {
                s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " THAI BAHT";
            }

            if (m2 > 0) {
                s = s + " và " + textMoneyUSD(m2, currency);
            }

            if (s.equals("m\u01B0\u01A1i")) {
                s = "kh\u00F4ng";
            }
    }
    catch (Exception ex)
    {
      s = "";
    }
    return s;
    }

    public static String textMoneyUSD(int money, String currency) {
        String s = "", s1 = "", m = NumberUtil.formatNumberDefault(money).replaceAll(",", "");
        for (int i = 0; i < m.length(); i++) {
            switch (m.charAt(i)) {
                case '0':
                    s1 = s1 + "kh\u00F4ng";
                    break;
                case '1':
                    s1 = s1 + "m\u1ED9t";
                    break;
                case '2':
                    s1 = s1 + "hai";
                    break;
                case '3':
                    s1 = s1 + "ba";
                    break;
                case '4':
                    s1 = s1 + "b\u1ED1n";
                    break;
                case '5':
                    s1 = s1 + "n\u0103m";
                    break;
                case '6':
                    s1 = s1 + "s\u00E1u";
                    break;
                case '7':
                    s1 = s1 + "b\u1EA3y";
                    break;
                case '8':
                    s1 = s1 + "t\u00E1m";
                    break;
                case '9':
                    s1 = s1 + "ch\u00EDn";
                    break;
            }
            switch (m.length() - i) {
                case 2:
                    s1 = s1 + " m\u01B0\u01A1i ";
                    break;
                case 5:
                    s1 = s1 + " m\u01B0\u01A1i ";
                    break;
                case 8:
                    s1 = s1 + " m\u01B0\u01A1i ";
                    break;
                case 11:
                    s1 = s1 + " m\u01B0\u01A1i ";
                    break;

                case 3:
                    s1 = s1 + " tr\u0103m ";
                    break;
                case 6:
                    s1 = s1 + " tr\u0103m ";
                    break;
                case 9:
                    s1 = s1 + " tr\u0103m ";
                    break;
                case 12:
                    s1 = s1 + " tr\u0103m ";
                    break;

                case 4:
                    s1 = s1 + " ng\u00E0n ";
                    break;
                case 7:
                    s1 = s1 + " tri\u1EC7u, ";
                    break;
                case 10:
                    s1 = s1 + " t\u1EF7, ";
                    break;

            }
        }
        s = s1.trim();

        s = s.replaceAll(" kh\u00F4ng m\u01B0\u01A1i kh\u00F4ng", "");
        s = s.replaceAll(" kh\u00F4ng tr\u0103m", "");
        s = s.replaceAll("kh\u00F4ng ng\u00E0n", "ng\u00E0n");
        s = s.replaceAll(" kh\u00F4ngkh\u00F4ngkh\u00F4ng", "");
        s = s.replaceAll(", tri\u1EC7u", "");
        s = s.replaceAll(", ng\u00E0n", "");
        s = s.replaceAll("m\u01B0\u01A1i m\u1ED9t", "m\u01B0\u01A1i m\u1ED1t");
        s = s.replaceAll("kh\u00F4ng m\u01B0\u01A1i", "l\u1EBB");
        s = s.replaceAll("m\u1ED9t m\u01B0\u01A1i", "m\u01B0\u1EDDi");
        s = s.replaceAll("m\u01B0\u1EDDi m\u1ED1t", "m\u01B0\u1EDDi m\u1ED9t");
        s = s.replaceAll("m\u01B0\u1EDDi n\u0103m", "m\u01B0\u1EDDi l\u0103m");
        s = s.replaceAll("m\u01B0\u01A1i n\u0103m", "m\u01B0\u01A1i l\u0103m");
        s = s.replaceAll(" kh\u00F4ngkh\u00F4ng", "");
        s = s.replaceAll("Kh\u00F4ngkh\u00F4ng", "Kh\u00F4ng");
        s = s.replaceAll("kh\u00F4ng tri\u1EC7u", "tri\u1EC7u");
        s = s.replaceAll("kh\u00F4ng t\u1EF7", "t\u1EF7");
        s = s.replaceAll("kh\u00F4ng", "m\u01B0\u01A1i");
        if (s.equals(" m\u01B0\u01A1i")) {
            s = "Kh\u00F4ng";
        }
        s = s.replaceAll("m\u01B0\u1EDDi m\u01B0\u01A1i", "m\u01B0\u1EDDi");
        s = s.replaceAll("m\u01B0\u01A1i m\u01B0\u01A1i", "m\u01B0\u01A1i");
        /*if (currency.startsWith("VN")) {
         s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u1ED3ng ch\u1EB3n";
         }
         if (currency.equals("USD")) {
         s = String.valueOf(s.charAt(0)).toUpperCase() + s.substring(1) + " \u0111\u00F4 la M\u1EF9 ch\u1EB3n";
         }
         */
        if (currency.startsWith("VN")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("USD")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("AUD")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("CAD")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("CHF")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("DKK")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("EUR")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("GBP")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("HKD")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("INR")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("JPY")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("KRW")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("KWD")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("MYR")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("NOK")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("RUB")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("SEK")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("SGD")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }
        if (currency.equals("THB")) {
            s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1) + " xu";
        }

        return s;
    }
}
