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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Klasa MyDataRestConfig implementuje interfejs RepositoryRestConfigurer, który umożliwia dostosowanie konfiguracji Spring Data REST.
@Configuration
public class MyDataRestConfig  implements RepositoryRestConfigurer {

    private final List<String> corsAllowedOrigins;
    private final EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager, @Value("${allowed.origins}") List<String> corsAllowedOrigins) {
        entityManager = theEntityManager;
        this.corsAllowedOrigins = corsAllowedOrigins;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);

        List<HttpMethod> unsupportedHttpMethods = Arrays.asList(HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PATCH);

        disableHttpMethods(Product.class,config, unsupportedHttpMethods);
        disableHttpMethods(ProductCategory.class,config, unsupportedHttpMethods);
        disableHttpMethods(Country.class,config, unsupportedHttpMethods);
        disableHttpMethods(State.class,config, unsupportedHttpMethods);
        disableHttpMethods(Order.class,config, unsupportedHttpMethods);

        exposeIds(config);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(corsAllowedOrigins.toArray(new String[0]));
    }

    private void disableHttpMethods(Class classAttribute, RepositoryRestConfiguration config, List<HttpMethod> unsupportedHttpMethods) {
        HttpMethod[] methodsArray = new HttpMethod[unsupportedHttpMethods.size()];
        unsupportedHttpMethods.toArray(methodsArray);

        config.getExposureConfiguration()
                .forDomainType(classAttribute)
                .withItemExposure((metadata, httpMethods) -> httpMethods.disable(methodsArray))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(methodsArray));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        List<Class> entityClasses = entities.stream()
                .map(EntityType::getJavaType)
                .collect(Collectors.toList());

        config.exposeIdsFor(entityClasses.toArray(new Class[0]));
    }
}