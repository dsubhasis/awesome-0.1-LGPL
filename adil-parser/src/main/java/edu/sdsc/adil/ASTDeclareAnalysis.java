/* Generated By:JJTree: Do not edit this line. ASTDeclareAnalysis.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTDeclareAnalysis extends SimpleNode {
  public ASTDeclareAnalysis(int id) {
    super(id);
  }

  public ASTDeclareAnalysis(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=3bb298f3cd5219e96e96b5b5e15634d5 (do not edit this line) */
