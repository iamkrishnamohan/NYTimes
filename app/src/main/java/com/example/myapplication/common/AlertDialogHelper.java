package com.example.myapplication.common;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;


public class AlertDialogHelper {

    private static Integer mAlertTheme = null;
    /**
     * Interface definition for a callback to be invoked when a button is clicked in
     * confirm alert dialog.
     */
    public interface OnDialogButtonClickListener{
        /** Indicates that neutral button clicked */
        int NEUTRAL = 0;

        /** Indicates that positive button clicked */
        int POSITIVE = 1;

        /** Indicates that negative button clicked */
        int NEGATIVE = 2;
        /**
         * Called when a button has been clicked in alert dialog.
         */
        void onButtonClick(int buttonCode);
    }

    public interface OnDialogCancelListener {
        void onDialogCanceled();
    }

    public static void setAlertTheme(int theme){
        mAlertTheme = theme;
    }

    /**
     * Displays an alert to show some information to user and this alert can be cancellable by user.
     *
     * @param activity {@link Activity} reference to create alert
     * @param title    Title for the alert dialog
     * @param alertMsg Message to display on alert dialog
     * @throws IllegalArgumentException when button is not having valid text to
     *                                  show.
     */
    public static void showAlert(final Activity activity, String title, String alertMsg) {
        showAlert(activity, title, alertMsg, activity.getString(android.R.string.ok), true, null);
    }

    /**
     * Displays an alert to show some information to user and this alert can be cancellable by user.
     *
     * @param activity   {@link Activity} reference to create alert
     * @param title      Title for the alert dialog
     * @param alertMsg   Message to display on alert dialog
     * @param buttonText Text should be displayed on button
     * @throws IllegalArgumentException when button is not having valid text to
     *                                  show.
     */
    public static void showAlert(final Activity activity, String title, String alertMsg,
                                 String buttonText) {
        showAlert(activity, title, alertMsg, buttonText, true, null);
    }

    /**
     * Displays an alert to show some information to user.
     *
     * @param activity   {@link Activity} reference to create alert
     * @param title      Title for the alert dialog
     * @param alertMsg   Message to display on alert dialog
     * @param buttonText Text should be displayed on button
     * @param listener   callback to call on button click.
     * @throws IllegalArgumentException when button is not having valid text to
     *                                  show.
     */
    public static void showAlert(final Activity activity, String title, String alertMsg,
                                 String buttonText, final OnDialogButtonClickListener listener) {
        showAlert(activity, title, alertMsg, buttonText, true, listener);
    }

    /**
     * Displays an alert to show some information to user.
     *
     * @param activity     {@link Activity} reference to create alert
     * @param title        Title for the alert dialog
     * @param alertMsg     Message to display on alert dialog
     * @param buttonText   Text should be displayed on button
     * @param isCancelable <code>true</code> indicates that this dialog will be
     *                     dismissed when device back button pressed ,otherwise not.
     * @throws IllegalArgumentException when button is not having valid text to
     *                                  show.
     */
    public static void showAlert(final Activity activity, String title, String alertMsg,
                                 String buttonText, boolean isCancelable) {
        showAlert(activity, title, alertMsg, buttonText, isCancelable, null);
    }

    /**
     * Displays an alert to show some information to user.
     *
     * @param activity     {@link Activity} reference to create alert
     * @param title        Title for the alert dialog
     * @param alertMsg     Message to display on alert dialog
     * @param buttonText   Text should be displayed on button
     * @param isCancelable <code>true</code> indicates that this dialog will be
     *                     dismissed when device back button pressed ,otherwise not.
     * @param listener     callback to call on button click.
     * @throws IllegalArgumentException when button is not having valid text to
     *                                  show.
     */
    public static void showAlert(final Activity activity, String title, String alertMsg,
                                 String buttonText, boolean isCancelable,
                                 final OnDialogButtonClickListener listener) {
        showAlert(activity,title,alertMsg,buttonText,null,isCancelable,listener);
    }// showAlert()

    /**
     * Creates and displays the {@link AlertDialog} to get the confirmation from
     * the user.
     *
     * @param activity           {@link Activity} reference to create alert
     * @param title              Title for the confirm dialog
     * @param alertMsg           Message to display on confirm dialog
     * @param positiveButtonText Text should be displayed on positive button
     * @param isCancelable       <code>true</code> indicates that this dialog will be
     *                           dismissed when device back button pressed ,otherwise not.
     * @param listener           callback to call on respective button clicks.
     * @throws IllegalArgumentException when at least one button is not having
     *                                  valid text to show.
     */
    public static void showAlert(final Activity activity, String title,
                                 String alertMsg, String positiveButtonText, String negativeButtonText,
                                 boolean isCancelable, final OnDialogButtonClickListener listener) {
        showAlert(activity, title, alertMsg, positiveButtonText, negativeButtonText,
                null, isCancelable, listener);
    }

