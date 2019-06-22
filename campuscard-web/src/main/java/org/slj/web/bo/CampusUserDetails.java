package org.slj.web.bo;

import org.slj.domain.FrontUserStudent;
import org.slj.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Description: SpringSecurity需要的用户详情
 * @create: 2019/5/12
 * @Author: SLJ
 */
public class CampusUserDetails implements UserDetails {

    private FrontUserStudent frontUserStudent;
    private Role role;

    public CampusUserDetails(FrontUserStudent frontUserStudent, Role role) {
        this.frontUserStudent = frontUserStudent;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getType());
        list.add(simpleGrantedAuthority);
        return list;
    }

    @Override
    public String getPassword() {
        return frontUserStudent.getStPassword();
    }

    @Override
    public String getUsername() {
        return frontUserStudent.getStNum();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
