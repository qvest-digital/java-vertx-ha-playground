package de.tarent;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import java.util.logging.Logger;

class JavaVertxLab {

    private static final Logger LOG = Logger.getLogger("main");

    public static void main(String[] args) {

        LOG.info("starting kotlin vert.x lab");

        String foo = System.getenv("START_VERTICLE");

        LOG.info(foo);

        Boolean startVerticle = (foo != null && foo.compareTo("true") == 0);



        final VertxOptions options = new VertxOptions();
        options.setHAEnabled(true);

        Vertx.clusteredVertx(options, cluster -> {

            if (cluster.succeeded()) {
                Vertx vertx = cluster.result();
                if(startVerticle){
                    DeploymentOptions deployOptions = new DeploymentOptions();
                    deployOptions.setHa(true);
                    LOG.info(deployOptions.toJson().toString());
                    vertx.deployVerticle(HaTestVerticle.class.getName(), deployOptions);
                }
            }

        });

        LOG.info("exiting kotlin vert.x lab");
    }



}
