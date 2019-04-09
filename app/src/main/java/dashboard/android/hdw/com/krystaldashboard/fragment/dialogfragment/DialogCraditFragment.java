package dashboard.android.hdw.com.krystaldashboard.fragment.dialogfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.credit.CreditItemColleationDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;

public class DialogCraditFragment extends DialogFragment implements View.OnClickListener {

    private static final String TAG = "DialogCraditFragment";

    TextView aMaxTextView,jcbTextView,masterTextView,unipayTextView,visaTextView,totalTextView;
    double amax = 0.0, jcb = 0.0, master = 0.0, unipay = 0.0, visa = 0.0;
    DecimalFormat formatter;
    String NameCredit = "" , Total = "0.00";
    TextView NameCreditDialog;
    RelativeLayout ToolbarColor;
    Button CloseDialog;
    ImageView Logo;

    public DialogCraditFragment(){
        super();
    }

    public static DialogCraditFragment newInstance() {
        DialogCraditFragment fragment = new DialogCraditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void setNameCredit(String name,String total){
        this.NameCredit = name;
        this.Total = total;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialogpopupkrungtep, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        formatter = new DecimalFormat("#,###,##0.00");

        ToolbarColor = (RelativeLayout) rootView.findViewById(R.id.toolbardialog);
        NameCreditDialog = (TextView) rootView.findViewById(R.id.textview_namecreddit);
        CloseDialog = (Button) rootView.findViewById(R.id.cloesdialog);

        aMaxTextView = (TextView) rootView.findViewById(R.id.textview_amax);
        jcbTextView = (TextView) rootView.findViewById(R.id.textview_jcb);
        masterTextView = (TextView) rootView.findViewById(R.id.textview_master);
        unipayTextView = (TextView) rootView.findViewById(R.id.textview_unipay);
        visaTextView = (TextView) rootView.findViewById(R.id.textview_visa);
        totalTextView = (TextView) rootView.findViewById(R.id.textview_total);

        Logo = (ImageView) rootView.findViewById(R.id.logo);

        CloseDialog.setOnClickListener(this);

        NameCreditDialog.setText(NameCredit);

        if (NameCredit.equals("ธนาคารธนชาต (T-BANK)")){
            ToolbarColor.setBackgroundColor(Color.parseColor("#F37637"));
            totalTextView.setText(Total+" บาท");
        }
        else if (NameCredit.equals("ธนาคารกรุงเทพ (BBL)")){
            ToolbarColor.setBackgroundColor(Color.parseColor("#204298"));
            totalTextView.setText(Total+" บาท");
            Logo.setImageResource(R.drawable.krungtap);
        }
        else if (NameCredit.equals("ธนาคารไทยพาณิชย์ (SCB)")){
            ToolbarColor.setBackgroundColor(Color.parseColor("#502984"));
            totalTextView.setText(Total+" บาท");
        }
        else if (NameCredit.equals("บัญชีคุณอ๊อด")){
            ToolbarColor.setBackgroundColor(Color.parseColor("#000000"));
            totalTextView.setText(Total+" บาท");
        }


        for(int i=0;true;i++){
            CreditItemColleationDto Credit;
            try{
                Credit = DashBoradManager.getInstance().getDao()
                        .getObject().getIncomeByCreditCardList().get(i);
            }catch (Exception e){
                break;
            }

            amax = Credit.getAmax();
            jcb = Credit.getJcb();
            master = Credit.getMaster();
            unipay = Credit.getUnipay();
            visa = Credit.getVisa();

            if (Credit.getBank().getBankName().equals(NameCredit)){

                    aMaxTextView.setText(formatter.format(amax)+" บาท");
                    jcbTextView.setText(formatter.format(jcb)+" บาท");
                    masterTextView.setText(formatter.format(master)+" บาท");
                    unipayTextView.setText(formatter.format(unipay)+" บาท");
                    visaTextView.setText(formatter.format(visa)+" บาท");

            }

        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if(v == CloseDialog){
            getDialog().dismiss();
        }
    }
}
