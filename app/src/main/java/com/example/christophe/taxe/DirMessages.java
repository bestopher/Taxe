package com.example.christophe.taxe;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by christophe on 10/30/17.
 */

public class DirMessages extends FolderPath{

    public static final String TAG = "DirMessages";
    static File DirMessagesPath;
    public DirMessages(){

        folderPath = new File(Environment.getExternalStorageDirectory() + Folders.SMS_DIR);
        DirMessagesPath = folderPath;
        Log.v(TAG, "folderPath :"+ Environment.getExternalStorageDirectory());

    }

    public static File getDirMessagesPath() {
        DirMessagesPath= new File(Environment.getExternalStorageDirectory() + Folders.SMS_DIR);

        return DirMessagesPath;
    }

    @Override
    public boolean createFolder(){
        if (!getDirMessagesPath().exists()) {
            getDirMessagesPath().mkdir();
            //Log.v(TAG, "DirTaskMessages", folderPath.getAbsolutePath() + "/" + folderPath.getName());
            return true;
        }else{
            return false;
        }
    }
}
