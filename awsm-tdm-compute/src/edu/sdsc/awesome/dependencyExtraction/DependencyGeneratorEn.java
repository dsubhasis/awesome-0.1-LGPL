package edu.sdsc.awesome.dependencyExtraction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.UniversalEnglishGrammaticalRelations;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.EnhancedDependenciesAnnotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

/**
 * @author Haoran Sun
 * @since 01-22-2018
 * 
 * A class that generates Universal Chinese Dependencies using Stanford parser
 * and extracts subject phrases, verbs, object phrases, subject-verb pairs,
 * verb-object pairs, and subject-object pairs.
 */
public class DependencyGeneratorEn {
	private StanfordCoreNLP corenlp;
	
	/**
	 * Default constructor. Initialize Stanford parser using settings in
	 * "chinese.properties"
	 */
	public DependencyGeneratorEn() {
		Properties props = PropertiesUtils.asProperties(
				"annotators", "tokenize,ssplit,pos,lemma,parse",
				"ssplit.boundaryTokenRegex", "[.]|[;]|[!?]+",
				"ssplit.newlineIsSentenceBreak", "always");
		this.corenlp = new StanfordCoreNLP(props);
	}
	
	/**
	 * Generate phrases and dependencies using Stanford parser.
	 * @param file - input file to process
	 * @param criteria - phrase extraction criteria
	 * @param outputDirs - a list of output directory names
	 */
	public void generate(String text, List<GrammaticalRelation> criteria, 
			StringBuilder[] outputBufs) {
		String text = text.toLowerCase();
		Annotation document = new Annotation(text);
		this.corenlp.annotate(document);
		
		System.out.println("Processing file: " + file.getName());
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		for(CoreMap sentence: sentences) {
			SemanticGraph dependencies = sentence.get
					(EnhancedDependenciesAnnotation.class);
			this.extractDependencyPairs(dependencies, criteria, outputBufs);
		}
	}
	
