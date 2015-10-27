<%@ attribute name="product" required="true" type="com.flowerseeker.domain.Product" %>
<%@ attribute name="clazz" required="true" %>
<img src="${pageContext.request.contextPath}/static/uploaded/product/${product.id}/${product.image}" class="${clazz}" />