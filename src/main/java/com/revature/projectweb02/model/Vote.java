package com.revature.projectweb02.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity; 

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Vote {
	@Id 
	@GeneratedValue(strategy = IDENTITY)
	private Long voteId;
	private VoteType voteType;
	@NonNull 
	@ManyToOne(fetch = LAZY) 
	@JoinColumn(name = "postId", referencedColumnName = "postId") 
	private Post post; 
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "userId", referencedColumnName = "userId")
private User user;
}
