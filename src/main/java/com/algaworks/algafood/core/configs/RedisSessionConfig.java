package com.algaworks.algafood.core.configs;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.session.FlushMode;
import org.springframework.session.SaveMode;
import org.springframework.session.config.SessionRepositoryCustomizer;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.session.data.redis.config.ConfigureNotifyKeyspaceEventsAction;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.RedisIndexedHttpSessionConfiguration;

import java.time.Duration;

/**
 * github:
 * https://github.com/spring-projects/spring-session/issues/2459
 *
 * Clone da classe:
 *  org.springframework.boot.autoconfigure.session.RedisSessionConfiguration
 */
@Configuration
@ConditionalOnProperty(prefix = "application", name = "session.store", havingValue = "redis") // Essa config só será ativada se o application.session.store=redis
@EnableConfigurationProperties(RedisSessionProperties.class) // configurações específicas do Redis: spring.session.redis.*
@Import({RedisAutoConfiguration.class}) // Importa a configuração automática do Redis
@Log4j2
public class RedisSessionConfig {


/**  Essa classe ativa e configura o uso do Redis como repositório de sessões HTTP com a opção padrão spring.session.redis.repository-type=default */
    @Configuration(proxyBeanMethods = false) // Não cria proxy para o metodos @Bean, melhora a performance
    @ConditionalOnProperty(prefix = "spring.session.redis", name = "repository-type", havingValue = "default", matchIfMissing = true) // Essa config só será ativada se o spring.session.redis.repository-type for "default" ou se não tiver definido
    @Import(RedisHttpSessionConfiguration.class) // Importa a configuração base do Spring Session para Redis
    @EnableRedisHttpSession // Habilita suporte para o gerenciamento de sessão HTTP no Redis.
    class DefaultRedisSessionConfiguration {

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        SessionRepositoryCustomizer<RedisSessionRepository> springBootSessionRepositoryCustomizer(
            SessionProperties sessionProperties // configurações gerais de sessão: spring.session.*
            ,RedisSessionProperties redisSessionProperties // configurações específicas do Redis: spring.session.redis.*
            ,ServerProperties serverProperties // Configurações do servidor embutido, como por exemplo o timeout de sessão padrão
        ) {
            log.info(">>> CARREGANDO CONFIGURAÇÃO AUTOMÁTICA DO SPRING BOOT PARA SUPORTAR SESSÕES HTTP ARMAZENADAS NO REDIS, USANDO A CONFIGURAÇÃO 'default', OU SEJA, NÃO INDEXADA.");

            String cleanupCron = redisSessionProperties.getCleanupCron();

//          Verifica se foi definido spring.session.redis.cleanup-cron. Se sim, lança exceção, pois essa opção só é válida com repository-type = indexed.
            if (cleanupCron != null) {
                throw new InvalidConfigurationPropertyValueException("spring.session.redis.cleanup-cron", cleanupCron,
                    "Cron-based cleanup is only supported when spring.session.redis.repository-type is set to indexed.");
            }
            return (sessionRepository) -> { // Customiza o repositório com base nas propriedades

//                PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull();  // só aplica o valor se ele não for null
//                map.from(sessionProperties.determineTimeout(() -> serverProperties.getServlet().getSession().getTimeout())).to(sessionRepository::setDefaultMaxInactiveInterval);
//                map.from(redisSessionProperties::getNamespace).to(sessionRepository::setRedisKeyNamespace);
//                map.from(redisSessionProperties::getFlushMode).to(sessionRepository::setFlushMode);
//                map.from(redisSessionProperties::getSaveMode).to(sessionRepository::setSaveMode);

                Duration timeout = sessionProperties.determineTimeout(() -> serverProperties.getServlet().getSession().getTimeout());
                if (timeout != null) {
                    log.info("🛠 Configurando timeout padrão da sessão: {}", timeout);
                    sessionRepository.setDefaultMaxInactiveInterval(timeout);
                }

                String namespace = redisSessionProperties.getNamespace();
                if (namespace != null) {
                    log.info("🛠 Configurando namespace Redis para sessões: {}", namespace);
                    sessionRepository.setRedisKeyNamespace(namespace);
                }

                FlushMode flushMode = redisSessionProperties.getFlushMode();
                if (flushMode != null) {
                    log.info("🛠 Configurando FlushMode: {}", flushMode);
                    sessionRepository.setFlushMode(flushMode);
                }

                SaveMode saveMode = redisSessionProperties.getSaveMode();
                if (saveMode != null) {
                    log.info("🛠 Configurando SaveMode: {}", saveMode);
                    sessionRepository.setSaveMode(saveMode);
                }

            };
        }

    }

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnProperty(prefix = "spring.session.redis", name = "repository-type", havingValue = "indexed") // Essa configuração só é carregada se a propriedade spring.session.redis.repository-type=indexed
    @Import(RedisIndexedHttpSessionConfiguration.class) // Importa a configuração base do Spring Session para sessões indexadas no Redis;
    @EnableRedisIndexedHttpSession // Habilita o uso de atributos indexados nas sessões, isso permite consultas mais eficientes, como localizar sessões por atributo.
    class IndexedRedisSessionConfiguration {


//      Define uma ação que o Spring Session executa para configurar eventos no Redis (ex: notify-keyspace-events)
//      necessários para que o Redis possa detectar expiração de chaves e outras funcionalidades reativas.
//      Evita erros se o Redis estiver em um ambiente onde não pode ser configurado (ex: AWS ElastiCache).
        @Bean
        @ConditionalOnMissingBean
        ConfigureRedisAction configureRedisAction(RedisSessionProperties redisSessionProperties) {
            return switch (redisSessionProperties.getConfigureAction()) {
                case NOTIFY_KEYSPACE_EVENTS -> new ConfigureNotifyKeyspaceEventsAction();
                case NONE -> ConfigureRedisAction.NO_OP;
            };
        }

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        SessionRepositoryCustomizer<RedisIndexedSessionRepository> springBootSessionRepositoryCustomizer(
            SessionProperties sessionProperties // configurações gerais de sessão: spring.session.*
            ,RedisSessionProperties redisSessionProperties // configurações específicas do Redis: spring.session.redis.*
            ,ServerProperties serverProperties // Configurações do servidor embutido, como por exemplo o timeout de sessão padrão
        ) {
            log.info(">>> CARREGANDO CONFIGURAÇÃO CUSTOMIZADA COM SUPORTE A INDEXAÇÃO POR ATRIBUTOS(RedisIndexedSessionRepository) PARA ARMAZENAR SESSÕES HTTP NO REDIS.");

            return (sessionRepository) -> { // Customiza o repositório para sessões indexadas

//                PropertyMapper map = PropertyMapper.get().alwaysApplyingWhenNonNull(); // só aplica o valor se ele não for null
//                map.from(sessionProperties.determineTimeout(() -> serverProperties.getServlet().getSession().getTimeout())).to(sessionRepository::setDefaultMaxInactiveInterval);
//                map.from(redisSessionProperties::getNamespace).to(sessionRepository::setRedisKeyNamespace);
//                map.from(redisSessionProperties::getFlushMode).to(sessionRepository::setFlushMode);
//                map.from(redisSessionProperties::getSaveMode).to(sessionRepository::setSaveMode);
//                map.from(redisSessionProperties::getCleanupCron).to(sessionRepository::setCleanupCron);

                Duration timeout = sessionProperties.determineTimeout(() -> serverProperties.getServlet().getSession().getTimeout());
                if (timeout != null) {
                    log.info("🛠 Configurando timeout padrão da sessão: {}", timeout);
                    sessionRepository.setDefaultMaxInactiveInterval((int) timeout.getSeconds());
                }

                String namespace = redisSessionProperties.getNamespace();
                if (namespace != null) {
                    log.info("🛠 Configurando namespace Redis para sessões: {}", namespace);
                    sessionRepository.setRedisKeyNamespace(namespace);
                }

                FlushMode flushMode = redisSessionProperties.getFlushMode();
                if (flushMode != null) {
                    log.info("🛠 Configurando FlushMode: {}", flushMode);
                    sessionRepository.setFlushMode(flushMode);
                }

                SaveMode saveMode = redisSessionProperties.getSaveMode();
                if (saveMode != null) {
                    log.info("🛠 Configurando SaveMode: {}", saveMode);
                    sessionRepository.setSaveMode(saveMode);
                }

                String cleanupCron = redisSessionProperties.getCleanupCron();
                if (cleanupCron != null) {
                    log.info("🛠 Configurando cleanup-cron: {}", cleanupCron);
                    sessionRepository.setCleanupCron(cleanupCron);
                }

            };
        }

    }
}