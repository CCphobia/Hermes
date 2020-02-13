package pwr.asi.java.hermes.CRM;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER_GROUP")
public class UserGroup {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GROUP_ID", nullable = false)
	private long groupId;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Lob
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATION_DATE", nullable = false)
    private Date creationDate;
	
	@Lob
	@Column(name = "AVATAR", nullable = false)
	private String avatar;
	
	@ManyToMany
	@JoinTable(name = "GROUP_USER", joinColumns = @JoinColumn(name = "USER_GROUP_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	private ArrayList<User> usersList;
	
	@ManyToMany
	@JoinTable(name = "GROUP_ADMIN", joinColumns = @JoinColumn(name = "USER_GROUP_ID"), inverseJoinColumns = @JoinColumn(name = "ADMIN_ID"))
	private ArrayList<User> adminsList;
	
	public UserGroup() {}
	
	public UserGroup(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		creationDate = new Date();
		usersList = new ArrayList<User>();
		adminsList = new ArrayList<User>();
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public ArrayList<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}
	
	public ArrayList<User> getAdminsList() {
		return adminsList;
	}

	public void setAdminsList(ArrayList<User> adminsList) {
		this.adminsList = adminsList;
	}
	
	public String getAvatar(){
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "UserGroup [groupId=" + groupId + ", name=" + name + ", description=" + description + ", creationDate="
				+ creationDate + ", avatar=" + avatar + ", usersList=" + usersList + ", adminsList=" + adminsList + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(adminsList, avatar, creationDate, description, groupId, name, usersList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		UserGroup other = (UserGroup) obj;
		return Objects.equals(adminsList, other.adminsList) && Objects.equals(avatar, other.avatar)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& groupId == other.groupId && Objects.equals(name, other.name)
				&& Objects.equals(usersList, other.usersList);
	}
	
}
