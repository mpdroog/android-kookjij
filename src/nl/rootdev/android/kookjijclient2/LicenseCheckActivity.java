package nl.rootdev.android.kookjijclient2;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;

import com.actionbarsherlock.app.SherlockActivity;
import com.google.android.vending.licensing.AESObfuscator;
import com.google.android.vending.licensing.LicenseChecker;
import com.google.android.vending.licensing.LicenseCheckerCallback;
import com.google.android.vending.licensing.ServerManagedPolicy;
 
public abstract class LicenseCheckActivity extends SherlockActivity {
 
    static boolean licensed = true;
    static boolean didCheck = false;
    static boolean checkingLicense = false;
    static final String BASE64_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkP83gDTuhMRtzXSBLiubFlrAhEi5U4eLYYqi7LabjNtpCzEmzchqOZ8ke3sDuRJD4uOW8ZFI+f+/Wb6S6ZdP2ylKzsnL2bnUr0LqslA531CojRW8qe6LeN6ai0SlV+QOKagg3MxI2ym6XKJOt/LimMslQYNNDEwBEVDs3YW6KdQALrhSLFQKXzJjOkW+tGxEkb0/6E92aENZdJYmUJQ4sa/OpOl+/4kbo9TRWkuWwWAK61GRO/uLDe6SuBywaKGfh3kCakH2H8of6vLNXRT+w4IKvd6b+IfSmDj97zXq1wNCtWmQMo6Pw0C0L+r0ADvEVXhN/UM10ykHwMwRwL6HMQIDAQAB";

    LicenseCheckerCallback mLicenseCheckerCallback;
    LicenseChecker mChecker;
 
    Handler mHandler;
 
    SharedPreferences prefs;
 
    // REPLACE WITH YOUR OWN SALT , THIS IS FROM EXAMPLE
    private static final byte[] SALT = new byte[]{
            -41, 55, 80, -8, -01, -93, 01, -64, 51, 88, -95, -45, 77, -35, -36, -120, -11, 32, -88,
            20
    };
 
    private void displayResult(final String result) {
        mHandler.post(new Runnable() {
            public void run() {
 
                setProgressBarIndeterminateVisibility(false);
 
            }
        });
    }
    
    protected abstract void updateSuccess();
 
    protected void doCheck() {
 
        didCheck = false;
        checkingLicense = true;
        setProgressBarIndeterminateVisibility(true);
 
        mChecker.checkAccess(mLicenseCheckerCallback);
    }
 
    protected void checkLicense() { 
        mHandler = new Handler();
 
        // Try to use more data here. ANDROID_ID is a single point of attack.
        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
 
        // Library calls this when it's done.
        mLicenseCheckerCallback = new MyLicenseCheckerCallback();
        // Construct the LicenseChecker with a policy.
        mChecker = new LicenseChecker(
                this, new ServerManagedPolicy(this,
                        new AESObfuscator(SALT, getPackageName(), deviceId)),
                BASE64_PUBLIC_KEY);
 
//        mChecker = new LicenseChecker(
//                this, new StrictPolicy(),
//                BASE64_PUBLIC_KEY);
 
        doCheck();
    }
 
    protected class MyLicenseCheckerCallback implements LicenseCheckerCallback {
 
        public void allow(int code) {
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            // Should allow user access.
            displayResult(getString(R.string.allow));
            licensed = true;
            checkingLicense = false;
            didCheck = true;
            updateSuccess();
        }
 
        public void dontAllow(int code) {
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            displayResult(getString(R.string.dont_allow));
            licensed = false;
            // Should not allow access. In most cases, the app should assume
            // the user has access unless it encounters this. If it does,
            // the app should inform the user of their unlicensed ways
            // and then either shut down the app or limit the user to a
            // restricted set of features.
            // In this example, we show a dialog that takes the user to Market.
            checkingLicense = false;
            didCheck = true;
 
            showDialog(0);
        }
 
        public void applicationError(int errorCode) {
            if (isFinishing()) {
                // Don't update UI if Activity is finishing.
                return;
            }
            licensed = false;
            // This is a polite way of saying the developer made a mistake
            // while setting up or calling the license checker library.
            // Please examine the error code and fix the error.
            //String result = String.format(getString(R.string.application_error), errorCode);
            checkingLicense = false;
            didCheck = true;
 
            //displayResult(result);
            showDialog(0);
        }
    }
 
    protected Dialog onCreateDialog(int id) {
        // We have only one dialog.
        return new AlertDialog.Builder(this)
                .setTitle(R.string.unlicensed_dialog_title)
                .setMessage(R.string.unlicensed_dialog_body)
                .setPositiveButton(R.string.buy_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                                "http://market.android.com/details?id=" + getPackageName()));
                        startActivity(marketIntent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.quit_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
 
                .setCancelable(false)
                .setOnKeyListener(new DialogInterface.OnKeyListener(){
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        Log.i("License", "Key Listener");
                        finish();
                        return true;
                    }
                })
                .create();
 
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
        if (mChecker != null) {
            mChecker.onDestroy();
            try {
            	unbindService(mChecker);
            }
            catch(IllegalArgumentException e) {
            	// Looks like difference between Android versions
            	// so unbind and ignore any errors..
            }
        }    	
    }
}