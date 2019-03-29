package com.hankcs.yjx;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;

/**
 * 
 * @author Administrator
 * 21. 依存句法分析
 *  依存句法分析（MaxEnt和神经网络句法模型需要-Xms1g -Xmx1g -Xmn512m）
 */
public class DemoDependencyParser {
	public static void main(String[] args) {
		CoNLLSentence sentence = HanLP.parseDependency("徐先生还具体帮助他确定了把画雄鹰、松鼠和麻雀作为主攻目标。");
		System.out.println(sentence);
		System.out.println("==========================================");
		for(CoNLLWord word:sentence) {
			System.out.printf("%s --(%s)--> %s\n",word.LEMMA,word.DEPREL,word.HEAD.LEMMA);
		}
		System.out.println("==========================================");

        // 也可以直接拿到数组，任意顺序或逆序遍历
		CoNLLWord[] wordArray = sentence.getWordArray();
		for(int i = wordArray.length-1;i>=0;i--) {
			CoNLLWord word =wordArray[i];
			System.out.printf("%s --(%s)--> %s\n",word.LEMMA,word.DEPREL,word.HEAD.LEMMA);
		}
		System.out.println("==========================================");

        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
		CoNLLWord head = wordArray[12];
		while((head = head.HEAD)!=null) {
			if(head==CoNLLWord.ROOT) System.out.println(head.LEMMA+"yyyyyyyyy");
			
			else System.out.printf("%s --(%s)-->", head.LEMMA,head.DEPREL);
			System.out.println("nnnnnnnn");
		}
	}
}
