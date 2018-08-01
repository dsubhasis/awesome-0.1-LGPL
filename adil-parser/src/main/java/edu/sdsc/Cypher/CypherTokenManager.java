/* Generated By:JJTree&JavaCC: Do not edit this line. CypherTokenManager.java */
package edu.sdsc.Cypher;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonArrayBuilder;
import static edu.sdsc.awesome.adil.parser.StatementOperation.CypherUtil.*;

/** Token Manager. */
public class CypherTokenManager implements CypherConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 32:
         jjmatchedKind = 1;
         return jjMoveNfa_0(0, 0);
      case 33:
         return jjMoveStringLiteralDfa1_0(0x2000000000L);
      case 34:
         jjmatchedKind = 24;
         return jjMoveNfa_0(0, 0);
      case 35:
         jjmatchedKind = 31;
         return jjMoveNfa_0(0, 0);
      case 37:
         jjmatchedKind = 15;
         return jjMoveNfa_0(0, 0);
      case 40:
         jjmatchedKind = 18;
         return jjMoveNfa_0(0, 0);
      case 41:
         jjmatchedKind = 19;
         return jjMoveNfa_0(0, 0);
      case 42:
         jjmatchedKind = 16;
         return jjMoveNfa_0(0, 0);
      case 43:
         jjmatchedKind = 17;
         return jjMoveNfa_0(0, 0);
      case 44:
         jjmatchedKind = 27;
         return jjMoveNfa_0(0, 0);
      case 45:
         jjmatchedKind = 14;
         return jjMoveNfa_0(0, 0);
      case 46:
         jjmatchedKind = 28;
         return jjMoveNfa_0(0, 0);
      case 47:
         jjmatchedKind = 12;
         return jjMoveNfa_0(0, 0);
      case 58:
         jjmatchedKind = 26;
         return jjMoveNfa_0(0, 0);
      case 59:
         jjmatchedKind = 30;
         return jjMoveNfa_0(0, 0);
      case 60:
         jjmatchedKind = 32;
         return jjMoveStringLiteralDfa1_0(0x4400000000L);
      case 61:
         jjmatchedKind = 36;
         return jjMoveNfa_0(0, 0);
      case 62:
         jjmatchedKind = 33;
         return jjMoveStringLiteralDfa1_0(0x800000000L);
      case 63:
         jjmatchedKind = 29;
         return jjMoveNfa_0(0, 0);
      case 64:
         jjmatchedKind = 25;
         return jjMoveNfa_0(0, 0);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x80000000020L);
      case 66:
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0xcL);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 71:
         return jjMoveStringLiteralDfa1_0(0x100000000000L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x200L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x400000000000L);
      case 77:
         return jjMoveStringLiteralDfa1_0(0x180L);
      case 79:
         return jjMoveStringLiteralDfa1_0(0x10L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 85:
         return jjMoveStringLiteralDfa1_0(0x10000000040L);
      case 91:
         jjmatchedKind = 20;
         return jjMoveNfa_0(0, 0);
      case 93:
         jjmatchedKind = 21;
         return jjMoveNfa_0(0, 0);
      case 94:
         jjmatchedKind = 10;
         return jjMoveNfa_0(0, 0);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x80000000020L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x200000000000L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0xcL);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x100000000000L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x2200L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x400000000000L);
      case 109:
         return jjMoveStringLiteralDfa1_0(0x180L);
      case 111:
         return jjMoveStringLiteralDfa1_0(0x10L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0x20000000000L);
      case 117:
         return jjMoveStringLiteralDfa1_0(0x10000000040L);
      case 123:
         jjmatchedKind = 22;
         return jjMoveNfa_0(0, 0);
      case 124:
         return jjMoveStringLiteralDfa1_0(0x800L);
      case 125:
         jjmatchedKind = 23;
         return jjMoveNfa_0(0, 0);
      case 126:
         return jjMoveStringLiteralDfa1_0(0x8000000000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 0);
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x400000000L) != 0L)
         {
            jjmatchedKind = 34;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x800000000L) != 0L)
         {
            jjmatchedKind = 35;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x2000000000L) != 0L)
         {
            jjmatchedKind = 37;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 39;
            jjmatchedPos = 1;
         }
         break;
      case 62:
         if ((active0 & 0x4000000000L) != 0L)
         {
            jjmatchedKind = 38;
            jjmatchedPos = 1;
         }
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000100L);
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0xc00000000000L);
      case 78:
         if ((active0 & 0x10L) != 0L)
         {
            jjmatchedKind = 4;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000040L);
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0x8L);
      case 82:
         return jjMoveStringLiteralDfa2_0(active0, 0x140000000004L);
      case 83:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 43;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 89:
         if ((active0 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 45;
            jjmatchedPos = 1;
         }
         break;
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x80L);
      case 100:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x20000000100L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0xc00000000000L);
      case 110:
         if ((active0 & 0x10L) != 0L)
         {
            jjmatchedKind = 4;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000040L);
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x8L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x140000000004L);
      case 115:
         if ((active0 & 0x200L) != 0L)
         {
            jjmatchedKind = 9;
            jjmatchedPos = 1;
         }
         else if ((active0 & 0x80000000000L) != 0L)
         {
            jjmatchedKind = 43;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x20L);
      case 121:
         if ((active0 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 45;
            jjmatchedPos = 1;
         }
         break;
      case 124:
         if ((active0 & 0x800L) != 0L)
         {
            jjmatchedKind = 11;
            jjmatchedPos = 1;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 1);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 1);
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa3_0(active0, 0x4L);
      case 71:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000000L);
      case 73:
         return jjMoveStringLiteralDfa3_0(active0, 0x40L);
      case 76:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000000000L);
      case 77:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000000L);
      case 78:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000008L);
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x140000000000L);
      case 82:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      case 83:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      case 84:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x4L);
      case 103:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000000000L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x2040L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x20000000000L);
      case 109:
         return jjMoveStringLiteralDfa3_0(active0, 0x400000000000L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x10000000008L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x140000000000L);
      case 114:
         return jjMoveStringLiteralDfa3_0(active0, 0x100L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x20L);
      case 116:
         return jjMoveStringLiteralDfa3_0(active0, 0x80L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 2);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 2);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 2);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x4L);
      case 67:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 69:
         return jjMoveStringLiteralDfa4_0(active0, 0x30000000020L);
      case 71:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 73:
         return jjMoveStringLiteralDfa4_0(active0, 0xc00000000000L);
      case 77:
         if ((active0 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 3;
         }
         break;
      case 81:
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 83:
         return jjMoveStringLiteralDfa4_0(active0, 0x8L);
      case 85:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000000L);
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x4L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x80L);
      case 101:
         return jjMoveStringLiteralDfa4_0(active0, 0x30000000020L);
      case 103:
         return jjMoveStringLiteralDfa4_0(active0, 0x100L);
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0xc00000000000L);
      case 109:
         if ((active0 & 0x40000000000L) != 0L)
         {
            jjmatchedKind = 42;
            jjmatchedPos = 3;
         }
         break;
      case 113:
         return jjMoveStringLiteralDfa4_0(active0, 0x40L);
      case 115:
         return jjMoveStringLiteralDfa4_0(active0, 0x8L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x100000000000L);
      case 118:
         if ((active0 & 0x2000L) != 0L)
         {
            jjmatchedKind = 13;
            jjmatchedPos = 3;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 3);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 3);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 3);
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L);
      case 69:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 8;
            jjmatchedPos = 4;
         }
         break;
      case 72:
         if ((active0 & 0x80L) != 0L)
         {
            jjmatchedKind = 7;
            jjmatchedPos = 4;
         }
         break;
      case 80:
         if ((active0 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 4;
         }
         break;
      case 82:
         return jjMoveStringLiteralDfa5_0(active0, 0x20L);
      case 83:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000000L);
      case 84:
         if ((active0 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x80000000000cL);
      case 85:
         return jjMoveStringLiteralDfa5_0(active0, 0x40L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x20000000000L);
      case 101:
         if ((active0 & 0x100L) != 0L)
         {
            jjmatchedKind = 8;
            jjmatchedPos = 4;
         }
         break;
      case 104:
         if ((active0 & 0x80L) != 0L)
         {
            jjmatchedKind = 7;
            jjmatchedPos = 4;
         }
         break;
      case 112:
         if ((active0 & 0x100000000000L) != 0L)
         {
            jjmatchedKind = 44;
            jjmatchedPos = 4;
         }
         break;
      case 114:
         return jjMoveStringLiteralDfa5_0(active0, 0x20L);
      case 115:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000000L);
      case 116:
         if ((active0 & 0x400000000000L) != 0L)
         {
            jjmatchedKind = 46;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x80000000000cL);
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x40L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 4);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 4);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 4);
   }
   switch(curChar)
   {
      case 65:
         if ((active0 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 47;
            jjmatchedPos = 5;
         }
         break;
      case 69:
         if ((active0 & 0x4L) != 0L)
         {
            jjmatchedKind = 2;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 6;
            jjmatchedPos = 5;
         }
         break;
      case 82:
         return jjMoveStringLiteralDfa6_0(active0, 0x8L);
      case 84:
         if ((active0 & 0x20L) != 0L)
         {
            jjmatchedKind = 5;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 41;
            jjmatchedPos = 5;
         }
         break;
      case 97:
         if ((active0 & 0x800000000000L) != 0L)
         {
            jjmatchedKind = 47;
            jjmatchedPos = 5;
         }
         break;
      case 101:
         if ((active0 & 0x4L) != 0L)
         {
            jjmatchedKind = 2;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x40L) != 0L)
         {
            jjmatchedKind = 6;
            jjmatchedPos = 5;
         }
         break;
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x8L);
      case 116:
         if ((active0 & 0x20L) != 0L)
         {
            jjmatchedKind = 5;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x10000000000L) != 0L)
         {
            jjmatchedKind = 40;
            jjmatchedPos = 5;
         }
         else if ((active0 & 0x20000000000L) != 0L)
         {
            jjmatchedKind = 41;
            jjmatchedPos = 5;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 5);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 5);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 5);
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa7_0(active0, 0x8L);
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x8L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 6);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 6);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 6);
   }
   switch(curChar)
   {
      case 73:
         return jjMoveStringLiteralDfa8_0(active0, 0x8L);
      case 105:
         return jjMoveStringLiteralDfa8_0(active0, 0x8L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 7);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 7);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 7);
   }
   switch(curChar)
   {
      case 78:
         return jjMoveStringLiteralDfa9_0(active0, 0x8L);
      case 110:
         return jjMoveStringLiteralDfa9_0(active0, 0x8L);
      default :
         break;
   }
   return jjMoveNfa_0(0, 8);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjMoveNfa_0(0, 8);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
   return jjMoveNfa_0(0, 8);
   }
   switch(curChar)
   {
      case 84:
         if ((active0 & 0x8L) != 0L)
         {
            jjmatchedKind = 3;
            jjmatchedPos = 9;
         }
         break;
      case 116:
         if ((active0 & 0x8L) != 0L)
         {
            jjmatchedKind = 3;
            jjmatchedPos = 9;
         }
         break;
      default :
         break;
   }
   return jjMoveNfa_0(0, 9);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int strKind = jjmatchedKind;
   int strPos = jjmatchedPos;
   int seenUpto;
   input_stream.backup(seenUpto = curPos + 1);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { throw new Error("Internal Error"); }
   curPos = 0;
   int startsAt = 0;
   jjnewStateCnt = 8;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAddStates(0, 4);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAdd(1);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 51)
                     kind = 51;
                  jjCheckNAddTwoStates(2, 3);
                  break;
               case 3:
                  if (curChar == 46)
                     jjCheckNAdd(4);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 51)
                     kind = 51;
                  jjCheckNAddTwoStates(3, 4);
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjCheckNAddTwoStates(5, 6);
                  break;
               case 6:
                  if (curChar == 58)
                     jjCheckNAdd(7);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjCheckNAdd(7);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAddStates(0, 4);
                  break;
               case 1:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 50)
                     kind = 50;
                  jjCheckNAdd(1);
                  break;
               case 2:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 51)
                     kind = 51;
                  jjCheckNAddTwoStates(2, 3);
                  break;
               case 4:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 51)
                     kind = 51;
                  jjCheckNAddTwoStates(3, 4);
                  break;
               case 5:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjCheckNAddTwoStates(5, 6);
                  break;
               case 7:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 52)
                     kind = 52;
                  jjstateSet[jjnewStateCnt++] = 7;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 8 - (jjnewStateCnt = startsAt)))
         break;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { break; }
   }
   if (jjmatchedPos > strPos)
      return curPos;

   int toRet = Math.max(curPos, seenUpto);

   if (curPos < toRet)
      for (i = toRet - Math.min(curPos, seenUpto); i-- > 0; )
         try { curChar = input_stream.readChar(); }
         catch(java.io.IOException e) { throw new Error("Internal Error : Please send a bug report."); }

   if (jjmatchedPos < strPos)
   {
      jjmatchedKind = strKind;
      jjmatchedPos = strPos;
   }
   else if (jjmatchedPos == strPos && jjmatchedKind > strKind)
      jjmatchedKind = strKind;

   return toRet;
}
static final int[] jjnextStates = {
   1, 2, 3, 5, 6, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, "\136", "\174\174", 
"\57", "\151\144\151\166", "\55", "\45", "\52", "\53", "\50", "\51", "\133", "\135", 
"\173", "\175", "\42", "\100", "\72", "\54", "\56", "\77", "\73", "\43", "\74", "\76", 
"\74\75", "\76\75", "\75", "\41\75", "\74\76", "\176\75", null, null, null, null, null, 
null, null, null, null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x1cfffffffffffdL, 
};
static final long[] jjtoSkip = {
   0x2L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[8];
private final int[] jjstateSet = new int[16];
protected char curChar;
/** Constructor. */
public CypherTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public CypherTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 8; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
