package com.study.network.proto;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;
import com.study.networkprogram.proto.MsgProto;
import com.study.networkprogram.proto.PersonProto;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2019/7/24.
 *
 * @author guijiamin.
 */
public class ProtoTest {
    @Test
    public void testN() throws InvalidProtocolBufferException {
        PersonProto.Person.Builder builder = PersonProto.Person.newBuilder();
        builder.setName("lisi");
        builder.setAge(10);
        builder.setBirth(123456);
        builder.setSex(true);

        PersonProto.Person p1 = builder.build();

        System.out.println("=====Before=====");
        System.out.println(p1);

        System.out.println("=====Bytes=====");
        for(byte b : p1.toByteArray()) {
            System.out.println(b);
        }
        System.out.println("");

        byte[] bytes = p1.toByteArray();
        PersonProto.Person p2 = PersonProto.Person.parseFrom(bytes);
        System.out.println("=====After=====");
        System.out.println(p2);

//        MsgProto.Msg.Builder builder = MsgProto.Msg.newBuilder();
//        builder.setMsgId("201");
//        builder.setMsgType("1");
////        JSONObject extend = new JSONObject() {{
////           put("chats", "123");
////           put("users", "456");
////        }};
////        builder.setExtend(extend.toJSONString());
//        builder.putExtend("chats", "123");
//        builder.putExtend("users", "456");
//        MsgProto.User.Builder u1 = MsgProto.User.newBuilder();
//        u1.setRid("jz123456789");
//        u1.setUid("123");
//        u1.setName("gjm");
//        u1.setImg("avatar1.svg");
//        builder.setFromUser(u1.build());
//        MsgProto.User.Builder u2 = MsgProto.User.newBuilder();
//        u2.setRid("jz123456789");
//        u2.setUid("123");
//        u2.setName("gjm");
//        u2.setImg("avatar1.svg");
//        builder.setToUser(u2.build());
//
//        System.out.println(builder.build());
//
//        MsgProto.Msg msg = MsgProto.Msg.parseFrom(builder.build().toByteArray());
//        System.out.println(msg.getExtendMap().get("chats"));
////        JSONObject jsonObject = JSONObject.parseObject(msg.getExtend());
////        System.out.println(jsonObject.get("chats"));

    }
    @Test
    public void test() throws Exception {
        int b = 0B11001010111111101011101010111110;
        int c = 0xcafebabe;
        System.out.println(b);
        System.out.println(c);
        byte[] bytes = intToByteArray(c);
        System.out.println(byteArrayToInt(bytes));
//        byte[] bytes = shortToByteArray((short) s);
//        System.out.println(byteArrayToShort(bytes));
//        PersonProto.Person.Builder msg = PersonProto.Person.newBuilder();
//        msg.setName("lisi");
//        msg.setAge(10);
//        msg.setBirth(123456);
//        msg.setSex(true);
//        System.out.println("=====开始输出=====");
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        //1、写入魔数
//        bos.write(new byte[]{0xc,0xa,0xf,0xe});
//
//        //2、写入正文长度和正文字节
//        byte[] msgBytes = msg.build().toByteArray();
//        byte[] lenBytes = shortToByteArray((short) msgBytes.length);
//        System.out.println(msgBytes.length);
//        bos.write(lenBytes);
//        bos.write(msgBytes);
//        bos.close();
//        System.out.println("=====结束输出=====");
//
//        System.out.println("=====开始输入=====");
//        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//        //找到魔数
//        int index = 0;
//        if (findMagic(bis.read(), index++)
//                && findMagic(bis.read(), index++)
//                && findMagic(bis.read(), index++)
//                && findMagic(bis.read(), index)) {
//            byte[] lenBuffer = new byte[4];
//            for (int i = 0; i < 2; i++) {
//                lenBuffer[i] = (byte) bis.read();
//            }
//            short length = byteArrayToShort(lenBuffer);
//            System.out.println(length);
//            byte[] msgBuffer = new byte[length];
//            while (bis.read(msgBuffer) > 0) {
//            }
//            PersonProto.Person parse = PersonProto.Person.parseFrom(msgBuffer);
//            System.out.println(parse.toString());
//        }
////        bis.close();
//        System.out.println("=====结束输入=====");
    }

    public static boolean findMagic(int b, int index) {
        if (b == new byte[]{0xc,0xa,0xf,0xe}[index]) {
            return true;
        }
        return false;
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

    public static int byteArrayToInt(byte[] bytes) {
        int b0 = bytes[0] & 0xFF;
        int b1 = bytes[1] & 0xFF;
        int b2 = bytes[2] & 0xFF;
        int b3 = bytes[3] & 0xFF;
        System.out.println("int: " + b0);
        System.out.println("int: " + b1);
        System.out.println("int: " + b2);
        System.out.println("int: " + b3);
        return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
    }

    public static byte[] shortToByteArray(short i) {
        byte[] targets = new byte[2];
        targets[1] = (byte) (i);
        targets[0] = (byte) (i >> 8);
        for (byte b : targets) {
            System.out.println("byte: " + (b));
        }
        return targets;
    }

    public static short byteArrayToShort(byte[] bytes) {
        int b0 = bytes[0] & 0xFF;
        int b1 = bytes[1] & 0xFF;
        System.out.println("short: " + bytes[0]);
        System.out.println("short: " + bytes[1]);
        return (short) ((b0 << 8) | b1);
    }
}
