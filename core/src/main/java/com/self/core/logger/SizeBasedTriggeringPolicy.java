/*
 *  See the file "LICENSE" for the full license governing this code.
 */

package com.self.core.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import ch.qos.logback.core.util.FileSize;

public class SizeBasedTriggeringPolicy<E> extends
        ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy<E> {
    private final Logger LOG = LoggerFactory
            .getLogger("SizeBasedTriggeringPolicy");

    @Override
    public boolean isTriggeringEvent(final File activeFile, final E event) {

        boolean trggeringEvent = activeFile.length() >= FileSize.valueOf(
                getMaxFileSize()).getSize();
        if (trggeringEvent) {
            LOG.debug("Log file size reached..." + activeFile.getAbsolutePath());
        }
        return trggeringEvent;
    }
}
