package edu.sdsc.awesome.stmData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;


public class LDACMatrix {
	private HashMap<String, Triplet> vocabMap;
	private HashSet<String> exclusion;
	private LinkedList<Row> docs;
	private LinkedList<String> fileIDs;
	private int idx;
	private boolean modified;
	
	private class Triplet implements Serializable {
		private int index;
		private int freq;
		private int numDoc;
		
		protected Triplet() {
			this.index = 0; //Index actually starts at 1
			this.freq = 0;
			this.numDoc = 0;
		}
		
		protected Triplet(int index, int freq, int numDoc) {
			this.index = index;
			this.freq = freq;
			this.numDoc = numDoc;
		}
		
		protected int getIndex() {
			return this.index;
		}
		
		protected int getFreq() {
			return this.freq;
		}
		
		protected int numDoc() {
			return this.numDoc;
		}
		
		protected void setIndex(int index) {
			this.index = index;
		}
		
		protected void setFreq(int freq) {
			this.freq = freq;
		}
		
		protected void setNumDoc(int numDoc) {
			this.numDoc = numDoc;
		}
	}
	
	private class Pair implements Serializable {
		private String term;
		private int freq;
		
		protected Pair(String term, int freq) {
			this.term = term;
			this.freq = freq;
		}
		
		protected void setFreq(int freq) {
			this.freq = freq;
		}
		
		protected String getTerm() {
			return this.term;
		}
		
		protected int getFreq() {
			return this.freq;
		}
	}
	
	private class Row implements Serializable {
		private int uniqueTerms;
		private LinkedList<Pair> terms;
		
		protected Row() {
			this.uniqueTerms = 0;
			this.terms = new LinkedList<>();
		}
		
		protected void addTerm(Pair pair) {
			this.uniqueTerms++;
			this.terms.add(pair);
		}
		
		protected int uniqueTerms() {
			return this.uniqueTerms;
		}
		
		protected LinkedList<Pair> getTerms() {
			return this.terms;
		}
	}

	public LDACMatrix() {
		this.vocabMap = new HashMap<>();
		this.exclusion = new HashSet<>();
		this.docs = new LinkedList<>();
		this.fileIDs = new LinkedList<>();
		this.idx = 0;
		this.modified = false;
	}
	
	/**
	 * Set exclusion term list; term in this list will not be included as vocab
	 * @param exclList - a list of exclusion terms
	 */
	public void setExclusion(List<String> exclList) {
		ListIterator<String> it = exclList.listIterator();
		while(it.hasNext())
			this.exclusion.add(it.next());
	}

	private void populateVocab(String text) {
		Scanner sc = new Scanner(text);
		while(sc.hasNext()) {
			String word = sc.next();
			if(!this.vocabMap.containsKey(word))
				this.vocabMap.put(word, new Triplet(0, 0, 1));
		}
		sc.close();
	}

	public void updateMatrix(String text, String id) {
		Scanner sc = new Scanner(text);
		/* Histogram for a document */
		HashMap<String, Integer> locMap = new HashMap<>();
		while(sc.hasNext()) {
			String term = sc.next();
			if(this.exclusion.contains(term)) continue;
			
			if(locMap.containsKey(term))
				locMap.put(term, locMap.get(term).intValue() + 1);
			else
				locMap.put(term, 1);
		}
		sc.close();
		
		if(locMap.size() == 0) return;

		Set<String> terms = locMap.keySet();
		Iterator<String> it = terms.iterator();
		Row row = new Row();
		/* Iterate over the histogram */
		while(it.hasNext()) {
			String term = it.next();
			/* Update frequency */
			if(this.vocabMap.containsKey(term)) {
				Triplet record = this.vocabMap.get(term);
				record.setFreq(record.getFreq() + locMap.get(term).intValue());
				record.setNumDoc(record.numDoc() + 1);
			} else /* Create a new triplet if a term is not present */
				this.vocabMap.put(term, new Triplet(0, locMap.get(term).intValue(),
						1));
			
			row.addTerm(new Pair(term, locMap.get(term)));
		}
		this.fileIDs.add(id);
		this.docs.add(row);
	}
	
	public int rowSum(String term) {
		return this.vocabMap.get(term).numDoc;
	}
	
	public int colSum(int index) {
		return this.docs.get(index).uniqueTerms();
	}

	public LinkedList<String> getMatrix() {
		LinkedList<String> list = new LinkedList<>();
		if(modified) { //Re-index if some vocabularies have been removed
			idx = 0;
			for(Triplet triplet: this.vocabMap.values())
				triplet.setIndex(0);
			
			for(Row row: this.docs) {
				StringBuilder sb = new StringBuilder();
				sb.append(row.uniqueTerms());
				for(Pair pair: row.getTerms()) {
					Triplet triplet = this.vocabMap.get(pair.getTerm());
					if(triplet.getIndex() == 0) {
						sb.append(" " + idx + ":" + pair.getFreq());
						triplet.setIndex(idx++);
					} else
						sb.append(" " + triplet.getIndex() + ":" + pair.getFreq());
				}
				list.add(sb.toString());
			}
			this.modified = false;
		} else {
			for(Row row: this.docs) {
				StringBuilder sb = new StringBuilder();
				sb.append(row.uniqueTerms());
				for(Pair pair: row.getTerms()) {
					Triplet triplet = this.vocabMap.get(pair.getTerm());
				  sb.append(" " + triplet.getIndex() + ":" + pair.getFreq());
				}
				list.add(sb.toString());
			}
		}
		return list;
	}

	public LinkedList<String> getMappings() {
		return this.fileIDs;
	}
	
	public void clearRows() {
		this.docs.clear();
		this.fileIDs.clear();
	}

	public static void main (String[] args) {
		LDACMatrix mat = new LDACMatrix();
		mat.updateMatrix("this is a demo. How is your day?", "1234");
		mat.updateMatrix("this is the second test", "3458");
		System.out.println(mat.getMatrix());
		System.out.println(mat.getMappings());
	}
}