package kr.or.comeeat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/","classpath:/static/");
		
		registry.addResourceHandler("/photo/**").addResourceLocations("file:///C:/Temp/upload/magazine/");
		
		registry.addResourceHandler("/editor/**").addResourceLocations("file:///C:/Temp/upload/editor/");
	}
}
