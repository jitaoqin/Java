package com.qin.domain;
// Generated 2017-10-22 14:53:45 by Hibernate Tools 5.1.4.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TForum generated by hbm2java
 */
@Entity
@Table(name = "t_forum", catalog = "sampledb")
public class Forum implements java.io.Serializable {

	private Integer forumId;
	private String forumName;
	private String forumDesc;

	public Forum() {
	}

	public Forum(String forumName) {
		this.forumName = forumName;
	}

	public Forum(String forumName, String forumDesc) {
		this.forumName = forumName;
		this.forumDesc = forumDesc;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "forum_id", unique = true, nullable = false)
	public Integer getForumId() {
		return this.forumId;
	}

	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}

	@Column(name = "forum_name", nullable = false, length = 150)
	public String getForumName() {
		return this.forumName;
	}

	public void setForumName(String forumName) {
		this.forumName = forumName;
	}

	@Column(name = "forum_desc")
	public String getForumDesc() {
		return this.forumDesc;
	}

	public void setForumDesc(String forumDesc) {
		this.forumDesc = forumDesc;
	}

}