/* Generated By:JJTree: Do not edit this line. ASTSelectStatementWith.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.SQLPP;

public
class ASTSelectStatementWith extends SimpleNode {
  public ASTSelectStatementWith(int id) {
    super(id);
  }

  public ASTSelectStatementWith(SQLPP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SQLPPVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=75861b676f91cf7901f42123d7376194 (do not edit this line) */
