/* Generated By:JJTree: Do not edit this line. ASTOperator.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTOperator extends SimpleNode {
  public ASTOperator(int id) {
    super(id);
  }

  public ASTOperator(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visitASTOperator(this, data);
  }
}
/* JavaCC - OriginalChecksum=6bb5b8276874b5c083dd74ec4c5241d1 (do not edit this line) */
