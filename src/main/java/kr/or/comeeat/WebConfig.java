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
		registry.addResourceHandler("/magazine/**").addResourceLocations("classpath:/static/img/magazine/");
		registry.addResourceHandler("/review/**").addResourceLocations("file:///C:/Temp/upload/review/");
		
		registry.addResourceHandler("/editor/**").addResourceLocations("file:///C:/Temp/upload/editor/");
		registry.addResourceHandler("/location/**").addResourceLocations("classpath:/static/img/location/");
		registry.addResourceHandler("/board/**").addResourceLocations("file:///C:/Temp/upload/board/");

		
		registry.addResourceHandler("/event/**")
		.addResourceLocations("file:///C:Temp/upload/event/");
	}
}
