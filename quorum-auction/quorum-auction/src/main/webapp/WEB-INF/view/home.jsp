<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Auction Home</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css">
	<style type="text/css">
		.grid.container {
		  margin-top: 5em;
		}
		.output {
			margin-top: 7em;
		}
	</style>
</head>
<body>
		<h1 class="ui center aligned icon header">
		  <i class="circular users icon"></i>
		  Auction
		</h1>
		<div class="ui middle aligned center aligned grid container">
			<div class="ui link cards">
			  <div class="card">
			    <div class="image">
			      <img src="https://images.unsplash.com/photo-1433444306168-f18e2a8dde77?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80">
			    </div>
			    <div class="content">
			      <div class="header">Antique Watch and Typewriter</div>
			      <div class="meta">
			        <a>Open for Auction with price ♦20</a>
			      </div>
			      <div class="description">
			        This antique watch and typewriter belong to 18th King of Uganda.
			      </div>
			    </div>
			    <div class="extra content">
			      <div class="right floated">
			        	<form action="${pageContext.request.contextPath}/placeBid" method="POST">
			        		<div class="ui action input">
							  <input type="text" name="biddingPrice in ether" placeholder="your bid">
							  <select class="ui compact selection dropdown" name="address">
							    <option value="0x0fbdc686b912d7722dc86510934589e0aaf3b55a">Node B</option>
							    <option value="0x9186eb3d20cbd1f5f992a950d808c4495153abd5">Node C</option>
							  </select>
							  <button class="ui button">Bid</button>
							</div>
				        </form>
			      </div>
			      <div class="floated">
			      	<a class="ui red button" href="${pageContext.request.contextPath}/endBid">End Auction</a>
			      </div>
			    </div>
			  </div>
			  <div class="card">
				<div class="ui action input output">
				  <input type="text" value="${winner}">
				  <button class="ui button">Winner</button>
				</div>
				<div class="ui action input output">
				  <input type="text" value="${highestBid} in Ether">
				  <button class="ui button">Highest Bid</button>
				</div>
			 </div>
			</div>
		</div>
</body>
</html>