/* Generated By:JJTree: Do not edit this line. ASTUseEnvironment.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sdsc.adil;

public
class ASTUseEnvironment extends SimpleNode {
  public ASTUseEnvironment(int id) {
    super(id);
  }

  public ASTUseEnvironment(Adil p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(AdilVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=dabdc2015b31bf654091fecfb50ed5fe (do not edit this line) */
