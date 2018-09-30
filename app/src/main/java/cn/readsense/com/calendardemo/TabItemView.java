package cn.readsense.com.calendardemo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabItemView extends ViewGroup {

    private String time;
    private String type;
    private float marginLeft;
    private float marginTop;
    private float marginBottom;
    private int timeColor;
    private int typeColor;
    private TextView txt_type;
    private TextView txt_value;

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
        if (attrs != null) {
            Resources resources = context.getResources();
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.calendar, defStyleAttr, 0);
            time = typedArray.getString(R.styleable.calendar_attr_time_value);
            type = typedArray.getString(R.styleable.calendar_attr_time_type);

            marginLeft = typedArray.getDimension(R.styleable.calendar_attr_margin_left, resources.getDimension(R.dimen.dimen_margin_left));
            marginTop = typedArray.getDimension(R.styleable.calendar_attr_margin_top, resources.getDimension(R.dimen.dimen_margin_top));
            marginBottom = typedArray.getDimension(R.styleable.calendar_attr_margin_bottom, resources.getDimension(R.dimen.dimen_margin_bottom));
            timeColor = typedArray.getColor(R.styleable.calendar_attr_value_color, resources.getColor(R.color.color_time_value));
            typeColor = typedArray.getColor(R.styleable.calendar_attr_type_color, resources.getColor(R.color.color_time_type));

            if (time != null) {

                setTextValue(time);
            }

            if (type != null) {
                setTextType(type);
            }

            txt_type.setTextColor(timeColor);
            typedArray.recycle();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private void initView() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.item_tablayout, null);
        txt_type = mView.findViewById(R.id.txt_type);
        txt_value = mView.findViewById(R.id.txt_value);
        addView(mView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void setTypeColor(int color) {
        txt_type.setTextColor(color);
    }

    public void setValueColor(int color) {
        txt_value.setTextColor(color);
    }

    public void setTextValue(String value) {

        if (value != null) {
            txt_value.setText(value);
        }
    }

    public void setTextType(String type) {

        if (type != null) {
            txt_type.setText(type);
        }
    }
}
