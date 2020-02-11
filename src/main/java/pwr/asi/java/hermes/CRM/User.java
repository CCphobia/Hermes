package pwr.asi.java.hermes.CRM;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String login;
	private String name;
	private String surename;
	private String nick;
	private String password;
	private String email;
	
	public User() {}
	
	public User(String login, String name, String surename, String nick, String password, String email) {
		super();
		this.login = login;
		this.name = name;
		this.surename = surename;
		this.nick = nick;
		this.password = password;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurename() {
		return surename;
	}

	public void setSurename(String surename) {
		this.surename = surename;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", name=" + name + ", surename=" + surename + ", nick=" + nick
				+ ", password=" + password + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, login, name, nick, password, surename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(nick, other.nick)
				&& Objects.equals(password, other.password) && Objects.equals(surename, other.surename);
	}


}
