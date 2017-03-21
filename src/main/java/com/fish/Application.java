package com.fish;

import com.fish.storage.StorageProperties;
import com.fish.storage.StorageService;
import com.fish.view.BlogProperties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.devtools.RemoteSpringApplication;
import org.springframework.cache.Cache;

import net.sf.ehcache.management.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@EnableCaching
@SpringBootApplication
//@ConfigurationProperties(prefix = "app",locations = "classpath:config/application.properties")
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    @Autowired
    private BlogProperties blogProperties;

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
    }

    //EmbeddedServletContainerAutoConfiguration.EmbeddedTomcat
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/admin_login.html");

            container.addErrorPages(error401Page, error404Page, error403Page,error500Page);
        });
    }


    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
//            storageService.deleteAll();
            storageService.init();
//            System.out.println("================978746456================="+blogProperties.getName());
        };
    }

//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        List<Cache> caches = new ArrayList<Cache>();
//        caches.add(new ConcurrentMapCache("getActionsBycasId"));
//        cacheManager.setCaches(caches);
//        return cacheManager;
//    }

//    @Autowired
//    private CacheManager cacheManager;

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
//    RestTemplate restTemplate = new RestTemplate();
//    Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);