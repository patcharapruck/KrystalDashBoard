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

    TextView TextViewUnpaid,TextViewDrink,TextViewMember,TextViewService,TextViewProduct,TextViewFoot;

    Double CashPayments,CreditPayments,MemberDebitPayments
            ,EntertainPayments,Credit, Unpaid,Drink
            ,Member,Service,Product;


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
        TextViewFoot = (TextView) rootView.findViewById(R.id.textview_foot);
        setTextViewIncome();
    }

    private void setTextViewIncome() {
        formatter = new DecimalFormat("#,###,##0.00");

        ODto = DashBoradManager.getInstance().getDto().getObject();

        CashPayments = ODto.getCashPayments();
        CreditPayments = ODto.getCreditPayments();
        Credit = ODto.getCreditCardPayments();
        MemberDebitPayments = ODto.getMemberDebitPayments();
        EntertainPayments = ODto.getEntertainPayments();

        Unpaid = ODto.getTotalServiceCharge();

        Drink = ODto.getSerivceDrinkCharge();
        Member = ODto.getMemberCharge();
        Service = ODto.getServiceCharge();
        Product = ODto.getProductPrice();


        TextViewCashPayments.setText(formatter.format(CashPayments));
        TextViewCreditPayments.setText(formatter.format(CreditPayments));
        TextViewMemberDebitPayments.setText(formatter.format(MemberDebitPayments));
        TextViewEntertainPayments.setText(formatter.format(EntertainPayments));
        TextViewCredit.setText(formatter.format(Credit));
        TextViewUnpaid.setText(formatter.format(Unpaid));

        TextViewDrink.setText(formatter.format(Drink));
        TextViewMember.setText(formatter.format(Member));
        TextViewService.setText(formatter.format(Service));
        TextViewProduct.setText(formatter.format(Product));
        TextViewFoot.setText(formatter.format(ODto.getFoodPrice()));

    }

}
