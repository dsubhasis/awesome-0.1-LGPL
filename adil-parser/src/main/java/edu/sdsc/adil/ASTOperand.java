/* Generated By:JJTree: Do not edit this line. ASTOperand.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTOperand extends SimpleNode {
  public ASTOperand(int id) {
    super(id);
  }

  public ASTOperand(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visitASTOperand(this, data);
  }
}
/* JavaCC - OriginalChecksum=e0c743dc3347671dda6e7704a7b57d0a (do not edit this line) */
