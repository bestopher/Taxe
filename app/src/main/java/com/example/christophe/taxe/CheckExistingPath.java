package com.example.christophe.taxe;

import java.io.File;

/**
 * Created by christophe on 10/30/17.
 */

public class CheckExistingPath {

    public boolean checkPath(String str){
        File path=new File(str);
        if (path.equals(null)){
            return false;
        }
        return false;

    }
}
