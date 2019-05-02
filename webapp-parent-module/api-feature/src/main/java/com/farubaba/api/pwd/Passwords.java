package com.farubaba.api.pwd;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 当前类不要修改了，如果需要其他的策略，另外编写新类完成。
 * @author farubaba@qq.com
 *
 */
public class Passwords {

	private static String EMPTY = "";
	private static String SPACE = " ";
	private static int MAX_PREFIX_LENGTH = 32;
	private static Map<String,String> startLetters = new HashMap<>();
	private static Map<String,String> endLetters = new HashMap<>();
	private static Map<String,String> specialLetters = new HashMap<>();
	private static Map<String,String> numbers = new HashMap<>();
	private static Map<String,String> actions = new HashMap<>();
	
	
	/**
	 *  "zll,"qp","qxbl","fr"
	 *	"single","notfund","synotfund","3sv"
	 *	"at","dollar","plus","underline"
	 *  "365"
	 *  
	 *  format = letter + action + specialLetter + number
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
		initMetadata();
		generateMagicPwdMap();
	}
	
	private static void initMetadata() {
		//startLetters
		startLetters.put("zll", "violet");
		startLetters.put("qp", "qpalzm");
		startLetters.put("qxbl", "shz");
		startLetters.put("fr", "farubaba");
		//actions
		actions.put("365", "");
		//specialLetters
		specialLetters.put("at", "@");
		specialLetters.put("dollar", "$");
		specialLetters.put("plus", "+");
		specialLetters.put("underline", "_");
		//numbers
		numbers.put("notfund", "404");
		numbers.put("3ynotfund", "31404");
		numbers.put("3sv", "777");
		numbers.put("single", "1111");	
		//endLetters
		endLetters.put("zll", "violet");
		endLetters.put("qp", "qpalzm");
		endLetters.put("qxbl", "shz");
		endLetters.put("fr", "farubaba");
	}
	
	private static Map<String,String> generateMagicPwdMap(){
		Map<String,String> magicPwdMap = new HashMap<>();
		Set<Entry<String,String>> lettersEntrySet = startLetters.entrySet();
		for(Entry<String,String> lettersEntry : lettersEntrySet) {
			String firstKey = lettersEntry.getKey();
			String firstValue = lettersEntry.getValue();
			//actions 目前只有一种delete me action： 365 = "" 
			String secondKey = "365";
			String secondValue = EMPTY;
			//sepcialLetters
			Set<Entry<String,String>> specialLetterEntrySet = specialLetters.entrySet();
			for(Entry<String,String> specialLetterEntry : specialLetterEntrySet) {
				String thirdKey = specialLetterEntry.getKey();
				String thirdValue = specialLetterEntry.getValue();
				//numbers
				Set<Entry<String,String>> numbersEntrySet = numbers.entrySet();
				for(Entry<String,String> numberEntry: numbersEntrySet) {
					String fourthKey = numberEntry.getKey();
					String fourthValue = numberEntry.getValue();
					Set<Entry<String,String>> endLettersEntrySet = endLetters.entrySet();
					for(Entry<String,String> endLetterEntry: endLettersEntrySet) {
						String fifthKey = endLetterEntry.getKey();
						String fifthValue = endLetterEntry.getValue();
						String magicKey = prepareString(firstKey,secondKey,thirdKey,fourthKey,fifthKey);
						String magicValue = prepareString(firstValue,secondValue,thirdValue, fourthValue,fifthValue);
						prettyPrint(magicKey,magicValue);
						magicPwdMap.put(magicKey, magicValue);
					}
				}
			}
		}
		return magicPwdMap;
	}
	
	private static void prettyPrint(String magicKey, String magicValue) {
		if(magicKey != null && magicValue != null) {
			System.out.println(computeSpace(magicKey) + " =   "+ magicValue);
		}
	}
	
	private static String computeSpace(String prefix) {
		if(prefix != null) {
			StringBuilder sb = new StringBuilder(prefix);
			int prefixLen = prefix.length();
			int maxLen = MAX_PREFIX_LENGTH > prefixLen ? MAX_PREFIX_LENGTH : prefixLen;
			int spaceLen = maxLen - prefixLen + 1;
			for(int i=0; i< spaceLen;i++) {
				sb.append(SPACE);
			}
			return sb.toString();
		}
		return EMPTY;
	}
	
	private static String prepareString(String... values) {
		StringBuilder result = new StringBuilder();
		if(values != null) {
			for(String value : values) {
				result.append(value);
			}
		}
		return result.toString();
	}
}
