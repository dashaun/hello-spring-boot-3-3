package dev.dashaun.service.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

@Component
class SpringInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> springBootDetails = new HashMap<>();
        springBootDetails.put("version", SpringBootVersion.getVersion());
        Map<String, Object> springDetails = new HashMap<>();
        springDetails.put("boot", springBootDetails);

        builder.withDetail("spring", springDetails);
    }
}

@Component
class JavaInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> javaDetails = new HashMap<>();
        javaDetails.put("version", System.getProperty("java.version"));
        javaDetails.put("vendor", System.getProperty("java.vendor"));
        javaDetails.put("vm", System.getProperty("java.vm.name"));

        builder.withDetail("java", javaDetails);
    }
}