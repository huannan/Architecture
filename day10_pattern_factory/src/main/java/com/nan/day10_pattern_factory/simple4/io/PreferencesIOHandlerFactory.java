package com.nan.day10_pattern_factory.simple4.io;

import com.nan.day10_pattern_factory.simple4.io.handler.IOHandler;
import com.nan.day10_pattern_factory.simple4.io.handler.PreferencesIOHandler;

public class PreferencesIOHandlerFactory implements IOHandlerFactory {
    @Override
    public IOHandler createIOHandler() {
        return new PreferencesIOHandler();
    }
}
