/* Generated By:JJTree: Do not edit this line. ASTFunctionAnalysis.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTFunctionAnalysis extends SimpleNode {
  public ASTFunctionAnalysis(int id) {
    super(id);
  }

  public ASTFunctionAnalysis(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visitASTFunctionAnalysis(this, data);
  }
}
/* JavaCC - OriginalChecksum=9b6290b21d1255ccc9991ef4834d0531 (do not edit this line) */
