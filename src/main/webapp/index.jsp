<!DOCTYPE html>
<html lang="en">
<head>
<title>InnTell</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="css/inntell.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
<script src="http://www.chartjs.org/dist/2.7.2/Chart.bundle.js"></script>
<script src="http://www.chartjs.org/samples/latest/utils.js"></script>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>

<script>
	function getData() {
		document.getElementById("loader1").style.display = "block";
		document.getElementById("loader2").style.display = "block";
		document.getElementById("loader3").style.display = "block";
		document.getElementById("loader4").style.display = "block";

		var e = document.getElementById("inlineFormSelectMonth");
		var month = e.options[e.selectedIndex].value;

		var f = document.getElementById("inlineFormSelectTier");
		var tier = f.options[f.selectedIndex].text;
		$.getJSON("month/".concat(month).concat("/visitors"), function(result){
	        loadPlot1(result);
	    });

		$.getJSON("month/".concat(month).concat("/visitors/nohotel"), function(result){
	        loadPlot2(result);
	    });

		$.getJSON("month/".concat(month).concat("/visitors/purpose-of-visit"), function(result){
	        loadPlot3(result);
	    });

		$.getJSON("month/".concat(month).concat("/visitors/age-group"), function(result){
	        loadPlot4(result);
	    });

		$.getJSON("month/".concat(month).concat("/hotels/tiers"), function(result){
	        loadTable(result);
	    });
		$.getJSON("month/".concat(month).concat("/hotel/").concat(tier).concat("/recommendation"), function(result){
	        loadRecommendations(result);
	    });
	}

	function loadPlot1(json) {
		var labels = [];
		var values = [];

		for (var i = 0; i < json.length; i++) {
			var obj = json[i];
			labels.push(obj['country']);
			values.push(obj['count']);
		}

		var barChartData1 = {
      labels: labels,
      datasets: [{
        label: 'Top Visitors by Nationality',
        backgroundColor: Chart.helpers.color(window.chartColors.red).alpha(0.5).rgbString(),
        borderColor: window.chartColors.red,
        borderWidth: 1,
        data: values
      }]
    };

		var ctx = document.getElementById('canvas1').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: barChartData1,
        options: {
          responsive: true,
          legend: {
            position: 'top',
          },
          title: {
            display: true
          }
        }
      });
		document.getElementById("loader1").style.display = "none";
	}

	function loadPlot2(json) {

		var labels = [];
		var values1 = [];
		var values2 = [];

		for (var i = 0; i < json.length; i++) {
			var obj = json[i];
			labels.push(obj['country']);
			values1.push(obj['total']);
			values2.push(obj['count']);
		}

		var data1 = {
			x : labels,
			y : values1,
			name : 'Total Number of Visitors',
			type : 'bar',
			text : values1,
			textposition : 'auto',
			hoverinfo : labels,
		};

		var data2 = {
			x : labels,
			y : values2,
			name : 'Visitors who did not Book Accomodation',
			type : 'bar',
			text : values2,
			textposition : 'auto',
			hoverinfo : labels
		};

		var dataArray = [];
		dataArray.push(data1);
		dataArray.push(data2);

		var layout = {
			barmode : 'group',
			title : 'No. of Visitors who did not Book Accomodation',
		};
		Plotly.newPlot('myDiv2', dataArray, layout);
		document.getElementById("loader2").style.display = "none";
	}
	function loadPlot3(json) {

		var labels = [];
		var values = [];

		for (var i = 0; i < json.length; i++) {
			var obj = json[i];
			labels.push(obj['category']);
			values.push(obj['count']);
		}

		var data = {
			values : values,
			labels : labels,
			name : 'Total Number of Visitors',
			type : 'pie',
			text : labels,
			textposition : 'auto',
			hoverinfo : labels,
		};

		var dataArray = [];
		dataArray.push(data);

		var layout = {
			title : 'Classification of Visitors by Purpose of their Visit',
		};

		Plotly.newPlot('myDiv3', dataArray, layout);
		document.getElementById("loader3").style.display = "none";
	}
	function loadPlot4(json) {

		var labels = [];
		var values1 = [];
		var values2 = [];
		var values3 = [];
		var values4 = [];

		for (var i = 0; i < json.length; i++) {
			var obj = json[i];
			labels.push(obj['year']);
			values1.push(obj['total1']);
			values2.push(obj['total2']);
			values3.push(obj['total3']);
			values4.push(obj['total4']);
		}

		var data1 = {
			x : labels,
			y : values1,
			name : 'Below 18',
			type : 'scatter',
			text : labels,
			textposition : 'auto',
			hoverinfo : labels,
		};

		var data2 = {
			x : labels,
			y : values2,
			name : 'Between 19 and 35',
			type : 'scatter',
			text : labels,
			textposition : 'auto',
			hoverinfo : labels,
		};

		var data3 = {
			x : labels,
			y : values3,
			name : 'Between 36 and 64',
			type : 'scatter',
			text : labels,
			textposition : 'auto',
			hoverinfo : labels,
		};

		var data4 = {
			x : labels,
			y : values4,
			name : 'Above 64',
			type : 'scatter',
			text : labels,
			textposition : 'auto',
			hoverinfo : labels,
		};

		var dataArray = [];
		dataArray.push(data1);
		dataArray.push(data2);
		dataArray.push(data3);
		dataArray.push(data4);

		var layout = {
			title : 'Classification of Visitors by Age Group',
		};

		Plotly.newPlot('myDiv4', dataArray, layout);
		document.getElementById("loader4").style.display = "none";

	}

	function loadTable(result){
		alert(result);
	}

	function loadRecommendations(result){
		alert(result);
	}

