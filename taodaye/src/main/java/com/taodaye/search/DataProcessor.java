package com.taodaye.search;

import java.io.InputStream;

public interface DataProcessor {

	<T> T process(InputStream in, Class<T> clazz);
}
