Flowerseeker
===========
The code in this repository are as-built as of the end of CSCI 577b Spring 2013 semester. 

Dependencies
============
Dependencies are in the maven pom.xml, current major ones are

* Spring Framework 3.2.1
* Spring Data JPA 1.3.0
* Spring Security 3.1.3
* PayPal AdaptivePayments SDK 2.2.98
* Hibernate EntityManager 4.1.9
* Amazon Web Services SDK 1.4.1

Code Structure
=============
The project uses Spring framework heavily - find all spring related configuration in /src/main/webapp/WEB-INF/applicationContext.xml

Packages
--------
* com.flowerseeker.cart - ShoppingCart related 
* com.flowerseeker.controllers - Spring MVC Controllers
* com.flowerseeker.controllers.forms - Form Beans with validation annotations
* com.flowerseeker.dao - Spring Data JPA Repository interfaces
* com.flowerseeker.domain - JPA 2.0 annotated domain classes
* com.flowerseeker.services - business logic classes, and authentication classes (CustomerUserDetails, UserService)

URL Controller Mappings
-----------------------
Access control is configured using Spring Security, see the "sec:http" configs

* /florist/.. - FloristController and FloristProfileController - only accessible to logged in users with florist role
* /user/.. - CustomerController and CustomerProfileController - only accessible to logged in users with customer role
* /cart/.. - CartController - publicly accessible
* /admin/.. - AdministratorController - only accessible to logged in users with admin role
* all others - HomeController and PublicController - public accessible

Other Info
---------
* Paypal config at src/main/resources/paypal_config.properties
* the master branch works for testing locally, aws branch contains additions and minor modifications to work with AWS Elastic Beanstalk. In particular, AWS spring config depends on env vars set via elastic beanstalk config for database connection information and uploading images to S3 storage buckets, see AWS_Setup.pdf for initial AWS setup. AWS also needs customized ChannelDecisionManagerBeanPostProcessor in order for spring security to correctly redirect to https (checks for X-Forwarded-Proto header set by aws's load balancer as indicator for client protocol)

References and Resources
========================
* [Spring Framework](http://www.springsource.org/spring-framework) - Spring Framework (MVC, [Data-JPA](http://www.springsource.org/spring-data/jpa), [Security](http://www.springsource.org/spring-security))
* [PayPal](https://developer.paypal.com/) - [Adaptive payments](https://developer.paypal.com/webapps/developer/docs/classic/adaptive-payments/gs_AdaptivePayments/) for flexible pay flow and fee distribution - using the Java SDK for adaptive payments
* [jQuery](http://jquery.com/) - js
* [Twitter Bootstrap](http://twitter.github.io/bootstrap/) - ui components and style (see also [Jasny extensions](http://jasny.github.io/bootstrap/) and [bootswatch](http://bootswatch.com/) for themes
* [Amazon Web Services](http://aws.amazon.com/elasticbeanstalk/) - Elastic Beanstalk
* [JPA](http://docs.oracle.com/javaee/6/tutorial/doc/bnbpz.html)

TODO and Known Issues
============
* Paypal integration for order checkout and subscriptions can be demoed only via developer sandbox, order information are hardcoded. Must complete Paypal integration and submit to Paypal via a real account for approval before production use, need to support IPN Notifications
* Paging for search results
* More search options and combining search criteria
* Coupons
* Buy a real certificate signed by trusted CA for ssl
* Email notifications - using Gmail to send email from AWS will cause Google to require authorization for each aws instance the first try, better to use Amazon's existing SES or SNS for notifications
* Setup test and staging envs on aws (will exceed aws free tier)
* ...
* ...