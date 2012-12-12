package nl.rootdev.android.kookjijclient2.ui.frames;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import nl.rootdev.android.kookjijclient2.datastructures.pb.Recipie;
import nl.rootdev.android.kookjijclient2.ui.fragments.RecipieFragment;
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
 * It simply tries to download a Recipie.
 * - During downloading show the LoadingFragment
 * - In case of an 'Error' open up the ErrorFragment
 * - In case of success open the HomeFragment
 * 
 * @author mark
 */
public class RecipieFrame extends AbstractLoadingFrame  {	
	protected void startAsyncDownload(Bundle savedInstanceState) throws MalformedURLException {
		_download = new AsyncDownload(getSherlockActivity().getCacheDir().toString()) {
			private Recipie _recipie;
			private Bitmap _image;
			
			@Override
			protected void onPostExecute(String result) {
				if(getException() == null) {
					final RecipieFragment home = new RecipieFragment(_recipie, _image);
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
					_recipie = new Recipie();
					Schema<Recipie> schema = RuntimeSchema.getSchema(Recipie.class);
					ProtobufIOUtil.mergeFrom(link, (Recipie)_recipie, schema);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				
			}
			
			@Override
			protected void getImageDownload(Bitmap image) {
				_image = image;
			}
		};
		
		long index = 0;
		if (getArguments() != null) {
			index = getArguments().getLong("index");
		}
		// TODO: Remove this crap
		if (index == 0L) {
			// No index, get recipie of the day
			_download.execute(new URL[] {
				new URL("http://dev.android.kookjij.mobi/api.php?f=h&date=" + AndroidUtilities.getInstance().getDate()),
				new URL("http://dev.android.kookjij.mobi/api.php?f=p&i=h&date=" + AndroidUtilities.getInstance().getDate())
			});
		} else {
			_download.execute(new URL[] {
				new URL("http://dev.android.kookjij.mobi/api.php?f=h&i=" + index + "&date=" + AndroidUtilities.getInstance().getDate()),
				new URL("http://dev.android.kookjij.mobi/api.php?f=p&i=" + index + "&date=" + AndroidUtilities.getInstance().getDate())
			});
		}
	}

	@Override
	protected int getFragmentId() {
		return 1;
	}
}
