package edu.sdsc.awesome.stmData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class LDACMatrix {
	private HashSet<String> vocab;
	private HashMap<String, Integer> vocabMap;
	private ArrayList<String> docs;
	private ArrayList<String> fileIDs;
	private int idx;

	public LDACMatrix() {
		this.vocab = new HashSet<>();
		this.vocabMap = new HashMap<>();
		this.docs = new ArrayList<>();
		this.fileIDs = new ArrayList<>();
		this.idx = 0;
	}

	public void populateVocab(String[]fileList,  String inDir,
			String vocabName) {
		for(int i = 0; i < fileList.length; i++) {
			try {
				Scanner sc = new Scanner(new FileInputStream(inDir + "/" + fileList[i]), 
						StandardCharsets.UTF_8.toString());
				while(sc.hasNext()) {
					vocab.add(sc.next());
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		/* Output vocabularies */
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new
					FileOutputStream(vocabName), 
					StandardCharsets.UTF_8.toString()));
			Iterator<String> it = vocab.iterator();
			while(it.hasNext()) {
				writer.write(it.next());
				writer.println();
			}
			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void populateVocab(String text) {
		Scanner sc = new Scanner(text);
		while(sc.hasNext()) {
			String word = sc.next();
			if(!this.vocabMap.containsKey(word))
				this.vocabMap.put(word, new Integer(this.idx++));
		}
		sc.close();
	}

	public void getMatrix(String[]fileList,  String inDir, String vocabName,
			String matName) {
		HashMap<String, Integer> map = new HashMap<>();
		try {
			Scanner vocSc = new Scanner(new FileInputStream(vocabName), 
					StandardCharsets.UTF_8.toString());
			int index = 0;
			while(vocSc.hasNext())
				map.put(vocSc.next(), index++);
			vocSc.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		ArrayList<HashMap<String, Integer>> list = new ArrayList<>(fileList.length);
		for(int i = 0; i < fileList.length; i++) {
			try {
				Scanner sc = new Scanner(new FileInputStream(inDir + "/" + fileList[i]), 
						StandardCharsets.UTF_8.toString());
				HashMap<String, Integer> locMap = new HashMap<>();
				list.add(locMap);
				while(sc.hasNext()) {
					String term = sc.next();
					if(locMap.containsKey(term))
						locMap.put(term, locMap.get(term).intValue() + 1);
					else
						locMap.put(term, 1);
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		/* Output dtm */
		try {
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(new
					FileOutputStream(matName), 
					StandardCharsets.UTF_8.toString()));
			for(int i = 0; i < list.size(); i++) {
				HashMap<String, Integer> locMap = list.get(i);
				writer.write(locMap.size() + " "); //# of unique words
				Set<String> set = locMap.keySet();
				Iterator<String> it = set.iterator();
				while(it.hasNext()) {
					String key = it.next();
					writer.write(map.get(key).intValue() +
							":" + locMap.get(key));
					if(it.hasNext())
						writer.print(" ");
				}
				writer.println();
			}
			writer.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void updateMatrix(String text, String id) {
		this.populateVocab(text);
		this.fileIDs.add(id);

		Scanner sc = new Scanner(text);
		HashMap<String, Integer> locMap = new HashMap<>();
		while(sc.hasNext()) {
			String term = sc.next();
			if(locMap.containsKey(term))
				locMap.put(term, locMap.get(term).intValue() + 1);
			else
				locMap.put(term, 1);
		}
		sc.close();

		StringBuilder sb = new StringBuilder();
		sb.append(locMap.size() + " ");
		Set<String> set = locMap.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String key = it.next();
			sb.append(this.vocabMap.get(key) +
					":" + locMap.get(key).intValue());
			if(it.hasNext())
				sb.append(" ");
		}
		this.docs.add(sb.toString());
	}

	public ArrayList<String> getMatrix() {
		return this.docs;
	}

	public ArrayList<String> getMappings() {
		return this.fileIDs;
	}
	
	public HashMap<String, Integer> getIndexedVocab() {
		return this.vocabMap;
	}

	public static void main (String[] args) {
		LDACMatrix mat = new LDACMatrix();
		mat.updateMatrix("this is a demo. How is your day?", "1234");
		mat.updateMatrix("this is the second test", "3458");
		System.out.println(mat.getMatrix());
		System.out.println(mat.getMappings());
	}
}