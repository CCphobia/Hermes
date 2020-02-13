package pwr.asi.java.hermes.CRM;

import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID", nullable = false)
	private long userId;
	
	@Column(name = "LOGIN", nullable = false)
	private String login;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "SURENAME", nullable = false)
	private String surename;
	
	@Column(name = "NICK", nullable = false)
	private String nick;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@Lob
	@Column(name = "AVATAR", nullable = false)
	private String avatar;
	
	@ManyToMany(mappedBy = "usersList")
	private ArrayList<UserGroup> usersGroups;
	
	@ManyToMany(mappedBy = "adminsList")
	private ArrayList<UserGroup> adminsGroups;
	
	public User() {}
	
	public User(String login, String name, String surename, String nick, String password, String email) {
		super();
		this.login = login;
		this.name = name;
		this.surename = surename;
		this.nick = nick;
		this.password = password;
		this.email = email;
		usersGroups = new ArrayList<UserGroup>();
		adminsGroups = new ArrayList<UserGroup>();
	}

	public long getUserId() {
		return userId;
	}

	public void setId(long userId) {
		this.userId = userId;
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

	public ArrayList<UserGroup> getUsersGroups() {
		return usersGroups;
	}

	public void setUsersGroups(ArrayList<UserGroup> userGroups) {
		this.usersGroups = userGroups;
	}
	
	public ArrayList<UserGroup> getAdminsGroups(){
		return adminsGroups;
	}
	
	public void setAdminsGroup(ArrayList<UserGroup> adminsGroups) {
		this.adminsGroups = adminsGroups;
	}
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void createUserGroup(String name, String description) {
		UserGroup ug = new UserGroup(name, description);
		usersGroups.add(ug);
		adminsGroups.add(ug);
		ug.getAdminsList().add(this);
		ug.getUsersList().add(this);
	}
	
	public void addMember(User user, UserGroup ug) throws NotAdminException {
		if(ug.getAdminsList().contains(this))
			ug.getUsersList().add(user);
		else
			throw new NotAdminException("Not authorized");
	}
	
	public void removeMember(User user, UserGroup ug) throws NotAdminException {
		if(ug.getAdminsList().contains(this))
			ug.getUsersList().add(user);
		else
			throw new NotAdminException("Not authorized");
	}
	
	public void addAdmin(User user, UserGroup ug) throws NotAdminException {
		if(ug.getAdminsList().contains(this))
			ug.getAdminsList().add(user);
		else
			throw new NotAdminException("Not authorized");
	}
	
	public void removeAdmin(User user, UserGroup ug) throws NotAdminException {
		if(ug.getAdminsList().contains(this))
			ug.getAdminsList().remove(user);
		else
			throw new NotAdminException("Not authorized");
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", login=" + login + ", name=" + name + ", surename=" + surename + ", nick="
				+ nick + ", password=" + password + ", email=" + email + ", avatar=" + avatar + ", usersGroups="
				+ usersGroups + ", adminsGroups=" + adminsGroups + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminsGroups, avatar, email, login, name, nick, password, surename, userId, usersGroups);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(adminsGroups, other.adminsGroups) && Objects.equals(avatar, other.avatar)
				&& Objects.equals(email, other.email) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(nick, other.nick)
				&& Objects.equals(password, other.password) && Objects.equals(surename, other.surename)
				&& userId == other.userId && Objects.equals(usersGroups, other.usersGroups);
	}
	
}

