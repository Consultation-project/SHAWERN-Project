package com.example.shawerni;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class home extends Fragment {

    private RecyclerView recyclerView;
    private SectionsAdapter adapter;
    private List<section> secList;
    private TextView title;
    private View view ;


    public home() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     view = inflater.inflate(R.layout.homefrag, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        secList = new ArrayList<>();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        secList.get(position);
                        Intent intent = new Intent(getActivity(), Add_Consultation.class);
                        intent.putExtra(Constance.key.TITLE , secList.get(position).getName());
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }


                }));

        adapter = new SectionsAdapter(getActivity(), secList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        prepareDepartment();

        return view;
    }

    private void prepareDepartment() {
        int[] covers = new int[]{
                R.drawable.legal
        ,R.drawable.health
        ,R.drawable.acadamic
        ,R.drawable.social
        ,R.drawable.comercial
        ,R.drawable.marketing};


        secList.add(new section(covers[0],getString(R.string.Legalconsultation)));
        secList.add(new section(covers[1],getString(R.string.healthconsultation)));
        secList.add(new section(covers[2],getString(R.string.Acadamicconsultation)));
        secList.add(new section(covers[3],getString(R.string.Socialconsultation)));
        secList.add(new section(covers[4],getString(R.string.Economicalconsultation)));
        secList.add(new section(covers[5],getString(R.string.Marketingconsultation)));

        adapter.notifyDataSetChanged();


    }




    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
