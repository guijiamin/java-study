package com.study.distributionpractice.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Decription
 * <p>
 * redis分布式锁伪代码：
 *     if (setnx(key, value) == 1) {
 *         expire(key, 30);
 *         try {
 *             //do something
 *         } finally {
 *             del(key);
 *         }
 *     }
 * 问题1：
 *      setnx和expire没有原子性
 * 解决1：
 *      set(key, value, 30, NX);
 * 问题2：
 *      在A持有锁期间未执行完业务，到期被B获得锁，A执行完后主动删除的其实是B的锁
 * 解决2：
 *      可绑定value作为锁的一个识别标识，如时间戳
 * </p>
 * DATE 2020-01-06.
 *
 * @author guijiamin.
 */
@Slf4j
@Component
public class RedisLock {
    @Resource
    private StringRedisTemplate redisTemplate;

    public boolean lock(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("key or value cannot be empty");
        }
        //尝试设置，成功则说明获取到锁
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        //当上述设置失败，查询当前key对应的值
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果当前key已经超时，但却没有释放，解决死锁
        if (!StringUtils.isEmpty(currentValue) && Long.valueOf(currentValue) >= System.currentTimeMillis()) {
            //获取上一个锁的时间，并设置为当前锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    public void unLock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("realese lock exception {}", e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        RedisLock redisLock = new RedisLock();
        String key = "test";
        long time = System.currentTimeMillis() + 5 * 1000;
        boolean lock = false;
        try {
            //加锁
            lock = redisLock.lock(key, String.valueOf(time));
            if (lock) {
                //TODO 执行业务代码
            }
        } finally {
            if (lock) {
                //解锁
                redisLock.unLock(key, String.valueOf(time));
            }
        }
    }
}
