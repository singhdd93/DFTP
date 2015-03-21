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
            Log.d("LENGTH",""+a.length);
            for(int i = 0; i<a.length; i++) {
                Log.w(TAG, a[i]);
            }
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

    public boolean ftpDisconnect() {
        try {
            mFTPClient.logout();
            mFTPClient.disconnect(true);
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

    public String getFTPCurrentDirectory() {
        try {
            String curDir = mFTPClient.currentDirectory();
            return curDir;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (FTPIllegalReplyException e) {
            e.printStackTrace();
            return null;
        } catch (FTPException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean changeFTPDirectory(String path) {
        try {
            mFTPClient.changeDirectory(path);
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
