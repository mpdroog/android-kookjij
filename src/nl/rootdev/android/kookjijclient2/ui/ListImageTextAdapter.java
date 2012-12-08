package nl.rootdev.android.kookjijclient2.ui;

import java.util.ArrayList;
import java.util.List;

import nl.rootdev.android.kookjijclient2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Wrapper for creating an image/text list.
 * @author mark
 *
 */
public class ListImageTextAdapter extends BaseAdapter {
	private List<ImageTextTuple> items_;

	public ListImageTextAdapter() {
		items_ = new ArrayList<ImageTextTuple>();
	}
	
	public void addImageText(int imageResId, String text, String smallText) {
		items_.add(new ImageTextTuple(imageResId, text, smallText));
	}
	
	@Override
	public int getCount() {
		return items_.size();
	}

	@Override
	public Object getItem(int position) {
		return items_.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items_.get(position).hashCode();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageTextTuple tuple = items_.get(position);
		LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout view = (RelativeLayout) inflater.inflate(R.layout.result_item, null);
		
		final ImageView image = (ImageView) view.findViewById(R.id.result_icon);
		image.setImageResource(R.drawable.ic_launcher);
		
/*		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			private Bitmap _bitmap;
			
			@Override
			protected void onPostExecute(Void result) {
				image.setImageBitmap(_bitmap);
			}
			@Override
			protected Void doInBackground(Void... params) {
				URL ulrn;
				try {
					ulrn = new URL("http://www.google.nl/images/srpr/logo3w.png");
				    HttpURLConnection con = (HttpURLConnection)ulrn.openConnection();
				    InputStream is = con.getInputStream();		
					_bitmap = BitmapFactory.decodeStream(is);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return params[0];
			}
		};
		task.execute(new Void[] {null});		*/
		
		TextView title = (TextView) view.findViewById(R.id.result_name);
		title.setText(tuple.getText_());
		
		TextView smallText = (TextView) view.findViewById(R.id.result_second_line);
		smallText.setText(tuple.getSmallText());
		
		return view;
	}

}
