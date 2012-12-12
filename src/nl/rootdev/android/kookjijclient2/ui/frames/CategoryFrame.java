package nl.rootdev.android.kookjijclient2.ui.frames;

import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.ICategory;
import nl.rootdev.android.kookjijclient2.datastructures.pb.Category;
import nl.rootdev.android.kookjijclient2.ui.fragments.ErrorFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.GridFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.LoadingFragment;
import nl.rootdev.android.kookjijclient2.ui.tasks.AsyncDownload;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * The master of a group of fragments responsible in
 * coordinating the download and showing fragments based
 * on it's results.
 * 
 * It simply tries to download a Category.
 * - During downloading show the LoadingFragment
 * - In case of an 'Error' open up the ErrorFragment
 * - In case of success open the HomeFragment
 * 
 * @author mark
 */
public class CategoryFrame extends SherlockFragment implements IConnectionHandle  {	
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
		action.replace(R.id.categoryFragment, loading).commit();
	}
	
	private void startAsyncDownload() {
		openLoading();
		final CategoryFrame that = this; /* This really feels like Javascript XD, async FTW */
		
		try {
			_download = new AsyncDownload(getSherlockActivity().getCacheDir().toString()) {
				private ICategory _category;
				
				@Override
				protected void onPostExecute(String result) {
					if(getException() == null) {
						final GridFragment home = new GridFragment(_category);
						final FragmentTransaction action2 = getFragmentManager().beginTransaction();
						action2.replace(R.id.categoryFragment, home).commit();
					} else {
						final ErrorFragment error = new ErrorFragment();
						Bundle bundle = new Bundle();
						bundle.putString("stacktrace", AndroidUtilities.getInstance().getStacktrace(getException()));
						bundle.putString("error", getException().getMessage());
						error.setArguments(bundle);

						final FragmentTransaction action2 = getFragmentManager().beginTransaction();
						action2.replace(R.id.categoryFragment, error).commit();
					}
				}
				
				@Override
				protected void startTextDownload(InputStream link) {
					try {
						_category = new Category();
						Schema<Category> schema = RuntimeSchema.getSchema(Category.class);
						ProtobufIOUtil.mergeFrom(link, (Category)_category, schema);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					
				}
				
				@Override
				protected void getImageDownload(Bitmap image) {
				}
			};
			
			_download.execute(new URL[] {
				new URL("http://dev.android.kookjij.mobi/api.php?f=b&c=" + "Groenten" + "&date=" + AndroidUtilities.getInstance().getDate()),
			});
		}
		catch(Exception e) {
			// TODO: Remove this?
			final ErrorFragment error = new ErrorFragment();
			Bundle bundle = new Bundle();
			bundle.putString("stacktrace", AndroidUtilities.getInstance().getStacktrace(e));
			bundle.putString("error", e.getMessage());
			error.setArguments(bundle);
			
			final FragmentTransaction action2 = getFragmentManager().beginTransaction();
			action2.replace(R.id.categoryFragment, error).commit();
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
		return inflater.inflate(R.layout.categoryfragment, container, false);
	}
}
