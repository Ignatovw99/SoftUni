//package residentevil.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import residentevil.web.interceptors.PreAuthenticationInterceptor;
//
//@Configuration
//public class ApplicationWebConfiguration implements WebMvcConfigurer {
//
//    private PreAuthenticationInterceptor preAuthenticationInterceptor;
//
//    public ApplicationWebConfiguration(PreAuthenticationInterceptor preAuthenticationInterceptor) {
//        this.preAuthenticationInterceptor = preAuthenticationInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(this.preAuthenticationInterceptor);
//    }
//}
