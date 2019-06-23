---
layout: post
title: "Beta Prototype and Testing"
date: 2019-06-25
---
Welcome to Blog#5 for team SPENDEMON. In this particular Blog we shall talk about how we put our App to test in different circumstances and what was the outcome of the tests. In a real-world scenario, a user interacts with an App at various levels from pressing buttons to putting in data to changing something by mistake. As an App Developer, we need to predict every possible circumstance that might test our App in any condition and come up with solutions for each such case. This is where Software Testing plays an important role in developing a user friendly App iteratively.

**<span style="color:#C2185B ">Methodology:</span>** In order to come up with an effective testing scheme, we referred to the <a href = "https://developer.android.com/training/testing/fundamentals" target="_blank" > site. </a>
This particular site gave us a systematic approach on how to design and implement our Tests. The basic methodology flow diagram that we have followed is as follows:

<img src="{{site.baseurl}}/images/testing-workflow.png" alt="Testing" width="200" />


The full workflow, as shown in the Figure, contains a series of nested, iterative cycles where a long, slow, UI-driven cycle tests the integration of code units. We test the units themselves using shorter, faster development cycles. This set of cycles continues until our app satisfies every use case.

**Configuring the Test Environments:** We had two directories created for running the tests as recommended by the official Android site:

* androidTest Directory: This has all the tests which run on the virtual environments like the integration tests, system tests etc. which cannot be implemented by the JVM alone.
* test Directory: This contains all the tests that runs on the local machine and are limited to the Unit Tests.


**Testing Pyramid:** The testing pyramid as shown below was implemented during each phase of our App development and consisted of the following categories:

**<span style="color:#C2185B ">Testing Pyramid:</span>** The testing pyramid as shown below was implemented by us in order to run three categories of Tests:


* Small tests are unit tests that validate our app's behavior one class at a time and constituted 70% of all Tests.
* Medium tests are integration tests that validate either interactions between levels of the stack within a module, or interactions between related modules and constituted 20% of the Tests.
* Large tests are end-to-end tests that validate user journeys spanning multiple modules of our app and constitute the final 10% of the Tests.

<img src="{{site.baseurl}}/images/Pyramid.png" alt="Pyramid" width="200" />


**Tests Implemented:**
* Local Unit Tests: Local Unit Tests are run on the Java Virtual machine rather than on the device or the emulator. In these tests we test the component lifecycle, Event loops and Resources that need to be present for running the app. When we need to call other components of the app like a Database or a server it might take a minute to respond but the test would fail it this didn't happen instantaneously, for this reason we use mocking, using which we can define the output of particular dependency before hand.
* Instrumentation Unit Tests: The implementation tests are the JUnit tests which test the App on the local level and contain the following test classes:
  * Launch Tests: These tests check if each component of the activity gets launched or if launching any activity crashes the App at some particular point in time.
  * End-to-end Tests: These are the kind of tests which start from one activity of the app and continue till the end of that particular series of operations is reached and testing in every step of the way. We have included a code snippet to help explain this concept. For the tests we have used Espresso library which is very good at emulating steps.
* System Tests:


Now say you want to implement these tests in your own app these are the steps we followed that can help you as well.
1. First step as always when you implement someone else code, to include all the dependencies in our app level gradle file. for the above tests dependencies are :
2. Next we created test classes which extend IntentTestRule as we test intents when testing end to end.
3. Then we created @Rule for the each class so that we can operate without disturbing the original class.
4. Now we define @Before setup that activity starts with defined Configuration.
5. Then we implement tests that are annotated as @Test for example to start an activity from Summary class and go to edit data class enter few details like amount and all the usual stuff there then click save now go to details of the saved activity using the following code.
6. we also write few more tests which take some other route and finally reach the destination.
7. After finishing the test for a class we had to invalidate the setup this is done in @After annotated classes.
8. These steps needed to be followed for all the classes that needed testing.






**<span style="color:#C2185B ">Final Thoughts:</span>**

The understanding of the User Interface Implementation and Design Pattern, helped in better understanding of both our App and the customers expectations from the App. Doing research on the Design Architectures opened up new windows for us to implement in our App and we are glad we could implement some of it into the App. We hopefully, have been able to create an App that will be able to assist our customers and shall be easy on usage.

We next intend to up our game some more by going into further detailing in our App and make it even more fun to use.  

So stay tuned!! Tschüss!!
