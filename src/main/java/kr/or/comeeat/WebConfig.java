package kr.or.comeeat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

		//이벤트 게시판
		registry.addResourceHandler("/event/**").addResourceLocations("file:///C:/Temp/upload/event/");
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/member/adminBook","/member/adminMember",
				"/member/adminMypage","/magazine/magazineWriteFrm","/magazine/magazineUpdateFrm","member/myBook",
				"/member/mySavePlace");
		/*
		.excludePathPatterns();
		*/
		
		registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/member/adminBook","/member/adminMember",
				"/member/adminMypage","/magazine/magazineWriteFrm","/magazine/magazineUpdateFrm");
	}
	
	
}
