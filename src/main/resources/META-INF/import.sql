insert into occasion values ('Christmas');
insert into occasion values ('Birthday');
insert into occasion values ('Anniversary');
insert into occasion values ('Wedding');
 


--SOME ORDERS AND SOME ORDER ENTRIES. REMEMBER IF YOU WANT TO USE THEM TO CREATE A STORE
--with USERNAME "mary" AND 2 PRODUCTS. ALSO A CUSTOMER WITH USERNAME "customer1". TOO QUICK TEST
--OR FIX TO YOUR NEEDS. THE REASON, ITS BECAUSE ADD THE ACTUAL ORDER ISNT YET FINISHED

--https://localhost:8443/flowerseeker/user/productCheckout/1    for product check

--insert into orders values(1, '2008-5-04', 'sen to ym house 2', 1, 'this is status', 40, 'customer1',1, 1);
--insert into orderentry values( 1, 80, 3,1,1);
--insert into orders values(2, '2008-6-04', 'sen to ym house 3', 1, 'this is status', 15, 'customer1',1, 1);
--insert into orderentry values( 2, 30, 1,2,1);
--insert into orders values(3, '2009-7-02', 'sen to ym house 4', 0, 'this is status', 100, 'customer1',1, 1);
--insert into orderentry values( 3, 200, 5,3,1);
--insert into orders values(4, '2010-7-04', 'sen to ym house 5', 0, 'this is status', 150, 'customer1',1, 1);
--insert into orderentry values( 4, 300, 6,4,1);
--insert into orders values(5, '2011-5-04', 'sen to ym house 6', 1, 'this is status', 100, 'customer1',1, 1);
--insert into orderentry values( 5, 100, 7,5,1);
--insert into orders values(6, '2011-7-04', 'sen to ym house 7', 0, 'this is status', 30, 'customer1',1, 1);
--insert into orderentry values( 6, 80, 2,6,1);
--insert into orders values(7, '2012-7-04', 'sen to ym house 8', 0, 'this is status', 500, 'customer1',1, 1);
--insert into orderentry values( 7, 100, 5,7,1);

--insert into users(username, email, enabled, firstname, hasStore, isAdmin, lastname, password, phone)
  values('jdoe', 'joe@example.com', 1, 'John', 1, 0, 'Doe', 'afe6ad563cc267cc98c88efbfe7dd37a5bd4b4d9', '555-555-5555');