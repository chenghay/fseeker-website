<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="${pageContext.request.contextPath}/">FlowerSeeker</a>

			<!-- the following shows register/login/username profile links depending on who's logged in or not -->
			<ul class="nav pull-right">
				<li><a href="${pageContext.request.contextPath}/cart">[Cart]</a></li>
				<sec:authorize access="hasRole('admin')">
					<li><a href="${pageContext.request.contextPath}/admin/profile">Admin</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('florist')">
					<li><a href="${pageContext.request.contextPath}/florist/profile">[Manage Store]</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('customer')">
					<li><a href="${pageContext.request.contextPath}/user/profile">[<sec:authentication
								property="principal.username" />]
					</a></li>
					<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
				</sec:authorize>

				<sec:authorize access="isAnonymous()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Register<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a
								href="${pageContext.request.contextPath}/florist/register">Florist
									Register</a></li>
							<li><a
								href="${pageContext.request.contextPath}/user/register">Customer
									Register</a></li>
						</ul></li>

					<li><a href="${pageContext.request.contextPath}/login">Login</a></li>

				</sec:authorize>
			</ul>
		</div>
	</div>
</div>