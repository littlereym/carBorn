package com.example.demo.config.shiro;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "server.scheme.chanage.enabled", havingValue = "true") // 开启注解才会启动
public class RedirectFilterConfig {

	@Bean
	public FilterRegistrationBean registFilter() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new AbsoluteSendRedirectFilter());
		registration.addUrlPatterns("*");
		registration.setName("filterRegistrationBean");
		registration.setOrder(1);
		return registration;
	}

}