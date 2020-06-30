package cn.aezo.netty.c3_io_bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class T2_SocketClient {

    public static void main(String[] args) {

        try {
            Socket client = new Socket("192.168.6.134",9090);

            client.setSendBufferSize(20);
            client.setTcpNoDelay(false);
            // SO_OOBINLINE 是否在一行发送。当开启延迟发送时，本身会在一起发送的；但是此参数为false时，会先发送一个小包，然后后面的一起发送
            client.setOOBInline(false);
            OutputStream out = client.getOutputStream();

            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true) {
                String line = reader.readLine();
                if(line != null ) {
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        // 此处为一个字节一个字节的写数据，且没有调用flush
                        out.write(b);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
