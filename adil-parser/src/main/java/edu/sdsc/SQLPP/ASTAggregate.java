/* Generated By:JJTree: Do not edit this line. ASTAggregate.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.SQLPP;

public
class ASTAggregate extends SimpleNode {
  public ASTAggregate(int id) {
    super(id);
  }

  public ASTAggregate(SQLPP p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SQLPPVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=73bd9c461e32e90dff5535d83a313641 (do not edit this line) */
