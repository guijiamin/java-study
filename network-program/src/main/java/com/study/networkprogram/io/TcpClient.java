package com.study.networkprogram.io;

import com.study.networkprogram.proto.PersonProto;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2019/3/23.
 *
 * @author guijiamin.
 */
public class TcpClient {
    private final static String DEFAULT_SERVERIP = "127.0.0.1";
    private final static int DEFAULT_PORT = 5555;

    private String serverIp;
    private int port;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private boolean runFlag;

    public TcpClient() {
        this(DEFAULT_SERVERIP, DEFAULT_PORT);
    }

    public TcpClient(String serverIp, int port) {
        this.serverIp = serverIp;
        this.port = port;
    }

    public void start() {
        try {
            this.socket = new Socket(this.serverIp, this.port);
            this.in = new DataInputStream(this.socket.getInputStream());
            this.out = new DataOutputStream(this.socket.getOutputStream());

            this.runFlag = true;
            System.out.println("tcpclient is started");
        } catch (IOException e) {
            e.printStackTrace();
            this.runFlag = false;
        }
        //开启线程监听输入流
        new Thread(new ReceiveMsgWatchDog()).start();
        //开启线程发送心跳包
        new Thread(new KeepAliveWatchDog()).start();
    }

    public void stop() {
        this.runFlag = false;
        try {
            this.in.close();
            this.out.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("tcpclient is stopped");
        System.exit(-1);
    }

    private class ReceiveMsgWatchDog implements Runnable {
        public void run() {
            while (TcpClient.this.runFlag) {
                try {
//                    String msg = TcpClient.this.in.readUTF();
//                    System.out.println("receive msg: " + msg);
                    TcpClient.this.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class KeepAliveWatchDog implements Runnable {
        public void run() {
            while (TcpClient.this.runFlag) {
                try {
                    Thread.sleep(1 * 1000);
//                    TcpClient.this.out.writeUTF("heartbeat");
                    PersonProto.Person.Builder msg = PersonProto.Person.newBuilder();
                    msg.setName("lisisjejijitjeitjijiisjirjeijriej" + Math.random()*100);
                    msg.setAge(10);
                    msg.setBirth(123456789);
                    msg.setSex(true);

                    //1、写入魔数
                    TcpClient.this.out.write(new byte[]{0xc,0xa,0xf,0xe});

                    //2、写入正文长度和正文字节
                    byte[] msgBytes = msg.build().toByteArray();
                    byte[] lenBytes = intToByteArray(msgBytes.length);
                    TcpClient.this.out.write(lenBytes);
                    TcpClient.this.out.write(msgBytes);
                    System.out.println("send heartbeat to server");
                    for (int i = 0; i < Math.random()*10; i++) {
                        TcpClient.this.out.write(i);
                    }
                } catch (InterruptedException|IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static byte[] intToByteArray(int i) {
        byte[] targets = new byte[4];
        targets[3] = (byte) (i);
        targets[2] = (byte) (i >> 8);
        targets[1] = (byte) (i >> 16);
        targets[0] = (byte) (i >> 24);
        for (byte b : targets) {
            System.out.println("byte: " + (b));
        }
        return targets;
    }

    public static void main(String[] args) {
        new TcpClient("127.0.0.1", 5678).start();
    }
}
