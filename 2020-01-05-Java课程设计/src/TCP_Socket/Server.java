package TCP_Socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.*;

public class Server {
    private static class TalkRunnable implements Runnable {
        //建立一个客户端Socket，以此来获取ip和port
        private Socket clientSocket;

        TalkRunnable(Socket socket){
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                Scanner scanner = new Scanner(System.in);
                //获取主机ip，并返回InetAddress
                InetAddress clientAddress = clientSocket.getInetAddress();
                //获取主机端口号
                int clientPort = clientSocket.getPort();
                //ip+port 标识网络上的唯一一台设备的唯一进程
                System.out.printf("有客户端链接上来%s:%d%n",
                        clientAddress.getHostAddress(),clientPort);
                //获取输入字节流
                InputStream is = null;
                is = clientSocket.getInputStream();
                //字节流转换为字符流
                InputStreamReader isReader = null;
                isReader = new InputStreamReader(is,"UTF-8");
                //字符流转换为缓冲字符流
                BufferedReader reader = new BufferedReader(isReader);

                //获取输出字节流
                OutputStream os = clientSocket.getOutputStream();
                PrintStream out = new PrintStream(os,true,"UTF-8");

                //等待客户端发送消息并回应消息
                while (true){
                    //读一行
                    String line = reader.readLine();
                    System.out.println("好友说：" + line);
                    System.out.println("请回复>");
                    //回复一行
                    String response = scanner.nextLine();
                    //输出
                    out.println(response);
                    //刷新
                    out.flush();
                }
                //捕获异常
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        //ServerSocket服务器端的socket的接口，
        // 是建立网络连接时使用的，完成所需会话，用于监听8080端口
        ServerSocket tcpServerSocket = new ServerSocket(80);
        //建立阻塞队列，将线程存放进去
        // LinkedBlockingQueue为大小不定的BlockingQueue
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        //建立线程池
        ExecutorService pool = new ThreadPoolExecutor(
                1,       //线程池基本大小
                1,  //线程池中允许的最大线程数
                0,//当阻塞队列中没有任务时，等
                // 待时间达到keepAliveTime毫秒值时就会被自动唤醒，而不会永远地沉睡下去
                TimeUnit.MILLISECONDS,  //线程等待时间
                queue  //队列
                //若线程数满则进入阻塞队列
        );
        while (true){
            //接收客户端的连接请求，并返回一个套接字
            //如果没有连接到客户端，线程处于阻塞状态，程序无法执行下去
            Socket clientSocket = tcpServerSocket.accept();
            //线程池提交任务运行
            pool.execute(new TalkRunnable(clientSocket));
        }
    }
}

