package loginwithfacebook.dminhhoang.com.jsondata.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import loginwithfacebook.dminhhoang.com.jsondata.Models.Accounts;
import loginwithfacebook.dminhhoang.com.jsondata.Models.Post;
import loginwithfacebook.dminhhoang.com.jsondata.R;

/**
 * Created by dminh on 1/29/2018.
 */

public class UserAdapter extends BaseAdapter {

    List<Accounts> accounts;
    Context mcontext;
    PostAdapter adapter;
    List<Post> posts;

    public UserAdapter(List<Accounts> accounts, Context mcontext) {
        this.accounts = accounts;
        this.mcontext = mcontext;
    }

    public void setAccounts(List<Accounts> accounts) {
        this.accounts = accounts;
    }

    public Context getMcontext() {
        return mcontext;
    }

    @Override
    public int getCount() {
        return accounts == null ? 0 : accounts.size();
    }

    @Override
    public Object getItem(int position) {
        return accounts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInfiater = (LayoutInflater) this.mcontext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInfiater.inflate(R.layout.list_view_user, null);
        ViewHolder viewHolder = new ViewHolder(convertView);
        viewHolder.tvEmail.setText("Email: " + accounts.get(position).getEmail());
        viewHolder.tvName.setText("Name: " + accounts.get(position).getName());
        viewHolder.tvUsername.setText("Username: " + accounts.get(position).getUsername());
        Picasso.with(mcontext).load(accounts.get(position).getAvatar()).into(viewHolder.imAvata);
        viewHolder.tvCity.setText("City: " + accounts.get(position).getAddress().getCity());
        viewHolder.tvLat.setText("Lat: " + accounts.get(position).getAddress().getGeo().getLat());
        viewHolder.tvLng.setText("Lng: " + accounts.get(position).getAddress().getGeo().getLng());
        viewHolder.tvCountry.setText("Country :" + accounts.get(position).getAddress().getCountry());

        posts = accounts.get(position).getPosts();

        adapter = new PostAdapter(posts, this.mcontext);
        viewHolder.listItemPost.setAdapter(adapter);

        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.im_avata)
        ImageView imAvata;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_email)
        TextView tvEmail;
        @BindView(R.id.tv_country)
        TextView tvCountry;
        @BindView(R.id.tv_city)
        TextView tvCity;
        @BindView(R.id.tv_lat)
        TextView tvLat;
        @BindView(R.id.tv_lng)
        TextView tvLng;
        @BindView(R.id.tv_zipcode)
        TextView tvZipcode;
        @BindView(R.id.tv_streetA)
        TextView tvStreetA;
        @BindView(R.id.list_item_post)
        ListView listItemPost;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
