/* Generated By:JJTree: Do not edit this line. ASTPropertyGarphCreate.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTPropertyGarphCreate extends SimpleNode {
  public ASTPropertyGarphCreate(int id) {
    super(id);
  }

  public ASTPropertyGarphCreate(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=56887cad9daeb25f8864911c5f659812 (do not edit this line) */
