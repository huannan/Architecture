package com.nan.day10_pattern_factory.simple4.io;

import com.nan.day10_pattern_factory.simple4.io.handler.DiskIOHandler;
import com.nan.day10_pattern_factory.simple4.io.handler.IOHandler;
import com.nan.day10_pattern_factory.simple4.io.handler.MemoryIOHandler;
import com.nan.day10_pattern_factory.simple4.io.handler.PreferencesIOHandler;

/**
 * 简单工厂模式
 * 问题：新增一种存储方式，必须要新增类型、添加case，说白了就是要改动原来的很多代码
 */
public class IOHandlerFactory {

    public enum IOType {
        MEMORY,
        PREFERENCES,
        DISK
    }

    public static IOHandler createIOHandler(IOType type) {
        switch (type) {
            case MEMORY:
                return new MemoryIOHandler();
            case PREFERENCES:
                return new PreferencesIOHandler();
            case DISK:
                return new DiskIOHandler();
            default:
                throw new UnsupportedOperationException();
        }
    }

}
