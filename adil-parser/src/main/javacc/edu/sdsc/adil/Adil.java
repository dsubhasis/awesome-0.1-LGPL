/* Generated By:JJTree&JavaCC: Do not edit this line. Adil.java */
package edu.sdsc.adil;

import java.io.*;
import java.util.*;
import edu.sdsc.awesome.adil.parser.AdilFirstPass;
public class Adil/*@bgen(jjtree)*/implements AdilTreeConstants, AdilConstants {/*@bgen(jjtree)*/
  protected static JJTAdilState jjtree = new JJTAdilState();

public static void main(String[] args) {
    Reader sr = new StringReader(args[0]);
    Adil p = new Adil(sr);
    try {
      SimpleNode node = p.ADILStatement();
     System.out.println(node.toString());
     node.dump(">");

    } catch (ParseException pe) {
      pe.printStackTrace();
    }
  }

  static final public SimpleNode ADILStatement() throws ParseException {
                              /*@bgen(jjtree) ADILStatement */
  ASTADILStatement jjtn000 = new ASTADILStatement(JJTADILSTATEMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case CREATE:
        case DELETE:
        case LOAD:
          ;
          break;
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        Statement();
      }
                  jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                 {if (true) return jjtn000;}
    } catch (Throwable jjte000) {
   if (jjtc000) {
     jjtree.clearNodeScope(jjtn000);
     jjtc000 = false;
   } else {
     jjtree.popNode();
   }
   if (jjte000 instanceof RuntimeException) {
     {if (true) throw (RuntimeException)jjte000;}
   }
   if (jjte000 instanceof ParseException) {
     {if (true) throw (ParseException)jjte000;}
   }
   {if (true) throw (Error)jjte000;}
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
    throw new Error("Missing return statement in Function");
  }

