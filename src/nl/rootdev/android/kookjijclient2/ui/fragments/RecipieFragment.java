package nl.rootdev.android.kookjijclient2.ui.fragments;

import java.net.URL;
import java.net.URLConnection;

import nl.rootdev.android.kookjijclient2.R;
import nl.rootdev.android.kookjijclient2.datastructures.IRecipie;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

/**
 * Fragment for showing a Recipie.
 * 
 * @author mark
 */
public class RecipieFragment extends SherlockFragment implements OnClickListener {
	/** Downloaded image from the webserver */
	private final Bitmap _image;
	/** Downloaded recipie from the webserver */
	private final IRecipie _recipie;
	
	public RecipieFragment(IRecipie recipie, Bitmap image) {
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
		super.onDestroyView();
	}
	
	/**
	 * Fill the view with actual data.
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final TextView text = (TextView) getSherlockActivity().findViewById(R.id.editText2);
		final TextView title = (TextView) getSherlockActivity().findViewById(R.id.title);
		final ImageView preview = (ImageView) getSherlockActivity().findViewById(R.id.preview);
		
		title.setText(_recipie.getName());
		text.setText(Html.fromHtml(_recipie.getDescription()), TextView.BufferType.SPANNABLE);
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

	/**
	 * When on a slow internet connection the image is
	 * never download automatically, so added onclick-listener
	 * here to download it if the user want's.
	 */
	@Override
	public void onClick(View v) {
		if (_image != null) {
			// No need to 'download image' if already loaded
			return;
		}
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
