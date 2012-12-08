package nl.rootdev.android.kookjijclient2.ui;


public class ImageTextTuple {
	private String text_;
	private String smallText_;
	private int imageResId_;
	
	public ImageTextTuple(int imageResId, String text, String smallText) {
		imageResId_ = imageResId;
		text_ = text;
		smallText_ = smallText;
	}
	
	public String getText_() {
		return text_;
	}
	public void setText(String text) {
		text_ = text;
	}

	public int getImageResId() {
		return imageResId_;
	}

	public void setImageResId(int imageResId) {
		imageResId_ = imageResId;
	}

	public String getSmallText() {
		return smallText_;
	}

	public void setSmallText(String smallText_) {
		this.smallText_ = smallText_;
	}
}
