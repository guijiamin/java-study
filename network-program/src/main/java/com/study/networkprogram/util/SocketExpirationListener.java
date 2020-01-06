package com.study.networkprogram.util;

import java.io.IOException;
import java.net.Socket;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2019/3/18.
 *
 * @author guijiamin.
 */
public class SocketExpirationListener<E> implements ExpirationListener<E> {
    public void expired(E conn) {
        Socket socket = (Socket) conn;
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
