import android.graphics.Bitmap;
import android.view.View;



public class ScreenShot {
    public static Bitmap takescreenshot(View view){
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static Bitmap takescreenshotofRootView(View view){
        return takescreenshotofRootView(view.getRootView());
    }
}
