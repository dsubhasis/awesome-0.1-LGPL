/* Generated By:JJTree: Do not edit this line. ASTADILStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTADILStatement extends edu.sdsc.adil.SimpleNode {
  public ASTADILStatement(int id) {
    super(id);
  }

  public ASTADILStatement(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=9d43f1efe064dcbbd3fba6806427fb5a (do not edit this line) */
