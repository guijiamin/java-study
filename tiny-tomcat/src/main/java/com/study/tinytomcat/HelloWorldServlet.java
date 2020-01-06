package com.study.tinytomcat;

import java.io.IOException;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
public class HelloWorldServlet extends MyServlet {

    public void doGet(MyRequest request, MyResponse response) {
        try {
            response.write("Hello world by Get!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(MyRequest request, MyResponse response) {
        try {
            response.write("Hello world by POST!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
