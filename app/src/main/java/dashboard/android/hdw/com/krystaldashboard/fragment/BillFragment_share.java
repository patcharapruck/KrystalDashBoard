package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.Date;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.activty.BillActivity;
import dashboard.android.hdw.com.krystaldashboard.util.screenshot.FileUtil;

public class BillFragment_share extends Fragment {
    private Button btnshare;
    private Button btnback;
    private Bitmap bitmap;
    private ImageView imageViewShowScreenshot;

    private String sharePath="no";

    //    BillActivity billActivity;
    public BillFragment_share() {
        super();
    }

    public static BillFragment_share newInstance(String sharePath) {
        BillFragment_share fragment = new BillFragment_share();
        Bundle args = new Bundle();
        args.putString("path",sharePath);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePath = getArguments().getString("path");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bill_share, container, false);
        initInstances(rootView);
        return rootView;
    }



    private void initInstances(final View rootView) {
//        btnshare = (Button) rootView.findViewById(R.id.share);
        imageViewShowScreenshot = (ImageView) rootView.findViewById(R.id.imageViewShowScreenshot);
        btnback = (Button) rootView.findViewById(R.id.close);
        Log.d("pathhhhh"," "+sharePath);
        imageViewShowScreenshot.setImageBitmap(BitmapFactory.decodeFile(sharePath));


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }





}
