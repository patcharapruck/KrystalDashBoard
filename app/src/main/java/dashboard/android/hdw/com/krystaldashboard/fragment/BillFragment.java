package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.activty.BillActivity;
import dashboard.android.hdw.com.krystaldashboard.util.screenshot.FileUtil;
import dashboard.android.hdw.com.krystaldashboard.util.screenshot.ScreenShotUtill;

public class BillFragment extends Fragment {


    Button btnsaveBILL;
    ImageButton backidalog;
    LinearLayout parentView;
    private Bitmap bitmap;

    String CodeID;

    //    BillActivity billActivity;
    public BillFragment() {
        super();
    }

    public static BillFragment newInstance(String s) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putString("CodeID",s);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CodeID = getArguments().getString("CodeID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bill, container, false);
        initInstances(rootView);

        return rootView;

    }

    private void screenshot() {
        Date date = new Date();
        CharSequence now = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        //ตรงนี้เป็นการบันทึกชื่อไฟล์ โดยจะต้องบันทึกจากหมายเลยบิล เช่น NO.20190421050

        bitmap = ScreenShotUtill.getInstance().takeScreenshotForView(parentView);

        if (bitmap != null) {
            String path = Environment.getExternalStorageDirectory() .toString() + "/DCIM/" + "/KrystalScreenShooter/" + now + ".jpg";
            FileUtil.getInstance().storeBitmap(bitmap, path);
            Toast.makeText(getContext(), getString(R.string.toast_message_screenshot_success) + " " + path, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.toast_message_screenshot), Toast.LENGTH_LONG).show();
        }

//        View root = getView().getRootView();
//        root.setDrawingCacheEnabled(true);
//        Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
//        root.setDrawingCacheEnabled(false);
//
//        File file = new File(filename);
//        file.getParentFile().mkdirs();
//
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//
//            Uri uri = Uri.fromFile(file);
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

//    private void capture() {
//
//        linearLayout.isDrawingCacheEnabled();
//
//    }

    private void initInstances(final View rootView) {
        btnsaveBILL = (Button) rootView.findViewById(R.id.save_bill);
        backidalog = (ImageButton) rootView.findViewById(R.id.cloesdialog);
        parentView = (LinearLayout) rootView.findViewById(R.id.container_bill);

        btnsaveBILL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenshot();


            }
        });

        backidalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }







}
