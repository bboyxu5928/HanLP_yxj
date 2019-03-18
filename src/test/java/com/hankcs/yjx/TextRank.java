package com.hankcs.yjx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Administrator
 * TextRank算法提取关键词的Java实现
 * 参考网址
 * http://www.hankcs.com/nlp/textrank-algorithm-to-extract-the-keywords-java-implementation.html
 */
public class TextRank {

	public static void main(String[] args) {
		HashMap<String, Set<String>> words = new HashMap<String, Set<String>>();
		String fileUrl = "D:/words.txt";
		File file = new File(fileUrl);
		String line = null;
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((line = bufferedReader.readLine()) != null) {
				String[] words2 = line.split("=");
				String setStr = words2[1];
				StringBuffer setBuffer = new StringBuffer(setStr);
				setBuffer.replace(0, 1, "");
				int setLeng = setBuffer.length();
				setBuffer.replace(setLeng - 2, setLeng, "");
				Set<String> words3 = new HashSet<String>();
				String[] setArray = String.valueOf(setBuffer).split(", ");
				for (String setChar : setArray) {
					words3.add(setChar);
				}
				words.put(words2[0], words3);
			}

			int max_iter = 5;
			Float d = 0.5f;
			Map<String, Float> score = new HashMap<String, Float>();

			int min_diff = 1;
			for (int i = 0; i < max_iter; ++i) {
				Map<String, Float> m = new HashMap<String, Float>();
				float max_diff = 0;
				for (Map.Entry<String, Set<String>> entry : words.entrySet()) {
					String key = entry.getKey();
					Set<String> value = entry.getValue();
					m.put(key, 1 - d);
					for (String other : value) {
						int size = words.get(other).size();
						if (key.equals(other) || size == 0)
							continue;
						m.put(key, m.get(key) + d / size * (score.get(other) == null ? 0 : score.get(other)));
					}
					max_diff = Math.max(max_diff, Math.abs(m.get(key) - (score.get(key) == null ? 0 : score.get(key))));
				}
				score = m;
				if (max_diff <= min_diff)
					break;
			}

		} catch (Exception e) {
		}

	}

}
