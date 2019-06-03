---
layout: post
title: "Blog#4: Implementation and User Interface Design"
date: 2019-06-04
---

**<span style="color:#C2185B; font-family:Cursive">Introduction:</span>**

Welcome to Blog#4 for team SPENDEMON. We are happy to see you again. In this blog we would like to talk about the design pattern that we have implemented for the App and talk about the reasons for doing so. We also talk about the coding conventions we are implementing and the measures taken by us to ensure that we follow the conventions. We also talk about the kind of users that are likely to find our App useful and how we have added to our prototype to include the demands from the users. We have for the first time also included some screenshots of our App for better Visualization.

**<span style="color:#C2185B; font-family:Cursive">Design Pattern:</span>**

When we started with the research for the Design Pattern, we wanted an architecture which could help us combine multiple features like accessing the Database, Configuration changes, Data Binding etc. with one design implementation. Hence we decided to opt for the View Model architecture design which combines multiple features we needed and also had scope for expanding the features in the future.

The View Model architecture basically has a structure as below:

<img src="{{site.baseurl}}/images/ViewModel.png" alt="View Model Diagram" width="800" align = "middle" />

<span style="color:#C2185B; font-family:Cursive">*Activity/Fragments:*</span>
This basically is the top most level of the UI and represents all the activity interfaces that we have in the App that the user interacts with. For us we have the Summary, Edit Data, Pie Charts (Daily, Monthly, Weekly and Total) as activities.

<span style="color:#C2185B; font-family:Cursive">*View Model:*</span>
This part of the Architecture takes care of any Configuration changes like App rotations and saves the Data even when the activity gets destroyed during configuration changes.


<span style="color:#C2185B; font-family:Cursive">*Repository:*</span>
The Repository creates an abstract layer between the View Model and actual Database. This particular layer does not much add too much into our App just yet, but can be implemented later if we want to make our App online and access data from Websites.


<span style="color:#C2185B; font-family:Cursive">*Room Database:*</span>
Room Database is an abstraction layer over the SQLite database which manages both local data as well as data from the Database.

* **Data Access Object:** An Interface implemented in the Database class and contains the methods like insert(), update(), delete(), deleteAll().


* **Database:** It extends the RoomDatabase class and stores all Entities that we pass from the App.


* **Entities:** These are values that we send from our App to the Database and are stored in the Database.



**<span style="color:#C2185B; font-family:Cursive">Coding Conventions :</span>**



When working together in an Agile manner and following SCRUM methods one needs to follow certain conventions such that all team members work effectively and can communicate well. For us, we decided to implement the Google Java Style Guide coding conventions for Android. Here is a list of the coding conventions we applied:

1. **Naming Conventions:**


  * Java Classes/Interfaces: UpperCamelCase.java

  * Activity Java Files: UpperCamelCaseActivity.java

  * Activity Layout Files: activity_lowercase_name.xml

  * Fragment Files: fragment_lowercase_name.xml

  * Menu Files: menu_lowercase_name.xml

  * Method Names: lowerCamelCase

  * Constants: UPPER_CASE

  * Variables: lowercase_name

2. **Source File Structure:**

  * Package name

  * Import statements (Android statements, third party statements, java/javax statements)

  * One high level class

  * Overrides of methods

  * JavaDoc: All Source files and sections should have params, functionalities to be written before.


3. **Line and Spacing Indentations:**

  * basicOffset: 4

  * braceAdjustment: 0

  * caseIndent: 4

  * throwsIndent: 8

  * lineWrappingIndentation: 8

  * arrayInitIndent: 4


<img src="{{site.baseurl}}/images/Conventions.png" alt="Conventions" width="800" align = "middle" />




**<span style="color:#C2185B; font-family:Cursive">Add Entries Activity:</span>**



This is the basic editorial page of our App where the user can add and edit incomes and expenses to the Summary page.

<span style="color:#C2185B; font-family:Cursive">*Add Button:*</span>
This is a FAB created to take into account the Incomes of the user. The user is then taken to the main edit page where he/she can edit their income details.

<span style="color:#C2185B; font-family:Cursive">*Minus Button:*</span> This FAB, like the Add Button, takes into account the user's Expenses and add details in the main edit page.

<span style="color:#C2185B; font-family:Cursive">*Input Category:*</span> This is a drop down menu which gives some sub categories for the Income (like Salary, Allowance etc.) and Expense (like Restaurant, Travel etc.) fields.

<span style="color:#C2185B; font-family:Cursive">*Input Amount:*</span> The amount for each case can be entered in here.

<span style="color:#C2185B; font-family:Cursive">*Input Date:*</span>
The date for the specific Income and Expenditure is added here.

<span style="color:#C2185B; font-family:Cursive">*Save Button:*</span>

**<span style="color:#C2185B; font-family:Cursive">Class Diagram:</span>**

<img src="{{site.baseurl}}/images/UMLClassDiagram.png" alt="Class Diagram" width="800" align = "middle" />

<span style="color:#C2185B; font-family:Cursive">*User Class:*</span>
This class is used to store all User information plus helps in interaction of the User with the App.

* **Attributes:** username, password and name

* **Methods:** Along with the usual getters and setters methods we also have the below mentioned methods,

  * login(): for authentication.

  * add(): to add entries.

  * remove(): to remove entries.

  * calculate(): to do various calculations.

  * carryOver(): to get the balance.

<span style="color:#C2185B; font-family:Cursive">*Entry Class:*</span>
This class is the model on which our App is built on.

* **Attributes:** amount, currency, sign, tag, category.

<span style="color:#C2185B; font-family:Cursive">*Category Class:*</span>
This class is used for defining the pre-existing categories.

* **Attributes:** name, entry, count

* **Methods:**

  * add(): for adding entries into categories.

  * edit(): for editing entries in categories.

  * remove(): for deleting entries from categories.

<span style="color:#C2185B; font-family:Cursive">*Expense Class:*</span> This is a class generalised from entry to store expenses.


* **Attributes:** type

<span style="color:#C2185B; font-family:Cursive">*Income Class:*</span> This is a class generalised from entry to store incomes.


* **Attributes:** type


<span style="color:#C2185B; font-family:Cursive">*Display Class:*</span> This is a class used for displaying a summary of the expenses and incomes in a sorted manner.

* **Methods:**

  * daily(): for displaying everyday summary.

  * weekly(): for displaying weekly summary.

  * monthly(): for displaying monthly summary.

  * yearly(): for displaying yearly summary.

<span style="color:#C2185B; font-family:Cursive">*Calculator Class:*</span>
This class is used for displaying a built-in calculator for quick calculations.

* **Attributes:** inputs, outputs

* **Methods:**

  * add(): adds two doubles.

  * subtract(): subtracts two doubles

  * multiply(): multiplies two doubles

  * divide(): divides two doubles

  * interest(): calculates the interest rates for loans etc.

  * tip(): calculates tips based on bills in Restaurants.

  * tax(): calculates taxes based on Incomes.


**<span style="color:#C2185B; font-family:Cursive">Final Thoughts:</span>**

The usage of the Class Diagrams and Activity Diagrams were immensely helpful during the implementation process and eased our struggles with Android and Java. It gave us a template and  structure to follow, such that we could keep track on the progress of our development phase and would not deviate.

The next task will be to illustrate before our users the Implementation procedures and also design a GUI for interactive usage of our application. Hope to make an interesting read for you again..

See you soon!! Tschüss!!
