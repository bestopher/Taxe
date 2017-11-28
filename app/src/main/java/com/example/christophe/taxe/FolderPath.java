package com.example.christophe.taxe;

import java.io.File;

/**
 * Created by christophe on 10/30/17.
 */

public  abstract class FolderPath {

    public static File folderPath;


    abstract boolean createFolder();


    public static File getFolderPath() {
        return folderPath;
    }
}
