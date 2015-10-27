
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><p class="navbar-text">
					<i class="icon-search"></i> Search
				</p></li>
			<li class="divider-vertical"></li>
			<li class="dropdown"><a
				href="${pageContext.request.contextPath}/searchLocation?zip=92602"
				class="dropdown-toggle" data-toggle="dropdown">Location<b
					class="caret"></b></a>
				<ul class="dropdown-menu pull-right">
					<form class="navbar-form"
						action="${pageContext.request.contextPath}/searchLocation"
						method="get">
						<input type="text" class="span2 pull-right" name="zip"
							value="zip code" onFocus="this.value=''">
						<button type="submit" class="btn pull-right">Search</button>
					</form>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Occasion<b class="caret"></b></a>
				<ul class="dropdown-menu pull-right">
					<c:forEach var="occasion" items="${occasions}">
						<li><a
							href="${pageContext.request.contextPath}/searchOccassions?occasion=${occasion.name}">${occasion.name}</a>
					</c:forEach>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Price<b class="caret"></b></a>
				<ul class="dropdown-menu pull-right">
					<li><a
						href="${pageContext.request.contextPath}/search?min=0&max=50">$0-50</a></li>
					<li><a
						href="${pageContext.request.contextPath}/search?min=50&max=100">$50-100</a></li>
					<li><a
						href="${pageContext.request.contextPath}/search?min=100&max=150">$100-$150</a></li>
					<li><a
						href="${pageContext.request.contextPath}/search?min=150&max=200">$150-200</a></li>
					<li><a
						href="${pageContext.request.contextPath}/searchPriceMin?min=200">$200+</a></li>
					<li><a
						href="${pageContext.request.contextPath}/searchPriceMin?min=0">Everything</a></li>
				</ul></li>
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Review Rating<b class="caret"></b></a>
				<ul class="dropdown-menu pull-right">
					<li><a
						href="${pageContext.request.contextPath}/searchReviews?rank=5"><i
							class="icon-star"></i><i class="icon-star"></i><i
							class="icon-star"></i><i class="icon-star"></i><i
							class="icon-star"></i></a></li>
					<li><a
						href="${pageContext.request.contextPath}/searchReviews?rank=4"><i
							class="icon-star"></i><i class="icon-star"></i><i
							class="icon-star"></i><i class="icon-star"></i></a></li>
					<li><a
						href="${pageContext.request.contextPath}/searchReviews?rank=3"><i
							class="icon-star"></i><i class="icon-star"></i><i
							class="icon-star"></i></a></li>
					<li><a
						href="${pageContext.request.contextPath}/searchReviews?rank=2"><i
							class="icon-star"></i><i class="icon-star"></i></a></li>
					<li><a
						href="${pageContext.request.contextPath}/searchReviews?rank=1"><i
							class="icon-star"></i></a></li>
				</ul></li>
		</ul>
	</div>
</div>