package de.tarent;

import io.vertx.core.AbstractVerticle;
import java.util.logging.Logger;

public class HaTestVerticle extends AbstractVerticle {

    private static final Logger LOG = Logger.getLogger(HaTestVerticle.class.getCanonicalName());

    public void start() {
        LOG.warning("HA verticle started");
        vertx.setPeriodic(2000, y -> {
                LOG.warning("Hello from HA verticle");
        });
    }
}