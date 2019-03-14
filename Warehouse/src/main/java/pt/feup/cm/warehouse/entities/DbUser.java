package pt.feup.cm.warehouse.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@NamedQueries({
	@NamedQuery(name = "User.getAll", query = "SELECT u from DbUser u"),
	@NamedQuery(name = "User.getByName", query = "SELECT u from DbUser u WHERE u.name = :name")
})
public class DbUser {

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "fis_num")
	private String fisNum;

	@Column(name = "password")
	private String password;

	public DbUser() {
	}

	public DbUser(String name, String password, String fisNum) {
		super();
		this.name = name;
		this.password = password;
		this.fisNum = fisNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFisNum() {
		return fisNum;
	}

	public void setFisNum(String fisNum) {
		this.fisNum = fisNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", fisNum=" + fisNum + ", password=" + password;
	}

}
