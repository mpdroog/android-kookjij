package nl.rootdev.android.kookjijclient2.ui.frames;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import nl.rootdev.android.kookjijclient2.datastructures.ICategory;
import nl.rootdev.android.kookjijclient2.datastructures.pb.Category;
import nl.rootdev.android.kookjijclient2.ui.fragments.GridFragment;
import nl.rootdev.android.kookjijclient2.ui.tasks.AsyncDownload;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

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
public class CategoryFrame extends AbstractLoadingFrame {
	private int _fragmentId;
	
	@Override
	protected void startAsyncDownload(Bundle savedInstanceState)
			throws MalformedURLException {
		_download = new AsyncDownload(getSherlockActivity().getCacheDir().toString()) {
			private ICategory _category;
			
			@Override
			protected void onPostExecute(String result) {
				if(getException() == null) {
					final GridFragment home = new GridFragment(_category);
					final FragmentTransaction action2 = getFragmentManager().beginTransaction();
					action2.replace(getFragmentId(), home).commit();
				} else {
					openError(getException());
				}
			}
			
			@Override
			protected void startTextDownload(InputStream link) {
				try {
					link = AndroidUtilities.getInstance().encapsulateGZipOnNeed(link);
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
			new URL("http://dev.android.kookjij.mobi/api.php?f=b&c=" + getArguments().getString("name") + "&date=" + AndroidUtilities.getInstance().getDate()),
		});
	}

	/**
	 * Using dirty trick here to ensure 'always unique'.
	 */
	@Override
	protected int getFragmentId() {
		if (_fragmentId == 0) {
			_fragmentId = AndroidUtilities.getInstance().getUniqueNumber();
		}
		return _fragmentId;
	}		
}