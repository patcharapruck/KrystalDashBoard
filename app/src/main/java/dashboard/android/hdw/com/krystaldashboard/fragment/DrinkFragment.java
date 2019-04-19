package dashboard.android.hdw.com.krystaldashboard.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.adapter.ProductListAdapter;
import dashboard.android.hdw.com.krystaldashboard.adapter.TopProductAdapter;
import dashboard.android.hdw.com.krystaldashboard.dto.DashBoardDto;
import dashboard.android.hdw.com.krystaldashboard.dto.ObjectItemDto;
import dashboard.android.hdw.com.krystaldashboard.dto.product.ProductSortDto;
import dashboard.android.hdw.com.krystaldashboard.manager.Contextor;
import dashboard.android.hdw.com.krystaldashboard.manager.http.HttpManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.DashBoradManager;
import dashboard.android.hdw.com.krystaldashboard.manager.singleton.ProductManager;
import dashboard.android.hdw.com.krystaldashboard.util.sharedprefmanager.SharedPrefDateManager;
import dashboard.android.hdw.com.krystaldashboard.view.TopProductModelClass;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrinkFragment extends Fragment {

    TextView TextViewTotal,TextViewEntertain,TextViewPurchase,TextViewWithdraw,TextViewAmount;

    ArrayList<String> nameProduct;
    ArrayList<Long> totalAllProduct;
    ArrayList<Long> entertainProduct;
    ArrayList<Long> purchaseProduct;
    ArrayList<Long> withdrawProduct;
    ArrayList<String> imageProduct;
    ObjectItemDto Odto;
    int size;


    String[] Sortname;
    Long[] Sorttotal;
    Long[] Sortentertain;
    Long[] Sortpurchase;
    Long[] Sortwithdraw;
    String[] Sortimage;

    ArrayList<TopProductModelClass> items;
    TopProductAdapter adapter;
    RecyclerView recyclerView;

    ListView listProduct;
    ProductListAdapter productListAdapter;

    ProductSortDto productSortDto;

    ProgressDialog progress;

    CardView cardView;
    ExpandableRelativeLayout mycontent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_drink,null);
        showProgress();
        reqAPI(SharedPrefDateManager.getInstance(Contextor.getInstance().getmContext()).getreqDate(),rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        TextViewTotal = (TextView) rootView.findViewById(R.id.textview_amount_drinks);
        TextViewAmount = (TextView) rootView.findViewById(R.id.textview_amount_product);
        TextViewEntertain = (TextView) rootView.findViewById(R.id.textview_entertain_amount_product);
        TextViewPurchase = (TextView) rootView.findViewById(R.id.textview_purchase_amount_product);
        TextViewWithdraw = (TextView) rootView.findViewById(R.id.textview_buy_amount_product);


        Odto = DashBoradManager.getInstance().getDto().getObject();
        size = Odto.getSummaryUseProductList().size();


        setViewDrink();
        setTextView(rootView);
        setRecyclerView(rootView);
        setListProduct(rootView);

    }

    private void setTextView(View rootView) {

        Long sumtotal = 0L, sumentertain = 0L, sumpurchase = 0L, sumwithdraw = 0L;
        for (int i = 0; i < size; i++) {
            sumtotal = sumtotal + totalAllProduct.get(i);
            sumentertain = sumentertain + entertainProduct.get(i);
            sumpurchase = sumpurchase + purchaseProduct.get(i);
            sumwithdraw = sumwithdraw + withdrawProduct.get(i);
        }

        TextViewTotal.setText(sumtotal.toString());
        TextViewEntertain.setText(sumentertain.toString());
        TextViewWithdraw.setText(sumwithdraw.toString());
        TextViewPurchase.setText(sumpurchase.toString());
        TextViewAmount.setText(String.valueOf(size));

    }

    private void setListProduct(View rootView) {
        listProduct  = (ListView) rootView.findViewById(R.id.list_item_product);
        productListAdapter = new ProductListAdapter();
        listProduct.setAdapter(productListAdapter);
    }

    private void setRecyclerView(View rootView) {
        items = new ArrayList<>();
        adapter = new TopProductAdapter(getContext(), items);

        recyclerView = rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 5; i++) {
            items.add(new TopProductModelClass(Sortimage[i],Sortname[i],Sorttotal[i].toString()));
            adapter.notifyDataSetChanged();
        }

