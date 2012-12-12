package nl.rootdev.android.kookjijclient2.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Utility-class for checking Android specific stuff from the deeper
 * layers of the code.
 * @author mark
 *
 */
public class AndroidUtilities {
	private Activity _activity;
	private static AndroidUtilities _instance;
		
	private AndroidUtilities(Activity activity) {
		_activity = activity;
	}
	
	public boolean isInternetConnected() {
		ConnectivityManager cm = (ConnectivityManager) _activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if(info == null) {
			return false;
		}
		return info.isConnected();
	}
	
	public boolean isPermissionGranted(String permissionKey) {
		int status = _activity.getPackageManager().checkPermission(permissionKey, _activity.getPackageName());
		return status == PackageManager.PERMISSION_GRANTED;
	}
	
	/**
	 * Return how fast the connection is.
	 * This can influence the GUI.
	 * @return
	 */
	public ConnectionTypes getConnectionSpeed() {
		ConnectivityManager connMgr = (ConnectivityManager) _activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		TelephonyManager callMgr = (TelephonyManager) _activity.getSystemService(Context.TELEPHONY_SERVICE);

		NetworkInfo info = connMgr.getActiveNetworkInfo();
		if(info.getType() == ConnectivityManager.TYPE_WIFI) {
			return ConnectionTypes.TYPE_WIFI;
		} else {
			// Mobile
			switch(callMgr.getNetworkType()) {
				case TelephonyManager.NETWORK_TYPE_LTE:
				case TelephonyManager.NETWORK_TYPE_HSPAP:
					// Fast
					return ConnectionTypes.TYPE_MOBILE_FAST;
				default:
					// Slow
					return ConnectionTypes.TYPE_MOBILE_SLOW;
			}
		}
	}
	
	/**
	 * Show a short-term message to the suer.
	 * @param msg String to output
	 * @param shortDuration If it should be shown short of quickly
	 */
	public void makeToast(String msg, boolean shortDuration) {
		if(shortDuration) {
			Toast.makeText(_activity, msg, Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(_activity, msg, Toast.LENGTH_LONG).show();
		}
	}
	
	/**
	 * Get human-friendly date.
	 * This is useful for the 'caching functionality', most HTTP-requests
	 * are appended by this date so an aggresive caching mechanism can be
	 * implementented.
	 * @return String YYYY-mm-dd
	 */
	public String getDate() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1; /* Jan=0 in Java */
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		StringBuilder builder = new StringBuilder();
		builder.append(year);
		builder.append('-');
		builder.append(month);
		builder.append('-');
		builder.append(day);

		return builder.toString();
	}
	
	public static void instantiate(Activity activity) {
		_instance = new AndroidUtilities(activity);
	}
	
	public static AndroidUtilities getInstance() {
		return _instance;
	}

	public String getDate(Long lastedit) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(lastedit * 1000L);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) +1; /* Jan=0 in Java */
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		StringBuilder builder = new StringBuilder();
		builder.append(year);
		builder.append('-');
		builder.append(month);
		builder.append('-');
		builder.append(day);

		return builder.toString();
	}
	
	public String getStacktrace(Exception e) {
		final StringWriter str = new StringWriter();
		final PrintWriter writer = new PrintWriter(str);
		e.printStackTrace(writer);
		return str.toString();
	}
}
