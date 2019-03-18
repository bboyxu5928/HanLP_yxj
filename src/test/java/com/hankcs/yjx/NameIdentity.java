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
		
//		11. 日本人名识别
//		目前标准分词器默认关闭了日本人名识别，用户需要手动开启；这是因为日本人名的出现频率较低，但是又消耗性能。
		String[] testCase2 = new String[] {
				  "北川景子参演了林诣彬导演的《速度与激情3》",
			        "林志玲亮相网友:确定不是波多野结衣？",
		};
		Segment segment3 = HanLP.newSegment().enableJapaneseNameRecognize(true);
		for(String sentence:testCase2) {
			List<Term> terms = segment3.seg(sentence);
			System.out.println(terms);
		}
		
//		12. 地名识别
//		目前标准分词器都默认关闭了地名识别，用户需要手动开启；这是因为消耗性能，其实多数地名都收录在核心词典和用户自定义词典中。
//		在生产环境中，能靠词典解决的问题就靠词典解决，这是最高效稳定的方法。
//		建议对命名实体识别要求较高的用户使用感知机词法分析器。
		String[] testCase4 = new String[] {
				 "武胜县新学乡政府大楼门前锣鼓喧天",
			        "蓝翔给宁夏固原市彭阳县红河镇黑牛沟村捐赠了挖掘机",
		};
		Segment segment4 = HanLP.newSegment().enablePlaceRecognize(true);
		for(String sentence:testCase4) {
			List<Term> terms = segment4.seg(sentence);
			System.out.println(terms);
		}
		
//		13. 机构名识别
		String[] testCase5 = new String[]{
			    "我在上海林原科技有限公司兼职工作，",
			    "我经常在台川喜宴餐厅吃饭，",
			    "偶尔去地中海影城看电影。",
			};
		Segment segment5 = HanLP.newSegment().enableOrganizationRecognize(true);
		for(String sentence:testCase5) {
			List<Term> terms = segment5.seg(sentence);
			System.out.println(terms);
		}
		
	}

}
