package ShippingHub;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Validation {
    public static  boolean isDuplicated(String field){
    Pattern pat= Pattern.compile("\\d+(\\.\\d+)?");
    Matcher mat = pat.matcher(field);
    return mat.matches();
}
    public static boolean isPhone(String field){
     Pattern pat= Pattern.compile("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}");
     Matcher mat = pat.matcher(field);
     return mat.matches();
    }
    public static boolean isEmail(String field){
        Pattern pat= Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
         Matcher mat = pat.matcher(field);
         return mat.matches();
    }
    public static boolean isNumber(String field){
         Pattern pat = Pattern.compile("[0-9]+");
         Matcher mat = pat.matcher(field);
         return mat.matches();
    }
    public static boolean isLetter(String field){
        Pattern pat = Pattern.compile("[a-zA-Z]+");
        Matcher mat = pat.matcher(field);
        return mat.matches();
    }
    public static boolean isName(String field){
        Pattern pat = Pattern.compile("^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}");
        Matcher mat = pat.matcher(field);
        return mat.matches();
    }
    public static boolean isZip(String field){
    Pattern pat = Pattern.compile("^[0-9]{5}(?:-[0-9]{4})?$");
    Matcher mat = pat.matcher(field);
    return mat.matches();
  }
}
