package com.example.christophe.taxe;

/**
 * Created by christophe on 10/30/17.
 */

public class FactoryPatternFolder {

    public static String TAG=FactoryPatternFolder.class.getSimpleName();

    public static boolean createInitialFolders(String args){

        FolderFactory folderFactory=new FolderFactory();
        try {
            FolderPath dirF = folderFactory.getFolder(args);
            CheckExistingPath checkExistingPath = new CheckExistingPath();

            if (!checkExistingPath.checkPath(args)) {
                dirF.createFolder();
                return true;
            }
        }catch(Exception e){

            return false;
        }

        return true;

    }
}