</script>
<style>
    canvas {
      -moz-user-select: none;
      -webkit-user-select: none;
      -ms-user-select: none;
    }
</style>
</head>
<body class="body-font">

	<div class="jumbotron banner">
		<div class="inline">
			<span class="glyphicon glyphicon-home" aria-hidden="true"></span> <span
				class="glyphicon glyphicon-cutlery" aria-hidden="true"></span> <span
				class="glyphicon glyphicon-tree-deciduous" aria-hidden="true"></span>
			<span class="title-font">InnTell</span>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="form-inline row">
				<div class="col-sm-2 form-group">
					<label class="control-label" for="inlineFormSelectMonth">Month&nbsp; &nbsp;</label>
					<select class="form-control selcls"	id="inlineFormSelectMonth">
						<option selected value="1">January</option>
						<option value="2">February</option>
						<option value="3">March</option>
						<option value="4">April</option>
						<option value="5">May</option>
						<option value="6">June</option>
						<option value="7">July</option>
						<option value="8">August</option>
						<option value="9">September</option>
						<option value="10">October</option>
						<option value="11">November</option>
						<option value="12">December</option>
					</select>
				</div>
				<div class="col-sm-2 form-group">
					<label class="control-label" for="inlineFormSelectTier">Tier&nbsp; &nbsp;</label>
					<select class="form-control selcls"	id="inlineFormSelectTier">
       			<option selected value="1">ECONOMY</option>
						<option value="2">MID-TIER</option>
						<option value="3">LUXURY</option>
						<option value="3">UPSCALE</option>
					</select>
				</div>
				<div class="col-sm-8">
					<button type="button" class="btn" style="background-color: #F5D547"
						onclick="getData(this);">Submit</button>
				</div>
			</div>
		</div>
	</div>
	<br />


	<div class="container">
		<div class="row">
			<div class="col-sm-6">  <!--style="background-color: #F4EEA9">-->
				<h3>Top Visitors by Nationality</h3>
				<div class="chart" id="chart1">
					<canvas id="canvas1"></canvas>
					<div id="loader1" class="loader" style="display: none;"></div>
				</div>
			</div>
			<div class="col-sm-6" style="background-color: #F4F482">
				<h3>Top Visitors by Nationality vs Hotel Booking</h3>
				<div class="chart">
					<div class="chart" id="myDiv2">
						<div id="loader2" class="loader" style="display: none;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="background-color: #F4F482">
			<div class="col-sm-6">
				<h3>Purpose of Visit</h3>
				<div class="chart" id="myDiv3">
					<div id="loader3" class="loader" style="display: none;"></div>
				</div>
			</div>
			<div class="col-sm-6" style="background-color: #F4EEA9">
				<h3>Age Group of Visitors</h3>
				<div class="chart" id="myDiv4">
					<div id="loader4" class="loader" style="display: none;"></div>
				</div>
			</div>
		</div>
	</div>


	<div class="container" style="padding-top: 20px;">
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Tier</th>
					<th scope="col">Average Occupancy Rate</th>
					<th scope="col">Average Room Rate</th>
					<th scope="col">Average Revenue per Available Room</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
				</tr>
				<tr>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
				</tr>
				<tr>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
				</tr>
				<tr>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
					<td>Blah</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
