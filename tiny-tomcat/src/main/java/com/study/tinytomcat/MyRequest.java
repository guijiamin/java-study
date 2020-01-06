package com.study.tinytomcat;

import java.io.IOException;
import java.io.InputStream;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
public class MyRequest {
    private String url;
    private String method;

    public MyRequest(InputStream inputStream) throws IOException {
        String httpRequest = "";
        byte[] httpRequestBytes = new byte[1024];

        int length = 0;
        if ((length = inputStream.read(httpRequestBytes)) > 0) {
            httpRequest = new String(httpRequestBytes, 0, length);
        }

//        HTTP请求头
//        GET /favicon.icon HTTP/1.1
//        Accept: */*
//        Accept-Encoding: gzip, deflate
//        User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36
//        Host: www.baidu.com
//        Connection: keep-alive
        String httpHead = httpRequest.split("\n")[0];
        this.url = httpHead.split("\\s")[1];
        this.method = httpHead.split("\\s")[0];
        System.out.println(this.url + "/" + this.method);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
