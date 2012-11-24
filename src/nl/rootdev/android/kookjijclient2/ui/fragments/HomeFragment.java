package nl.rootdev.android.kookjijclient2.ui.fragments;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.IRecipie;
import nl.rootdev.android.kookjijclient2.datastructures.pb.Recipie;
import nl.rootdev.android.kookjijclient2.utils.AndroidUtilities;
import nl.rootdev.android.kookjijclient2.utils.ConnectionTypes;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyuproject.protostuff.me.ProtostuffIOUtil;

public class HomeFragment extends SherlockFragment implements OnClickListener {
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

		final TextView text = (TextView) getSherlockActivity().findViewById(R.id.editText2);
		final TextView title = (TextView) getSherlockActivity().findViewById(R.id.title);
		final ImageView preview = (ImageView) getSherlockActivity().findViewById(R.id.preview);
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			private IRecipie _recipie;
			private Bitmap _bitmap;
			private Exception _error;
			private boolean _noImagePreload;
			
			/**
			 * http://developer.android.com/training/efficient-downloads/redundant_redundant.html
			 */
			private void enableHttpResponseCache() {
				  try {
				    long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
				    File httpCacheDir = new File(getSherlockActivity().getCacheDir(), "http");
				    Class.forName("android.net.http.HttpResponseCache")
				         .getMethod("install", File.class, long.class)
				         .invoke(null, httpCacheDir, httpCacheSize);
				  } catch (Exception httpResponseCacheNotAvailable) {
				    System.out.println("HTTP response cache is unavailable");
				  }
				}			
			
			@Override
			protected void onPostExecute(Void result) {
				if(_error != null) {
					// Load error screen...
					/*Intent intent = new Intent(getSherlockActivity(), ErrorFragment.class);
					Bundle bundle = new Bundle();
					bundle.putString("exception", _error.getStackTrace().toString());
					intent.putExtras(bundle);
					*/
					
					System.err.println("postexecute yes...");
/*					Fragment screen = SherlockFragment.instantiate(getSherlockActivity(), ErrorFragment.class.getName());
					FragmentTransaction transaction = getSherlockActivity().getSupportFragmentManager().beginTransaction();
					transaction.add(android.R.id.content, screen);
					transaction.commit();*/					
				}
				else {
					title.setText(_recipie.getName());
					text.setText(_recipie.getDescription());
					if(_noImagePreload) {
						preview.setImageResource(R.drawable.logo_gray);
					}
					else {
						preview.setImageBitmap(_bitmap);
					}
				}
				//getActivity().setProgressBarIndeterminate(false);
			}
			@Override
			protected Void doInBackground(Void... params) {
				try {
					enableHttpResponseCache();
					ConnectionTypes connection = AndroidUtilities.getInstance().getConnectionSpeed();

					// Always read text
					{
						URLConnection link = new URL("http://dev.android.kookjij.mobi/api.php?f=h").openConnection();
						_recipie = new Recipie();
						ProtostuffIOUtil.mergeFrom(new GZIPInputStream(link.getInputStream()), _recipie,
								Recipie.getSchema());
					}
					
					// Only image on speedy connection
					if(connection == ConnectionTypes.TYPE_WIFI || connection == ConnectionTypes.TYPE_MOBILE_FAST) {
						URLConnection img = new URL("http://dev.android.kookjij.mobi/api.php?f=p&i=12").openConnection();
						_bitmap = BitmapFactory.decodeStream(img.getInputStream());
					} else {
						_noImagePreload = true;
					}
					
					//_recipie = BackendSingleton.getHttpClient().getRecipieOfDay();
					//_bitmap = BackendSingleton.getHttpClient().getBitmap(new URI(
					//	"http://dev.android.kookjij.mobi/api.php?f=p&i=12"
					//));
				}
				catch(Exception e) {
					_error = e;
				}
				return params[0];
			}
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}
		};
		task.execute(new Void[] {null});
		
		preview.setOnClickListener(this);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.home, container, false);
	}

	@Override
	public void onClick(View v) {
		final ImageView preview = (ImageView) getSherlockActivity().findViewById(R.id.preview);
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			private Bitmap _bitmap;

			@Override
			protected void onPostExecute(Void result) {
				if(_bitmap != null) {
					preview.setImageBitmap(_bitmap);
				}
			}
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				//preview.setImageResource(R.drawable.);
			}

			@Override
			protected Void doInBackground(Void... params) {
				URLConnection img;
				try {
					img = new URL("http://dev.android.kookjij.mobi/api.php?f=p&i=12").openConnection();
					_bitmap = BitmapFactory.decodeStream(img.getInputStream());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return params[0];
			}			
		};
		task.execute(new Void[] {null});
	}	
}
