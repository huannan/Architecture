package com.nan.day10_pattern_factory.simple4.io;

import com.nan.day10_pattern_factory.simple4.io.handler.IOHandler;

/**
 * 工厂方法模式
 * 一种产品对应一个工厂，不用改动原来的代码，直接新增类即可，但是随着功能的扩展工厂实现类会不断增加
 */
public interface IOHandlerFactory {

    IOHandler createIOHandler();

}
