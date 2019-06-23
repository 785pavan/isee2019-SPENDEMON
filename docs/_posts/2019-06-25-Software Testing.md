---
layout: post
title: "Blog#5: Beta Prototype and Testing"
date: 2019-06-25
---

**<span style="color:#C2185B; font-family:Cursive">Introduction:</span>**

Welcome to Blog#5 for team SPENDEMON. In this particular Blog we shall talk about how we put our App to test in different circumstances and what was the outcome of the tests. We have implemented three basic kinds of Tests: Unit Tests, Implementation Tests and Integration Tests. These tests use both White and Black Box Testing methods and shall be described further ahead.

**<span style="color:#C2185B; font-family:Cursive">White Box Testing:</span>**
The White Box testing methods are basically implemented by the software engineer who has a clear knowledge of the functioning of the App and the implementation of each unit of the software. There are several White Box Testing methods that can be implemented out of which we decided to implement the Unit tests and the  




**<span style= "color:#C2185B">Storyboard 2:</span>** Here we have displayed the Graph visualizations for all the expenses in the form of a Pie chart and a Trendline.



---
<img src="{{site.baseurl}}/images/PieChart.png" alt="PieChart" width="200" />
<img src="{{site.baseurl}}/images/PieChart_1.png" alt="PieChart_1" width="200" />
<img src="{{site.baseurl}}/images/TrendLine.png" alt="Trendline" width="200" />
<img src="{{site.baseurl}}/images/TrendLine_1.png" alt="Trendline_1" width="200" />
---





**Design Principles:** The basic idea behind our design choice is easy use of the App by the user and making the App such that one can have an easy and fruitful experience with it. The user should be able to keep track of his expenses and get an overview of his expenses in one quick glance. The basic design principles that we took into account are as follows:

* Cut Out The Clutter

* Create a Seamless Experience

* Design Finger-friendly Tap-targets

* Text Content Should Be Legible

* Design Controls Based on Hand Position

* Minimize Need For Typing


**<span style="color:#C2185B; font-family:Cursive">Summary of Changes:</span>**
As mentioned in the previous Blog, we started with a pretty simple App design which had a Login page, a Summary Page and an Edit Data page. Along with that we had implemented the Database as a simple JSON file which was pretty basic in it's functionality.

Old version:

<img src="{{site.baseurl}}/images/OldVersion.png" alt="Old Version" width="300" align = "middle" />

New version:

<img src="{{site.baseurl}}/images/Summary.png" alt="Summary" width="300"  />

However, with the Advanced Prototype version we have tried to refine our App with the implementation of certain features to make our App more, well, App like. A summary of the added changes has been given below.

* Room Database implemented.

* Navigation Bar added.

* Charts feature added.

* Icons added for each category.

* Date Picker feature added.

The added features also were reflected the in the user stories as new additions an well as in the Class Diagrams which have been shown below:

**Addition to User Stories:**

  * Easy visualization of expenses through graphs.

  * Sort data by Date​

  * Add small descriptions to the expenses.​

  * Data stored in the Database permanently.​

  * Deal with Configuration changes, App rotations etc.​

**Addition to Class Diagrams:**

  * View Model Class​

  * Room Database Class​

  * Pie Chart Class​



**<span style="color:#C2185B; font-family:Cursive">Final Thoughts:</span>**

The understanding of the User Interface Implementation and Design Pattern, helped in better understanding of both our App and the customers expectations from the App. Doing research on the Design Architectures opened up new windows for us to implement in our App and we are glad we could implement some of it into the App. We hopefully, have been able to create an App that will be able to assist our customers and shall be easy on usage.

We next intend to up our game some more by going into further detailing in our App and make it even more fun to use.  

So stay tuned!! Tschüss!!
