package sample.server;

import com.gilecode.yagson.converters.YaGsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author Andrey Mogilev
 */
@Configuration
public class RestConfig extends WebMvcConfigurationSupport {

    @Bean
    public YaGsonHttpMessageConverter yagsonMessageConverter() {
        return new YaGsonHttpMessageConverter(false);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.addDefaultHttpMessageConverters(converters);
        converters.add(yagsonMessageConverter());
    }
}
