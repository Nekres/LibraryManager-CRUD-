# LibraryManager-CRUD-
Simple CRUD web-interface.
Used:
Spring MVC, Spring Security, Hibernate, Mysql,JSP,Spring Java Config, Maven.
#how to deploy:
- import sql script(library.sql) to mysql at db "library" on port 3306
- create user for mysql and give him access rights(user:librarian, password:librarian); configured into a code
- build with maven and run
- to log into administrator's panel press "log as superadmin" on main page. login:admin, pass:admin

#To make an order you need to select books into table and fill the form for the order. Than press "make order". Quantity of books you selected will be stored at DB.
Access to admin-interface implemented via Spring Security, configuration via Java Config(not xml).
![Screenshot](http://i.piccy.info/i9/6a41794af09271fdad00f574cf04ee7e/1499164732/139765/1159415/Snymok_ekrana_ot_2017_07_04_13_38_27.png)
