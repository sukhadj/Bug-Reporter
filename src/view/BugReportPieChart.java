/**
 * Copyright 2015-2017 Knowm Inc. (http://knowm.org) and contributors.
 * Copyright 2011-2015 Xeiam LLC (http://xeiam.com) and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package view;

import java.awt.Color;
import java.util.HashMap;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;

import org.knowm.xchart.demo.charts.ExampleChart;

import dbClient.DBClient;


public class BugReportPieChart implements ExampleChart<PieChart> {

	private int resolved;
	private int working;
	private int pending;
	private int project_id;
	
	public BugReportPieChart(int project_id)
	{
		super();
		this.project_id = project_id;
	}
	
	public BugReportPieChart(){}
	
	public PieChart getChart() {

		this.setValues();
		PieChart chart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();

		Color[] sliceColors = new Color[] { new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255) };
		chart.getStyler().setSeriesColors(sliceColors);

		// Get no. of pending,resolved and working bugs
		chart.addSeries("Pending", this.pending);
		chart.addSeries("Resolved", this.resolved);
		chart.addSeries("Working", this.working);

		return chart;
	}
	
	public void setValues() {
		//Call a function from db and set values
		DBClient client = new DBClient();
		HashMap<String,Integer> bug_status = client.get_bug_status_count(this.project_id);
		
		this.pending = bug_status.get("pending");
		this.resolved = bug_status.get("resolved");
		this.working = bug_status.get("working");
	}
}
