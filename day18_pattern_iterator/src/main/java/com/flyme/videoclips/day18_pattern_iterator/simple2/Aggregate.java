package com.flyme.videoclips.day18_pattern_iterator.simple2;

import com.flyme.videoclips.day18_pattern_iterator.simple2.iterator.Iterator;

/**
 * 容器的接口
 *
 * @param <T>
 */
public interface Aggregate<T> {

    // Iterator离开Aggregate还是能用，但是因此它们之间是聚合关系
    Iterator<T> iterator();

}
