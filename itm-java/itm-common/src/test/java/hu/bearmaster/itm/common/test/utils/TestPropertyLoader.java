package hu.bearmaster.itm.common.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropertyLoader {
	
	private Properties props;
	
	public TestPropertyLoader(String source) throws IOException {
		props = new Properties();
		InputStream inputStream = TestPropertyLoader.class.getResourceAsStream(source);
        props.load(inputStream);
        inputStream.close();
	}
	
	public String get(String key) {
		return props.getProperty(key);
	}
	
	public long getLong(String key) {
		return Long.valueOf(get(key));
	}
	
	public boolean getBoolean(String key) {
		return Boolean.parseBoolean(get(key));
	}

}
