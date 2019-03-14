package com.hankcs.yjx;


import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

public class NameIdentity {
	
	public static void main(String[] args) {
		
//		9. 中文人名识别
		String[] testCase = new String[] {
				"签约仪式前，秦光荣、李纪恒、仇和,杨利伟刘春光等一同会见了参加签约的企业家。",
		        "王国强、高峰、汪洋、张朝阳光着头、韩寒、小四",
		        "张浩和胡健康复员回家了",
		        "王总和小丽结婚了",
		        "编剧邵钧林和稽道青说",
		        "这里有关天培的有关事迹",
		        "龚学平等领导,邓颖超生前",
		};
		Segment segment = HanLP.newSegment().enableNameRecognize(true);
		for(String sentence:testCase) {
			List<Term> termList = segment.seg(sentence);
			System.out.println(termList);
		}
		
//		10. 音译人名识别
		String[] testCase1 = new String[] {
				 "一桶冰水当头倒下，微软的比尔盖茨、Facebook的扎克伯格跟桑德博格、亚马逊的贝索斯、苹果的库克全都不惜湿身入镜，这些硅谷的科技人，飞蛾扑火似地牺牲演出，其实全为了慈善。",
	                "世界上最长的姓名是简森·乔伊·亚历山大·比基·卡利斯勒·达夫·埃利奥特·福克斯·伊维鲁莫·马尔尼·梅尔斯·帕特森·汤普森·华莱士·普雷斯顿。",
	       
		};
		Segment segment2 = HanLP.newSegment().enableTranslatedNameRecognize(true);
		for(String sentence:testCase1) {
			List<Term> terms = segment2.seg(sentence);
			System.out.println(terms);
		}
		
	}

}
