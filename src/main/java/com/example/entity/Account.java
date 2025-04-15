package com.example.entity;

import javax.persistence.*;

// Marks class as entity for spring and specifies table mapping
@Entity
@Table(name="account")
public class Account {
    // Maps accountID to the accountID column in the Account table and as a generated value primary key
    @Column(name="accountId")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;
    private String username;
    private String password;
   
    // Default Constructor
    public Account() {}
  
    // Copy Constructor
    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Copy Constructor with accountID
    public Account(Integer accountId, String username, String password) {
        this.accountId = accountId;
        this.username = username;
        this.password = password;
    }
    
    // Properly named accountID getter
    public Integer getAccountId() {
        return accountId;
    }
    
    // Properly named accountID setter
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    
    // Properly named username getter
    public String getUsername() {
        return username;
    }

    // Properly named username setter
    public void setUsername(String username) {
        this.username = username;
    }
    
    // Properly named password getter
    public String getPassword() {
        return password;
    }
    
    // Properly named password setter
    public void setPassword(String password) {
        this.password = password;
    }
    
    // equals override method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

    // toString override method
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
