package cn.aezo.designpattern.c07_adapter.t4_input_stream_reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author smalle
 * @date 2020-06-14 22:42
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // FileInputStream默认只能一个字节一个字节的读，此时通过InputStreamReader适配，最后可使用BufferedReader进行一行一行的读
        FileInputStream fis = new FileInputStream("c:/test.text");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String line = br.readLine();
        while (line != null && !line.equals("")) {
            System.out.println(line);
        }
        br.close();
    }
}