    /**
     * Creates and displays the {@link AlertDialog} to get the confirmation from
     * the user.
     *
     * @param activity           {@link Activity} reference to create alert
     * @param title              Title for the confirm dialog
     * @param alertMsg           Message to display on confirm dialog
     * @param positiveButtonText Text should be displayed on positive button
     * @param negativeButtonText Text should be displayed on negative button
     * @param isCancelable       <code>true</code> indicates that this dialog will be
     *                           dismissed when device back button pressed ,otherwise not.
     * @param listener           callback to call on respective button clicks.
     * @param cancelListener     callback to call on dialog canceled
     * @throws IllegalArgumentException when at least one button is not having
     *                                  valid text to show.
     */
    public static void showAlert(final Activity activity, String title,
                                 String alertMsg, String positiveButtonText, String negativeButtonText,
                                 boolean isCancelable,
                                 final OnDialogButtonClickListener listener, final OnDialogCancelListener cancelListener) {
        showAlert(activity, title, alertMsg, positiveButtonText, negativeButtonText, null, isCancelable, listener,cancelListener);
    }

    /**
     * Creates and displays the {@link AlertDialog} to get the confirmation from
     * the user.
     *
     * @param activity           {@link Activity} reference to create alert
     * @param title              Title for the confirm dialog
     * @param alertMsg           Message to display on confirm dialog
     * @param positiveButtonText Text should be displayed on positive button
     * @param negativeButtonText Text should be displayed on negative button
     * @param neutralButtonText  Text should be displayed on neutral button
     * @param isCancelable       <code>true</code> indicates that this dialog will be
     *                           dismissed when device back button pressed ,otherwise not.
     * @param listener           callback to call on respective button clicks.
     * @throws IllegalArgumentException when at least one button is not having
     *                                  valid text to show.
     */
    public static void showAlert(final Activity activity, String title,
                                 String alertMsg, String positiveButtonText, String negativeButtonText,
                                 String neutralButtonText, boolean isCancelable,
                                 final OnDialogButtonClickListener listener) {
        showAlert(activity, title, alertMsg, positiveButtonText, negativeButtonText, neutralButtonText, isCancelable, listener, null);
    }




    /**
     * Creates and displays the {@link AlertDialog} to get the confirmation from
     * the user.
     *
     * @param activity           {@link Activity} reference to create alert
     * @param title              Title for the confirm dialog
     * @param alertMsg           Message to display on confirm dialog
     * @param positiveButtonText Text should be displayed on positive button
     * @param negativeButtonText Text should be displayed on negative button
     * @param neutralButtonText  Text should be displayed on neutral button
     * @param isCancelable       <code>true</code> indicates that this dialog will be
     *                           dismissed when device back button pressed ,otherwise not.
     * @param listener           callback to call on respective button clicks.
     * @param cancelListener     callback to call on dialog canceled
     * @throws IllegalArgumentException when at least one button is not having
     *                                  valid text to show.
     */
    public static void showAlert(final Activity activity, String title,
                                 String alertMsg, String positiveButtonText, String negativeButtonText,
                                 String neutralButtonText, boolean isCancelable,
                                 final OnDialogButtonClickListener listener, final OnDialogCancelListener cancelListener) {

        if ((positiveButtonText == null || "".equalsIgnoreCase(positiveButtonText.trim()))
                && (negativeButtonText == null || "".equalsIgnoreCase(negativeButtonText.trim()))
                && (neutralButtonText == null || "".equalsIgnoreCase(neutralButtonText.trim()))) {
            throw new IllegalArgumentException(
                    "Atleast one button text must be supplied to show the confirm alert dialog");

        }


        AlertDialog.Builder alert = null;
        if (mAlertTheme == null){
            alert = new AlertDialog.Builder(activity);
        } else {
            alert = new AlertDialog.Builder(activity, mAlertTheme);
        }

        alert.setTitle(title);
        alert.setIcon(null);
        alert.setCancelable(isCancelable);
        alert.setMessage(alertMsg);

        if (isCancelable && cancelListener != null) {
            alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface arg0) {
                    cancelListener.onDialogCanceled();
                }
            });
        }
        if (positiveButtonText != null) {
            alert.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (listener != null) {
                        listener.onButtonClick(OnDialogButtonClickListener.POSITIVE);
                    }

                }
            });
        }
        if (!TextUtils.isEmpty(negativeButtonText)) {
            alert.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (listener != null) {
                        listener.onButtonClick(OnDialogButtonClickListener.NEGATIVE);
                    }

                }
            });
        }
        if (!TextUtils.isEmpty(neutralButtonText)) {
            alert.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (listener != null) {
                        listener.onButtonClick(OnDialogButtonClickListener.NEUTRAL);
                    }

                }
            });
        }
        alert.show();
        /*AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);//Confirm dialog shoudn't be closed on out side click, because user has to select yes/no
        dialog.show();*/
    }// showAlert()
}
