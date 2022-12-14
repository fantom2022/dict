package net.vniia.dictionaries.configs;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

public class FlywayMigrationWithDictionariesStrategy implements FlywayMigrationStrategy {
    public void migrate(Flyway flyway) {
        Flyway dictFlyway = Flyway.configure()
                .dataSource(flyway.getConfiguration().getDataSource())
                .locations("db/dictionaries_migration")
                .baselineVersion("0")
                .baselineOnMigrate(true)
                .table("flyway_schema_history_dict")
                .schemas(flyway.getConfiguration().getSchemas())
                .load();
        dictFlyway.migrate();

        flyway.migrate();
    }
}
