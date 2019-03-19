package demo.juster.spboot.pojo.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="username",length=20,unique=true,nullable=false)
	private String name;
	@Column(name="pwd",length=255,nullable=false)
	private String pwd;
	
	//多对多映射，用户角色
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    private List<Role> roles;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public User(String name, String password) {
        this.name = name;
        this.pwd = password;
    }

    public User() {
    }
    
	
@Override
public String toString() {
	return "user's id is:" + this.id + " name is:"+this.name ;
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	List<GrantedAuthority> authorities = new ArrayList<>();
    List<Role> roles = this.getRoles();
    for (Role role : roles) {
        authorities.add(new SimpleGrantedAuthority(role.getRolename()));
    }
    return authorities;
}

@Override
public String getUsername() {
	return this.name;
}

@Override
public String getPassword() {
	return this.pwd;
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
