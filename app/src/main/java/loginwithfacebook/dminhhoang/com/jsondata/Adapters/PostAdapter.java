package loginwithfacebook.dminhhoang.com.jsondata.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import loginwithfacebook.dminhhoang.com.jsondata.Models.Post;
import loginwithfacebook.dminhhoang.com.jsondata.R;

/**
 * Created by dminh on 1/29/2018.
 */

public class PostAdapter extends BaseAdapter {
    List<Post> posts;
    Context mcontext;

    public PostAdapter(List<Post> posts, Context mcontext) {
        this.posts = posts;
        this.mcontext = mcontext;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Context getMcontext() {
        return mcontext;
    }

    @Override
    public int getCount() {
        return posts == null ? 0 : posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInfiater = (LayoutInflater) this.mcontext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = layoutInfiater.inflate(R.layout.post_items, null);
        ViewHolder viewHolder = new ViewHolder(convertView);

        viewHolder.tvWords.setText(posts.get(position).getWords());
        viewHolder.tvParagraph.setText(posts.get(position).getParagraph());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_words)
        TextView tvWords;
        @BindView(R.id.tv_paragraph)
        TextView tvParagraph;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