  static final public void Statement() throws ParseException {
                    /*@bgen(jjtree) Statement */
                    ASTStatement jjtn000 = new ASTStatement(JJTSTATEMENT);
                    boolean jjtc000 = true;
                    jjtree.openNodeScope(jjtn000);Token t;
    try {
      if (jj_2_1(2)) {
        CreateStatement();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case LOAD:
          LoadStatement();
          break;
        case DELETE:
          DeleteStatement();
          break;
        default:
          jj_la1[1] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    } catch (Throwable jjte000) {
   if (jjtc000) {
     jjtree.clearNodeScope(jjtn000);
     jjtc000 = false;
   } else {
     jjtree.popNode();
   }
   if (jjte000 instanceof RuntimeException) {
     {if (true) throw (RuntimeException)jjte000;}
   }
   if (jjte000 instanceof ParseException) {
     {if (true) throw (ParseException)jjte000;}
   }
   {if (true) throw (Error)jjte000;}
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
  }

  static final public void CreateStatement() throws ParseException {
                          /*@bgen(jjtree) CreateStatement */
                          ASTCreateStatement jjtn000 = new ASTCreateStatement(JJTCREATESTATEMENT);
                          boolean jjtc000 = true;
                          jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(CREATE);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DATAVERSE:
        CreateDataVerse();
        break;
      case ENV:
        CreatEnvironment();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(SEMICOLON);
    } catch (Throwable jjte000) {
  if (jjtc000) {
    jjtree.clearNodeScope(jjtn000);
    jjtc000 = false;
  } else {
    jjtree.popNode();
  }
  if (jjte000 instanceof RuntimeException) {
    {if (true) throw (RuntimeException)jjte000;}
  }
  if (jjte000 instanceof ParseException) {
    {if (true) throw (ParseException)jjte000;}
  }
  {if (true) throw (Error)jjte000;}
    } finally {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  static final public void LoadStatement() throws ParseException {
                        /*@bgen(jjtree) LoadStatement */
                        ASTLoadStatement jjtn000 = new ASTLoadStatement(JJTLOADSTATEMENT);
                        boolean jjtc000 = true;
                        jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(LOAD);
      jj_consume_token(DIGITS);
      jj_consume_token(SEMICOLON);
                                    jjtree.closeNodeScope(jjtn000, true);
                                    jjtc000 = false;
                                   jjtn000.setImage(t.image);
    } finally {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  static final public void DeleteStatement() throws ParseException {
                          /*@bgen(jjtree) DeleteStatement */
                          ASTDeleteStatement jjtn000 = new ASTDeleteStatement(JJTDELETESTATEMENT);
                          boolean jjtc000 = true;
                          jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(DELETE);
      jj_consume_token(DIGITS);
      jj_consume_token(SEMICOLON);
                                     jjtree.closeNodeScope(jjtn000, true);
                                     jjtc000 = false;
                                    jjtn000.setImage(t.image);
    } finally {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  static final public void CreateDataVerse() throws ParseException {
                          /*@bgen(jjtree) CreateDataVerse */
                          ASTCreateDataVerse jjtn000 = new ASTCreateDataVerse(JJTCREATEDATAVERSE);
                          boolean jjtc000 = true;
                          jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(DATAVERSE);
      t = jj_consume_token(ALPHANUM);
                              jjtree.closeNodeScope(jjtn000, true);
                              jjtc000 = false;
                             jjtn000.setImage(t.image);
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
  }

  static final public void CreatEnvironment() throws ParseException {
                           /*@bgen(jjtree) CreatEnvironment */
                           ASTCreatEnvironment jjtn000 = new ASTCreatEnvironment(JJTCREATENVIRONMENT);
                           boolean jjtc000 = true;
                           jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(ENV);
      t = jj_consume_token(ALPHANUM);
                       System.out.println("test");
                                                       jjtree.closeNodeScope(jjtn000, true);
                                                       jjtc000 = false;
                                                      jjtn000.setImage(t.image);
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
  }

  static final public void UseEnvironment() throws ParseException {
                         /*@bgen(jjtree) UseEnvironment */
                         ASTUseEnvironment jjtn000 = new ASTUseEnvironment(JJTUSEENVIRONMENT);
                         boolean jjtc000 = true;
                         jjtree.openNodeScope(jjtn000);Token t;
    try {
      jj_consume_token(ENV);
      t = jj_consume_token(ALPHANUM);
                       System.out.println("test");
                                                       jjtree.closeNodeScope(jjtn000, true);
                                                       jjtc000 = false;
                                                      jjtn000.setImage(t.image);
    } finally {
   if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
  }

  static final public void Operator() throws ParseException {
                   /*@bgen(jjtree) Operator */
                   ASTOperator jjtn000 = new ASTOperator(JJTOPERATOR);
                   boolean jjtc000 = true;
                   jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DIGITS:
        Operand();
        break;
      case CREATE:
        action();
        t = jj_consume_token(PLUS);
         jjtn000.setImage(t.image);
        Operand();
        break;
      default:
        jj_la1[3] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } catch (Throwable jjte000) {
    if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  static final public void Operand() throws ParseException {
                  /*@bgen(jjtree) Operand */
                  ASTOperand jjtn000 = new ASTOperand(JJTOPERAND);
                  boolean jjtc000 = true;
                  jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(DIGITS);
               jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
              jjtn000.setImage(t.image);
    } finally {
    if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  static final public void action() throws ParseException {
                 /*@bgen(jjtree) action */
                 ASTaction jjtn000 = new ASTaction(JJTACTION);
                 boolean jjtc000 = true;
                 jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(CREATE);
               jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
              jjtn000.setImage(t.image);
    } finally {
  if (jjtc000) {
    jjtree.closeNodeScope(jjtn000, true);
  }
    }
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_3R_2() {
    if (jj_scan_token(CREATE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_3()) {
    jj_scanpos = xsp;
    if (jj_3R_4()) return true;
    }
    return false;
  }

  static private boolean jj_3R_4() {
    if (jj_3R_6()) return true;
    return false;
  }

  static private boolean jj_3R_3() {
    if (jj_3R_5()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_3R_2()) return true;
    return false;
  }

  static private boolean jj_3R_5() {
    if (jj_scan_token(DATAVERSE)) return true;
    return false;
  }

  static private boolean jj_3R_6() {
    if (jj_scan_token(ENV)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public AdilTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x160,0x140,0xc00,0x24,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[1];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Adil(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Adil(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new AdilTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Adil(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AdilTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Adil(AdilTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(AdilTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        exists = true;
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.add(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[15];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 4; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 15; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
