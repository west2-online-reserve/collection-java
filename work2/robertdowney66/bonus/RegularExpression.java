package com.west2.check02.bonus;

import org.junit.Test;

/**
 * @author yuyu
 */
public class RegularExpression {
    public boolean is(String s){
        return s.matches("^[A-Z0-9a-z]*@[a-z]*.com$");
    }

}
