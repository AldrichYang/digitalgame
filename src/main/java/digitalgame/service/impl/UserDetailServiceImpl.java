package digitalgame.service.impl;

import digitalgame.common.Constants;
import digitalgame.dao.AdminInfoMapper;
import digitalgame.model.po.AdminInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by yh on 17/10/18.
 */
public class UserDetailServiceImpl implements UserDetailsService {

    private final AdminInfoMapper adminInfoMapper;
    public UserDetailServiceImpl(AdminInfoMapper adminInfoMapper){
        this.adminInfoMapper=adminInfoMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AdminInfo adminInfo = adminInfoMapper.selectByName(userName);
        if (!Objects.isNull(adminInfo)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (adminInfo.getAdminName().equals(Constants.SYSTEM_ADMIN_NAME)) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new User(adminInfo.getAdminName(), adminInfo.getPassWord(), authorities);
        } else {
            throw new UsernameNotFoundException("用户" + userName + "不存在");
        }
    }
}
