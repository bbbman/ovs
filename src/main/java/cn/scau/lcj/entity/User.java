package cn.scau.lcj.entity;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userId;
	private String username;
	private String password;
	private String email;
	private Integer userType;
	private Integer enable;
	private Integer isDelete;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}

	/** full constructor */
	public User(String username, String password, String email,Integer userType,Integer enable,Integer isDelete) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.userType = userType;
		this.enable = enable;
		this.isDelete = isDelete;
	}

	// Property accessors
		
	public Integer getUserId() {
		return this.userId;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	

}