package com.singhdd.dftpclient.common;

import android.content.Context;
import android.util.Log;

import com.singhdd.dftpclient.resuable.FileItem;

import java.io.IOException;
import java.util.ArrayList;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;


public class FTPHelper{

    private static final String TAG = "FTP Helper";
    public static FTPClient mFTPClient = null;
    private Context mContext;
    private ArrayList<FileItem> files;
    private ArrayList<FileItem> directories;

    public FTPHelper(Context ctx) {

        this.mContext = ctx;
    }

    public boolean ftpConnect(String host, String username, String password, int port)
    {
        mFTPClient = new FTPClient();

        try {
            String[] a = mFTPClient.connect(host, port);
            Log.v(TAG,a[0]);
            mFTPClient.login(username, password);
            mFTPClient.setType(FTPClient.TYPE_BINARY);
            mFTPClient.setPassive(true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (FTPIllegalReplyException e) {
            e.printStackTrace();
            return false;
        } catch (FTPException e) {
            e.printStackTrace();
            return false;
        }

    }

}
