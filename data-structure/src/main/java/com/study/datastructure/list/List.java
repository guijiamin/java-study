package com.study.datastructure.list;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/10.
 *
 * @author guijiamin.
 */
public interface List<T> {
    /**
     * 判断线性表是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 获取线性表的长度
     * @return
     */
    int length();

    /**
     * 清空线性表
     */
    void clear();

    /**
     * 查询线性表是否包含指定数据
     * @param data
     * @return
     */
    boolean contains(T data);

    /**
     * 根据索引获取线性表中的元素
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 设置指定索引为指定元素
     * @param index
     * @param data
     * @return
     */
    T set(int index, T data);

    /**
     * 添加数据到线性表尾部
     * @param data
     * @return
     */
    boolean add(T data);

    /**
     * 插入数据到线性表中
     * @param index
     * @param data
     * @return
     */
    boolean insert(int index, T data);

    /**
     * 删除线性表指定索引的数据
     * @param index
     * @return
     */
    boolean remove(int index);

    /**
     * 删除线性表指定数据（第一个出现的）
     * @param data
     * @return
     */
    boolean remove(T data);

    /**
     * 删除线性表所有指定数据
     * @param data
     * @return
     */
    boolean removeAll(T data);
}
