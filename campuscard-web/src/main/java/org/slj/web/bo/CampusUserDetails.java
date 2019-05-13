package org.slj.web.bo;

import org.slj.domain.FrontUserStudent;
import org.slj.domain.Permission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * @Description: SpringSecurity需要的用户详情
 * @create: 2019/5/12
 * @Author: SLJ
 */
public class CampusUserDetails implements UserDetails {

    private FrontUserStudent frontUserStudent;
    private List<Permission> permissionList;

    public CampusUserDetails(FrontUserStudent frontUserStudent, List<Permission> permissionList) {
        this.frontUserStudent = frontUserStudent;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
