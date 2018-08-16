package com.boot.commons.utils;


import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import static org.apache.commons.io.FileUtils.readLines;

public class IoUtils {

    public static void main(String[] args) throws  Exception{

        //标准代码：
        /*InputStream in = new URL( "http://jakarta.apache.org" ).openStream();
        try {
            InputStreamReader inR = new InputStreamReader( in );
            BufferedReader buf = new BufferedReader( inR );
            String line;
            while ( ( line = buf.readLine() ) != null ) {
                System.out.println( line );
            }
        } finally {
            in.close();
        }*/

        InputStream in = new URL( "https://blog.csdn.net/hu199055/article/details/71908295" ).openStream();
        try {
            //System.out.println( IOUtils.toString(in) );
        } finally {
            IOUtils.closeQuietly(in);
        }

        //2．读取文件
        File file = new File("D:\\usergui.properties");
        List lines = readLines(file, "UTF-8");
        for (int i=0;i<lines.size();i++){
            System.out.println(lines.get(i));
        }
        //3．察看剩余空间
        long freeSpace = FileSystemUtils.freeSpace("C:/");

    }
}
