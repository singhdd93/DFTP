package com.singhdd.dftpclient;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.singhdd.dftpclient.common.FTPHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class RemoteFragment extends Fragment {

    private RelativeLayout loadingLayout;
    private TextView loadingTextView;
    private ListView mRemoteFileListView;
    private TextView mCurrentDirTextView;
    FTPHelper mFTPHelper;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_remote, container, false);
        mRemoteFileListView = (ListView) rootView.findViewById(R.id.remote_file_list);
        mCurrentDirTextView = (TextView) rootView.findViewById(R.id.remote_current_dir_path);
        loadingLayout = (RelativeLayout) rootView.findViewById(R.id.loading_layout);
        loadingTextView = (TextView) rootView.findViewById(R.id.loading_text);
        loadingLayout.setVisibility(View.GONE);

        mFTPHelper = new FTPHelper(getActivity());
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

    public void connectFTP(String host,String username,String password, String port) {
        new FTPConnect().execute(host,username,password,port);
    }


    public class FTPConnect extends AsyncTask<String,Void,Boolean> {

        @Override
        protected void onPreExecute() {
            loadingLayout.setVisibility(View.VISIBLE);
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Boolean doInBackground(String... params) {
            return mFTPHelper.ftpConnect(params[0],params[1],params[2],Integer.parseInt(params[3]));
        }

        @Override
        protected void onPostExecute(Boolean b) {
            loadingLayout.setVisibility(View.GONE);
            if(b) {
                Toast.makeText(getActivity(),"Connected",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
            }
        }
    }



/*    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }*/
}
