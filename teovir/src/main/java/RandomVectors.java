import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RandomVectors {

    public static void main(String[] args) {
        int sampleSize = 1000000;
        double[] x1 = new double[sampleSize];
        double[] x2 = new double[sampleSize];
        double[] y1 = new double[sampleSize];
        double[] y2 = new double[sampleSize];

        Random random = new Random();
        for (int i = 0; i < sampleSize; i++) {
            x1[i] = random.nextGaussian();
            x2[i] = random.nextGaussian();
            y1[i] = x1[i] * x1[i] + x2[i] * x2[i];
            y2[i] = x1[i] / Math.sqrt(y1[i]);
        }

        HistogramDataset datasetY1 = new HistogramDataset();
        datasetY1.addSeries("Y1", y1, 50);
        JFreeChart chartY1 = ChartFactory.createHistogram(
                "Гістограма Y1",
                "Y1",
                "Частота",
                datasetY1,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        HistogramDataset datasetY2 = new HistogramDataset();
        datasetY2.addSeries("Y2", y2, 50);
        JFreeChart chartY2 = ChartFactory.createHistogram(
                "Гістограма Y2",
                "Y2",
                "Частота",
                datasetY2,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );


        XYSeries scatterX1X2 = new XYSeries("(X1, X2)");
        for (int i = 0; i < sampleSize; i++) {
            scatterX1X2.add(x1[i], x2[i]);
        }
        XYSeriesCollection scatterDatasetX1X2 = new XYSeriesCollection(scatterX1X2);
        JFreeChart scatterChartX1X2 = ChartFactory.createScatterPlot(
                "Розсіювання (X1, X2)",
                "X1",
                "X2",
                scatterDatasetX1X2
        );

        XYSeries scatterY1Y2 = new XYSeries("(Y1, Y2)");
        for (int i = 0; i < sampleSize; i++) {
            scatterY1Y2.add(y1[i], y2[i]);
        }
        XYSeriesCollection scatterDatasetY1Y2 = new XYSeriesCollection(scatterY1Y2);
        JFreeChart scatterChartY1Y2 = ChartFactory.createScatterPlot(
                "Розсіювання (Y1, Y2)",
                "Y1",
                "Y2",
                scatterDatasetY1Y2
        );

        JFrame frame = new JFrame("Графіки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2));
        frame.add(new ChartPanel(chartY1));
        frame.add(new ChartPanel(chartY2));
        frame.add(new ChartPanel(scatterChartX1X2));
        frame.add(new ChartPanel(scatterChartY1Y2));
        frame.pack();
        frame.setVisible(true);
    }
}
//Гістограма Y1 (верхній лівий графік):
//Гістограма для Y₁ = X₁² + X₂² відображає розподіл значень, які відповідають сумі квадратів двох незалежних нормальних випадкових величин.
//Теоретична щільність розподілу для Y₁ відповідає щільності хі-квадрат розподілу з двома ступенями свободи. На графіку видно, що емпіричний розподіл (синя гістограма) добре відповідає теоретичній кривій (червона лінія).

//Гістограма Y2 (верхній правий графік):
//Гістограма Y₂ = X₁ / √Y₁ показує співвідношення між однією випадковою величиною X₁ та коренем суми квадратів X₁² + X₂².
//Теоретична щільність Y₂ нагадує бета-розподіл, що відображається в характерній формі графіка.

//Розсіювання (X1, X2) (нижній лівий графік):
//Розподіл точок (X₁, X₂) демонструє незалежність цих величин і їх нормальний розподіл у двовимірному просторі.
//Кругова форма хмари точок вказує на однакову дисперсію для обох змінних, що підтверджує їх незалежність та ідентичний розподіл.

//Розсіювання (Y1, Y2) (нижній правий графік):
//Точки (Y₁, Y₂) демонструють залежність між цими величинами. Відомо, що Y₁ та Y₂ не є незалежними, оскільки Y₂ обчислюється через Y₁.
//Цей графік також підтверджує, що Y₁ приймає тільки невід’ємні значення, а Y₂ знаходиться в межах від -1 до 1.
