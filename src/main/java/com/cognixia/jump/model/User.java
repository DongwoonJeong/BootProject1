package com.cognixia.jump.model;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Table(name = "store_user")
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //user id auto increment
	private Long id;
	
	@Column(nullable = false)
	@NotBlank(message = "Please enter name.")
	private String username;
	
	@Column(nullable = false)
	@NotBlank(message = "Please enter password.")
	private String password;
	
	@Column(unique = true,nullable = false)
	@NotBlank(message = "Please enter email.")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
    flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;
	
	@Column(nullable = false)
	@NotBlank(message = "Plese enter the address.")
	private String address;
	// will store the role as a string in the db
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	public User() {
		this.id = -1L;
		this.username = "abc";
		this.password = "123";
		this.email = "abc@gamil.com";
		this.address = "address";
		this.role = role;
		this.enabled = enabled;
	}



	public User(Long id, String username, String password, String email, String address, Role role, boolean enabled) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.role = role;
		this.enabled = enabled;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", role=" + role + ", enabled=" + enabled + "]";
	}

}
