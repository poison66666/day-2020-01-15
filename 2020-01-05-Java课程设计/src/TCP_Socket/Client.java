package TCP_Socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket tcpClientSocket = new Socket();
        byte[] ipv4 = {(byte)192,(byte)168,(byte)43,(byte)140};
        //InetAddress是表示IP地址
        InetAddress serverAddress = InetAddress.getByAddress(ipv4);
        //SocketAddress它提供不可变对象，供套接字用于绑定、连接或用作返回值
        SocketAddress serverSocketAddress = new InetSocketAddress(serverAddress,8080);
        //进行与服务器连接
        tcpClientSocket.connect(serverSocketAddress);

        while (true){
            System.out.println("请输入>");
            //写一行
            String request = scanner.nextLine();
            //通过字节流直接写入请求
            OutputStream os = tcpClientSocket.getOutputStream();
            PrintStream out = new PrintStream(os,true,"UTF-8");
            out.println(request);
            out.flush();
            //通过字节流，直接读取数据
            InputStream is = tcpClientSocket.getInputStream();
            //字符流转换为缓冲字符流
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is,"UTF-8")
            );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("好友说: " + line);
                System.out.print("请回复> ");
                String response = scanner.nextLine();
                out.println(response);
            }
        }
    }
}
