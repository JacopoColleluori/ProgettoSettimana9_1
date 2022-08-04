package com.filmproject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableWebMvc	//diciamo a spring di abilitare una [arte di librerie Mvc
@Configuration	//diciamo a spring che questa è una classe di configurazione
@ComponentScan({ "com.filmproject" })		//da dove partono le classi
public class SpringWebConfig extends WebMvcConfigurerAdapter {	//classe che verranno sostituite con le annotation con Spring

	/*
	 * By default, this handler serves static content from any of the /static,
	 * /public, /resources, and /META-INF/resources directories that are on the
	 * classpath.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
