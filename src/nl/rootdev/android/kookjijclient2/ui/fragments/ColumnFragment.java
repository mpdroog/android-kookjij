package nl.rootdev.android.kookjijclient2.ui.fragments;

import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.pb.Column;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.dyuproject.protostuff.me.ProtostuffIOUtil;

public class ColumnFragment extends SherlockFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final TextView text = (TextView) getSherlockActivity().findViewById(R.id.column_message);
		final TextView title = (TextView) getSherlockActivity().findViewById(R.id.column_title);
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			private Column _recipie;
			private Exception _error;
			
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
					if(_error.getMessage() == null) {
						text.setText(_error.getClass().getName());
					} else {
						text.setText(_error.getMessage());
					}
				}
				else {
					title.setText(_recipie.getName());
					text.setText(Html.fromHtml(_recipie.getText()));
				}
			}
			@Override
			protected Void doInBackground(Void... params) {
				try {
					enableHttpResponseCache();
					System.out.println("Column  ");

					// Always read text
					{
						URLConnection link = new URL("http://dev.android.kookjij.mobi/api.php?f=a").openConnection();
						_recipie = new Column();
						ProtostuffIOUtil.mergeFrom(new GZIPInputStream(link.getInputStream()), _recipie,
								Column.getSchema());
					}
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
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.column, container, false);
	}
}
