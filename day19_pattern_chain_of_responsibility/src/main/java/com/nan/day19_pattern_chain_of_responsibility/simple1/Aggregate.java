package com.nan.day19_pattern_chain_of_responsibility.simple1;

import com.nan.day19_pattern_chain_of_responsibility.simple1.iterator.Iterator;

/**
 * 容器的接口
 *
 * @param <T>
 */
public interface Aggregate<T> {

    // Iterator离开Aggregate还是能用，但是因此它们之间是聚合关系
    Iterator<T> iterator();

}
