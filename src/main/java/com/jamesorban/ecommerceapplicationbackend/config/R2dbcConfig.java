package com.jamesorban.ecommerceapplicationbackend.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;

import java.util.List;
import java.util.Objects;

@Configuration
@EnableR2dbcRepositories(basePackages = {"com.jamesorban.ecommerceapplicationbackend.dao"})
@PropertySource("classpath:r2dbc.properties")
public class R2dbcConfig extends AbstractR2dbcConfiguration {
    private static final String URL = "r2dbc.url";
    private static final String USERNAME = "r2dbc.username";
    private static final String PASSWORD = "r2dbc.password";
    @Autowired
    private Environment env;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions baseOptions = ConnectionFactoryOptions.parse(Objects.requireNonNull(env.getProperty(URL)));
        ConnectionFactoryOptions connectionFactoryOptions = ConnectionFactoryOptions.builder()
                                                                                    .from(baseOptions)
                                                                                    .option(ConnectionFactoryOptions.USER,
                                                                                            Objects.requireNonNull(env.getProperty(USERNAME)))
                                                                                    .option(ConnectionFactoryOptions.PASSWORD,
                                                                                            Objects.requireNonNull(env.getProperty(PASSWORD)))
                                                                                    .build();
        return ConnectionFactories.get(connectionFactoryOptions);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Override
    protected List<Object> getCustomConverters() {
        return CustomConverterProvider.getConverters();
    }
}