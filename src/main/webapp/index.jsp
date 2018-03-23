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

<script>
	function getData() {
		document.getElementById("loader1").style.display = "block";
		document.getElementById("loader2").style.display = "block";
		document.getElementById("loader3").style.display = "block";
		document.getElementById("loader4").style.display = "block";
	}
</script>
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
					<label class="control-label" for="inlineFormSelectMonth">Month &nbsp; &nbsp; &nbsp;</label>
					<select	class="form-control selcls" id="inlineFormSelectMonth">
						<option selected>Month</option>
						<option value="1">Jan</option>
						<option value="2">Feb</option>
						<option value="3">March</option>
						<option value="1">April</option>
						<option value="2">May</option>
						<option value="3">June</option>
						<option value="1">July</option>
						<option value="2">Aug</option>
						<option value="3">Sep</option>
						<option value="1">Oct</option>
						<option value="2">Nov</option>
						<option value="3">Dec</option>
					</select>
				</div>
				<div class="col-sm-2 form-group">
				<label class="control-label" for="inlineFormSelectTier">Tier &nbsp; &nbsp; &nbsp;</label>
				<select	class="form-control selcls" id="inlineFormSelectTier">
					<option selected>Tier </option>
					<option value="1">Tier One</option>
					<option value="2">Tier Two</option>
					<option value="3">Tier Three</option>
				</select>
			</div>
			<div class="col-sm-8">
				<button type="button" class="btn" style="background-color: #F5D547"
					onclick="getData(this);">Submit</button>
			</div>
		</div>
		</div>
	</div><br/>


	<div class="container">
		<div class="row">
			<div class="col-sm-6" style="background-color: #F4EEA9">
				<h3>Top Visitors by Nationality</h3>
				<div class="chart">
					<div id="loader1" class="loader" style="display: none;"></div>
				</div>
			</div>
			<div class="col-sm-6" style="background-color: #F4F482">
				<h3>Top Visitors by Nationality vs Hotel Booking</h3>
				<div class="chart">
					<div class="chart">
						<div id="loader2" class="loader" style="display: none;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row" style="background-color: #F4F482">
			<div class="col-sm-6">
				<h3>Purpose of Visit</h3>
				<div class="chart">
					<div id="loader3" class="loader" style="display: none;"></div>
				</div>
			</div>
			<div class="col-sm-6" style="background-color: #F4EEA9">
				<h3>Age Group of Visitors</h3>
				<div class="chart">
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
					<th scope="col">Heading 1</th>
					<th scope="col">Heading 2</th>
					<th scope="col">Heading 3</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>Blah</td>
					<td>Blah</td>
					<td>@Blah</td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Blah</td>
					<td>Blah</td>
					<td>@Blah</td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Blah</td>
					<td>Blah</td>
					<td>@Blah</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
