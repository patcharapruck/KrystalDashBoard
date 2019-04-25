package dashboard.android.hdw.com.krystaldashboard.util.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import static android.graphics.Bitmap.createBitmap;

public class ScreenShotUtill {

    private static ScreenShotUtill mInstance;

    public ScreenShotUtill() {
    }

    public static ScreenShotUtill getInstance() {
        if (mInstance == null) {
            synchronized (ScreenShotUtill.class) {
                if (mInstance == null) {
                    mInstance = new ScreenShotUtill();
                }
            }
        }
        return mInstance;
    }


    /**
     * Measures and takes a screenshot of the provided {@link View}.
     *
     * @param view The view of which the screenshot is taken
     * @return A {@link Bitmap} for the taken screenshot.
     */
    public Bitmap takeScreenshotForView(View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.AT_MOST),
                View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.EXACTLY));

        view.layout((int) view.getX(), (int) view.getY(), (int) view.getX() + view.getMeasuredWidth(),
                (int) view.getY() + view.getMeasuredHeight());

        Log.d("Layout","Width"+view.getWidth()
                +"\n Height"+view.getHeight());

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        bitmap =Bitmap.createBitmap(bitmap);
        view.setDrawingCacheEnabled(false);


        return bitmap;
}
//    public Bitmap takeScreenshotForView(View view) {
//
//        Canvas canvas = new Canvas();
//        view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
//                View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.EXACTLY));
//        view.layout((int) view.getX(), (int) view.getY(), (int) view.getX() + view.getMeasuredWidth(),
//                (int) view.getY() + view.getMeasuredHeight());
//
//        Log.d("Layout", "Width" + view.getWidth()
//                + "\n Height" + view.getHeight());
//
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache(true);
//
//        Bitmap bitmap = createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_4444);
//        view.draw(canvas);
//        view.setDrawingCacheEnabled(false);
//
//
//        return bitmap;
//    }



    public Bitmap takeScreenshotForScreen(Activity activity) {
        return takeScreenshotForView(activity.getWindow().getDecorView().getRootView());
    }
}
