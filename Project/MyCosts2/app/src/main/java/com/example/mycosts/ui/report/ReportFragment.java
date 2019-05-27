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

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class ReportFragment extends Fragment {

    PieChartView pieChartView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Отчет");
        View view = inflater.inflate(R.layout.activity_report, container, false);
        pieChartView = view.findViewById(R.id.chart);
        List pieData = new ArrayList<>();
        pieData.add(new SliceValue(15, Color.parseColor("#EE82EE")).setLabel("Здоровье 30% "));
        pieData.add(new SliceValue(25, Color.parseColor("#87CEFA")).setLabel("Супермаркеты 50%"));
        pieData.add(new SliceValue(10, Color.parseColor("#00FF7F")).setLabel("Развлечения 20%"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true).setValueLabelTextSize(14);
        pieChartData.setHasCenterCircle(true).setCenterText1("").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#0097A7"));
        pieChartView.setPieChartData(pieChartData);
        return view;
    }

}
