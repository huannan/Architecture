package com.nan.day10_pattern_factory.simple5.io;

import com.nan.day10_pattern_factory.simple5.io.handler.DiskIOHandler;
import com.nan.day10_pattern_factory.simple5.io.handler.IOHandler;
import com.nan.day10_pattern_factory.simple5.io.handler.MemoryIOHandler;
import com.nan.day10_pattern_factory.simple5.io.handler.PreferencesIOHandler;

/**
 * 抽象工厂模式
 * 与简单工厂模式比较类似
 */
public class IOHandlerFactory {

    public static IOHandler createIOHandler(Class<? extends IOHandler> clz) {
        try {
            return clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IOHandler getMemoryIOHandler() {
        return createIOHandler(MemoryIOHandler.class);
    }

    public static IOHandler getPreferencesIOHandler() {
        return createIOHandler(PreferencesIOHandler.class);
    }

    public static IOHandler getDiskIOHandler() {
        return createIOHandler(DiskIOHandler.class);
    }

    /**
     * 为什么搞个默认的，有时候代码写完了，但是网上有很多高效的；又或者是本来就是用了别人的，但是某些人出了更好的。这样方便切换
     */
    public static IOHandler getDefaultIOHandler() {
        return getPreferencesIOHandler();
    }

}
