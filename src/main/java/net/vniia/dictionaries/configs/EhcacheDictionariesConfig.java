package net.vniia.dictionaries.configs;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.io.IOException;
import java.net.URI;

@Configuration
@EnableCaching
public class EhcacheDictionariesConfig {
    @Bean
    JCacheCacheManager dictionariesCacheManager() {
        try {
            ClassPathResource cpr = new ClassPathResource("/ehcache/ehcache-dictionaries.xml");
            URI uri = cpr.getURI();
            Class clazz = Class.forName("org.ehcache.jsr107.EhcacheCachingProvider");
            CachingProvider ehcacheCachingProvider = Caching.getCachingProvider(clazz.getName());
            CacheManager cacheManager = ehcacheCachingProvider.getCacheManager(uri, this.getClass().getClassLoader());
            return new JCacheCacheManager(cacheManager);
        } catch (IOException e) {
            throw new NullPointerException("Config not found");
        } catch (ClassNotFoundException e) {
            throw new NullPointerException("Cache Provider not found");
        }
    }
}
