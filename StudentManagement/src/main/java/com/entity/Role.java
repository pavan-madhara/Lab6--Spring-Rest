package com.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
//	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY, 
//			cascade = {CascadeType.MERGE})
//	@JsonIgnore  //To prevent recursion of getting roles and users while using Get Mapping
//	@Exclude    //To exclude toString method to prevent recursion while using Put & Post mapping
//	private Set<User> users = new HashSet<>();;
//	
//	public void addUser(User user) {
//		if(users == null) {
//			users = new HashSet<>();
//		}
//		this.users.add(user);
//	}

		
}