	private void extractDependencyPairs(SemanticGraph graph,
			List<GrammaticalRelation> criteria, StringBuilder[] outputBufs) {
		/* Get all specified relationships from graph */
		List<SemanticGraphEdge> list = graph.findAllRelns(
				UniversalEnglishGrammaticalRelations.NOMINAL_SUBJECT);
		
		if(this.handlePassiveSentence(graph, criteria, writers)) return;
		if(list.size() == 0) return; //error condition
		
		ListIterator<SemanticGraphEdge> it = list.listIterator();
		List<SemanticGraphEdge> allObjEdges = graph.findAllRelns
				(UniversalEnglishGrammaticalRelations.DIRECT_OBJECT);
		HashSet<SemanticGraphEdge> allVerbObjs = new HashSet<>();
		allVerbObjs.addAll(allObjEdges);
		HashMap<IndexedWord, String> dict = new HashMap<>();
		
		try {
			while(it.hasNext()) { //Extract word pairs connect to subjects
				LinkedList<SemanticGraphEdge> verbObjects = new LinkedList<>();
				LinkedList<String> subjects = new LinkedList<>();
				SemanticGraphEdge currEdge = it.next();
				IndexedWord subject = currEdge.getDependent(); //Get subject
				subjects.add(this.extendToPhrase(graph, subject, criteria));

				/* Find subjects linked by conjunctions */
				this.findConjunctPhrases(graph, subject, criteria, subjects);

				LinkedList<String> objPhrases = new LinkedList<>();
				this.findObjs(graph, currEdge.getGovernor(), criteria, verbObjects,
						objPhrases); //Find all verb-object relations
				allVerbObjs.removeAll(verbObjects); //Remove all dobj edges found
				this.writeOutput(subjects, objPhrases, verbObjects, outputBufs);
				ListIterator<String> itPhrase = objPhrases.listIterator();
				for(SemanticGraphEdge edge : verbObjects) //Store all verb-object pairs
					dict.put(edge.getGovernor(), itPhrase.next());

				this.handleCopulaSentence(graph, currEdge, subjects, criteria,
						OutputBufs);
			}

			/* Write verb and object afterward to avoid duplication */
			Set<Entry<IndexedWord, String>> voPairs = dict.entrySet();
			for(Entry<IndexedWord, String> entry : voPairs) {
				outputBufs[1].append(entry.getKey().word() + " "); //verb
				outputBufs[2].append(entry.getValue() + " "); //object
			}

			/* Extract the remaining verb-object pairs */
			for(SemanticGraphEdge verbObject: allVerbObjs) {
				outputBufs[1].append(verbObject.getGovernor().word() + " "); //verb
				outputBufs[2].append(verbObject.getDependent().word() + " "); //object
				outputBufs[4].append(verbObject.getGovernor().word() + "-" + 
						verbObject.getDependent().word() + " "); //verb-object
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	private void writeOutput(LinkedList<String> subjects, LinkedList<String>
	objPhrases, LinkedList<SemanticGraphEdge> verbObjects, StringBuilder[]
			outputBufs) {
		HashSet<String> printed = new HashSet<>();
		for(String subject: subjects) {
			try {
				outputBufs[0].append(subject + " ");
				ListIterator<String> it = objPhrases.listIterator();
				for(SemanticGraphEdge verbObject: verbObjects) {
					String objPhrase = it.next();
					String sv = subject + "-" + verbObject.getGovernor().word() + " ";
					if(printed.add(sv))
						outputBufs[3].append(sv); //subject-verb
					String vo = verbObject.getGovernor().word() + "-" + objPhrase + " ";
					if(printed.add(vo))
						outputBufs[4].append(vo); //verb-object
					String so = subject + "-" + objPhrase + " ";
					if(printed.add(so))
						outputBufs[5].append(so); //subject-object
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeOutputPass(LinkedList<String> subjects, LinkedList<String>
	verbs, LinkedList<String> objects, StringBuilder[] outputBufs) {
		try {
			for(String object : objects)
				outputBufs[2].append(object + " ");
			
			for(String verb : verbs) { //Write verbs and verb-object pairs
				outputBufs[1].append(verb + " ");
				for(String object : objects)
					outputBufs[4].append(verb + "-" + object + " ");
			}
			
		 /* Write subjects, subject-verb pairs, and subject-object pairs */
			for(String subject : subjects) {
				outputBufs[0].append(subject + " ");
				for(String verb : verbs)
					outputBufs[3].append(subject + "-" + verb + " ");
				for(String object : objects)
					outputBufs[5].append(subject + "-" + object + " ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void findObjs(SemanticGraph graph, IndexedWord parent, 
			List<GrammaticalRelation> criteria, List<SemanticGraphEdge> verbObjects,
			List<String> objPhrases) {
		Iterator<SemanticGraphEdge> it = graph.outgoingEdgeIterator(parent);
		while(it.hasNext()) {
			SemanticGraphEdge currEdge = it.next();
			if(currEdge.getDependent().index() < parent.index())
				continue;
			if(currEdge.getRelation().equals(UniversalEnglishGrammaticalRelations.
					DIRECT_OBJECT)) {
				LinkedList<SemanticGraphEdge> edges = new LinkedList<>();
				this.findConjunctVerbs(graph, currEdge.getGovernor(), 
						currEdge.getDependent(), edges);
				for(SemanticGraphEdge edge : edges) {
					verbObjects.add(edge);
					objPhrases.add(this.extendToPhrase(graph, edge.getDependent(),
							criteria));
					int count = objPhrases.size();
					this.findConjunctPhrases(graph, edge.getDependent(), criteria,
							objPhrases);
					/* Include corresponding number of verbs */
					for(int i = 0; i < objPhrases.size() - count; i++)
						verbObjects.add(edge);
				}
			} else
				this.findObjs(graph, currEdge.getDependent(), criteria, verbObjects,
						objPhrases);
		}
	}
	
	private void handleCopulaSentence(SemanticGraph graph, SemanticGraphEdge
			currEdge, LinkedList<String> subjects, List<GrammaticalRelation>
	criteria, StringBuilder[] outputBufs) throws IOException {
		IndexedWord cop = graph.getChildWithReln(currEdge.getGovernor(), 
				UniversalEnglishGrammaticalRelations.COPULA);
		if(cop == null) return;
		
		LinkedList<String> objPhrases = new LinkedList<>();
		outputBufs[1].append(cop.word() + " ");
		objPhrases = new LinkedList<>();
		objPhrases.add(this.extendToPhrase(graph, currEdge.getGovernor(),
				criteria));
		this.findConjunctPhrases(graph, currEdge.getGovernor(), criteria,
				objPhrases);
		for(String phrase: subjects) {
			outputBufs[3].append(phrase + "-" + cop.word() + " ");
			for(String objPhrase : objPhrases)
				outputBufs[5].append(phrase + "-" + objPhrase + " ");
		}
		for(String objPhrase : objPhrases) {
			outputBufs[2].append(objPhrase + " ");
			outputBufs[4].append(cop.word() + "-" + objPhrase + " ");
		}
	}
	
	private boolean handlePassiveSentence(SemanticGraph graph, 
			List<GrammaticalRelation> criteria, StringBuilder[] outputBufs) {
		boolean isPassive = false;
		/* Passive subjects should be treated as objects in normal sentence */
		List<SemanticGraphEdge> passSbjs = graph.findAllRelns
				(UniversalEnglishGrammaticalRelations.AUX_PASSIVE_MODIFIER);
		
		ListIterator<SemanticGraphEdge> it = passSbjs.listIterator();
		while(it.hasNext()) {
			SemanticGraphEdge auxpassReln = it.next();
			IndexedWord object = null;
			try {
				object = graph.getNodeByIndex(auxpassReln.getDependent().index() - 1);
			} catch(IllegalArgumentException e) {
				continue;
			}
			
			String pos = object.get(CoreAnnotations.PartOfSpeechAnnotation.class);
			if(!pos.startsWith("N")) continue; //Not a passive sentence
			
			LinkedList<String> verbs = new LinkedList<>();
			LinkedList<String> objPhrases = new LinkedList<>();
			LinkedList<String> nsubjs = new LinkedList<>();
			/* Find subjects (in normal sentence order) if exist */
			List<SemanticGraphEdge> agents = 
					graph.findAllRelns(UniversalEnglishGrammaticalRelations.
							getNmod("agent"));
			if(!agents.isEmpty()) {
				IndexedWord subject = agents.get(0).getDependent();
				nsubjs.add(this.extendToPhrase(graph, subject, criteria));
				this.findConjunctPhrases(graph, subject, criteria, nsubjs);
			}
			
			this.findConjnctVerbsPass(graph, auxpassReln.getGovernor(), verbs);
			objPhrases.add(this.extendToPhrase(graph, object, criteria));
			this.findConjunctPhrases(graph, object, criteria, objPhrases);
			this.writeOutputPass(nsubjs, verbs, objPhrases, outputBufs);
			isPassive = true;
		}
		return isPassive;
	}
	
	private String extendToPhrase(SemanticGraph graph, IndexedWord candidate,
			List<GrammaticalRelation> criteria) {
		LinkedList<IndexedWord> result = new LinkedList<>();
		this.DFS(graph, candidate, criteria, result);
		Collections.sort(result, (a, b) -> a.index() > b.index() ? 1 : -1);
		ListIterator<IndexedWord> iter = result.listIterator(result.size());
		int countDown = result.getLast().index();
		StringBuilder sb = new StringBuilder();
		while(iter.hasPrevious()) { //Iterate from the end
			IndexedWord word = iter.previous();
			if(word.index() != countDown) break; //Check continuity
			sb.insert(0, word.word() + '+');
			countDown--;
		}
		String ret = sb.toString();
		if(ret.endsWith("+"))
			return ret.substring(0, ret.length() - 1);
		return ret;
	}
	
	private void DFS(SemanticGraph graph, IndexedWord parent, 
			List<GrammaticalRelation> criteria, List<IndexedWord> result) {
		result.add(parent);
		Set<IndexedWord> children = graph.getChildrenWithRelns(parent, 
				criteria);
		if(children.size() == 0) return; //Base case
		Iterator<IndexedWord> it = children.iterator();
		while(it.hasNext()) {
			this.DFS(graph, it.next(), criteria, result);
		}
	}
	
	private void findConjunctPhrases(SemanticGraph graph, IndexedWord parent,
			List<GrammaticalRelation> criteria, List<String> result) {
		Set<IndexedWord> children = graph.getChildrenWithReln(parent,
				UniversalEnglishGrammaticalRelations.CONJUNCT);
		Iterator<IndexedWord> it = children.iterator();
		while(it.hasNext()) {
			IndexedWord candidate = it.next();
			LinkedList<IndexedWord> phrases = new LinkedList<>();
			this.DFS(graph, candidate, criteria, phrases);
			Collections.sort(phrases, (a, b) -> a.index() > b.index() ? 1 : -1);
			ListIterator<IndexedWord> iter = phrases.listIterator(phrases.size());
			int countDown = phrases.getLast().index();
			StringBuilder sb = new StringBuilder();
			while(iter.hasPrevious()) { //Iterate from the end
				IndexedWord word = iter.previous();
				if(word.index() != countDown) break; //Check continuity
				sb.insert(0, word.word() + "+");
				countDown--;
			}
			String ret = sb.toString();
			if(ret.endsWith("+"))
				result.add(ret.substring(0, ret.length() - 1));
			else
				result.add(ret);
			
			this.findConjunctPhrases(graph, candidate, criteria, result);
		}
	}
	
	private void findConjunctVerbs(SemanticGraph graph, IndexedWord verb, 
			IndexedWord object, List<SemanticGraphEdge> results) {
		results.add(new SemanticGraphEdge(verb, object,
				UniversalEnglishGrammaticalRelations.DIRECT_OBJECT, 0, true));
		Set<IndexedWord> set = graph.getParentsWithReln(verb, 
				UniversalEnglishGrammaticalRelations.CONJUNCT);
		for(IndexedWord conjVerb: set)
			this.findConjunctVerbs(graph, conjVerb, object, results);
	}
	
	private void findConjnctVerbsPass(SemanticGraph graph, IndexedWord verb,
			List<String> results) {
		results.add(verb.word());
		Set<IndexedWord> set = graph.getChildrenWithReln(verb, 
				UniversalEnglishGrammaticalRelations.CONJUNCT);
		for(IndexedWord conjVerb: set)
			this.findConjnctVerbsPass(graph, conjVerb, results);
	}
}