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
public class Revanuef2Fragment extends Fragment {

    ObjectItemDto ODto;

    TextView TextViewCashPayments,TextViewCreditPayments,TextViewMemberDebitPayments
            ,TextViewEntertainPayments,TextViewCredit;

    TextView TextViewUnpaid,TextViewDrink,TextViewMember,TextViewService,TextViewProduct;

    Double CashPayments,CreditPayments,MemberDebitPayments
            ,EntertainPayments,Credit, Unpaid,Drink
            ,Member,Service,Product;

//    String CashPaymentsText,CreditPaymentsText,MemberDebitPaymentsText
//            ,EntertainPaymentsText,CreditText,UnpaidText,DrinkText
//            ,MemberText,ServiceText,ProductText;

    DecimalFormat formatter;

    public Revanuef2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_revanue_f2,container,false);
        initInstances(rootView);
        return rootView;

    }

    private void initInstances(View rootView) {

        TextViewCashPayments = (TextView) rootView.findViewById(R.id.textview_cash_payments);
        TextViewCreditPayments = (TextView) rootView.findViewById(R.id.textview_credit_payments);
        TextViewMemberDebitPayments = (TextView) rootView.findViewById(R.id.textview_member_debit_payments);
        TextViewEntertainPayments = (TextView) rootView.findViewById(R.id.textview_entertain_payments);
        TextViewCredit = (TextView) rootView.findViewById(R.id.textview_credit);

        TextViewUnpaid = (TextView) rootView.findViewById(R.id.textview_unpaid);
        TextViewDrink = (TextView) rootView.findViewById(R.id.textview_drink);
        TextViewMember = (TextView) rootView.findViewById(R.id.textview_member);
        TextViewService = (TextView) rootView.findViewById(R.id.textview_service);
        TextViewProduct = (TextView) rootView.findViewById(R.id.textview_product);

        setTextViewIncome();
    }

    private void setTextViewIncome() {
        formatter = new DecimalFormat("#,###,##0.00");

        ODto = DashBoradManager.getInstance().getDto().getObject();
       // income = ODto.getIncome();
        CashPayments = ODto.getCashPayments();
        CreditPayments = ODto.getCreditPayments();
       // revenue = ODto.getRevenue();
        Credit = ODto.getCreditCardPayments();
        MemberDebitPayments = ODto.getMemberDebitPayments();
        EntertainPayments = ODto.getEntertainPayments();
        //unpaid = ODto.getUnpaid();
        Unpaid = ODto.getTotalServiceCharge();

        TextViewCashPayments.setText(formatter.format(CashPayments));
        TextViewCreditPayments.setText(formatter.format(CreditPayments));
        TextViewMemberDebitPayments.setText(formatter.format(MemberDebitPayments));
        TextViewEntertainPayments.setText(formatter.format(EntertainPayments));
        TextViewCredit.setText(formatter.format(Credit));
        TextViewUnpaid.setText(formatter.format(Unpaid));


    }

}
