package dashboard.android.hdw.com.krystaldashboard.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.dto.ObjectItemDto;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Revanuef2Fragment2 extends Fragment {

    ObjectItemDto ODto;
    TextView TextViewCashPayments,TextViewCreditPayments,TextViewCredit;
    Double CashPayments,CreditPayments,Credit;
    DecimalFormat formatter;

    public Revanuef2Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_revanue_f2_2,container,false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        TextViewCashPayments = (TextView) rootView.findViewById(R.id.textview_cash_payments_revanus2);
        TextViewCreditPayments = (TextView) rootView.findViewById(R.id.textview_credit_payments_revanus2);
        TextViewCredit = (TextView) rootView.findViewById(R.id.textview_credit_revanus2);

        setTextViewIncome();
    }

    private void setTextViewIncome() {
        formatter = new DecimalFormat("#,###,##0.00");

        try {
            ODto = DashBoradManager.getInstance().getDto().getObject();
        }catch (Exception e){
            ODto = null;
        }

        try {
            CashPayments = ODto.getCashPayments();
        }catch (Exception e){
            CashPayments = 0.00;
        }

        try {
            CreditPayments = ODto.getCreditPayments();
        }catch (Exception e){
            CreditPayments = 0.00;
        }

        try {
            Credit = ODto.getCreditCardPayments();
        }catch (Exception e){
            Credit = 0.00;
        }


        TextViewCashPayments.setText(formatter.format(CashPayments));
        TextViewCreditPayments.setText(formatter.format(CreditPayments));
        TextViewCredit.setText(formatter.format(Credit));

    }

}
