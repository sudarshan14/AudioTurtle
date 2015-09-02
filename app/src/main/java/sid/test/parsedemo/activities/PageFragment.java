package sid.test.parsedemo.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import sid.test.parsedemo.R;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    ListView headings;
    SimpleAdapter adapter;
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list_items,
                container, false);



        return rootView;
    }
}
//public class ListItem extends Fragment{
//
//    ListView headings;
//    SimpleAdapter adapter;
//    private static final String ARG_POSITION = "position";
//    NetworkInfo ni;
//    private int position;
//
//
//    public static ListItem newInstance(int position) {
//        ListItem f = new ListItem(position);
//        Bundle b = new Bundle();
//        b.putInt(ARG_POSITION, position);
//        f.setArguments(b);
//        return f;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ConnectivityManager cm = (ConnectivityManager) getActivity()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        ni = cm.getActiveNetworkInfo();
//        position = getArguments().getInt(ARG_POSITION);
//
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View rootView = inflater.inflate(R.layout.activity_list_items,
//                container, false);
//
//        headings = (ListView) rootView.findViewById(R.id.listIntroduction);
//
//        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
//
//
//        for (int i = 0; i < 10; i++) {
//            HashMap<String, String> hm = new HashMap<String, String>();
//            hm.put("listItems", "item" + i);
//            data.add(hm);
//        }
//
//        String from[] = {"listItems"};
//        int to[] = {R.id.textView1};
//
//        adapter = new SimpleAdapter(getActivity(), data, R.layout.activity_row,
//                from, to);
//
//        headings.setAdapter(adapter);
//
//        return rootView;
//    }

//}