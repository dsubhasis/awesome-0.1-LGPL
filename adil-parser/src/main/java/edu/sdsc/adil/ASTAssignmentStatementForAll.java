/* Generated By:JJTree: Do not edit this line. ASTAssignmentStatementForAll.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTAssignmentStatementForAll extends SimpleNode {
  public ASTAssignmentStatementForAll(int id) {
    super(id);
  }

  public ASTAssignmentStatementForAll(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=2ff3dcf554a79febc05e4954c47db2b3 (do not edit this line) */
