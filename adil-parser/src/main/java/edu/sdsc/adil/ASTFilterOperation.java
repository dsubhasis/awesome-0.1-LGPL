/* Generated By:JJTree: Do not edit this line. ASTFilterOperation.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTFilterOperation extends SimpleNode {
  public ASTFilterOperation(int id) {
    super(id);
  }

  public ASTFilterOperation(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visitASTFilterOperation(this, data);
  }
}
/* JavaCC - OriginalChecksum=7e9382823db226c48763fa05d83c34ae (do not edit this line) */
