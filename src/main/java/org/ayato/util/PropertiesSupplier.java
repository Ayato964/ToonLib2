package org.ayato.util;

import org.ayato.animation.Properties;

public interface PropertiesSupplier<T extends Properties<?>> {
    public T of(int x, int y);
}
