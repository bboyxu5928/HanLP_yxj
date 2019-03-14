package com.hankcs.yjx;


import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

public class NameIdentity {
	
	public static void main(String[] args) {
		
//		9. 中国人名识别
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
	
		
	}

}
