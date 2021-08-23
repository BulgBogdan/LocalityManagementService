package bulgakov.locality;

import bulgakov.locality.filter.AdminFilter;
import bulgakov.locality.filter.AuthFilter;
import bulgakov.locality.filter.ChairmenFilter;
import bulgakov.locality.filter.CharsetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class FilterConfig {

    private final AdminFilter adminFilter;
    private final AuthFilter authFilter;
    private final ChairmenFilter chairmenFilter;
    private final CharsetFilter charsetFilter;

    @Autowired
    public FilterConfig(AdminFilter adminFilter, AuthFilter authFilter,
                        ChairmenFilter chairmenFilter, CharsetFilter charsetFilter) {
        this.adminFilter = adminFilter;
        this.authFilter = authFilter;
        this.chairmenFilter = chairmenFilter;
        this.charsetFilter = charsetFilter;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFil() {
        FilterRegistrationBean<AdminFilter> adminFilterReg = new FilterRegistrationBean<>();
        adminFilterReg.setFilter(adminFilter);
        adminFilterReg.setUrlPatterns(
                Arrays.asList("/edit/user", "/delete/user", "/users"));
        adminFilterReg.setDispatcherTypes(DispatcherType.REQUEST);
        return adminFilterReg;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFil() {
        FilterRegistrationBean<AuthFilter> authFilterReg = new FilterRegistrationBean<>();
        authFilterReg.setFilter(authFilter);
        authFilterReg.setUrlPatterns(Arrays.asList("/locality", "/infrastructure", "/home", "/cabinet"));
        authFilterReg.setDispatcherTypes(DispatcherType.REQUEST);
        return authFilterReg;
    }

    @Bean
    public FilterRegistrationBean<ChairmenFilter> chairmenFil() {
        FilterRegistrationBean<ChairmenFilter> chairmenFilterReg = new FilterRegistrationBean<>();
        chairmenFilterReg.setFilter(chairmenFilter);
        chairmenFilterReg.setUrlPatterns(Arrays.asList("/create/infrastructure", "/create/locality",
                "/delete/infrastructure", "/delete/locality", "/edit/infrastructure", "/edit/locality"));
        chairmenFilterReg.setDispatcherTypes(DispatcherType.REQUEST);
        return chairmenFilterReg;
    }

    @Bean
    public FilterRegistrationBean<CharsetFilter> charsetFil() {
        FilterRegistrationBean<CharsetFilter> charsetFilterReg = new FilterRegistrationBean<>();
        charsetFilterReg.setFilter(charsetFilter);
        charsetFilterReg.setUrlPatterns(Collections.singletonList("/*"));
        charsetFilterReg.setDispatcherTypes(DispatcherType.REQUEST);
        return charsetFilterReg;
    }

}
