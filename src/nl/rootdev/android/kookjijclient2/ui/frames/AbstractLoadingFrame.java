package nl.rootdev.android.kookjijclient2.ui.frames;

import java.net.MalformedURLException;

import nl.rootdev.android.kookjijclient2.ui.fragments.ErrorFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.LoadingFragment;
import nl.rootdev.android.kookjijclient2.ui.tasks.AsyncDownload;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * The master of a group of fragments responsible in
 * coordinating the download and showing fragments based
 * on it's results.
 * 
 * It simply tries to download a Recipie.
 * - During downloading show the LoadingFragment
 * - In case of an 'Error' open up the ErrorFragment
 * - In case of success open the HomeFragment
 * 
 * @author mark
 */
public abstract class AbstractLoadingFrame extends SherlockFragment implements IConnectionHandle  {	
	/** Reference in case we need to intervene (UI requests) */
	protected AsyncDownload _download;
	
	/**
	 * Stop downloading the data from the web.
	 */
	public void stopDownload() {
		_download.cancel(true);
	}
	
	/**
	 * Stop the download and spawn a new one.
	 * Useful for 'hanging' connections so
	 * the user can say stop and try again.
	 */
	public void retryDownload() {
		_download.cancel(true);
		
		try {
			startAsyncDownload(getArguments());
		} catch (MalformedURLException e) {
			openError(e);
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onDestroyView() {
		_download.cancel(true);
		super.onDestroyView();
	}
	
	@Override
	public int getLoadingPercentage() {
		return _download.getDownloadProgress();
	}
	
	private void openLoading() {
		LoadingFragment loading = new LoadingFragment(this);
		FragmentTransaction action = getFragmentManager().beginTransaction();
		action.replace(getFragmentId(), loading).commit();
	}
	
	protected void openError(Exception e) {		
		final ErrorFragment error = new ErrorFragment();
		Bundle bundle = new Bundle();
		bundle.putString("stacktrace", AndroidUtilities.getInstance().getStacktrace(e));
		bundle.putString("error", e.getMessage());
		error.setArguments(bundle);

		final FragmentTransaction action2 = getFragmentManager().beginTransaction();
		action2.replace(getFragmentId(), error).commit();
	}
	
	protected abstract void startAsyncDownload(Bundle savedInstanceState) throws MalformedURLException;
	protected abstract int getFragmentId();
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		openLoading();
		
		try {
			startAsyncDownload(savedInstanceState);
		}
		catch(Exception e) {
			openError(e);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		FrameLayout frame = new FrameLayout(getSherlockActivity().getBaseContext());
		frame.setId(getFragmentId());
		return frame;
	}
}
