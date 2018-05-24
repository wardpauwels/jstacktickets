package be.jstack.ticketing.entity;

import be.jstack.ticketing.util.constants.Authority;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    @Id
    @ApiModelProperty(notes = "The database generated user ID")
    private String id;
    @Indexed(unique = true)
    @ApiModelProperty(notes = "The username of the user", required = true)
    private String username;
    @ApiModelProperty(notes = "The password of the user", required = true)
    private String password;
    @ApiModelProperty(notes = "The email of the user", required = true)
    private String email;
    @ApiModelProperty(notes = "The first name of the user", required = true)
    private String lastName;
    @ApiModelProperty(notes = "The last name of the user", required = true)
    private String firstName;
    @ApiModelProperty(notes = "The phone number of the user")
    private String phoneNumber;
    @ApiModelProperty(notes = "The authority roles of the user")
    private List<GrantedAuthority> authorities;

    public void addAuthority(Authority authority) {
        GrantedAuthority newAuthority = new SimpleGrantedAuthority(authority.toString());
        if (authorities == null) authorities = new ArrayList<>();
        if (!authorities.contains(newAuthority)) authorities.add(newAuthority);
    }

    public void removeAuthority(Authority authority) {
        GrantedAuthority toRemoveAuthority = new SimpleGrantedAuthority(authority.toString());
        if (authorities != null) authorities.remove(toRemoveAuthority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public boolean isStaff() {
        List<String> roleNames = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            roleNames.add(authority.getAuthority().toUpperCase());
        }
        return roleNames.contains(Authority.ADMINISTRATOR.toString()) || roleNames.contains(Authority.MODERATOR.toString());
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}