package com.singhdd.dftpclient.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.singhdd.dftpclient.FTPInterface;
import com.singhdd.dftpclient.common.FTPHelper;

public class FTPService extends Service {

    private FTPHelper mFTPHelper = null;
    public FTPService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mFTPHelper = new FTPHelper(this);

        return super.onStartCommand(intent, flags, startId);
    }

    private final FTPInterface.Stub mBinder = new FTPInterface.Stub() {

        /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         *
         * @param host
         * @param username
         * @param password
         * @param port
         */
        @Override
        public boolean connectFTP(String host, String username, String password, int port) throws RemoteException {
            return mFTPHelper.ftpConnect(host, username, password, port);
        }

        @Override
        public boolean disconnectFTP() throws RemoteException {
            return false;
        }
    };
}
