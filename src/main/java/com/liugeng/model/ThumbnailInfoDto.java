package com.liugeng.model;

import java.util.List;

import lombok.Data;

@Data
public class ThumbnailInfoDto {
	
	private int count;
	private String type;
	private List<ThumbnailsDto> thumbnails;
}
