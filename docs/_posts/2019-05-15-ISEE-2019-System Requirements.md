---
layout: post
title: "Blog#3: System Requirements"
date: 2019-05-15
---

**<span style="color:#C2185B; font-family:Cursive">Introduction:</span>**

Welcome to Blog#3 for team SPENDEMON. In this blog, we would like to delve deep into the actual implementation process for our App.

In the previous blog we looked into the user aspects of the App with the User stories and the Use-Case Diagram. In this blog we will look more into the implementation level of the App with Activity diagram and Class diagrams. We try to explain each aspect of these diagrams in detail for better understanding of the user and you our readers! So, let us begin..

**<span style="color:#C2185B; font-family:Cursive">Activity Diagram:</span>**

We present here two Activity Diagrams for our App, namely:

* Login Activity

* Add Entries Activity.

**<span style="color:#C2185B; font-family:Cursive">Login Activity:</span>**

<img src="{{site.baseurl}}/images/LoginActivity.png" alt="Login Activity" width="400" align = "middle" />


This activity diagram, depicts the entry point of the user into the App. We shall look at each activity one at a time.

<span style="color:#C2185B; font-family:Cursive">*Sign-Up:*</span>  Incase of a first time installation of the app, the user will be required to add his/her signup details (Username and Password) into the Sign-Up activity which has a condition for a new and an existing user. Incase of the former, the Sign-Up details are used to create a new account and to authorize the user into the Summary page.

<span style="color:#C2185B; font-family:Cursive">*Login:*</span> The Login activity is used to authenticate an existing user. On correctly entering the Username and Password, the user shall be directed to the Summary page of the App.

<span style="color:#C2185B; font-family:Cursive">*Summary:*</span> This page displays all the added expenses and incomes of the user sorted on the basis of the most recently added.   

**<span style="color:#C2185B; font-family:Cursive">Add Entries Activity:</span>**

<img src="{{site.baseurl}}/images/AddEntryActivity.png" alt="Add Entries Activity" width="400" align = "middle" />

This is the basic editorial page of our App where the user can add and edit incomes and expenses to the Summary page.

<span style="color:#C2185B; font-family:Cursive">*Add Button:*</span>
This is a FAB created to take into account the Incomes of the user. The user is then taken to the main edit page where he/she can edit their income details.

<span style="color:#C2185B; font-family:Cursive">*Minus Button:*</span> This FAB, like the Add Button, takes into account the user's Expenses and add details in the main edit page.

<span style="color:#C2185B; font-family:Cursive">*Input Category:*</span> This is a drop down menu which gives some sub categories for the Income (like Salary, Allowance etc.) and Expense (like Restaurant, Travel etc.) fields.

<span style="color:#C2185B; font-family:Cursive">*Input Amount:*</span> The amount for each case can be entered in here.

<span style="color:#C2185B; font-family:Cursive">*Input Date:*</span>
The date for the specific Income and Expenditure is added here.

<span style="color:#C2185B; font-family:Cursive">*Save Button:*</span>
