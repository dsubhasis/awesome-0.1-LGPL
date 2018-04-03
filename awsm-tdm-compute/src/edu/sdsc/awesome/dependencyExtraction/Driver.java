package edu.sdsc.awesome.dependencyExtraction;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.sdsc.awesome.stmData.LDACMatrix;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.UniversalEnglishGrammaticalRelations;
import edu.stanford.nlp.trees.international.pennchinese.UniversalChineseGrammaticalRelations;

/**
 * A class for demo phrase extraction using Stanford Parser and HanLP
 * @author Haoran Sun
 * @since 02-25-2018
 */
public class Driver {
	static final String USAGE = "Execute HanLP: java -jar [JAR NAME] -H "
			+ "[INPUT DIR] -P [PARSER]\nParser choices: DEF | index | NShort | CRF\n"
			+ "Execute Stanford Chinese parser: java -jar "
			+ "[JAR NAME] -SZh [INPUT DIR]\n Execute Stanford English parser: java "
			+ "-jar [JAR NAME] -SEn [INPUT DIR]\nGenerate LDA-C matrix: java -jar "
			+ "[JAR NAME] -M [DIR] -v [VOCAB NAME] -m [MATRIX NAME]\n";

	private static void executeStanfordParserZh(String text) {
		DependencyGeneratorZh gen = new DependencyGeneratorZh();
		ArrayList<GrammaticalRelation> criteria = new ArrayList<>();
		criteria.add(UniversalChineseGrammaticalRelations.NOUN_COMPOUND);
		criteria.add(UniversalChineseGrammaticalRelations.ADJECTIVAL_MODIFIER);
		criteria.add(UniversalChineseGrammaticalRelations.CLAUSAL_MODIFIER);
		criteria.add(UniversalChineseGrammaticalRelations.ASSOCIATIVE_MODIFIER);
		criteria.add(UniversalChineseGrammaticalRelations.ORDINAL_MODIFIER);
		/* Order: S, V, O, SV, VO, SO */
		StringBuilder[] res = new StringBuilder[6];
		for(StringBuilder sb: res)
			sb = new StringBuilder();
		gen.generate(text, criteria, res);
	}

	private static void executeStanfordParserEn(String text) {
		DependencyGeneratorEn gen = new DependencyGeneratorEn();
		ArrayList<GrammaticalRelation> criteria = new ArrayList<>();
		criteria.add(UniversalEnglishGrammaticalRelations.NP_ADVERBIAL_MODIFIER);
		criteria.add(UniversalEnglishGrammaticalRelations.COMPOUND_MODIFIER);
		criteria.add(UniversalEnglishGrammaticalRelations.ADJECTIVAL_MODIFIER);
		/* Order: S, V, O, SV, VO, SO */
		StringBuilder[] res = new StringBuilder[6];
		for(StringBuilder sb: res)
			sb = new StringBuilder();
		gen.generate(text, criteria, res);
	}

	/**
	 * Execute one of HanLP dependency parser to get dependency pairs.
	 * @param text - input string to parse
	 * @param option - name of segmenter, by default use neural network
	 * dependency parser with NLPTokenizer; use "index" to use IndexTokenizer;
	 * use "NShort" to use NShortSegment; use "CRF" to use CRF dependency parser.
	 */
	private static void executeHanLPDependencyParser(String text, String 
			option) {
		HanLPDependencyExtractor extr = new HanLPDependencyExtractor();
		/* Order: S, V, O, SV, VO, SO */
		StringBuilder[] res = new StringBuilder[6];
		for(StringBuilder sb: res)
			sb = new StringBuilder();

		extr.buildDep(text, option);
		extr.getSingleComp(res[0], "S");
		extr.getSingleComp(res[1], "V");
		extr.getSingleComp(res[2], "O");
		extr.getSV(res[3]);
		extr.getVO(res[4]);
		extr.getSO(res[5]);
	}

	private static void buildMatrix(String dirPath, String vocabName, String matName) {
		File dir = new File(dirPath);
		String[] fileList = dir.list(); //Get all the files of the source folder
		Arrays.sort(fileList);
		LDACMatrix ldac = new LDACMatrix();
		ldac.populateVocab(fileList, dirPath, vocabName);
		ldac.getMatrix(fileList, dirPath, vocabName, matName);
	}

	private static void buildMatrix(String text, String id) {
		LDACMatrix ldac = new LDACMatrix();
		ldac.updateMatrix(text, id); //Update matrix
		ArrayList<String> rows = ldac.getMatrix(); //Get all rows
		/* Index of this list map to document id */
		ArrayList<String> mappings = ldac.getMappings();
		HashMap<String, Integer> indexedVocab = ldac.getIndexedVocab();
		ldac.clearRows(); //Clear all rows to avoid memory limit error
	}

	public static void main(String[] args) {
		Options options = new Options();
		options.addOption("u", "usage", false, "show usage.");
		options.addOption("H", "HanLP", true, "call HanLp");
		options.addOption("SZh", true, "Execute Stanford Chinese parser.");
		options.addOption("SEn", true, "Execute Stanford English parser.");
		options.addOption("M", true, "Generate ldac matrix");
		options.addOption("v", true, "vocab name");
		options.addOption("m", true, "matrix name");
		options.addOption("P", true, "HanLP parser options");

		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

			if(cmd.hasOption("u")) {
				System.out.println(USAGE);
			} else if(cmd.hasOption("H")) {
				String tokenizer = cmd.getOptionValue("P");
				if(tokenizer == null)
					tokenizer = "DEF";
				Driver.executeHanLPDependencyParser(cmd.getOptionValue("H"), 
						tokenizer);
			} else if(cmd.hasOption("SZh"))
				Driver.executeStanfordParserZh(cmd.getOptionValue("SZh"));
			else if(cmd.hasOption("SEn"))
				Driver.executeStanfordParserEn(cmd.getOptionValue("SEn"));
			else if(cmd.hasOption("M") && cmd.hasOption("v") && cmd.hasOption("m"))
				Driver.buildMatrix(cmd.getOptionValue("M"), cmd.getOptionValue("v"), 
						cmd.getOptionValue("m"));
			else
				System.err.println(USAGE);
		} catch (ParseException e) {
			System.err.println(USAGE);
		}
	}
}