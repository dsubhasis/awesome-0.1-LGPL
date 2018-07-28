package edu.sdsc.awesome.adil.parser.ParserTable;

import java.util.List;
import java.util.Map;

public class DecisionElement {

    public DecisionElement() {
    }

    public DecisionElement(String elementName, String elementType, List<String> storageOptions, Map<String, String> indexOptions, boolean memoize, boolean isTemporary) {
        this.elementName = elementName;
        this.elementType = elementType;
        this.storageOptions = storageOptions;
        this.indexOptions = indexOptions;
        this.memoize = memoize;
        this.isTemporary = isTemporary;
    }

    private String elementName;
    private String elementType;
    private List<String> storageOptions;
    private Map<String, String> indexOptions;
    private boolean memoize;
    private boolean isTemporary;


}
