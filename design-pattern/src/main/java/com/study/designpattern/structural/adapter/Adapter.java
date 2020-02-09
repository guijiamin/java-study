package com.study.designpattern.structural.adapter;

/**
 * Decription
 * <p>
 *     适配器模式：在原有基础上新建一个接口/类兼容新需求，内部其实调用老接口
 * </p>
 * DATE 19/1/18.
 *
 * @author guijiamin.
 */
interface IOrderApi {
    void updateDate(String orderId, String date, String client);
}

class OrderApiImpl implements IOrderApi {
    @Override
    public void updateDate(String orderId, String date, String client) {
        System.out.println("client已将订单：" + orderId + "退款期延长至：" + date);
    }
}

interface IAppOrderApi {
    void updateDate(String orderId, String client);
}

class AppOrderApiImpl implements IAppOrderApi {
    private IOrderApi appOrderApi;

    public AppOrderApiImpl() {
        this.appOrderApi = new OrderApiImpl();
    }

    @Override
    public void updateDate(String orderId, String client) {
        this.appOrderApi.updateDate(orderId, "9999-12-31", client);
    }
}
