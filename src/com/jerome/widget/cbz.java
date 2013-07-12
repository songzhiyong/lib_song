package com.jerome.widget;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.PopupWindow;

/**
 * 
 * ClassName:cbz <br>
 * Function: 腾讯QQ代码 TODO：未完
 * 
 * @author Jerry
 * @version
 * @Date 2013 2013-7-12 下午5:01:52
 * 
 * @see
 */
public final class cbz extends AutoCompleteTextView {
	private boolean jdField_a_of_type_Boolean;

	public cbz(DropdownView paramDropdownView, Context paramContext) {
		super(paramContext);
		setId(526);
		this.jdField_a_of_type_Boolean = true;
	}

	public cbz(DropdownView paramDropdownView, Context paramContext,
			AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		setId(526);
		this.jdField_a_of_type_Boolean = true;
	}

	public cbz(DropdownView paramDropdownView, Context paramContext,
			AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		setId(526);
		this.jdField_a_of_type_Boolean = true;
	}

	public final boolean enoughToFilter() {
		return true;
	}

	public final boolean isPopupShowing() {
		if (this.jdField_a_of_type_Boolean)
			;
		for (boolean bool = super.isPopupShowing();; bool = false)
			return bool;
	}

	public final void onEditorAction(int paramInt) {
		if (paramInt == 5) {
			View localView = focusSearch(130);
			if ((localView == null) || (localView.requestFocus(130)))
				return;
			throw new IllegalStateException(
					"focus search returned a view that wasn't able to take focus!");
		}
		super.onEditorAction(paramInt);
	}

	protected final void onTextChanged(CharSequence paramCharSequence,
			int paramInt1, int paramInt2, int paramInt3) {
		super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
		try {
			if (isPopupShowing())
				dismissDropDown();
			return;
		} catch (Exception localException) {
		}
	}

	protected final void performFiltering(CharSequence paramCharSequence,
			int paramInt) {
	}

	public final void showDropDown() {
		super.showDropDown();
		if (Build.VERSION.SDK_INT <= 8)
			try {
				Field localField3 = super.getClass().getSuperclass()
						.getDeclaredField("mDropDownList");
				localField3.setAccessible(true);
				Object localObject3 = localField3.get(this);
				Class localClass2 = localObject3.getClass().getSuperclass();
				Class[] arrayOfClass2 = new Class[1];
				arrayOfClass2[0] = Integer.TYPE;
				Method localMethod2 = localClass2.getMethod("setDividerHeight",
						arrayOfClass2);
				Object[] arrayOfObject2 = new Object[1];
				arrayOfObject2[0] = Integer.valueOf(0);
				localMethod2.invoke(localObject3, arrayOfObject2);
			} catch (Exception localException1) {
				try {
					Field localField1 = super.getClass().getSuperclass()
							.getDeclaredField("mPopup");
					localField1.setAccessible(true);
					Object localObject1 = localField1.get(this);
					Field localField2 = localObject1.getClass()
							.getDeclaredField("mDropDownList");
					localField2.setAccessible(true);
					Object localObject2 = localField2.get(localObject1);
					Class localClass1 = localObject2.getClass().getSuperclass();
					Class[] arrayOfClass1 = new Class[1];
					arrayOfClass1[0] = Integer.TYPE;
					Method localMethod1 = localClass1.getMethod(
							"setDividerHeight", arrayOfClass1);
					Object[] arrayOfObject1 = new Object[1];
					arrayOfObject1[0] = Integer.valueOf(0);
					localMethod1.invoke(localObject2, arrayOfObject1);
					((PopupWindow) localObject1).setAnimationStyle(2130968624);
				} catch (Exception localException2) {
				}
			}
	}
}