package com.example.christophe.taxe;

/**
 * Created by christophe on 10/30/17.
 */

public class FolderFactory{

    public FolderPath getFolder(String fileType) {

        CheckExistingPath checkExistingPath=new CheckExistingPath();

        if (checkExistingPath.checkPath(fileType)) {
            return null;
        } else if (fileType.equalsIgnoreCase("dirmessages")) {
            return new DirMessages();
        } else if (fileType.equalsIgnoreCase("maindir")){
            new MainDir();
        }

        return null;
    }
}
