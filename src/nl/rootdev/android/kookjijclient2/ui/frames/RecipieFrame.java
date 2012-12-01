package nl.rootdev.android.kookjijclient2.ui.frames;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.pb.Recipie;
import nl.rootdev.android.kookjijclient2.ui.fragments.ErrorFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.HomeFragment;
import nl.rootdev.android.kookjijclient2.ui.fragments.LoadingFragment;
import nl.rootdev.android.kookjijclient2.ui.tasks.AsyncDownload;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyuproject.protostuff.me.ProtostuffIOUtil;

public class RecipieFrame extends SherlockFragment {
	private AsyncDownload _download;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onDestroyView() {
		// Close all connections from the background processes
		super.onDestroyView();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final LoadingFragment loading = new LoadingFragment();
		FragmentTransaction action = getFragmentManager().beginTransaction();
		action.replace(R.id.contentFragment, loading).commit();

		try {
			_download = new AsyncDownload(getSherlockActivity().getCacheDir().toString()) {
				private Recipie _recipie;
				private Bitmap _image;
				
				@Override
				protected void onPostExecute(String result) {
					final HomeFragment home = new HomeFragment(_recipie, _image);
					final FragmentTransaction action2 = getFragmentManager().beginTransaction();
					action2.replace(R.id.contentFragment, home).commit();

					// set text+ image
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
				new URL("http://dev.android.kookjij.mobi/api.php?f=h"),
				new URL("http://dev.android.kookjij.mobi/api.php?f=p&i=12")
			});
		}
		catch(Exception e) {
			// Catch all exceptions, if this happens
			// something went wrong while downloading and we can
			// display this..
			final ErrorFragment error = new ErrorFragment();
			final FragmentTransaction action2 = getFragmentManager().beginTransaction();
			action2.replace(R.id.contentFragment, error).commit();
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View screen = inflater.inflate(R.layout.recipiefragment, container, false);

		return screen;
	}
}
