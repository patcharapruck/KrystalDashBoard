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
public class Revanuef2Fragment1 extends Fragment {

    ObjectItemDto ODto;

    TextView TextViewCashPayments,TextViewCreditPayments,TextViewMemberDebitPayments
            ,TextViewEntertainPayments,TextViewCredit;

    Double CashPayments,CreditPayments,MemberDebitPayments
            ,EntertainPayments,Credit;


    DecimalFormat formatter;

    public Revanuef2Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_revanue_f2_1,container,false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        TextViewCashPayments = (TextView) rootView.findViewById(R.id.textview_cash_payments);
        TextViewCreditPayments = (TextView) rootView.findViewById(R.id.textview_credit_payments);
        TextViewMemberDebitPayments = (TextView) rootView.findViewById(R.id.textview_member_debit_payments);
        TextViewEntertainPayments = (TextView) rootView.findViewById(R.id.textview_entertain_payments);
        TextViewCredit = (TextView) rootView.findViewById(R.id.textview_credit);

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
            CreditPayments = ODto.getCreditCardPayments();
        }catch (Exception e){
            CreditPayments = 0.00;
        }

        try {
            Credit = ODto.getCreditPayments();
        }catch (Exception e){
            Credit = 0.00;
        }

        try {
            MemberDebitPayments = ODto.getMemberDebitPayments();
        }catch (Exception e){
            MemberDebitPayments = 0.00;
        }

        try {
            EntertainPayments = ODto.getEntertainPayments();
        }catch (Exception e){
            EntertainPayments = 0.00;
        }

        TextViewCashPayments.setText(formatter.format(CashPayments));
        TextViewCreditPayments.setText(formatter.format(CreditPayments));
        TextViewMemberDebitPayments.setText(formatter.format(MemberDebitPayments));
        TextViewEntertainPayments.setText(formatter.format(EntertainPayments));
        TextViewCredit.setText(formatter.format(Credit));

    }

}
