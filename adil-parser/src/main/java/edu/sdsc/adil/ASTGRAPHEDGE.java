/* Generated By:JJTree: Do not edit this line. ASTGRAPHEDGE.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTGRAPHEDGE extends SimpleNode {
  public ASTGRAPHEDGE(int id) {
    super(id);
  }

  public ASTGRAPHEDGE(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=25f40b4cdae733cd468969dec6b3a3ee (do not edit this line) */