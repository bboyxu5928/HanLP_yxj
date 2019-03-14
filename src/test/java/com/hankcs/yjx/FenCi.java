package com.hankcs.yjx;

import java.io.IOException;
import java.util.List;

import javax.sound.midi.Soundbank;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CoreDictionary.Attribute;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

public class FenCi {

	public static void main(String[] args) throws IOException {
		/*
		 * // 3. NLP分词 // NLP分词NLPTokenizer会执行词性标注和命名实体识别，由结构化感知机序列标注框架支撑。
		 * System.out.println(NLPTokenizer.segment("我新造一个词叫幻想乡你能识别并标注正确词性吗？"));
		 * System.out.println(NLPTokenizer.analyze("我的希望是希望张晚霞的背影被晚霞映红").translateLabels
		 * ()); System.out.println(NLPTokenizer.analyze(
		 * "支援臺灣正體香港繁體：微软公司於1975年由比爾·蓋茲和保羅·艾倫創立。")); // 4. 索引分词 //
		 * 索引分词IndexTokenizer是面向搜索引擎的分词器，能够对长词全切分，另外通过term.offset可以获取单词在文本中的偏移量。 //
		 * 任何分词器都可以通过基类Segment的enableIndexMode方法激活索引模式。 List<Term> termList =
		 * IndexTokenizer.segment("主副食品"); for (Term term : termList) {
		 * System.out.println(term + "[" + term.offset + ":" + (term.offset +
		 * term.word.length()) + "]"); }
		 * 
		 * //5. N-最短路径分词 // N最短路分词器NShortSegment比最短路分词器慢，但是效果稍微好一些，对命名实体识别能力更强。 //
		 * 一般场景下最短路分词的精度已经足够，而且速度比N最短路分词器快几倍，请酌情选择。 Segment nShortSegment = new
		 * NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).
		 * enableOrganizationRecognize(true); Segment shorttestSegment = new
		 * DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).
		 * enableOrganizationRecognize(true); String[] testCase = new String[] {
		 * "今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。", "刘喜杰石国祥会见吴亚琴先进事迹报告团成员", }; for(String
		 * sentence:testCase) {
		 * System.out.println("N-最短分词："+nShortSegment.seg(sentence)+"\n最短路分词："+
		 * shorttestSegment.seg(sentence)); }
		 * 
		 * //6. CRF分词 // CRF对新词有很好的识别能力，但是开销较大。 CRFLexicalAnalyzer analyzer = new
		 * CRFLexicalAnalyzer(); String[] teStrings = new String[] { "商品和服务",
		 * "上海华安工业（集团）公司董事长谭旭光和秘书胡花蕊来到美国纽约现代艺术博物馆参观",
		 * "微软公司於1975年由比爾·蓋茲和保羅·艾倫創立，18年啟動以智慧雲端、前端為導向的大改組。" // 支持繁体中文 }; for(String
		 * sentence:teStrings) { System.out.println(analyzer.analyze(sentence)); }
		 * 
		 * //7. 极速词典分词 // 极速分词是词典最长分词，速度极其快，精度一般。 // 在i7-6700K上跑出了4500万字每秒的速度。 //
		 * 分词速度：10689470.87字每秒 String text="江西鄱阳湖干枯，中国最大淡水湖变成大草原";
		 * System.out.println(SpeedTokenizer.segment(text)); long start =
		 * System.currentTimeMillis(); int pressure = 1000000; for(int i
		 * =0;i<pressure;i++) { SpeedTokenizer.segment(text); } double costTime =
		 * (System.currentTimeMillis()-start)/(double)1000;
		 * System.out.printf("分词速度：%.2f字每秒",text.length()*pressure/costTime);
		 * 
		 * //Ctrl+Shift+/
		 */

		// 8. 用户自定义词典
		String text = "攻城狮逆袭单身狗，迎娶白富美，走上人生巅峰";
		System.out.println("未添加自定义词典前：---------"+HanLP.segment(text));

		// 演示用户词典的动态增删
		// 动态增加
		CustomDictionary.add("攻城狮");
		// 强行插入
		CustomDictionary.insert("白富美", "nz 1014");
		// 删除词语
		// CustomDictionary.remove("攻城狮");
		System.out.println(CustomDictionary.add("单身狗", "nz 1024 n 1"));
		System.out.println(CustomDictionary.get("单身狗"));
		final char[] charArray = text.toCharArray();

		CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
			@Override
			public void hit(int begin, int end, CoreDictionary.Attribute value) {
				System.out.printf("[%d:%d]=%s %s\n", begin, end, new String(charArray, begin, end - begin), value);
			}
		});
		// 自定义词典在所有的分类器中都有效
		System.out.println(HanLP.segment(text));
		
		
		
	}

}
