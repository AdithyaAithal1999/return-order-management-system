package com.roms.microservice.authenticationservice.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailPrincipal implements UserDetails{

    private long id;
    private String username;
    private String password;
    private String creditCardNum; 
    private BigDecimal creditLimit;
    private List<GrantedAuthority> authorities;
    

    public UserDetailPrincipal(long id,String name,String password,String creditCardNum,BigDecimal creditLimit) {
        this.id = id;
        this.username = name;
        this.password =password;
        this.creditCardNum=creditCardNum;
        this.creditLimit=creditLimit;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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
