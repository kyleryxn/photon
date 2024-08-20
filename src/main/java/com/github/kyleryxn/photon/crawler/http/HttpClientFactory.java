package com.github.kyleryxn.photon.crawler.http;

import org.apache.hc.client5.http.SystemDefaultDnsResolver;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.config.TlsConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.http2.HttpVersionPolicy;
import org.apache.hc.core5.pool.PoolConcurrencyPolicy;
import org.apache.hc.core5.pool.PoolReusePolicy;
import org.apache.hc.core5.util.Timeout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HttpClientFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientFactory.class);
    private final int maxConnections;
    private final int maxConnectionsPerRoute;
    private final PoolingHttpClientConnectionManager connectionManager;

    HttpClientFactory() {
        maxConnections = 200;
        maxConnectionsPerRoute = 100;
        connectionManager = createConnectionManager();
    }

    private PoolingHttpClientConnectionManager createConnectionManager() {
        SocketConfig socketConfig = createSocketConfig();

        return PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultSocketConfig(socketConfig)
                .setConnPoolPolicy(PoolReusePolicy.FIFO)
                .setPoolConcurrencyPolicy(PoolConcurrencyPolicy.STRICT)
                .setDefaultTlsConfig(TlsConfig.custom()
                        .setVersionPolicy(HttpVersionPolicy.NEGOTIATE)
                        .build())
                .setMaxConnTotal(maxConnections)
                .setMaxConnPerRoute(maxConnectionsPerRoute)
                .setDnsResolver(SystemDefaultDnsResolver.INSTANCE)
                .build();
    }

    private SocketConfig createSocketConfig() {
        return SocketConfig.custom()
                .setSoTimeout(Timeout.ofMilliseconds(2000))
                .setTcpNoDelay(true)
                .setSoKeepAlive(true)
                .build();
    }

    @Bean("httpClient")
    public CloseableHttpClient createCustom() {
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictExpiredConnections()
                .evictIdleConnections(Timeout.ofSeconds(30))
                .setDefaultRequestConfig(
                        RequestConfig.custom()
                                .setConnectionRequestTimeout(Timeout.ofMilliseconds(2000))
                                .setConnectTimeout(Timeout.ofMilliseconds(2000))
                                .setResponseTimeout(Timeout.ofMilliseconds(2000))
                                .build())
                .build();
    }

}
