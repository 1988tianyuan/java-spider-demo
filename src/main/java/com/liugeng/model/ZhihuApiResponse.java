package com.liugeng.model;

import java.util.List;

import lombok.Data;

@Data
public class ZhihuApiResponse {
	
	private PagingDto paging;
	private SearchActionInfoDto search_action_info;
	private List<DataDto> data;
	private List<?> related_search_result;
}
