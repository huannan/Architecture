package com.nan.day10_pattern_factory.simple6.io;

import com.nan.day10_pattern_factory.simple6.io.handler.DiskIOHandler;
import com.nan.day10_pattern_factory.simple6.io.handler.IOHandler;
import com.nan.day10_pattern_factory.simple6.io.handler.MemoryIOHandler;
import com.nan.day10_pattern_factory.simple6.io.handler.PreferencesIOHandler;

/**
 * 抽象工厂模式与单例模式结合
 */
public class IOHandlerFactory {

    private IOHandler mMemoryIOHandler;
    private IOHandler mPreferencesIOHandler;
    private IOHandler mDiskIOHandler;

    private IOHandlerFactory() {

    }

    private static class Holder {
        private static IOHandlerFactory INSTANCE = new IOHandlerFactory();
    }

    public static IOHandlerFactory getInstance() {
        return Holder.INSTANCE;
    }

    public IOHandler createIOHandler(Class<? extends IOHandler> clz) {
        try {
            return clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public IOHandler getMemoryIOHandler() {
        if (null == mMemoryIOHandler) {
            mMemoryIOHandler = createIOHandler(MemoryIOHandler.class);
        }
        return mMemoryIOHandler;
    }

    public IOHandler getPreferencesIOHandler() {
        if (null == mPreferencesIOHandler) {
            mPreferencesIOHandler = createIOHandler(PreferencesIOHandler.class);
        }
        return mPreferencesIOHandler;
    }

    public IOHandler getDiskIOHandler() {
        if (null == mDiskIOHandler) {
            mDiskIOHandler = createIOHandler(DiskIOHandler.class);
        }
        return mDiskIOHandler;
    }

    /**
     * 为什么搞个默认的，有时候代码写完了，但是网上有很多高效的；又或者是本来就是用了别人的，但是某些人出了更好的。这样方便切换
     */
    public IOHandler getDefaultIOHandler() {
        return getPreferencesIOHandler();
    }

}
