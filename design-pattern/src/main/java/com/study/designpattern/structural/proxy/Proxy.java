package com.study.designpattern.structural.proxy;

/**
 * Decription
 * <p>
 *     代理模式：
 * </p>
 * DATE 19/1/18.
 *
 * @author guijiamin.
 */
interface IOrderApi {
    void updateDate(String orderId, String date, String client);
}

class OrderApiImpl implements IOrderApi {
    public void updateDate(String orderId, String date, String client) {
        System.out.println("client已将订单：" + orderId + "退款期延长至：" + date);
    }
}
class ProxyOrderApiImpl implements IOrderApi {
    private IOrderApi orderApi;

    public ProxyOrderApiImpl() {
        this.orderApi = new OrderApiImpl();
    }

    public void updateDate(String orderId, String date, String client) {
        if (client.equals("admin")) {
            this.orderApi.updateDate(orderId, date, client);
        } else {
            System.out.println("权限不够");
        }
    }
}

