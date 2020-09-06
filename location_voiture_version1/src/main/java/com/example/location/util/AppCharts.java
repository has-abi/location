package com.example.location.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.location.bean.Reservation;
import com.example.location.service.facade.ReservationService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class AppCharts {
	@Autowired
	private static ReservationService reservationService;
	
	public static BarChart<String, Number> buildBarChart(List<String> voitures) {
		int count;
		//init chartdata
		Map<String, Integer> chartData = new HashMap<String, Integer>();
		//filter data from db and fill the chartdata
		for(int i = 0;i<voitures.size();i++) {
			count = 0;
			String v = voitures.get(i);
			for(int j = 0;j<voitures.size();j++) {
				if(v.equals(voitures.get(j))) count++;
			}
			chartData.put(v, count);
		}
		
		
		// create xAxis
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Voitures");
		// create yAxis
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Réservation");
		// init the chart
		BarChart<String, Number> bar_chart = new BarChart<String, Number>(xAxis, yAxis);
		bar_chart.setTitle("Nombre de réservation par voiture");

		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		// create chart data
		
		//add chartdata to our barchart
		for(Map.Entry<String, Integer> data : chartData.entrySet()) {
			series.getData().add(new XYChart.Data<>(data.getKey(),data.getValue()));
		}

		// add chart data to the bar chart
		bar_chart.getData().add(series);
		bar_chart.getStylesheets().add("/styles/chart.css");
		

		bar_chart.setPrefSize(800,500);
		bar_chart.setMinSize(800, 500);
		bar_chart.setMaxSize(800, 500);
		return bar_chart;
	}

	public static PieChart buildPieChart(List<String> marques) {
		int count;
		//init chartdata
		Map<String, Integer> chartData = new HashMap<String, Integer>();
		//filter data from db and fill the chartdata
		for(int i = 0;i<marques.size();i++) {
			count = 0;
			String v = marques.get(i);
			for(int j = 0;j<marques.size();j++) {
				if(v.equals(marques.get(j))) count++;
			}
			chartData.put(v, count);
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for(Map.Entry<String, Integer> data : chartData.entrySet()) {
			pieChartData.add(new PieChart.Data(data.getKey(), data.getValue()));
		}

		// Creating a Pie chart
		PieChart pieChart = new PieChart(pieChartData);

		// Setting the title of the Pie chart
		pieChart.setTitle("Réservations suivant les marques");

		// setting the direction to arrange the data
		pieChart.setClockwise(true);

		// Setting the length of the label line
		pieChart.setLabelLineLength(50);

		// Setting the labels of the pie chart visible
		pieChart.setLabelsVisible(true);

		pieChart.setPrefSize(800,500);
		pieChart.setMinSize(800, 500);
		pieChart.setMaxSize(800, 500);
		// Setting the start angle of the pie chart
		pieChart.setStartAngle(180);
		return pieChart;
	}
	
	public static int count(Date d,int index) {
	List<Reservation> res =	reservationService.findAllByAnneeAndMois(Integer.toString(DateUtil.getCurrentYear()), Integer.toString(index));
	int count = 0;
	for(int i = 0;i<res.size();i++) {
		if(DateUtil.getMonthFromDate(res.get(i).getDateReserv()) == DateUtil.getMonthFromDate(d) && DateUtil.getDayFromDate(res.get(i).getDateReserv()) == DateUtil.getDayFromDate(d)) {
			count++;
		}
	}
		return count;
	}

	public static AreaChart<String, Number> buildAreaChart() {
		CategoryAxis xAxis = new CategoryAxis();
		Map<String, Map<Integer, Integer>> areaData = new HashMap<String, Map<Integer,Integer>>();
		final Map<Integer,String> months = getMonths();
		for(int i = 1 ;i<=DateUtil.getCurrentMonth()+1;i++) {
			areaData.put(months.get(i), null);
			System.out.println(Integer.toString(DateUtil.getCurrentYear()));
			
			List<Reservation> res =	reservationService.findAllByAnneeAndMois(Integer.toString(DateUtil.getCurrentYear()), Integer.toString(i));
			System.out.println(res);
			if(res!=null) {
				for(int j = 0;j<res.size();j++) {
					areaData.get(getMonths().get(i)).put(DateUtil.getDayFromDate(res.get(j).getDateReserv()), count(res.get(j).getDateReserv(), i));
				}
			}
		}
	
		
		// defining the y Axis
		NumberAxis yAxis = new NumberAxis(0, 15, 2.5);
		yAxis.setLabel("Jours");

		// Creating the Area chart
		AreaChart<String, Number> areaChart = new AreaChart<String, Number>(xAxis, yAxis);
		areaChart.setTitle("Réservation par mois");

		// Prepare XYChart.Series objects by setting data
		
		for(Map.Entry<String, Map<Integer,Integer>> en:areaData.entrySet()) {
			XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
			series.setName(en.getKey());
			for(Map.Entry<Integer, Integer> values:en.getValue().entrySet()) {
				series.getData().add(new XYChart.Data<String, Number>(Integer.toString(values.getKey()),values.getValue()));
			}
			areaChart.getData().add(series);
		}

		return areaChart;
	}

	public static LineChart<Number, Number> buildLineChart() {
		NumberAxis xAxis = new NumberAxis(1960, 2020, 10);
		xAxis.setLabel("Years");

		// Defining the y axis
		NumberAxis yAxis = new NumberAxis(0, 350, 50);
		yAxis.setLabel("No.of schools");

		// Creating the line chart
		LineChart<Number, Number> linechart = new LineChart<Number, Number>(xAxis, yAxis);

		// Prepare XYChart.Series objects by setting data
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.setName("No of schools in an year");

		series.getData().add(new XYChart.Data<Number, Number>(1970, 15));
		series.getData().add(new XYChart.Data<Number, Number>(1980, 30));
		series.getData().add(new XYChart.Data<Number, Number>(1990, 60));
		series.getData().add(new XYChart.Data<Number, Number>(2000, 120));
		series.getData().add(new XYChart.Data<Number, Number>(2013, 240));
		series.getData().add(new XYChart.Data<Number, Number>(2014, 300));

		// Setting the data to Line chart
		linechart.getData().add(series);
		linechart.maxWidth(300);
		linechart.maxHeight(200);
		linechart.getStylesheets().add("/styles/chart.css");
		return linechart;
	}
	
	
	public static Map<Integer,String> getMonths(){
		Map<Integer,String> months = new HashMap<Integer, String>();
		months.put(1, "Janvier");
		months.put(2,"Février");
		months.put(3,"Mars");
		months.put(4,"Avril");
		months.put(5,"Mai");
		months.put(6,"Juin");
		months.put(7,"Juillet");
		months.put(8,"Août");
		months.put(9,"Septembre");
		months.put(10,"Octobre");
		months.put(11,"Novembre");
		months.put(12,"Décembre");
		return months;
	}
}
