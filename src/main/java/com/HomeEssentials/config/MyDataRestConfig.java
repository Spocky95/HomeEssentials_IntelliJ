package com.HomeEssentials.config;

import com.HomeEssentials.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// Klasa MyDataRestConfig implementuje interfejs RepositoryRestConfigurer, który umożliwia dostosowanie konfiguracji Spring Data REST.
@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer {

    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;
    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    // Metoda configureRepositoryRestConfiguration jest używana do konfiguracji CORS dla repozytoriów Spring Data REST.
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        HttpMethod[] theUnsupportedActions = {  HttpMethod.PUT,
                                                HttpMethod.POST,
                                                HttpMethod.DELETE,
                                                HttpMethod.PATCH};

        // Wyłączenie metod PUT, POST i DELETE dla wszystkich klas encji.
        disableHttpMethods(Product.class,config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);
        disableHttpMethods(Country.class,config, theUnsupportedActions);
        disableHttpMethods(State.class,config, theUnsupportedActions);

        disableHttpMethods(Order.class,config, theUnsupportedActions);



        // call an internal helper method
        exposeIds(config);

        //config.getCorsRegistry().addMapping("/**").allowedOrigins("http://localhost:4200");
//        cors.addMapping(config.getBasePath() + "/**").allowedOrigins("http://localhost:4200");
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    private static void disableHttpMethods(Class classAttribute, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(classAttribute)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {


        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);

        
    }
}