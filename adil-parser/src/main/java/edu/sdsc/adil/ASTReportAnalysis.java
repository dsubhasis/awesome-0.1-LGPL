/* Generated By:JJTree: Do not edit this line. ASTReportAnalysis.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTReportAnalysis extends SimpleNode {
  public ASTReportAnalysis(int id) {
    super(id);
  }

  public ASTReportAnalysis(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=1ac0227b09c87182a9734318be9c3538 (do not edit this line) */
