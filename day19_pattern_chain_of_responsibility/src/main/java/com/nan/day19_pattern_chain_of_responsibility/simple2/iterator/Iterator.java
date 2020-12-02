package com.nan.day19_pattern_chain_of_responsibility.simple2.iterator;

/**
 * 迭代器的接口
 *
 * @param <T>
 */
public interface Iterator<T> {

    /**
     * 获取下一个
     *
     * @return
     */
    T next();

    /**
     * 是否还有下一个
     *
     * @return
     */
    boolean hasNext();

}
