package com.helloworld.helloworld;
import com.helloworld.helloworld.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        //
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        HelloWorldResource helloWorldResource = new HelloWorldResource(
                helloWorldConfiguration.getTemplate(),
                helloWorldConfiguration.getDefaultName()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(
                helloWorldConfiguration.getTemplate()
        );
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(helloWorldResource);
    }
}
