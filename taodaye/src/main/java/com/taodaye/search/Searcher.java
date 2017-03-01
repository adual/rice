package com.taodaye.search;

public interface Searcher {

	public abstract String getApi();
	
	public abstract <T> T search(String searchStr, DataProcessor processor);
}
