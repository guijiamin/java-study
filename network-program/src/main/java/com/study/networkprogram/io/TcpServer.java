package com.study.networkprogram.io;

import com.study.networkprogram.proto.PersonProto;
import com.study.networkprogram.util.SocketExpirationListener;
import com.study.networkprogram.util.TimeWheel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2019/3/23.
 *
 * @author guijiamin.
 */
public class TcpServer {
    private final static int DEFAULT_PORT = 5555;

    private int port;
    private ServerSocket server;
    private boolean runFlag;
    private Map<String, Socket> clients = new ConcurrentHashMap<String, Socket>();
    private TimeWheel<String, Socket> timeWheel;

    public TcpServer() {
        this(DEFAULT_PORT);
    }

    public TcpServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            server = new ServerSocket(this.port);
            this.runFlag = true;
            System.out.println("tcpserver is started");
        } catch (IOException e) {
            e.printStackTrace();
            this.runFlag = false;
        }

        //使用时间轮管理客户端心跳
//        if (this.runFlag) {
//            this.timeWheel = new TimeWheel<String, Socket>(1, 60, TimeUnit.SECONDS);
//            this.timeWheel.addExpirationListener(new SocketExpirationListener<Socket>());
//            this.timeWheel.start();
//        }

        while (this.runFlag) {
            try {
                final Socket client = server.accept();
                //维护一个客户端连接列表
                this.clients.put(client.getInetAddress().getHostAddress(), client);
                //加入到时间轮里
//                this.timeWheel.add(client.getInetAddress().getHostAddress(), client);
                //针对每一个接收的client，开启一个线程处理收发消息
                SocketTransceiver socketTransceiver = new SocketTransceiver(client) {
                    @Override
                    public void onHeartBeat() {
                        //收到心跳消息，需要更新时间轮
//                        TcpServer.this.timeWheel.add(this.getIp(), this.getSocket());
                    }
                };
                new Thread(socketTransceiver).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.runFlag = false;
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("tcpserver is stopped");
        System.exit(-1);
    }

    private abstract class SocketTransceiver implements Runnable {
        private Socket socket;
        private String ip;
        private DataInputStream in;
        private DataOutputStream out;
        private boolean runFlag;

        public String getIp() {
            return ip;
        }

        public Socket getSocket() {
            return socket;
        }

        public SocketTransceiver(Socket socket) {
            this.socket = socket;
            this.ip = socket.getInetAddress().getHostAddress();
            try {
                this.in = new DataInputStream(socket.getInputStream());
                this.out = new DataOutputStream(socket.getOutputStream());
                this.runFlag = true;
            } catch (IOException e) {
                e.printStackTrace();
                this.runFlag = false;
            }

        }

        public boolean send(String msg) {
            if (this.out != null) {
                try {
                    this.out.writeUTF(msg);
                    this.out.flush();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        public void run() {
            while (this.runFlag) {
                try {
//                    final String msg = this.in.readUTF();
//                    if (msg.contains("heartbeat")) {
//                        this.onHeartBeat();
//                        this.send("reply heartbeat");
//                    }
                    doRead(this.in);
                } catch (IOException e) {
                    e.printStackTrace();
                    this.runFlag = false;
                }
            }

            try {
                this.in.close();
                this.out.close();
                this.socket.close();
                this.in = null;
                this.out = null;
                this.socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public abstract void onHeartBeat();
    }

    public static void main(String[] args) {
        new TcpServer(5678).start();
    }

    public void doRead(DataInputStream in) throws IOException {
        System.out.println("=====开始读取一个包=====");
        //找到魔数
        int index = 0;
        if (findMagic(in.read(), index++)
                && findMagic(in.read(), index++)
                && findMagic(in.read(), index++)
                && findMagic(in.read(), index)) {
            //读取长度
            byte[] lenBuffer = new byte[4];
            for (int i = 0; i < 4; i++) {
                lenBuffer[i] = (byte)in.read();
            }
            int length = byteArrayToInt(lenBuffer);
            System.out.println("包长度：" + length);
            //读取指定长度字节
            byte[] msgBuffer = new byte[length];
            for (int i = 0; i < length; i++) {
                msgBuffer[i] = (byte)in.read();
            }
            PersonProto.Person parse = PersonProto.Person.parseFrom(msgBuffer);
            System.out.println(parse.toString());
        } else {
            System.out.println("错误包");
        }
        System.out.println("=====结束读取一个包=====");
    }

    public static boolean findMagic(int b, int index) {
        if (b == new byte[]{0xc,0xa,0xf,0xe}[index]) {
            return true;
        }
        return false;
    }

    public static int byteArrayToInt(byte[] bytes) {
        int b0 = bytes[0] & 0xFF;
        int b1 = bytes[1] & 0xFF;
        int b2 = bytes[2] & 0xFF;
        int b3 = bytes[3] & 0xFF;
//        System.out.println("int: " + b0);
//        System.out.println("int: " + b1);
//        System.out.println("int: " + b2);
//        System.out.println("int: " + b3);
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    public static int byteArrayToShort(byte[] bytes) {
        int b0 = bytes[0] & 0xFF;
        int b1 = bytes[1] & 0xFF;
        System.out.println("short: " + b0);
        System.out.println("short: " + b1);
        return (b1 << 8) | b0;
    }
}
