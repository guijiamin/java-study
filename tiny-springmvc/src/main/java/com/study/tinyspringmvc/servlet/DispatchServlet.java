package com.study.tinyspringmvc.servlet;

import com.study.tinyspringmvc.UserController;
import com.study.tinyspringmvc.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Decription
 * <p>
 *     https://www.jianshu.com/p/f454662f497e
 * </p>
 * DATE 19/1/9.
 *
 * @author guijiamin.
 */
@WebServlet(name = "dispatcherServlet", urlPatterns = "/*", loadOnStartup = 1, initParams = {@WebInitParam(name = "base-package", value = "com.study.tinyspringmvc")})
public class DispatchServlet extends HttpServlet {
    //基包名称
    private String basePackage = "com.study.tinyspringmvc";
    //基包下边所有类的全限定名
    private List<String> packageNames = new ArrayList<String>();
    //注解实例化 annotation_value:annotation_object
    private Map<String, Object> instanceMap = new HashMap<String, Object>();
    //包路径全限定名称:注解值
    private Map<String, String> packageNameMap = new HashMap<String, String>();
    //url:method
    private Map<String, Method> urlMethodMap = new HashMap<String, Method>();
    //method:package_name
    private Map<Method, String> methodPackageNameMap = new HashMap<Method, String>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        basePackage = config.getInitParameter("base-package");

        try {
            //扫描基包下的所有文件，写入到packageNames里
            scanBasePackage(basePackage);
            //实例化注解，保存注解值：实例到instanceMap里，保存全限定名：注解值到packageNameMap里
            instance(packageNames);
            //注入
            springIOC();
            //url映射方法注册
            handleUrlMethodMap();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void scanBasePackage(String basePackage) {
        //将包名中的"."替换成路径"/"
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", "/"));
        File basePackageFile = new File(url.getPath());
        File[] childFiles = basePackageFile.listFiles();
        for (File file : childFiles) {
            if (file.isDirectory()) {
                scanBasePackage(basePackage + "." + file.getName());
            } else if (file.isFile()) {
                //去掉尾部的".class"
                packageNames.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }
    }

    private void instance(List<String> packageNames) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (packageNames.size() < 1) {
            return;
        }

        for (String name : packageNames) {
            Class c = Class.forName(name);
            //只实例化使用了Controller,Service,Reposity标注的类
            if (c.isAnnotationPresent(Controller.class)) {
                Controller controller = (Controller) c.getAnnotation(Controller.class);
                String controllerName = controller.value();//配置的路径名

                instanceMap.put(controllerName, c.newInstance());
                packageNameMap.put(name, controllerName);
                System.out.println("className: " + name + ", controllerName: " + controllerName);
            } else if (c.isAnnotationPresent(Service.class)) {
                Service service = (Service) c.getAnnotation(Service.class);
                String serviceName = service.value();

                instanceMap.put(serviceName, c.newInstance());
                packageNameMap.put(name, serviceName);
                System.out.println("className: " + name + ", serviceName: " + serviceName);
            } else if (c.isAnnotationPresent(Reposity.class)) {
                Reposity reposity = (Reposity) c.getAnnotation(Reposity.class);
                String reposityName = reposity.value();

                instanceMap.put(reposityName, c.newInstance());
                packageNameMap.put(name, reposityName);
                System.out.println("className: " + name + ", reposityName: " + reposityName);
            }
        }
    }

    private void springIOC() throws ClassNotFoundException, IllegalAccessException {
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {
            //找到被注解标注的类的所有字段
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    String name = field.getAnnotation(Qualifier.class).value();
                    field.setAccessible(true);
                    field.set(entry.getValue(), instanceMap.get(name));
                }
            }
        }
    }

    private void handleUrlMethodMap() throws ClassNotFoundException {
        if (packageNames.size() < 1) {
            return;
        }

        for (String name : packageNames) {
            Class c = Class.forName(name);
            if (c.isAnnotationPresent(Controller.class)) {
                Method[] methods = c.getMethods();
                StringBuffer baseUrl = new StringBuffer();
                if (c.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = (RequestMapping)c.getAnnotation(RequestMapping.class);
                    baseUrl.append(requestMapping.value());
                }

                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                        baseUrl.append(requestMapping.value());

                        urlMethodMap.put(baseUrl.toString(), method);
                        methodPackageNameMap.put(method, name);
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.replaceAll(contextPath, "");

        //通过path找到method
        Method method = urlMethodMap.get(path);
        if (method != null) {
            String packaName = methodPackageNameMap.get(method);
            String controllerName = packageNameMap.get(packaName);

            UserController controller = (UserController) instanceMap.get(controllerName);
            try {
                method.setAccessible(true);
                method.invoke(controller);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
