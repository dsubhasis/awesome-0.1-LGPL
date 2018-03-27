package edu.sdsc.awesome.dependencyExtraction;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
	/**
	 * Execute Stanford Parser to get dependency pairs.
	 * @param dirPath - directory of input files
	 */
	private static void executeStanfordParserZh(String dirPath) {
		File dir = new File(dirPath);
		DependencyGeneratorZh gen = new DependencyGeneratorZh();
		ArrayList<GrammaticalRelation> criteria = new ArrayList<>();
		criteria.add(UniversalChineseGrammaticalRelations.NOUN_COMPOUND);
		criteria.add(UniversalChineseGrammaticalRelations.ADJECTIVAL_MODIFIER);
		criteria.add(UniversalChineseGrammaticalRelations.CLAUSAL_MODIFIER);
		criteria.add(UniversalChineseGrammaticalRelations.ASSOCIATIVE_MODIFIER);
		criteria.add(UniversalChineseGrammaticalRelations.ORDINAL_MODIFIER);
		String[] outputDirs = {"S/", "V/", "O/", "SV/", "VO/", "SO/"};
		for(String depType: outputDirs) {
			File directory = new File(depType.substring(0, depType.length() - 1));
			directory.mkdir();
		}
		for(final File file : dir.listFiles())
			gen.generate(file, criteria, outputDirs);
	}

	private static void executeStanfordParserEn(String dirPath) {
		File dir = new File(dirPath);
		DependencyGeneratorEn gen = new DependencyGeneratorEn();
		ArrayList<GrammaticalRelation> criteria = new ArrayList<>();
		criteria.add(UniversalEnglishGrammaticalRelations.NP_ADVERBIAL_MODIFIER);
		criteria.add(UniversalEnglishGrammaticalRelations.COMPOUND_MODIFIER);
		criteria.add(UniversalEnglishGrammaticalRelations.ADJECTIVAL_MODIFIER);
		String[] outputDirs = {"S/", "V/", "O/", "SV/", "VO/", "SO/"};
		for(String outDir: outputDirs) {
			File out = new File(outDir.substring(0, outDir.length() - 1));
			out.mkdir();
		}
		for(final File file : dir.listFiles())
			gen.generate(file, criteria, outputDirs);
	}

	/**
	 * Execute one of HanLP dependency parser to get dependency pairs.
	 * @param dirPath - directory of input files
	 * @param option - name of segmenter, by default use neural network
	 * dependency parser with NLPTokenizer; use "index" to use IndexTokenizer;
	 * use "NShort" to use NShortSegment; use "CRF" to use CRF dependency parser.
	 */
	private static void executeHanLPDependencyParser(String dirPath, String 
			option) {
		File dir = new File(dirPath);
		HanLPDependencyExtractor extr = new HanLPDependencyExtractor();
		String[] outputDirs = {"S", "V", "O", "SV", "VO", "SO"};
		for(String depType: outputDirs) {
			File directory = new File(depType);
			directory.mkdir();
		}
		for(final File file : dir.listFiles()) {
			extr.buildDep(file.getPath(), option);
			extr.getSingleComp(outputDirs[0], file.getName(), "S");
			extr.getSingleComp(outputDirs[1], file.getName(), "V");
			extr.getSingleComp(outputDirs[2], file.getName(), "O");
			extr.getSV(outputDirs[3], file.getName());
			extr.getVO(outputDirs[4], file.getName());
			extr.getSO(outputDirs[5], file.getName());
		}
	}

	private static void buildMatrix(String dirPath, String vocabName, String matName) {
		File dir = new File(dirPath);
		String[] fileList = dir.list(); //Get all the files of the source folder
		Arrays.sort(fileList);
		LDACMatrix ldac = new LDACMatrix();
		ldac.populateVocab(fileList, dirPath, vocabName);
		ldac.getMatrix(fileList, dirPath, vocabName, matName);
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