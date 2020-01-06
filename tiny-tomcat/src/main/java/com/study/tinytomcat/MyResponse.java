package com.study.tinytomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
public class MyResponse {
    private OutputStream outputStream;

    public MyResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
//        HTTP响应头
//        HTTP/1.1 200 OK
//        Content-Type: text/html
//
//        <html>...</html>
        StringBuffer httpResponse = new StringBuffer();
        httpResponse.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html\n")
                .append("\r\n")
                .append("<html><body>")
                .append(content)
                .append("</body></html>");
        outputStream.write(httpResponse.toString().getBytes());
        System.out.println(httpResponse.toString());
        outputStream.close();
    }
}
