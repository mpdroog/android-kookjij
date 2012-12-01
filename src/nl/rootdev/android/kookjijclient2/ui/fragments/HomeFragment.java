package nl.rootdev.android.kookjijclient2.ui.fragments;

import java.net.URL;
import java.net.URLConnection;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.IRecipie;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class HomeFragment extends SherlockFragment implements OnClickListener {
	private final Bitmap _image;
	private final IRecipie _recipie;
	
	public HomeFragment(IRecipie recipie, Bitmap image) {
		_image = image;
		_recipie = recipie;
	}

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
		
		title.setText(_recipie.getName());
		text.setText(_recipie.getDescription());
		if (_image != null) {
			preview.setImageBitmap(_image);
		}
		else {
			preview.setImageResource(R.drawable.logo_gray);
		}
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
