---
layout: post
title: "Blog#5: Beta Prototype and Testing"
date: 2019-06-25
---

**<span style="color:#C2185B; font-family:Cursive">Introduction:</span>**

Welcome to Blog#5 for team SPENDEMON. In this particular Blog we shall talk about how we put our App to test in different circumstances and what was the outcome of the tests. In a real-world scenario, a user interacts with an App at various levels from pressing buttons to putting in data to changing something by mistake. As an App Developer, we need to predict every possible circumstance that might test our App in any condition and come up with solutions for each such case. This is where Software Testing plays an important role in developing a user friendly App iteratively.

**<span style="color:#C2185B; font-family:Cursive">Methodology:</span>** In order to come up with an effective testing scheme, we referred to the <a href = "https://developer.android.com/training/testing/fundamentals" target="_blank" > site. </a>
This particular site gave us a systematic approach on how to design and implement our Tests. The basic methodology flow diagram that we have followed is as follows:

<img src="{{site.baseurl}}/images/testing-workflow.png" alt="Testing" width="200" />


The full workflow, as shown in the Figure, contains a series of nested, iterative cycles where a long, slow, UI-driven cycle tests the integration of code units. We test the units themselves using shorter, faster development cycles. This set of cycles continues until our app satisfies every use case.

**Configuring the Test Environments:** We had two directories created for running the tests as recommended by the official Android site:

* androidTest Directory: This has all the tests which run on the virtual environments like the integration tests, system tests etc. which cannot be implemented by the JVM alone.
* test Directory: This contains all the tests that runs on the local machine and are limited to the Unit Tests. 








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
