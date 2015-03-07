package com.singhdd.dftpclient;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.singhdd.dftpclient.adapters.LocalFileListAdapter;
import com.singhdd.dftpclient.resuable.FileItem;
import com.singhdd.dftpclient.resuable.Globals;
import com.singhdd.dftpclient.resuable.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class LocalFragment extends Fragment{

    private ListView mLocalFileListView;
    private TextView mCurrentDirTextView;
    protected LocalFileListAdapter mAdapter = null;
    protected ArrayList<FileItem> filesInDir = null;
    protected String currentDirPath = null;
    private int sortType ;

    private String STATE_FILESINDIR = "filesindir";
    private String STATE_CURRENTDIR = "currentdir";
    private String STATE_SORTTYPE = "sorttype";
    //private String STATE_

//    private OnFragmentInteractionListener mListener;


/*
    // TODO: Rename and change types and number of parameters
    public static LocalFragment newInstance(String param1, String param2) {
        LocalFragment fragment = new LocalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable(STATE_FILESINDIR, filesInDir);
        outState.putString(STATE_CURRENTDIR,currentDirPath);
        outState.putInt(STATE_SORTTYPE, sortType);

    }


    public LocalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_local, container, false);
        mLocalFileListView = (ListView) rootView.findViewById(R.id.local_file_list);
        mCurrentDirTextView = (TextView) rootView.findViewById(R.id.local_current_dir_path);

        filesInDir = new ArrayList<FileItem>();

        if(savedInstanceState == null){
            sortType = Globals.SORT_BY_NAME;
            File sdCardDir = Environment.getExternalStorageDirectory();
            currentDirPath = sdCardDir.getAbsolutePath();
            filesInDir.addAll(Utilities.fileList(sdCardDir,sortType));//Utilities.fileList(sdCardDir, sortType);
        }
        else {
            sortType = savedInstanceState.getInt(STATE_SORTTYPE);
            currentDirPath = savedInstanceState.getString(STATE_CURRENTDIR);
            filesInDir = (ArrayList<FileItem>) savedInstanceState.getSerializable(STATE_FILESINDIR);

        }

        mCurrentDirTextView.setText(currentDirPath);

        mAdapter = new LocalFileListAdapter(getActivity(),R.layout.file_list_item,filesInDir);

        mLocalFileListView.setAdapter(mAdapter);

        mLocalFileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileItem fileItem = mAdapter.getItem(position);

                if(fileItem.getType() == Globals.FILE_TYPE_DIRECTORY || fileItem.getType() == Globals.FILE_TYPE_PARENT) {
                   // filesInDir = Utilities.fileList(new File(fileItem.getPath()),sortType);
                    filesInDir.clear();
                    currentDirPath = fileItem.getPath();
                    mCurrentDirTextView.setText(currentDirPath);
                    filesInDir.addAll(Utilities.fileList(new File(currentDirPath),sortType));
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    try {
                        Utilities.openLocalFile(getActivity(),fileItem.getPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return rootView;
    }



/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
*/

/*    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

/*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
/*    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }*/

}
