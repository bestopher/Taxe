package com.example.christophe.taxe;

import android.os.Environment;

import java.io.File;

/**
 * Created by christophe on 10/30/17.
 */

public class MainDir extends FolderPath{

    public static final String TAG = "DirMessages";
    static File MainDirPath;
    public MainDir(){

        folderPath = new File(Environment.getExternalStorageDirectory() + Folders.MAIN_DIR);
        MainDirPath = folderPath;
        //Flog.v(TAG, "folderPath :", folderPath);

    }

    public static File getDirTaskMessagesPath() {
        MainDirPath= new File(Environment.getExternalStorageDirectory() + Folders.MAIN_DIR);

        return MainDirPath;
    }

    @Override
    public boolean createFolder(){
        if (!MainDirPath.exists()) {
            MainDirPath.mkdir();
            //Flog.v(TAG, "DirTaskMessages", folderPath.getAbsolutePath() + "/" + folderPath.getName());
            return true;
        }else{
            return false;
        }
    }
}
