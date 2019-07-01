---
layout: post
title: "Reflections and Learnings"
date: 2019-07-02
---
Welcome to the penultimate Blog of team SPENDEMON. We are so grateful to you for showing interest in our journey so far. After a lot of technical discussion on the various mechanisms that we have applied to the App, it is also very important to discuss on the learning curve that we have had so far and reflect upon what we have learned both as hard as well as soft skills.
So let us dive into these pools of reflections and see what kind of a journey team SPENDEMON has completed in these three months time.


## **<span style="color:#008183 ">Team Formation:</span>**
<div style = "text-align:justify">The team formation process for SPENDEMON was quite spontaneous as all of us have been friends since the previous semester and had decide to take the course together. Although we were in need of another team member for our team, but it looked like Lady Luck also wanted the three Musketeers to be so and hence we were allowed to stick with the team of three. Hence, the team formation milestone was as smooth as a piece of cheesecake.</div>
<div></div>

## **<span style="color:#008183 ">About the App:</span>**
<div style = "text-align:justify">The App that we decided on was based on our own struggles and experiences with money management and taking control of our expenses from the beginning of the month. The App came to us as an opportunity to turn the lemons of our lives into lemonade and create something out of our struggles.</div>
<div></div>
<div style = "text-align:justify">The name SPENDEMON was coined by the three of us as we were creating permutations and combinations with words related to *Spending*, *Expenses*, *Currencies* etc. But the amount of discussions we had to finalise the name of the App made us realise how much time and brainstorming a small task such as coming up with an appropriate name takes. It was however an extremely fun beginning to the entire project.</div>


## **<span style="color:#008183 ">User Requirements:</span>**
After the initial smooth transitions through the team formation and coining the App name, the real job started once we stared preparing the user requirements that the customer might require from the App. Predicting customer requirements, assigning priorities to them and mapping them into classes and interfaces in the App tested our skillsets of Android and Java to the most. Also, convincing the client with the requirement designs that we came up with was an altogether different experience for us.


## **<span style="color:#008183 ">Technical Difficulties:</span>**


<h3>Android Studio:</h3>
If you have followed our Blog before, you probably are familiar with the fact that all three of us are from different backgrounds namely, Mechanical, Electrical and Electronics. This is why, we needed to get our hands dirty with the tool Android Studio itself before we could even think about designing the 1st stage of the App. Android App development courses and YouTube tutorials as well as Stack Overflow came to our rescue during this constant learning phase. Also, the weekly meetings with the Coach/Customer helped us a lot to keep track of our progress and clarify any technical issues that we might be facing.

<h3> Activities: </h3> Designing the interface in a way, that we have an optimised number of Activities such that all required information is displayed yet the App is not cluttered with un-necessary information was a task that required some brainstorming. Also linking each Activity to one another and creating intents in an effective way was something we needed to spend time on.

<h3> Database:</h3> The major concern for us during 60% of the entire project was implementing an effective Database and extracting as well as manipulating transactions and Data to and from the Database.

The first approach for storing and updating data that we tried implementing was parsing all data into a JSON format and storing it into a JSON script. However, doing this took a lot of space as well as effort and was inefficient.

The second approach that we tried implementing was the MVVM approach i.e. the View Model approach. This approach was rather versatile and not only sorted issues we were facing with Database but also help us with things like *Data Binding,* *App Configuration Changes,* *Easy updating of data in the Database* etc. Although it took a bit longer to implement and was a bit more complicated than say, the View Control approach, but it helped solve a lot of problems at one go.

However, as we progressed with the App, we realised that as a result of implementing the View Model approach later and certain features added earlier, we had to re-wire certain design features and implement/remove certain changes made to some of the activities and their functionalities. In this way, even though we worked in an Agile method, we would have saved ourselves a lot time and effort, had we thought through the entire approach of the App earlier.


<h3> Navigation and Visualisation:</h3> An App, however many features it might offer, can only be popular if the User finds it easy to use it and does not find it difficult to navigate through the various features. This was the final major difficulty that we had to resolve in order to make our App more..well..App-like. For this particular reason, two Navigation Drawers were added and various Fragments were added in a way that the User would find it easy to navigate through the entire App and all the categories are easily accessible.
