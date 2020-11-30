package com.flyme.videoclips.day18_pattern_iterator.simple2;

import com.flyme.videoclips.day18_pattern_iterator.simple2.iterator.Iterator;

/**
 * 容器的接口
 *
 * @param <T>
 */
public interface Aggregate<T> {

    Iterator<T> iterator();

}
