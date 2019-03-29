package com.hankcs.yjx;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

/**
 * 汉字转拼音
 * 
 * @author hankcs
 */

/*
 * HanLP不仅支持基础的汉字转拼音，还支持声母、韵母、音调、音标和输入法首字母首声母功能。
 * HanLP能够识别多音字，也能给繁体中文注拼音。
 * 最重要的是，HanLP采用的模式匹配升级到AhoCorasickDoubleArrayTrie，
 * 性能大幅提升，能够提供毫秒级的响应速度！
 */
public class DemoPinyin {

	public static void main(String[] args) {
		String text = "重载不是重任";
		List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
		System.out.print("原文,");
		for (char c : text.toCharArray()) {
			System.out.printf("%c,", c);
		}
		System.out.println();

		System.out.print("拼音（数字音调）,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin);
		}
		System.out.println();

		System.out.print("拼音（符号音调）,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin.getPinyinWithToneMark());
		}
		System.out.println();

		System.out.print("拼音（无音调）,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin.getPinyinWithoutTone());
		}
		System.out.println();

		System.out.print("声调,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin.getTone());
		}
		System.out.println();

		System.out.print("声母,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin.getShengmu());
		}
		System.out.println();

		System.out.print("韵母,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin.getYunmu());
		}
		System.out.println();

		System.out.print("输入法头,");
		for (Pinyin pinyin : pinyinList) {
			System.out.printf("%s,", pinyin.getHead());
		}
		System.out.println();
	}

}
