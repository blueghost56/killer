package org.codelife.app.killer.constant;

/**
 * @author csl
 * @create 07/27/2017 10:35
 **/
public enum  SymbolContract {
    BackSlash("/"),Comma(","),DoubleQuotes("\""),Colon(":");
    private final String symbol;
    SymbolContract(String symbol){
        this.symbol=symbol;
    }

    @Override
    public String toString(){
        return this.symbol;
    }

}
