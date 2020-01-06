package com.study.datastructure.tree;

/**
 * Decription
 * <p>
 * </p>
 * DATE 18/12/17.
 *
 * @author guijiamin.
 */
public interface Tree<T extends Comparable> {
    /**
     * 判断是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 树的节点个数
     * @return
     */
    int size();

    /**
     * 树的高度或深度
     * @return
     */
    int height();

    /**
     * 插入
     * @param data
     */
    void insert(T data);

    /**
     * 删除
     * @param data
     */
    void remove(T data);

    /**
     * 是否包含数据
     * @param data
     * @return
     */
    boolean contains(T data);

    /**
     * 清除
     */
    void clear();

    /**
     * 查找最小
     * @return
     */
    T findMin();

    /**
     * 查找最大
     * @return
     */
    T findMax();

    String preOrder();

    String inOrder();

    String postOrder();

    String levelOrder();
}
