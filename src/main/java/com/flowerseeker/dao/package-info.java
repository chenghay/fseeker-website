/**
 * <p>This package contains dao like interfaces. Using Spring Data JPA all of these extends JpaRepository, 
 * which automatically gives CRUD methods for dealing with the parameterized entity classes. </p>
 * <p>To create custom queries simply declare new methods in the interfaces that follow the conventions in
 * the spring jpa documentation, or create methods annotated with "Query" and give the query in JPQL, or native
 * SQL with the native flag set to "true". See 
 * <a href="http://static.springsource.org/spring-data/data-jpa/docs/1.1.0.RELEASE/reference/html/#jpa.query-methods">here</a></p>
 * <p>Spring will auto provide the implementation at run time, we can also implement custom things if we really have to.</p>
 */
package com.flowerseeker.dao;
