package org.napbad.org.napbad.config

import org.babyfish.jimmer.sql.meta.DatabaseNamingStrategy
import org.babyfish.jimmer.sql.runtime.DefaultDatabaseNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component


@Component
class SqlClientConfig {
    //    @Bean
    //    public JSqlClient sqlClient(DruidDataSource dataSource) {
    //        return JSqlClient
    //                .newBuilder()
    //                .setDialect(new MySqlDialect())
    //                .setConnectionManager(
    //                        new ConnectionManager() {
    //                            @Override
    //                            public <R> R execute(Function<Connection, R> block) {
    //                                Connection connection = DataSourceUtils.getConnection(dataSource);
    //                                try {
    //                                    return block.apply(connection);
    //                                } finally {
    //                                    DataSourceUtils.releaseConnection(connection, dataSource);
    //                                }
    //                            }
    //                        }
    //                ).setDatabaseNamingStrategy(
    //                        DefaultDatabaseNamingStrategy.LOWER_CASE
    //                ).addScalarProvider(new UUIDScalarProvider())


    @Bean
    fun databaseNamingStrategy(): DatabaseNamingStrategy =
        DefaultDatabaseNamingStrategy.LOWER_CASE
}