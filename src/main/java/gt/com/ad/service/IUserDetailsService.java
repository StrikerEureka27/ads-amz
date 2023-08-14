package gt.com.ad.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gt.com.ad.data.AdmRol;
import gt.com.ad.data.AdmUser;
import gt.com.ad.data.IAdmUserDao;
import jakarta.transaction.Transactional;

@Service("userDetailsService")
public class IUserDetailsService implements UserDetailsService {

    @Autowired
    IAdmUserDao userdao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdmUser u = userdao.findByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException(username);
        }
        getUserRoles(u);
        return new User(u.getUsername(), u.getPassword(), getUserRoles(u));

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static ArrayList<GrantedAuthority> getUserRoles(AdmUser u) {
        ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        Iterator<AdmRol> i = u.getRoles().iterator();
        while (i.hasNext()) {
            roles.add(new SimpleGrantedAuthority(i.next().getName()));
        }
        return roles;
    }

}
