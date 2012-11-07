package hu.scythe.gridlayouttest;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class FontFitTextView extends TextView {

	// Scaling factor
	private static final float VERTICAL_FONT_SCALING_FACTOR = 0.9f;

	// Attributes
	private Paint mTestPaint;

	public FontFitTextView(Context context) {
		super(context);
		initialise();
	}

	public FontFitTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialise();
	}

	private void initialise() {
		mTestPaint = new Paint();
		mTestPaint.set(this.getPaint());
	}

	/*
	 * Resize the font so the specified text fits in the text box assuming the
	 * text box is the specified width.
	 */
	private void refitText(String text, int textWidth, int textHeight) {
		if (textHeight <= 0 || textWidth <= 0) {
			return;
		}

		// Find target height
		float targetTextSizeVertical = (textHeight - this.getPaddingTop() - this.getPaddingBottom()) * VERTICAL_FONT_SCALING_FACTOR;

		// Find target width
		float targetWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();

		float hi = 800;
		float lo = 2;
		final float threshold = 0.5f; // How close we have to be

		mTestPaint.set(this.getPaint());

		while ((hi - lo) > threshold) {
			float size = (hi + lo) / 2;
			mTestPaint.setTextSize(size);
			if (mTestPaint.measureText(text) >= targetWidth)
				hi = size; // too big
			else
				lo = size; // too small
		}
		float targetTextSizeHorizontal = lo;

		// Set the text size
		float targetTextSize = Math.min(targetTextSizeVertical, targetTextSizeHorizontal);

		this.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) targetTextSize);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
		int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

		refitText(this.getText().toString(), parentWidth, parentHeight);
		this.setMeasuredDimension(parentWidth, parentHeight);
	}

	@Override
	protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
		refitText(text.toString(), this.getWidth(), this.getHeight());
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		if (h != oldh || w != oldw) {
			refitText(this.getText().toString(), w, h);
		}
	}

}