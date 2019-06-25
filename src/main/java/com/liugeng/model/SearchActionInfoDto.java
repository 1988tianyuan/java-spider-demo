package com.liugeng.model;

import lombok.Data;

@Data
public class SearchActionInfoDto {

	private String attached_info_bytes;
	private int lc_idx;
	private String search_hash_id;
	private boolean isfeed;
}
