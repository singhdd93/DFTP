package com.singhdd.dftpclient;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.singhdd.dftpclient.common.FTPHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class RemoteFragment extends Fragment {



    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static RemoteFragment newInstance(int sectionNumber) {
        RemoteFragment fragment = new RemoteFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RemoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_remote, container, false);

        MainActivity mAct = (MainActivity) getActivity();

      //  FTPHelper mFTPHelper = new FTPHelper(getActivity());
       // boolean connected = mFTPHelper.ftpConnect("server.vigaas.com", "admin", "ER.dds1ngh", 21);
      //  initConnection();
        /*try {
            boolean connected = mAct.mIFTPInterface.connectFTP("server.vigaas.com", "admin", "ER.dds1ngh", 21);
            Log.d("FTPConnection",""+connected);
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
        return rootView;
    }




//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        ((MainActivity) activity).onSectionAttached(
//                getArguments().getInt(ARG_SECTION_NUMBER));
//    }
}
