/* Generated By:JJTree: Do not edit this line. ASTGRAPHNODE.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTGRAPHNODE extends SimpleNode {
  public ASTGRAPHNODE(int id) {
    super(id);
  }

  public ASTGRAPHNODE(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=b428ed168d91fd29cc1533c8de3c3638 (do not edit this line) */