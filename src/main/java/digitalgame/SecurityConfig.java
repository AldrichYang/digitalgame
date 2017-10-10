package digitalgame;

import digitalgame.service.impl.UserDetailServiceImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by yh on 17/10/10.
 */
//@Configuration
//启用web安全性
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 通过重载,配置如何通过拦截器保护请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //默认:要是所有进入应用的http请求都要进行认证，也配置spring security支持基于表单的登录以及HTTP Basic方式的认证
//        http.authorizeRequests().anyRequest().authenticated()
//                .and().formLogin().and().httpBasic();



    }

    /**
     * 通过重载,配置user-detail服务
     * 用户存储支撑认证过程
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailServiceImpl());
    }
}
