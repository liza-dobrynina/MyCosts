package com.example.mycosts.ui.report;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mycosts.R;
import com.example.mycosts.api.model.Category;
import com.google.common.collect.Comparators;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class ReportFragment extends Fragment implements ReportContract {

    private static final ImmutableList<Integer> colors = ImmutableList.of(
            Color.parseColor("#EE82EE"),
            Color.parseColor("#87CEFA"),
            Color.parseColor("#00FF7F"),
            Color.parseColor("#3b7080"),
            Color.parseColor("#3a5743"),
            Color.parseColor("#7e4e60"),
            Color.parseColor("#c0f8d1")
    );

    private PieChartView pieChartView;
    private ReportPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Отчет");
        View view = inflater.inflate(R.layout.activity_report, container, false);
        pieChartView = view.findViewById(R.id.chart);
        presenter = new ReportPresenter(this);
        presenter.prepareView();
        return view;
    }

    @Override
    public void setData(List<Category> categories) {
        List<SliceValue> pieData = new ArrayList<>();

        List<Category> sortedByName = new ArrayList<>(categories);

        Collections.sort(sortedByName, new Comparator<Category>() {
            @Override
            public int compare(Category c1, Category c2) {
                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });

        Iterator<Integer> colorsIterator = colors.iterator();
        for (Category category : sortedByName) {
            if (!colorsIterator.hasNext()) {
                break;
            }

            if (category.getCurrentSum() == null || category.getCurrentSum().equals(0)) {
                continue;
            }

            String label =
                    String.format("%s\n%s/%s", category.getName(), category.getCurrentSum(), category.getMaxSum());
            SliceValue sliceValue =
                    new SliceValue(category.getCurrentSum(), colorsIterator.next())
                            .setLabel(label);
            pieData.add(sliceValue);
        }

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData
                .setHasLabels(true)
                .setValueLabelTextSize(13);
        pieChartData
                .setHasCenterCircle(true)
                .setCenterText1("")
                .setCenterText1FontSize(15)
                .setCenterText1Color(Color.parseColor("#0097A7"));

        pieChartView.setPieChartData(pieChartData);
    }
}
