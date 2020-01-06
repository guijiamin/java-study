package com.study.tinytomcat;

import java.util.ArrayList;
import java.util.List;

/**
 * Decription
 * <p>
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
public class ServletMappingConfig {
    public static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();

    static {
        servletMappingList.add(new ServletMapping("helloworld","/helloworld", "com.study.tinytomcat.HelloWorldServlet"));
    }
}
