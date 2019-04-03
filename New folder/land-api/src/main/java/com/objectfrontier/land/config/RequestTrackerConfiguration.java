package com.objectfrontier.land.config;

import com.objectfrontier.land.filters.RequestTrackerFilter;
import lombok.Data;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class RequestTrackerConfiguration {

    public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "X-RequestTracker-Id";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMDCFilter.UUID";
    public static final String DEFAULT_MDC_CLIENT_IP_KEY = "Slf4jMDCFilter.ClientIP";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String mdcClientIpKey = DEFAULT_MDC_CLIENT_IP_KEY;
    private String requestHeader = null;

    @Bean
    public FilterRegistrationBean<RequestTrackerFilter> servletRegistrationBean() {
        final FilterRegistrationBean<RequestTrackerFilter> registrationBean = new FilterRegistrationBean<RequestTrackerFilter>();
        final RequestTrackerFilter requestTrackerFilter = new RequestTrackerFilter(responseHeader, mdcTokenKey, requestHeader);
        registrationBean.setFilter(requestTrackerFilter);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
