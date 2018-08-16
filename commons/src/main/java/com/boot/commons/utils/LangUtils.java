package com.boot.commons.utils;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class LangUtils {

    public static void main(String[] args) throws  Exception{

        //testArr();

        //2 截取从from开始字符串
        String str=StringUtils.substringAfter("123.jpg", ".");
        String str2=StringUtils.substringBefore("123.jpg", ".");
        System.out.println(str+"    -   "+str2);

        System.out.println(StringUtils.addUnderlineOfWord("123"));
        System.out.println(StringUtils.getRandomNumberString(6));
        System.out.println(StringUtils.string2Boolean("1"));

        System.out.println(StringEscapeUtils.escapeCsv("王,鹏"));

    }

    private static void testArr() {
        String[] s1 = new String[] { "1", "2", "3" };
        String[] s2 = new String[] { "a", "b", "c" };
        String[] s = (String[]) ArrayUtils.addAll(s1, s2);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
        String str = ArrayUtils.toString(s);
        str = str.substring(1, str.length() - 1);
        System.out.println(str + ">>" + str.length());

    }
}
