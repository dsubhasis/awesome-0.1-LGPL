/* Generated By:JJTree&JavaCC: Do not edit this line. AdilConstants.java */
package edu.sdsc.adil;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface AdilConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int DIGITS = 6;
  /** RegularExpression Id. */
  int PLUS = 7;
  /** RegularExpression Id. */
  int NOT = 8;
  /** RegularExpression Id. */
  int SEMICOLON = 9;
  /** RegularExpression Id. */
  int COLON = 10;
  /** RegularExpression Id. */
  int EQAL = 11;
  /** RegularExpression Id. */
  int GREATER = 12;
  /** RegularExpression Id. */
  int LESS = 13;
  /** RegularExpression Id. */
  int FBRACKETSTART = 14;
  /** RegularExpression Id. */
  int FBRACKETEND = 15;
  /** RegularExpression Id. */
  int DOT = 16;
  /** RegularExpression Id. */
  int COMMA = 17;
  /** RegularExpression Id. */
  int StartSQBracket = 18;
  /** RegularExpression Id. */
  int EndSQBracket = 19;
  /** RegularExpression Id. */
  int AT = 20;
  /** RegularExpression Id. */
  int OPERATOR = 21;
  /** RegularExpression Id. */
  int MINUS = 22;
  /** RegularExpression Id. */
  int CREATE = 23;
  /** RegularExpression Id. */
  int DELETE = 24;
  /** RegularExpression Id. */
  int ALTER = 25;
  /** RegularExpression Id. */
  int LOAD = 26;
  /** RegularExpression Id. */
  int IMPORT = 27;
  /** RegularExpression Id. */
  int DATASOURCE = 28;
  /** RegularExpression Id. */
  int WITH = 29;
  /** RegularExpression Id. */
  int WHERE = 30;
  /** RegularExpression Id. */
  int AND = 31;
  /** RegularExpression Id. */
  int STORE = 32;
  /** RegularExpression Id. */
  int TYPE = 33;
  /** RegularExpression Id. */
  int PARTITION = 34;
  /** RegularExpression Id. */
  int BY = 35;
  /** RegularExpression Id. */
  int AS = 36;
  /** RegularExpression Id. */
  int TEMPORALRELATION = 37;
  /** RegularExpression Id. */
  int TEMPORALGRAPH = 38;
  /** RegularExpression Id. */
  int ON = 39;
  /** RegularExpression Id. */
  int REPORT = 40;
  /** RegularExpression Id. */
  int ANALYSIS = 41;
  /** RegularExpression Id. */
  int EXECUTE = 42;
  /** RegularExpression Id. */
  int EVERY = 43;
  /** RegularExpression Id. */
  int PROPERTYGRAPH = 44;
  /** RegularExpression Id. */
  int VIEW = 45;
  /** RegularExpression Id. */
  int COLLECTION = 46;
  /** RegularExpression Id. */
  int CONNECTION = 47;
  /** RegularExpression Id. */
  int TO = 48;
  /** RegularExpression Id. */
  int FROM = 49;
  /** RegularExpression Id. */
  int LIBRARY = 50;
  /** RegularExpression Id. */
  int DICTIONARY = 51;
  /** RegularExpression Id. */
  int ONTOLOGY = 52;
  /** RegularExpression Id. */
  int WITNESS = 53;
  /** RegularExpression Id. */
  int PROJECT = 54;
  /** RegularExpression Id. */
  int AWSM = 55;
  /** RegularExpression Id. */
  int NODE = 56;
  /** RegularExpression Id. */
  int NODES = 57;
  /** RegularExpression Id. */
  int PATH = 58;
  /** RegularExpression Id. */
  int SELECT = 59;
  /** RegularExpression Id. */
  int ENV = 60;
  /** RegularExpression Id. */
  int IN = 61;
  /** RegularExpression Id. */
  int ANNOTATE = 62;
  /** RegularExpression Id. */
  int FILTER = 63;
  /** RegularExpression Id. */
  int EXECUTESQLPP = 64;
  /** RegularExpression Id. */
  int CONSTRUCTGRAPH = 65;
  /** RegularExpression Id. */
  int CYPHERQUERY = 66;
  /** RegularExpression Id. */
  int EXECUTECYPHER = 67;
  /** RegularExpression Id. */
  int DIGIT = 68;
  /** RegularExpression Id. */
  int LETTER = 69;
  /** RegularExpression Id. */
  int ALPHANUM = 70;
  /** RegularExpression Id. */
  int FIELDNAME = 71;
  /** RegularExpression Id. */
  int QUOTED_IDENTIFIER = 72;
  /** RegularExpression Id. */
  int GraphElementType = 73;
  /** RegularExpression Id. */
  int INPATH = 74;
  /** RegularExpression Id. */
  int OUTPATH = 75;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\n\"",
    "\"\\r\"",
    "<token of kind 4>",
    "<token of kind 5>",
    "<DIGITS>",
    "\"+\"",
    "\"!\"",
    "\";\"",
    "\":\"",
    "\"=\"",
    "\">\"",
    "\"<\"",
    "\"(\"",
    "\")\"",
    "\".\"",
    "\",\"",
    "\"[\"",
    "\"]\"",
    "\"@\"",
    "<OPERATOR>",
    "\"-\"",
    "\"create\"",
    "\"delete\"",
    "\"alter\"",
    "\"load\"",
    "\"import\"",
    "\"datasource\"",
    "\"with\"",
    "\"where\"",
    "\"and\"",
    "\"store\"",
    "\"type\"",
    "\"partition\"",
    "\"by\"",
    "\"as\"",
    "\"temporalrelation\"",
    "\"temporalgraph\"",
    "\"on\"",
    "\"report\"",
    "\"analysis\"",
    "\"execute\"",
    "\"every\"",
    "\"propertygraph\"",
    "\"view\"",
    "\"collection\"",
    "\"connection\"",
    "\"to\"",
    "\"from\"",
    "\"library\"",
    "\"dictionary\"",
    "\"ontology\"",
    "\"witness\"",
    "\"project\"",
    "\"awsm\"",
    "\"node\"",
    "\"nodes\"",
    "\"path\"",
    "\"select\"",
    "\"env\"",
    "\"in\"",
    "\"annotate\"",
    "\"filter\"",
    "\"executesqlpp\"",
    "\"constructgraph\"",
    "\"cypherquery\"",
    "\"executecypher\"",
    "<DIGIT>",
    "<LETTER>",
    "<ALPHANUM>",
    "<FIELDNAME>",
    "<QUOTED_IDENTIFIER>",
    "<GraphElementType>",
    "<INPATH>",
    "<OUTPATH>",
  };

}