// Expandable card
//        cardView = (CardView) rootView.findViewById(R.id.carddrink);
//        mycontent = (ExpandableRelativeLayout)rootView.findViewById(R.id.mycontent);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mycontent.toggle();
//            }
//        });

    }

    private Long getWithdraw(int i) {
        Long withdraw;
        try {
            withdraw = DashBoradManager.getInstance().getDto().getObject().getSummaryUseProductList().get(i).getWithdrawUse();
        } catch (NullPointerException e) {
            return 0L;
        }
        return withdraw;
    }

    private Long getPurchase(int i) {
        Long purchase;
        try {
            purchase = DashBoradManager.getInstance().getDto().getObject().getSummaryUseProductList().get(i).getPurchaseAmount();
        } catch (NullPointerException e) {
            return 0L;
        }
        return purchase;
    }

    private Long getEntertain(int i) {
        Long enter;
        try {
            enter = DashBoradManager.getInstance().getDto().getObject().getSummaryUseProductList().get(i).getEntertainAmount();
        } catch (NullPointerException e) {
            return 0L;
        }
        return enter;
    }

    private Long gettotalProduct(int i) {
        Long total;
        try {
            total = DashBoradManager.getInstance().getDto().getObject().getSummaryUseProductList().get(i).getTotalAll();
        } catch (NullPointerException e) {
            return 0L;
        }
        return total;
    }

    private String getnameProduct(int i) {
        String name;
        try {
            name = DashBoradManager.getInstance().getDto().getObject().getSummaryUseProductList().get(i).getProduct().getProductNameEn();
        } catch (NullPointerException e) {
            return "";
        }
        return name;
    }

    private String getimageProduct(int i) {
        String image;
        try {
            image = DashBoradManager.getInstance().getDto().getObject().getSummaryUseProductList().get(i).getProduct().getImage();
        } catch (NullPointerException e) {
            return "";
        }
        return image;
    }

    private void setViewDrink() {

        nameProduct = new ArrayList<>(size);
        totalAllProduct = new ArrayList<>(size);
        entertainProduct = new ArrayList<>(size);
        purchaseProduct = new ArrayList<>(size);
        withdrawProduct = new ArrayList<>(size);
        imageProduct = new ArrayList<>(size);


        Sortname = new String[size];
        Sorttotal = new Long[size];
        Sortentertain = new Long[size];
        Sortpurchase = new Long[size];
        Sortwithdraw = new Long[size];
        Sortimage = new String[size];


        for (int i = 0; i < size; i++) {
            String name = getnameProduct(i);
            Long total = gettotalProduct(i);
            Long entertain = getEntertain(i);
            Long purchase = getPurchase(i);
            Long withdraw = getWithdraw(i);
            String image = getimageProduct(i);

            nameProduct.add(name);
            totalAllProduct.add(total);
            entertainProduct.add(entertain);
            purchaseProduct.add(purchase);
            withdrawProduct.add(withdraw);
            imageProduct.add(image);
        }

        Sortname = nameProduct.toArray(new String[0]);
        Sorttotal = totalAllProduct.toArray(new Long[0]);
        Sortentertain = entertainProduct.toArray(new Long[0]);
        Sortpurchase = purchaseProduct.toArray(new Long[0]);
        Sortwithdraw = withdrawProduct.toArray(new Long[0]);
        Sortimage = imageProduct.toArray(new String[0]);

        Long tt,ent,pur,with;
        String np,img;
        for(int i=0;i<size;i++){
            for (int j=0;j<size-i-1;j++){
                if(Sorttotal[j+1]>Sorttotal[j]){
                    tt = Sorttotal[j];
                    np = Sortname[j];
                    ent = Sortentertain[j];
                    pur = Sortpurchase[j];
                    with = Sortwithdraw[j];
                    img = Sortimage[j];

                    Sorttotal[j] = Sorttotal[j+1];
                    Sortname[j] = Sortname[j+1];
                    Sortentertain[j] = Sortentertain[j+1];
                    Sortpurchase[j] = Sortpurchase[j+1];
                    Sortwithdraw[j] = Sortwithdraw[j+1];
                    Sortimage[j] = Sortimage[j+1];

                    Sorttotal[j+1] = tt;
                    Sortname[j+1] = np;
                    Sortentertain[j+1] = ent;
                    Sortpurchase[j+1] = pur;
                    Sortwithdraw[j+1] = with;
                    Sortimage[j+1] = img;
                }
            }
        }


        System.out.print(Sortimage.length);

        productSortDto = new ProductSortDto();
        productSortDto.setNameProductSort(Sortname);
        productSortDto.setTotalAllProductSort(Sorttotal);
        productSortDto.setEntertainProductSort(Sortentertain);
        productSortDto.setPurchaseProductSort(Sortpurchase);
        productSortDto.setWithdrawProductSort(Sortwithdraw);
        productSortDto.setImageProductSort(Sortimage);

        ProductManager.getInstance().setProductSortDto(productSortDto);


    }

    public void reqAPI(String date, final View rootView) {
        final Context mcontext = Contextor.getInstance().getmContext();
        String nn = "{\"property\":[],\"criteria\":{\"sql-obj-command\":\"( tb_sales_shift.open_date >= '"+date+" 00:00:00' AND tb_sales_shift.open_date <= '"+date+" 23:59:59')\",\"summary-date\":\"*\"},\"orderBy\":{\"InvoiceDocument-id\":\"desc\"},\"pagination\":{}}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),nn);
        Call<DashBoardDto> call = HttpManager.getInstance().getService().loadAPI(requestBody);
        call.enqueue(new Callback<DashBoardDto>() {
            @Override
            public void onResponse(Call<DashBoardDto> call, Response<DashBoardDto> response) {
                String aa = String.valueOf(response.raw().code());
                if(response.isSuccessful()){
                    DashBoardDto dao = response.body();
                    DashBoradManager.getInstance().setDto(dao);
                    progress.dismiss();
                    initInstances(rootView);

                }
                else {
                    progress.dismiss();
                    Toast.makeText(mcontext,"เกิดข้อผิดพลาด",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<DashBoardDto> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(mcontext,"ไม่สามารถเชื่อมต่อกับข้อมูลได้",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showProgress() {
        progress = new ProgressDialog(getContext());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }
}
