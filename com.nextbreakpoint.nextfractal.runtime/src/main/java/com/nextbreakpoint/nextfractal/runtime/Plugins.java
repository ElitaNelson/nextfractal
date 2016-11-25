package com.nextbreakpoint.nextfractal.runtime;

import com.nextbreakpoint.Try;
import com.nextbreakpoint.nextfractal.core.FractalFactory;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Plugins {
    private Plugins() {}

    private static ServiceLoader<FractalFactory> loadPlugins() {
        return ServiceLoader.load(FractalFactory.class);
    }

    public static Stream<? extends FractalFactory> pluginsStream() {
        return StreamSupport.stream(loadPlugins().spliterator(), false);
    }

    public static Optional<? extends FractalFactory> findPlugin(String pluginId) {
        return pluginsStream().filter(plugin -> pluginId.equals(plugin.getId())).findFirst();
    }

    public static Try<? extends FractalFactory, Exception> tryPlugin(String pluginId) {
        return findPlugin(pluginId).map(plugin -> Try.of(() -> plugin)).orElse(Try.failure(new Exception("Plugin not found")));
    }

    public static <T> Try<T, Exception> tryPlugin(String pluginId, Function<FractalFactory, T> action) {
        return findPlugin(pluginId).map(plugin -> Try.of(() -> action.apply(plugin))).orElse(Try.failure(new Exception("Plugin not found")));
    }
}
