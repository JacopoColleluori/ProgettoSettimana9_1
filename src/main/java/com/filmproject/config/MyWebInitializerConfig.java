package com.filmproject.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebInitializerConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() { //classe che verranno sostituite con le annotation con Spring
		return new Class[0];
	}

	/* Classi di configurazione delle servlet e view di Spring*/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}
	
	/* Dichiariamo url del sito */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}