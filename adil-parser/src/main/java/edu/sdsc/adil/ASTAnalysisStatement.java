/* Generated By:JJTree: Do not edit this line. ASTAnalysisStatement.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTAnalysisStatement extends SimpleNode {
  public ASTAnalysisStatement(int id) {
    super(id);
  }

  public ASTAnalysisStatement(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visitASTAnalysisStatement(this, data);
  }
}
/* JavaCC - OriginalChecksum=b49be22878a76d14ec5f167ddc67fba4 (do not edit this line) */
