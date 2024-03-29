package retrofit2practice.com.retrofit2practice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


  private List<RetroPhoto> dataList;
  private Context context;

  public CustomAdapter(Context context, List<RetroPhoto> dataList){
      this.context = context;
      this.dataList=dataList;
  }

    class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        TextView txtTitle;
        private ImageView convertImage;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

            txtTitle = mView.findViewById(R.id.title);
            convertImage= mView.findViewById(R.id.covertImage);
        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
    customViewHolder.txtTitle.setText(dataList.get(i).getTitle());

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(i).getThumbnailUrl())
          .placeholder(R.drawable.ic_launcher_background)
          .error(R.drawable.ic_launcher_background)
          .into(customViewHolder.convertImage);
  }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}
