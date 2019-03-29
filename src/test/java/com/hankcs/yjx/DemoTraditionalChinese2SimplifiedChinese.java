package com.hankcs.yjx;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.suggest.Suggester;

public class DemoTraditionalChinese2SimplifiedChinese {
	public static void main(String[] args) {

		// 18. 简繁转换
		// HanLP能够识别简繁分歧词，比如打印机=印表機。许多简繁转换工具不能区分“以后”“皇后”中的两个“后”字，HanLP可以。

		System.out.println(HanLP.convertToTraditionalChinese("用笔记本电脑写程序"));
		System.out.println(HanLP.convertToSimplifiedChinese("脚踏车，印表機「一隻龍，以後等妳當上皇后，就能買士多啤梨慶祝了」"));

		// 19. 文本推荐
//		文本推荐(句子级别，从一系列句子中挑出与输入句子最相似的那一个)
//		在搜索引擎的输入框中，用户输入一个词，搜索引擎会联想出最合适的搜索词，HanLP实现了类似的功能。
//		可以动态调节每种识别器的权重
		Suggester suggester = new Suggester();
		String[] titleArray =
		        (
		                "威廉王子发表演说 呼吁保护野生动物\n" +
		                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
		                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
		                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
		                "英报告说空气污染带来“公共健康危机”"
		        ).split("\\n");
		for(String title:titleArray) {
			suggester.addSentence(title);
		}
		System.out.println(suggester.suggest("发言", 1));//语义
		System.out.println(suggester.suggest("危机公共", 1));//字符
		System.out.println(suggester.suggest("heigebi", 1));//拼音
		
		
		
//		20. 语义距离
//		演示词向量的训练与应用
//		WordVectorModel wordVectorModel = trainOrloadModel();
		
	}
}
