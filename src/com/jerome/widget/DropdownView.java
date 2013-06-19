package com.jerome.widget;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;

import com.jerome.lib_song.R;

public class DropdownView extends RelativeLayout implements OnDismissListener {
	private float jdField_a_of_type_Float;
	private Context context;
	private Drawable loginMore;
	private InputMethodManager inputMethodManager;
	public ImageView clearImg;
	private final RelativeLayout.LayoutParams jdField_a_of_type_AndroidWidgetRelativeLayout$LayoutParams = new RelativeLayout.LayoutParams(
			-1, -1);
	public cbz jdField_a_of_type_Cbz;
	private boolean jdField_a_of_type_Boolean = false;
	private Drawable loginMoreup;
	public ImageView accountRight;

	public DropdownView(Context paramContext) {
		super(paramContext);
		this.context = paramContext;
		this.jdField_a_of_type_Cbz = new cbz(this, paramContext);
		a(paramContext);
	}

	public DropdownView(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		this.jdField_a_of_type_Cbz = new cbz(this, paramContext,
				paramAttributeSet);
		a(paramContext);
	}

	@TargetApi(11)
	public DropdownView(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		this.jdField_a_of_type_Cbz = new cbz(this, paramContext,
				paramAttributeSet, paramInt);
		a(paramContext);
	}

	private void a(Context paramContext) {
		this.inputMethodManager = ((InputMethodManager) paramContext
				.getSystemService("input_method"));
		this.jdField_a_of_type_Float = paramContext.getResources()
				.getDisplayMetrics().density;
		setLayoutParams(this.jdField_a_of_type_AndroidWidgetRelativeLayout$LayoutParams);
		setPadding(0, 0, 0, 0);
		RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(
				this.jdField_a_of_type_AndroidWidgetRelativeLayout$LayoutParams);
		addView(this.jdField_a_of_type_Cbz, localLayoutParams1);
		this.jdField_a_of_type_Cbz.setDropDownVerticalOffset(0);
		LinearLayout localLinearLayout = new LinearLayout(paramContext);
		RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(
				-2, -1);
		localLayoutParams2.setMargins(0, 0, 0, 0);
		localLayoutParams2.addRule(15);
		localLayoutParams2.addRule(7, this.jdField_a_of_type_Cbz.getId());
		addView(localLinearLayout, localLayoutParams2);
		this.clearImg = new ImageView(paramContext);
		this.clearImg.setPadding((int) (10.0F * this.jdField_a_of_type_Float),
				(int) (10.0F * this.jdField_a_of_type_Float),
				(int) (12.0F * this.jdField_a_of_type_Float),
				(int) (10.0F * this.jdField_a_of_type_Float));
		this.clearImg.setImageResource(R.drawable.common_input_box_clear_bg);
		this.clearImg.setClickable(true);
		this.clearImg.setVisibility(8);
		new LinearLayout.LayoutParams(
				this.jdField_a_of_type_AndroidWidgetRelativeLayout$LayoutParams);
		LinearLayout.LayoutParams localLayoutParams3 = new LinearLayout.LayoutParams(
				(int) (41.0F * this.jdField_a_of_type_Float),
				(int) (39.0F * this.jdField_a_of_type_Float));
		localLayoutParams3.gravity = 16;
		localLinearLayout.addView(this.clearImg, localLayoutParams3);
		this.accountRight = new ImageView(paramContext);
		this.accountRight.setId(571);
		this.accountRight.setPadding(
				(int) (1.0F * this.jdField_a_of_type_Float),
				(int) (10.0F * this.jdField_a_of_type_Float),
				(int) (12.0F * this.jdField_a_of_type_Float),
				(int) (10.0F * this.jdField_a_of_type_Float));
		this.loginMore = getResources().getDrawable(R.drawable.login_more);
		this.loginMoreup = getResources().getDrawable(R.drawable.login_more_up);
		this.accountRight.setImageDrawable(this.loginMore);
		this.accountRight.setClickable(true);
		LinearLayout.LayoutParams localLayoutParams4 = new LinearLayout.LayoutParams(
				(int) (27.0F * this.jdField_a_of_type_Float),
				(int) (28.0F * this.jdField_a_of_type_Float));
		localLayoutParams4.gravity = 16;
		localLinearLayout.addView(this.accountRight, localLayoutParams4);
		this.accountRight.setOnClickListener(new cbx(this));
		try {
			Field localField = this.jdField_a_of_type_Cbz.getClass()
					.getSuperclass().getDeclaredField("mPopup");
			localField.setAccessible(true);
			Object localObject = localField.get(this.jdField_a_of_type_Cbz);
			Class localClass = localObject.getClass();
			Class[] arrayOfClass = new Class[1];
			arrayOfClass[0] = PopupWindow.OnDismissListener.class;
			Method localMethod = localClass.getMethod("setOnDismissListener",
					arrayOfClass);
			Object[] arrayOfObject = new Object[1];
			arrayOfObject[0] = this;
			localMethod.invoke(localObject, arrayOfObject);
			return;
		} catch (Exception localException) {
		}
	}

	public final AutoCompleteTextView getAutoCompleteTextView() {
		return this.jdField_a_of_type_Cbz;
	}

	public final ImageView a() {
		return this.clearImg;
	}

	public final ImageView b() {
		return this.accountRight;
	}

	public void onDismiss() {
		this.accountRight.setImageDrawable(this.loginMore);
		new Handler().postDelayed(new cby(this), 500L);
	}

	public final class cby implements Runnable {
		public cby(DropdownView paramDropdownView) {
		}

		public final void run() {
			jdField_a_of_type_Boolean = false;
		}
	}

	public final class cbx implements View.OnClickListener {
		public cbx(DropdownView paramDropdownView) {
		}

		public final void onClick(View paramView) {
			jdField_a_of_type_Cbz.clearFocus();
			inputMethodManager.hideSoftInputFromInputMethod(
					jdField_a_of_type_Cbz.getWindowToken(), 0);
			if (jdField_a_of_type_Cbz.getAdapter() == null
					|| (jdField_a_of_type_Cbz.getAdapter().getCount() <= 0)
					|| (((ImageView) paramView).getDrawable() != loginMore)
					|| jdField_a_of_type_Boolean)
				return;
			jdField_a_of_type_Cbz.showDropDown();
			((ImageView) paramView).setImageDrawable(loginMoreup);
			jdField_a_of_type_Boolean = true;
		}
	}

}
