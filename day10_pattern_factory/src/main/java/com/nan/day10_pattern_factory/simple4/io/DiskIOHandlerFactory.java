package com.nan.day10_pattern_factory.simple4.io;

import com.nan.day10_pattern_factory.simple4.io.handler.DiskIOHandler;
import com.nan.day10_pattern_factory.simple4.io.handler.IOHandler;

public class DiskIOHandlerFactory implements IOHandlerFactory {
    @Override
    public IOHandler createIOHandler() {
        return new DiskIOHandler();
    }
}
