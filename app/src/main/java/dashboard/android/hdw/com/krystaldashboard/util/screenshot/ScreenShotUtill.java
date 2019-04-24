package dashboard.android.hdw.com.krystaldashboard.util.screenshot;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;

public class ScreenShotUtill {
    
    private static  ScreenShotUtill mInstance;

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

    public static ScreenShotUtill getmInstance() {
        return mInstance;
    }

    public static void setmInstance(ScreenShotUtill mInstance) {
        ScreenShotUtill.mInstance = mInstance;
    }

    /**
     * Measures and takes a screenshot of the provided {@link View}.
     *
     * @param view The view of which the screenshot is taken
     * @return A {@link Bitmap} for the taken screenshot.
     */
    public Bitmap takeScreenshotForView(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(view.getHeight(), View.MeasureSpec.AT_MOST));
        view.layout((int) view.getX(), (int) view.getY(), (int) view.getX() + view.getMeasuredWidth(),
                (int) view.getY() + view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);

        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return bitmap;
    }

    public Bitmap takeScreenshotForScreen(Activity activity) {
        return takeScreenshotForView(activity.getWindow().getDecorView().getRootView());
    }
}
