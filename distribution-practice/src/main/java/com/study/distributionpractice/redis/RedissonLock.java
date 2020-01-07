package com.study.distributionpractice.redis;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Decription
 * <p>
 * </p>
 * DATE 2020-01-07.
 *
 * @author guijiamin.
 */
@Slf4j
@Component
public class RedissonLock {
    /**
     * 获取锁最大等待时间
     */
    private static final long MAX_LOCK_WAIT_TIME = 5;
    /**
     * 锁自动释放最大等待时间
     */
    private static final long MAX_LOCK_LEASE_TIME = 3;
    @Resource
    private RedissonClient redissonClient;

    public boolean lock(String key) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(MAX_LOCK_WAIT_TIME, MAX_LOCK_LEASE_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("acquire lock exception {}", e.getMessage());
            return false;
        }
    }

    public void unLock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    public static void main(String[] args) throws Exception {
        RedissonLock redissonLock = new RedissonLock();
        String key = "test";
        boolean lock = false;
        try {
            //加锁
            lock = redissonLock.lock(key);
            if (lock) {
                //TODO 执行业务代码
            }
        } finally {
            if (lock) {
                //解锁
                redissonLock.unLock(key);
            }
        }
    }
}
