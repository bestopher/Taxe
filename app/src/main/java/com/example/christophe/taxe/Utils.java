package com.example.christophe.taxe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by christophe on 10/30/17.
 */

public class Utils {

    private final static String TAG = "Utils.";


    public static String getAndroidId(Context c) {
        return Settings.Secure.getString(c.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String formatDate(Date date, String pattern) {
        if(pattern == null)
            return formatDate(date);

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    public static String format(Date date) {
        DateFormat df;
        df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        return df.format(date);
    }

    public static String getPhoneNumber(Context ctx) {
        TelephonyManager tMgr = (TelephonyManager)ctx.getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = tMgr.getLine1Number();
        return phoneNumber;
    }

    public static long getCurrentDateInLong(){
        Calendar calendar1 = Calendar.getInstance();
        Date date1 = calendar1.getTime();
        long currentDate = date1.getTime();

        return currentDate;
    }

    public static void createFlyFile(String devId, String ext, String extras) {
        String _L_ = TAG + "createFlyFile";
        //Log.begin(_L_);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String taxeMessage;

        if (extras == null || extras.equals("")) {
            taxeMessage = dateFormat.format(new Date()) + "_" + devId + ext;
        }
        else taxeMessage = dateFormat.format(new Date()) + "_" + devId + "_" + extras + ext;

        File taxeFile = new File(Environment.getExternalStorageDirectory() + Folders.SMS_DIR + "/" + taxeMessage);

        if (taxeFile.exists() == false) {
            try {
                taxeFile.createNewFile();
            } catch (IOException e) {
                Log.e(_L_, "IOException", e);
            }
        }
    }
}
