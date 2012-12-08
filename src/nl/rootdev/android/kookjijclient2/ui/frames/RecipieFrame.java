package nl.rootdev.android.kookjijclient2.ui.frames;

import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.pb.Recipie;
import nl.rootdev.android.kookjijclient2.ui.fragments.ErrorFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.LoadingFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.RecipieFragment;
import nl.rootdev.android.kookjijclient2.ui.tasks.AsyncDownload;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyuproject.protostuff.me.ProtostuffIOUtil;

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
public class RecipieFrame extends SherlockFragment implements IConnectionHandle  {	
	/** Reference in case we need to intervene (UI requests) */
	private AsyncDownload _download;
	
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
		startAsyncDownload();
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
		action.replace(R.id.contentFragment, loading).commit();
	}
	
	private void startAsyncDownload() {
		openLoading();
		final RecipieFrame that = this; /* This really feels like Javascript XD, async FTW */
		
		try {
			_download = new AsyncDownload(getSherlockActivity().getCacheDir().toString()) {
				private Recipie _recipie;
				private Bitmap _image;
				
				@Override
				protected void onPostExecute(String result) {
					if(getException() == null) {
						final RecipieFragment home = new RecipieFragment(_recipie, _image);
						final FragmentTransaction action2 = getFragmentManager().beginTransaction();
						action2.replace(R.id.contentFragment, home).commit();
					} else {
						final ErrorFragment error = new ErrorFragment(that, getException());
						final FragmentTransaction action2 = getFragmentManager().beginTransaction();
						action2.replace(R.id.contentFragment, error).commit();
					}
				}
				
				@Override
				protected void startTextDownload(InputStream link) {
					try {
						_recipie = new Recipie();
						ProtostuffIOUtil.mergeFrom(new GZIPInputStream(link), _recipie,
								Recipie.getSchema());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					
				}
				
				@Override
				protected void getImageDownload(Bitmap image) {
					_image = image;
				}
			};
			
			_download.execute(new URL[] {
				new URL("http://dev.android.kookjij.mobi/api.php?f=h&date=" + System.currentTimeMillis()),
				new URL("http://dev.android.kookjij.mobi/api.php?f=p&i=12")
			});
		}
		catch(Exception e) {
			// TODO: Remove this?
			final ErrorFragment error = new ErrorFragment(this, e);
			final FragmentTransaction action2 = getFragmentManager().beginTransaction();
			action2.replace(R.id.contentFragment, error).commit();
		}		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		startAsyncDownload();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.recipiefragment, container, false);
	}
}
