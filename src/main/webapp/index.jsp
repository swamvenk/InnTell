<!DOCTYPE html>
<html lang="en">
<head>
<title>InnTell</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="css/inntell.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<script
	src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
	<script src="http://www.chartjs.org/dist/2.7.2/Chart.bundle.js"></script>
	<script src="http://www.chartjs.org/samples/latest/utils.js"></script>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>

<script>

	function loadDefaultData(){
			var month = new Date().getMonth();
			var e = document.getElementById("inlineFormSelectMonth");
			e.value = month + 1;
			getData();
	}

	function getData() {

		var can1 = document.getElementById('canvas1');
		var can2 = document.getElementById('canvas2');
		var can3 = document.getElementById('canvas3');
		var can4 = document.getElementById('canvas4');
		

		if(can1 != null){
			can1.remove();
		}
		if(can2 != null){
			can2.remove();
		}
		if(can3 != null){
			can3.remove();
		}
		if(can4 != null){
			can4.remove();
		}
		

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
	}

	function loadPlot1(json) {

		var can1 = document.createElement('canvas');
		can1.id = "canvas1";
		document.getElementById('chart1').appendChild(can1);

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
        label: 'No of visitors',
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

		var can2 = document.createElement('canvas');
		can2.id = "canvas2";
		document.getElementById('chart2').appendChild(can2);

		var labels = [];
		var values1 = [];
		var values2 = [];

		for (var i = 0; i < json.length; i++) {
			var obj = json[i];
			labels.push(obj['country']);
			values1.push(obj['total']);
			values2.push(obj['count']);
		}

		var barChartData2 = {
      labels: labels,
      datasets: [{
        label: 'Total visitors',
        backgroundColor: Chart.helpers.color(window.chartColors.red).alpha(0.5).rgbString(),
        borderColor: window.chartColors.red,
        borderWidth: 1,
        data: values1
      }, {
        label: 'Visitors who did not book hotel',
        backgroundColor: Chart.helpers.color(window.chartColors.blue).alpha(0.5).rgbString(),
        borderColor: window.chartColors.blue,
        borderWidth: 1,
        data: values2
      }]
    };

		var ctx = document.getElementById('canvas2').getContext('2d');
    new Chart(ctx, {
        type: 'bar',
        data: barChartData2,
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

		document.getElementById("loader2").style.display = "none";
	}
	function loadPlot3(json) {

		var can3 = document.createElement('canvas');
		can3.id = "canvas3";
		document.getElementById('chart3').appendChild(can3);

		var labels = [];
		var values = [];

		for (var i = 0; i < json.length; i++) {
			var obj = json[i];
			labels.push(obj['category']);
			values.push(obj['count']);
		}

		var pieChartData = {
      datasets: [{
        label: 'Purpose of Visit',
        backgroundColor: [
            window.chartColors.red,
            window.chartColors.orange,
            window.chartColors.yellow,
            window.chartColors.green,
            window.chartColors.blue,
          ],
        data: values,
      }],
      labels: labels
    };

		var ctx = document.getElementById('canvas3').getContext('2d');
    new Chart(ctx, {
        type: 'pie',
        data: pieChartData,
        options: {
          responsive: true,
          title: {
            display: true
          }
        }
      });

		document.getElementById("loader3").style.display = "none";
	}
	function loadPlot4(json) {

		var can4 = document.createElement('canvas');
		can4.id = "canvas4";
		document.getElementById('chart4').appendChild(can4);

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

		var lineChartData = {
      labels: labels,
      datasets: [{
        label: 'Above 64 years',
        backgroundColor: window.chartColors.red,
        borderColor: window.chartColors.red,
        fill: false,
        data: values4
      	}, {
        label: 'Between 36 and 64',
        backgroundColor: window.chartColors.blue,
        borderColor: window.chartColors.blue,
        fill: false,
        data: values3
        }, {
        label: 'Between 19 and 36',
        backgroundColor: window.chartColors.green,
        borderColor: window.chartColors.green,
        fill: false,
        data: values2
        },{
        label: 'Below 18',
        backgroundColor: window.chartColors.orange,
        borderColor: window.chartColors.orange,
        fill: false,
        data: values1
        }]
    }

		var ctx = document.getElementById('canvas4').getContext('2d');
    new Chart(ctx, {
        type: 'line',
        data: lineChartData,
        options: {
          responsive: true,
          scales: {
            xAxes: [{
              display: true,
            }],
            yAxes: [{
              display: true,
            }]
          }
        }
      });

		document.getElementById("loader4").style.display = "none";
	}

	function loadTable(result){

		  var myTableDiv = document.getElementById("metrics_table");
			if(document.getElementById('metric_results') != null){
				document.getElementById('metric_results').remove();
			}
			var div = document.createElement('div');
			div.id = "metric_results";
      var table = document.createElement('table');
      table.className = "table table-striped";
      var tableBody = document.createElement('tbody');

      table.border = '1';
      table.appendChild(tableBody);

      var headings = ["Tier", "Avarage Occupancy Rate", "Average Room Rate", "Revenue Per Available Room"];

      var keys = ["tier", "aor", "arr", "revpar"];

      var data = result;

      //TABLE COLUMNS
      var tr = document.createElement('TR');
      tableBody.appendChild(tr);
      for (i = 0; i < headings.length; i++) {
          var th = document.createElement('TH');
          th.bgColor = '#FFC145';
          th.width = '75';
          var header = document.createTextNode(headings[i]);
          th.appendChild(header);
          tr.appendChild(th);
      }

      //TABLE ROWS
      for (i = 0; i < data.length; i++) {
          var tr = document.createElement('TR');
          for (j = 0; j < keys.length; j++) {
              var td = document.createElement('TD')
              td.appendChild(document.createTextNode(data[i][keys[j]]));
              tr.appendChild(td)
          }
          tableBody.appendChild(tr);
      }
			div.appendChild(table);
      myTableDiv.appendChild(div);

	}

	function loadRecommendations(){
		var modal = document.getElementById('recco-modal');

		if(modal != null){
			modal.remove();
		}


		$.getJSON("https://api.myjson.com/bins/ijwwf", function(result){
			var list = $("#reco-modal").append('<ul id="recco-modal" style="list-style-type: none;"></ul>').find('ul');
			var value = result['increase'] == true ? "increase" : "derease"
 			list.append('<li>'+ 'According to data we advise you to ' + value + ' the price from ' + result['minimum'] + '% to '  + result['maximum'] +'% for the month of '+ $('#inlineFormSelectMonth').find(":selected").text()+ ' in the ' + $('#inlineFormSelectTier').find(":selected").text() +' tier.</li>');
 			list.append('<li> <i>Recommended cuisine: </i>' + result['foodPreferences'] + '</li>' )
		})
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
<body class="body-font" onload="loadDefaultData()">

	<div class="jumbotron banner">
		<div class="inline">
			<span class="fa fa-home" aria-hidden="true"></span> <span
				class="fa fa-cutlery" aria-hidden="true"></span> <span
				class="fa fa-tree" aria-hidden="true"></span>
			<span class="title-font">InnTell</span>
		</div>
	</div>

	<div class="container">
		<div class="form-row form-inline">
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
				<div class="col-sm-1">
					<button type="button" class="btn" style="background-color: #F5D547"
						onclick="getData(this);">Submit</button>
				</div>
				<div class="col-sm-7">
            <button type="button" class="btn" style="background-color:#F5D547;" data-toggle="modal" 
            data-target="#recoModal" onclick="loadRecommendations(this);">
              Show Recommendation
            </button>
        </div>
		</div>
	</div>
	<br />


	<div class="container">
		<div class="row">
			<div class="col-sm-6 card"> <!--style="background-color: #F4F482"-->
				<div class="card-body">
				<h3 class="card-title">Top Visitors by Nationality</h3>
			</div>
				<div class="chart card-img-bottom" id="chart1">
					<canvas id="canvas1"></canvas>
					<div id="loader1" class="loader" style="display: none;"></div>
				</div>
			</div>
			<div class="col-sm-6 card"> <!--style="background-color: #F4F482;"-->
				<div class="card-body">
				<h3 class="card-title">Top Visitors by Nationality vs Hotel Booking</h3>
			</div>
				<div class="chart card-img-bottom" id="chart2">
						<canvas id="canvas2"></canvas>
						<div id="loader2" class="loader" style="display: none;"></div>
				</div>
			</div>
		</div>
		<div class="row"> <!--style="background-color: #F4F482"-->
			<div class="col-sm-6 card">
				<div class="card-body">
				<h3 class="card-title">Purpose of Visit</h3>
			</div>
				<div class="chart card-img-bottom" id="chart3">
					<canvas id="canvas3"></canvas>
					<div id="loader3" class="loader" style="display: none;"></div>
				</div>
			</div>
			<div class="col-sm-6 card"> <!--style="background-color: #F4EEA9"-->
				<div class="card-body">
				<h3 class="card-title">Age Group of Visitors</h3>
			</div>
				<div class="chart card-img-bottom" id="chart4">
					<canvas id="canvas4"></canvas>
					<div id="loader4" class="loader" style="display: none;"></div>
				</div>
			</div>
		</div>
	</div>
	<br/>

	<div class="container">
		<div class="card">
			<div class="card-body" id="metrics_table">
				<h3 class="card-title">Hotel Statistics by Tier</h3>
			</div>
		</div>
	</div>

	<div class="modal fade" id="recoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-notify modal-warning" role="document">
          <div class="modal-content">
              <div class="modal-header" id = "reco-modal">
                  <p class="heading lead">Hotel Recommendation</p>
              </div>
              <div class="modal-body">
                  <div class="text-center hotel-reco"> </div>
              </div>
          </div>
      </div>
    </div>

</body>
</html>