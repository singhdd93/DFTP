// FTPInterface.aidl
package com.singhdd.dftpclient;

// Declare any non-default types here with import statements

interface FTPInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    boolean connectFTP(String host, String username, String password, int port);
    boolean disconnectFTP();

}
