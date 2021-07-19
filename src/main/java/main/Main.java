package main;

import main.model.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import main.repository.RegionRepository;


@ConfigurationPropertiesScan
@SpringBootApplication
@ComponentScan(basePackageClasses = main.controller.APIGeneralController.class)
@EnableJpaRepositories(basePackages = "main/repository")

public class Main implements CommandLineRunner {

    @Autowired
    RegionRepository regionRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);


    }

    @Override
    public void run(String... args) {
        Region region = regionRepository.findById(1).get();
        System.out.println(region.getName());
    }

}
