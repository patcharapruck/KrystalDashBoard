package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.content.Context;
import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.BillCollectionDto;
import dashboard.android.hdw.com.krystaldashboard.dto.paymentstatus.PayItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.BillManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.PayManager;
import dashboard.android.hdw.com.krystaldashboard.util.screenshot.FileUtil;
import dashboard.android.hdw.com.krystaldashboard.util.screenshot.ScreenShotUtill;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillFragment extends Fragment {
    private Button btnsaveBILL;
    private ImageButton backidalog;
    private LinearLayout parentView;
    private Bitmap bitmap;

    Long CodeID;
    private ImageView imageViewShowScreenshot;

    private String sharePath = "no";


    //    BillActivity billActivity;
    public BillFragment() {
        super();
    }

    public static BillFragment newInstance(Long s) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putLong("CodeID",s);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CodeID = getArguments().getLong("CodeID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bill, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(final View rootView) {
        btnsaveBILL = (Button) rootView.findViewById(R.id.save_bill);
        backidalog = (ImageButton) rootView.findViewById(R.id.cloesdialog);
        parentView = (LinearLayout) rootView.findViewById(R.id.container_bill);

        imageViewShowScreenshot = (ImageView) rootView.findViewById(R.id.imageViewShowScreenshot);
        btnsaveBILL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture(v);
                requestPermissionAndSave();
                getFragmentManager().beginTransaction().replace(R.id.frame_bill,BillFragment_share.newInstance(sharePath)).commit();

            }
        });

        backidalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    // captureImage
    private Bitmap capture(View view) {

        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        bitmap = Bitmap.createBitmap(parentView.getMeasuredWidth(), parentView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
        parentView.layout(0, 0, parentView.getMeasuredWidth(), parentView.getMeasuredHeight());
        parentView.draw(canvas);
        view.setDrawingCacheEnabled(false);
        imageViewShowScreenshot.setImageBitmap(bitmap);

        return bitmap;
    }

    //    saveImage
    private void requestPermissionAndSave() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Date date = new Date();
                        CharSequence now = android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
                        if (bitmap != null) {
                            String path = Environment.getExternalStorageDirectory().toString() + "/DCIM/" + "/KrystalScreenShooter/" + now + ".jpg";
                            FileUtil.getInstance().storeBitmap(bitmap, path);

                            File imageFile = new File(path);
                            String filePath = imageFile.getPath();
                            sharePath = filePath;

                            Log.d("pathhhhha"," "+sharePath);

                            Toast.makeText(getActivity(), getString(R.string.toast_message_screenshot_success) + " " + path, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), getString(R.string.toast_message_screenshot), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            Toast.makeText(getActivity(), getString(R.string.settings_message), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void reqAPIpay() {
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"criteria\":{\"InvoiceDocument-id\":\""+ CodeID+"\"},\"property\":" +
                "[\"memberAccount->customerMemberAccount\",\"sales->employee\",\"place\",\"itemList\",\"transactionPaymentList->bank\"" +
                ",\"documentStatus\",\"transactionPaymentList->memberPayer\",\"transactionPaymentList->employeePayer\"]" +
                ",\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<BillCollectionDto> call = HttpManager.getInstance().getService().loadAPIBill(requestBody);
        call.enqueue(new Callback<BillCollectionDto>() {
            @Override
            public void onResponse(Call<BillCollectionDto> call, Response<BillCollectionDto> response) {
                if(response.isSuccessful()){
                    BillCollectionDto dto = response.body();
                    BillManager.getInstance().setBillCollectionDto(dto);

                }else {
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<BillCollectionDto> call, Throwable t) {
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อได้",Toast.LENGTH_LONG).show();
            }
        });

    }



}
