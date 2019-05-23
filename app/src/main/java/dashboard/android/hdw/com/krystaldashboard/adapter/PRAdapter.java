package dashboard.android.hdw.com.krystaldashboard.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dashboard.android.hdw.com.krystaldashboard.R;
import dashboard.android.hdw.com.krystaldashboard.view.PRModelClass;

public class PRAdapter extends RecyclerView.Adapter<PRAdapter.CustomViewPR> {

    private Context context;
    private ArrayList<PRModelClass> items;

    public PRAdapter(Context context, ArrayList<PRModelClass> item){
        this.context = context;
        this.items = item;
    }

    @NonNull
    @Override
    public CustomViewPR onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CustomViewPR(LayoutInflater.from(context).inflate(R.layout.customview_pr, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewPR customViewPr, int i) {
        customViewPr.textviewNamePR.setText(items.get(i).getName());
        customViewPr.textviewNicknamePR.setText(items.get(i).getNickName());
        customViewPr.textviewIdPR.setText(items.get(i).getEmployeeCode());
        customViewPr.textViewRundrink.setText(items.get(i).getRunDrink());
        customViewPr.textviewPosition.setText(items.get(i).getPosiTion());
        customViewPr.textviewOnfloor.setText(items.get(i).getOnFloor());
        customViewPr.textviewStart.setText(items.get(i).getStart().toString());
        customViewPr.textviewAll.setText(items.get(i).getAll().toString());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class CustomViewPR extends RecyclerView.ViewHolder {

        TextView textviewNamePR,textviewNicknamePR, textviewIdPR
                ,textviewPosition,textviewOnfloor,textviewStart,textviewAll,textViewRundrink;

        public CustomViewPR(@NonNull View itemView) {
            super(itemView);

            textviewNamePR = (TextView) itemView.findViewById(R.id.textview_name_pr);
            textviewNicknamePR = (TextView) itemView.findViewById(R.id.textview_nickname_pr);
            textviewIdPR = (TextView) itemView.findViewById(R.id.textview_id_pr);
            textViewRundrink = (TextView) itemView.findViewById(R.id.text_rundrink);
            textviewPosition = (TextView) itemView.findViewById(R.id.textview_position_pr);
            textviewStart = (TextView) itemView.findViewById(R.id.textview_start_pr);
            textviewOnfloor = (TextView) itemView.findViewById(R.id.textview_onfloor_pr);
            textviewAll = (TextView) itemView.findViewById(R.id.textview_all_pr);
        }
    }
}

