package com.example.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.fragments.LoaderDialogFragment;


/**
 * Created by Venu on 10/04/2017.
 */
public class BaseActivity extends AppCompatActivity {

    public BaseActivity mActivity;
    private LoaderDialogFragment mLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    //Display loader when every perform long run operation
    protected void showLoader() {
        if (mLoader == null || mLoader.isHidden()) {
            mLoader = new LoaderDialogFragment();
            mLoader.show(getFragmentManager(), LoaderDialogFragment.TAG);
        }
    }

    //hide loader if any loader existed on screen
    protected void hideLoader() {
        if (mLoader != null && !mLoader.isHidden()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mLoader.dismiss();
                        mLoader = null;
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }



}
