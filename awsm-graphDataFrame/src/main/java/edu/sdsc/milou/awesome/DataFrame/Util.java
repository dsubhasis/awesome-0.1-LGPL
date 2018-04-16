package edu.sdsc.milou.awesome.DataFrame;

public class Util {

	public static String findPrimaryKey(String node) {

		String key = null;

		switch (node) {
		case "tweet":
			key = "tid";
			break;
		case "user":
			key = "uid";
			break;
		case "hashtag":
			key = "name";
			break;
		}

		return key;

	}
	
	public static String formatString(String node, String name)
	{
		String text = null;

		switch (node) {
		case "tweet":
			text = name;
			break;
		case "user":
			text = name;
			break;
		case "hashtag":
			text = "\""+name+"\"";
			break;
		}

		
		return text;
		
	}

}
