package com.taodaye.search;

import java.util.List;

public class SearchResultObject {

	private int error;
	
	private int counts;
	
	private List<SearchResultRecordDetail> content;

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public List<SearchResultRecordDetail> getContent() {
		return content;
	}

	public void setContent(List<SearchResultRecordDetail> content) {
		this.content = content;
	}
	
	
}
