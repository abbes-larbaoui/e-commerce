package com.kyrios.ecommerce.config;

import com.kyrios.ecommerce.entity.Country;
import com.kyrios.ecommerce.entity.Product;
import com.kyrios.ecommerce.entity.ProductCategory;
import com.kyrios.ecommerce.entity.State;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.SecondaryTable;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    public MyDataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        cors.addMapping("/**")
                .allowedOrigins("http://localhost:4200");

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Product.class), theUnsupportedActions);

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(ProductCategory.class), theUnsupportedActions);

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(Country.class), theUnsupportedActions);

        disableHttpMethods(config.getExposureConfiguration()
                .forDomainType(State.class), theUnsupportedActions);

        exposeIds(config);
    }

    private static void disableHttpMethods(ExposureConfigurer config, HttpMethod[] theUnsupportedActions) {
        config
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for (EntityType tempEntityType: entities) {
            entityClasses.add((tempEntityType.getJavaType()));
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
