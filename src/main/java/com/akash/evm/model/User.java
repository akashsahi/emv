package com.akash.evm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.akash.evm.enums.UserStatusEnum;

@Entity
@Table(name = "USERS")
public class User extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7854870431615651789L;

	@SequenceGenerator(sequenceName = "USERS_SEQ", name = "USERS_SEQ", allocationSize = 1)
	@GeneratedValue(generator = "USERS_SEQ", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private UserStatusEnum status;
	@ManyToOne
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (userId != null)
			builder.append("userId=").append(userId).append(", ");
		if (firstName != null)
			builder.append("firstName=").append(firstName).append(", ");
		if (lastName != null)
			builder.append("lastName=").append(lastName).append(", ");
		if (userName != null)
			builder.append("userName=").append(userName).append(", ");
		if (email != null)
			builder.append("email=").append(email).append(", ");
		if (status != null)
			builder.append("status=").append(status).append(", ");
		if (brand != null)
			builder.append("brand=").append(brand);
		builder.append("]");
		return builder.toString();
	}

}
