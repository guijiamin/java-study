package com.study.designpattern.structural.decorator;

/**
 * Decription
 * <p>
 *     装饰者模式：在原有基础上，添加新功能
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

class NewOrderApiImpl implements IOrderApi {
    private OrderApiImpl orderApi;

    public NewOrderApiImpl() {
        this.orderApi = new OrderApiImpl();
    }

    public void updateDate(String orderId, String date, String client) {
        //调用老方法修改退款期
        this.orderApi.updateDate(orderId, date, client);
        System.out.println("client已将订单：" + orderId + "有效期延长至：" + date);
    }
}
