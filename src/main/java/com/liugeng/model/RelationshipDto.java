package com.liugeng.model;

import lombok.Data;

@Data
public class RelationshipDto {
	
	private int voting;
	private boolean is_author;
	private boolean is_thanked;
	private boolean is_nothelp;
}
