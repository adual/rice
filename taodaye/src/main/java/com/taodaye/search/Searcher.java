package com.taodaye.search;

import java.util.List;

public interface Searcher {

	public abstract String getApi();
	
	public abstract List<? extends SearchResultObject> search();
}
