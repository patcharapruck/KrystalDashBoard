package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import dashboard.android.hdw.com.krystaldashboard.R;

public class BillFragment extends Fragment {


    Button btnsaveBILL;
    public BillFragment() {
        super();
    }

    public static BillFragment newInstance() {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        String filename = Environment.getExternalStorageDirectory() +"/DCIM/"+ "/KrystalScreenShooter/" + now + ".png";

        View root = getView().getRootView();
        root.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(root.getDrawingCache());
        root.setDrawingCacheEnabled(false);

        File file = new File(filename);
        file.getParentFile().mkdirs();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();

            Uri uri = Uri.fromFile(file);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initInstances(final View rootView) {
        btnsaveBILL = (Button)rootView.findViewById(R.id.save_bill);

        btnsaveBILL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenshot();
                Toast.makeText(getContext(), "save image ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
