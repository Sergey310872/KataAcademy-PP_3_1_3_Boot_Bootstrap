package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private Byte age;

    private String userName;

    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;

    public User() {
    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public String userRolesToString() {
        return this.roleList.stream().map(r -> ("[" + r.getRoleName() + "]")).collect(Collectors.joining());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
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


    public String toJSONString() {
        String jsonString = "{"
                + "\"id\": " + this.id
                + ", \"name\": \"" + this.name + "\""
                + ", \"lastName\": \"" + this.lastName + "\""
                + ", \"age\": " + this.age
                + ", \"userName\": \"" + this.userName + "\""
                + ", \"password\": \"" + this.password + "\" "
                + ", \"roleList\": [";
        boolean first = true;
        for (Role role : this.roleList) {
            if (!first) {
                jsonString += ", ";
            }
            jsonString +=  "\"" + role.getRoleName() + "\"";
            first = false;
        }
        jsonString += "]}";
        return jsonString;
    }

    public String getRoles() {
        StringBuilder sb = new StringBuilder();
        for (Role role : this.roleList) {
            sb.append(role.getRoleName());
            sb.append(" ");
        }
        return sb.toString();
    }
}
