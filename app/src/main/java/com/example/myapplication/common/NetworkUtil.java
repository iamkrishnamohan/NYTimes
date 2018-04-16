package com.example.myapplication.common;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;

import com.example.myapplication.R;


public class NetworkUtil {

    public abstract static class NetworkStatusListener {
        /**Callback to invoke if network is available*/
        public abstract void onNetworkAvailable();
        /**Callback to invoke if network is not available*/
        public void onNetworkNotAvailable(){}
    }

    /**Verifies the Internet connection availability.
     * @param context {@link Context} object reference only.
     * @return returns <tt>true</tt> if network connection available, otherwise <tt>false</tt>
     * @throws IllegalArgumentException if the context is instance of {@link Activity}. So, you can use the <tt>checkInternetConnection(Activity activity, NetworkStatusListener listener)</tt> method ,if you want to verify the network availability in activity*/
    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected();
    }

    /**Verifies the Internet connection availability and calls the callback methods in {@link NetworkStatusListener} class object based on the network status.
     * Also, displays an alert(popup) saying that there is no network connection.
     * <br><code>&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /&gt;</code>
     * @param activity {@link Activity} object reference only.
     * @param listener Reference object of {@link NetworkStatusListener} class. This will used to trigger the status of the network.
     */
    public static void checkInternetConnection(Activity activity, NetworkStatusListener listener) {
        checkInternetConnection(activity, listener, true);
    }

    /**Verifies the Internet connection availability and calls the callback methods in {@link NetworkStatusListener} class object based on the network status.
     * Also, displays an alert(popup) saying that there is no network connection based on the <code>showAlertOnFailure</code> parameter value.
     * <br><code>&lt;uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" /&gt;</code>
     * @param activity {@link Activity} object reference only.
     * @param listener Reference object of {@link NetworkStatusListener} class. This will used to trigger the status of the network.
     * @param showAlertOnFailure if <tt>true</tt> displays an alert, otherwise not.
     */
    public static void checkInternetConnection(Activity activity, NetworkStatusListener listener, boolean showAlertOnFailure) {
        ConnectivityManager conMgr = (ConnectivityManager)activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()){
            listener.onNetworkAvailable();
        }else{
            if(showAlertOnFailure)
                AlertDialogHelper.showAlert(activity, activity.getString(R.string.noNetworkTitle), activity.getString(R.string.noNetworkMessage));
            listener.onNetworkNotAvailable();
        }
    }// checkInternetConnection()
}
