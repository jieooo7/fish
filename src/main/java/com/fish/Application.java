package com.fish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ConfigurationProperties(prefix = "app",locations = "classpath:config/application.properties")
public class Application {



    private static final Logger log = LoggerFactory.getLogger(Application.class);
	
    public static void main(String[] args) {

         SpringApplication.run(Application.class, args);
    }

//commandline 在SpringApplication.run方法之前运行
//    @Bean
//    public CommandLineRunner demo(CustomerRepository repository) {
//        return (args) -> {
//             // save a couple of customers
//            repository.save(new Customer("Jack", "Bauer"));
//            repository.save(new Customer("Chloe", "O'Brian"));
//            repository.save(new Customer("Kim", "Bauer"));
//            repository.save(new Customer("David", "Palmer"));
//            repository.save(new Customer("Michelle", "Dessler"));
//            repository.save(new Customer("胡", "毛"));
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Customer customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            Customer customer = repository.findOne(1L);
//            log.info("Customer found with findOne(1L):");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            for (Customer bauer : repository.findByLastName("Bauer")) {
//                log.info(bauer.toString());
//            }
//            log.info("");
//        };
//    }

}
