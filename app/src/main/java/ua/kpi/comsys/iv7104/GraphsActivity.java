package ua.kpi.comsys.iv7104;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class GraphsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        RadioButton graphRadio = findViewById(R.id.radio_graph);
        graphRadio.setOnClickListener(radioButtonClickListener);

        RadioButton diagramRadio = findViewById(R.id.radio_diagram);
        diagramRadio.setOnClickListener(radioButtonClickListener);

    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GraphView graph = findViewById(R.id.graph);
            PieChartView diagram = findViewById(R.id.diagram);
            RadioButton rb = (RadioButton) v;
            boolean hasLabels = true;
            boolean hasCenterCircle = true;
            switch (rb.getId()) {
                case R.id.radio_graph:
                    diagram.setVisibility(View.GONE);
                    graph.setVisibility(View.VISIBLE);

                    LineGraphSeries<DataPoint> series = new LineGraphSeries<>(graphSinX());
                    series.setColor(Color.RED);

                    graph.setTitle("y = sin(x)");
                    graph.setTitleColor(Color.BLACK);
                    graph.getGridLabelRenderer().setVerticalLabelsSecondScaleColor(Color.RED);
                    graph.getGridLabelRenderer().setGridColor(Color.BLACK);
                    graph.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);
                    graph.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);
                    graph.getViewport().setMinX(-7);
                    graph.getViewport().setMaxX(7);
                    graph.getViewport().setMinY(-2);
                    graph.getViewport().setMaxY(2);
                    graph.getViewport().setYAxisBoundsManual(true);
                    graph.getViewport().setXAxisBoundsManual(true);
                    graph.addSeries(series);
                    break;
                case R.id.radio_diagram:
                    graph.setVisibility(View.GONE);
                    diagram.setVisibility(View.VISIBLE);

                    PieChartData data;
                    List<SliceValue> values = new ArrayList<>();
                    values.add(new SliceValue(25, Color.YELLOW));
                    values.add(new SliceValue(20, Color.GREEN));
                    values.add(new SliceValue(55, Color.BLUE));

                    data = new PieChartData(values);
                    data.setHasLabels(hasLabels);
                    data.setHasCenterCircle(hasCenterCircle);

                    diagram.setPieChartData(data);
                    break;
                default:
                    break;
            }
        }
    };

    public DataPoint[] graphSinX() {
        double x, y;
        DataPoint[] result = new DataPoint[401];

        x = -2*Math.PI;
        for (int i = 0; i < result.length; i++) {
            y = Math.sin(x);
            result[i] = new DataPoint(x, y);
            x += (Math.PI/100);
        }

        return result;
    }
}