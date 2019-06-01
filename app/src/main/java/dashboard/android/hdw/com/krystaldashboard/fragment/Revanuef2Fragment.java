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


    TextView TextViewUnpaid,TextViewDrink,TextViewMember,TextViewService,TextViewProduct,TextViewFoot;

    Double Unpaid,Drink,Member,Service,Product,Foot;


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

        try {
            ODto = DashBoradManager.getInstance().getDto().getObject();
        }catch (Exception e){
            ODto = null;
        }

        try {
            Unpaid = ODto.getUnpaid();
        }catch (Exception e){
            Unpaid = 0.00;
        }

        try {
            Drink = ODto.getSerivceDrinkCharge();
        }catch (Exception e){
            Drink = 0.00;
        }

        try {
            Member = ODto.getMemberCharge();
        }catch (Exception e){
            Member = 0.00;
        }

        try {
            Service = ODto.getServiceCharge();
        }catch (Exception e){
            Service = 0.00;
        }

        try {
            Product = ODto.getProductPrice();
        }catch (Exception e){
            Product = 0.00;
        }

        try {
            Foot = ODto.getFoodPrice();
        }catch (Exception e){
            Foot = 0.00;
        }

        TextViewUnpaid.setText(formatter.format(Unpaid));
        TextViewDrink.setText(formatter.format(Drink));
        TextViewMember.setText(formatter.format(Member));
        TextViewService.setText(formatter.format(Service));
        TextViewProduct.setText(formatter.format(Product));
        TextViewFoot.setText(formatter.format(Foot));

    }

}
