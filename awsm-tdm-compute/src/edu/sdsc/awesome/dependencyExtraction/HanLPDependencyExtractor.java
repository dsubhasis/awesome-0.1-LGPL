package edu.sdsc.awesome.dependencyExtraction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dependency.IDependencyParser;
import com.hankcs.hanlp.dependency.nnparser.NeuralNetworkDependencyParser;
import com.hankcs.hanlp.seg.CRF.CRFSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

/**
 * A class that generates and output dependency pairs based on HanLP
 * dependency parsers. Refactored from previous project.
 * @author Haoran Sun
 * @since 02-25-2018
 */
public class HanLPDependencyExtractor {
	static final String PUNCT= "(?<=[������ ])";
	static final String SV = "SBV";
	static final String VO = "VOB";
	static final String CORE = "HED";

	private LinkedList<CoNLLWord[]> doc;

	/**
	 * Generate dependency graphs from input file.
	 * @param text - input text
	 * @param option - name of segmenter, by default use neural network
	 * dependency parser with NLPTokenizer; use "index" to use IndexTokenizer;
	 * use "NShort" to use NShortSegment; use "CRF" to use CRF dependency parser.
	 */
	public void buildDep(String text, String option) {
		IDependencyParser parser = 
				new NeuralNetworkDependencyParser().enableDeprelTranslator(false);
		if(option.equals("index"))
			parser.setSegment(IndexTokenizer.SEGMENT);
		else if(option.equals("NShort"))
			parser.setSegment(new NShortSegment());
		else if(option.equals("CRF"))
			parser.setSegment(new CRFSegment());
		else
			parser.setSegment(NLPTokenizer.SEGMENT);

		String[] sentences = text.split(PUNCT);
		LinkedList<CoNLLWord[]> doc = new LinkedList<>();
		this.doc = doc;
		/* Add dependency graph of each sentence*/
		for(int j = 0; j < sentences.length; j++) {
			CoNLLSentence sentence = parser.parse(sentences[j].trim());
			CoNLLWord[] wordArray = sentence.getWordArray();
			doc.add(wordArray);
		}
	}

	/**
	 * Read a single component of sentence, i.e. subject, verb, or object.
	 * @param buf - buffer to hold the output of the parser
	 * @param component - "S," "V," or "O"
	 */
	public void getSingleComp(StringBuilder buf, String component) {
		ListIterator<CoNLLWord[]> docIt = this.doc.listIterator();
		while(docIt.hasNext()) {
			CoNLLWord[] wordArray = docIt.next();
			for(CoNLLWord term: wordArray) {
				switch(component) {
				case "S":
					if(term.DEPREL.equals(SV)) {
						buf.append(this.formPhrase(wordArray, term.ID - 1));
						buf.append(" ");
					}
					break;
				case "V":
					if(term.DEPREL.equals(SV)) {
						buf.append(term.HEAD.LEMMA);
						buf.append(" ");
					} else if(term.DEPREL.equals(VO)) {
						buf.append(term.HEAD.LEMMA);
						buf.append(" ");
					}
					break;
				case "O":
					if(term.DEPREL.equals(VO)) {
						buf.append(this.formPhrase(wordArray, term.ID - 1));
						buf.append(" ");
					}
					break;
				}
			}
			buf.append("\n");
		}
	}

	/**
	 * Output subject-object pairs.
	 * @param buf - buffer to hold subject-object relations
	 */
	public void getSO(StringBuilder buf) {
		ListIterator<CoNLLWord[]> docIt = this.doc.listIterator();
		while(docIt.hasNext()) {
			CoNLLWord[] wordArray = docIt.next();
			HashMap<CoNLLWord, CoNLLWord> map = new HashMap<>();
			for(CoNLLWord term: wordArray) {
				if(term.DEPREL.equals(SV))
					map.put(term.HEAD, term);
			}

			for(int k = wordArray.length - 1; k >= 0; k--) {
				if(!wordArray[k].DEPREL.equals(VO)) continue;
				if(wordArray[k].HEAD == null) continue;

				CoNLLWord subject = this.findSbj(wordArray[k].HEAD,
						map);
				if(subject == null) continue;
				buf.append(this.formPhrase(wordArray, subject.ID - 1));
				buf.append("-");
				buf.append(this.formPhrase(wordArray, k));
				buf.append(" ");
			}
		}
		buf.append("\n");
	}

	/**
	 * Output verb-object pairs.
	 * @param buf - buffer to hold subject-object relations
	 */
	public void getVO(StringBuilder buf) {
		ListIterator<CoNLLWord[]> docIt = this.doc.listIterator();
		while(docIt.hasNext()) {
			CoNLLWord[] wordArray = docIt.next();
			HashMap<CoNLLWord, CoNLLWord> map = new HashMap<>();
			for(CoNLLWord term: wordArray) {
				if(term.DEPREL.equals(SV))
					map.put(term.HEAD, term);
			}

			for(int k = wordArray.length - 1; k >= 0; k--) {
				if(!wordArray[k].DEPREL.equals(VO)) continue;
				if(wordArray[k].HEAD == null || 
						(!wordArray[k].HEAD.CPOSTAG.startsWith("v")))
					continue;

				CoNLLWord subject = this.findSbj(wordArray[k].HEAD,
						map);
				if(subject == null) continue;
				buf.append(wordArray[k].HEAD.LEMMA);
				buf.append("-");
				buf.append(this.formPhrase(wordArray, k));
				buf.append(" ");
			}
		}
		buf.append("\n");
	}

	/**
	 * Output subject-verb pairs.
	 * @param buf - buffer to hold subject-object relations
	 */
	public void getSV(StringBuilder buf) {
		ListIterator<CoNLLWord[]> docIt = this.doc.listIterator();
		while(docIt.hasNext()) {
			CoNLLWord[] wordArray = docIt.next();
			HashMap<CoNLLWord, CoNLLWord> map = new HashMap<>();
			for(CoNLLWord term: wordArray) {
				if(term.DEPREL.equals(SV))
					map.put(term.HEAD, term);
			}

			for(int k = wordArray.length - 1; k >= 0; k--) {
				if(!wordArray[k].CPOSTAG.startsWith("v")) continue;
				if(wordArray[k].HEAD == null) continue;

				CoNLLWord subject = this.findSbj(wordArray[k].HEAD,
						map);
				if(subject == null) continue;
				buf.append(this.formPhrase(wordArray, subject.ID - 1));
				buf.append("-");
				buf.append(wordArray[k].LEMMA);
				buf.append(" ");
			}
		}
		buf.append("\n");
	}

	public boolean isPunc(String str) {
		if(str.equals("��") || str.equals("��") || str.equals("��") ||
				str.equals("��") || str.equals("��") || str.equals("��") ||
				str.equals("��") || str.equals(" ") || str.equals("��")
				|| str.endsWith("��"))
			return true;
		return false;
	}

	private CoNLLWord findSbj(CoNLLWord object, HashMap<CoNLLWord, CoNLLWord> map) {
		while(object.HEAD != null) {
			if(map.containsKey(object))
				return map.get(object);
			if(object.DEPREL.equals("COO") || object.DEPREL.equals(VO))
				object = object.HEAD;
			else
				return null;
		}
		return null;
	}

	private String formPhrase(CoNLLWord[] wordArray, int k) {
		StringBuilder sb = new StringBuilder();
		int i = k;
		while(i > 0) {
			if(wordArray[i - 1].POSTAG.startsWith("n"))
				i--;
			else break;
		}
		while(i <= k) {
			sb.append(wordArray[i].LEMMA);
			i++;
		}
		return sb.toString();
	}
}