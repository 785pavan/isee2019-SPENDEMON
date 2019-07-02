---
layout: post
title: "Reflections and Learnings"
date: 2019-07-02
---
Welcome to the penultimate Blog of team SPENDEMON. We are so grateful to you for showing interest in our journey so far. After a lot of technical discussion on the various mechanisms that we have applied to the App, it is also very important to discuss on the learning curve that we have had so far and reflect upon what we have learned both as hard as well as soft skills.
So let us dive into these pools of reflections and see what kind of a journey team SPENDEMON has completed in these three months time.


<h2> <b><span style="color:#008183 ">Team Formation:</span></b></h2>
<div style = "text-align:justify">The team formation process for SPENDEMON was quite spontaneous as all of us have been friends since the previous semester and had decide to take the course together. Although we were in need of another team member for our team, but it looked like Lady Luck also wanted the three Musketeers to be so and hence we were allowed to stick with the team of three. Hence, the team formation milestone was as smooth as a piece of cheesecake.
<p>

</p>
<h2> <b><span style="color:#008183 ">About the App:</span></b></h2>
The App that we decided on was based on our own struggles and experiences with money management and taking control of our expenses from the beginning of the month. The App came to us as an opportunity to turn the lemons of our lives into lemonade and create something out of our struggles.
<p>

</p>
The name SPENDEMON was coined by the three of us as we were creating permutations and combinations with words related to *Spending*, *Expenses*, *Currencies* etc. But the amount of discussions we had to finalise the name of the App made us realise how much time and brainstorming a small task such as coming up with an appropriate name takes. It was however an extremely fun beginning to the entire project.
<p>

</p>

<h2> <b><span style="color:#008183 ">User Requirements:</span></b></h2>
After the initial smooth transitions through the team formation and coining the App name, the real job started once we stared preparing the user requirements that the customer might require from the App. Predicting customer requirements, assigning priorities to them and mapping them into classes and interfaces in the App tested our skillsets of Android and Java to the most. Also, convincing the client with the requirement designs that we came up with was an altogether different experience for us.
<p>

</p>
<h2> <b><span style="color:#008183 ">Technical Difficulties:</span></b></h2>
<p>

</p>

<h3>Android Studio:</h3>
If you have followed our Blog before, you probably are familiar with the fact that all three of us are from different backgrounds namely, Mechanical, Electrical and Electronics. This is why, we needed to get our hands dirty with the tool Android Studio itself before we could even think about designing the 1st stage of the App. Android App development courses and YouTube tutorials as well as Stack Overflow came to our rescue during this constant learning phase. Also, the weekly meetings with the Coach/Customer helped us a lot to keep track of our progress and clarify any technical issues that we might be facing.
<p>

</p>

<h3> Activities: </h3> Designing the interface in a way, that we have an optimised number of Activities such that all required information is displayed yet the App is not cluttered with un-necessary information was a task that required some brainstorming. Also linking each Activity to one another and creating intents in an effective way was something we needed to spend time on.
<p>


</p>

<h3> Database:</h3> The major concern for us during 60% of the entire project was implementing an effective Database and extracting as well as manipulating transactions and Data to and from the Database.

The first approach for storing and updating data that we tried implementing was parsing all data into a JSON format and storing it into a JSON script. However, doing this took a lot of space as well as effort and was inefficient.

The second approach that we tried implementing was the MVVM approach i.e. the View Model approach. This approach was rather versatile and not only sorted issues we were facing with Database but also help us with things like *Data Binding,* *App Configuration Changes,* *Easy updating of data in the Database* etc. Although it took a bit longer to implement and was a bit more complicated than say, the View Control approach, but it helped solve a lot of problems at one go.

However, as we progressed with the App, we realised that as a result of implementing the View Model approach later and certain features added earlier, we had to re-wire certain design features and implement/remove certain changes made to some of the activities and their functionalities. In this way, even though we worked in an Agile method, we would have saved ourselves a lot time and effort, had we thought through the entire approach of the App earlier.
<p>

</p>

<h3> Navigation and Visualisation:</h3> An App, however many features it might offer, can only be popular if the User finds it easy to use it and does not find it difficult to navigate through the various features. This was the final major difficulty that we had to resolve in order to make our App more..well..App-like. For this particular reason, two Navigation Drawers were added and various Fragments were added in a way that the User would find it easy to navigate through the entire App and all the categories are easily accessible.
<p>

</p>
However, there was some problems that we encountered some difficulties while linking Fragments with both the Navigation Drawer as well as with the necessary Activities. But this also was resolved by using classes instead of fragments and navigating through the classes instead of the fragments.
<p>

</p>
It was also important to visualise the transactions not only in the form of just numbers and texts but also in the form of <i>Pie Charts</i> and <i>Trendlines</i>.
<p>

</p>
<h2> <b><span style="color:#008183 ">Difficulties Encountered by Team:</span></b></h2>

As a team totally new to Android it was difficult for us to come to a conclusion on how to design and create classes and what should be the content of each class. Also, the flow of each activity into the next and the exact structure of the App took some time to come to a common form.
<p>

</p>
As a team working in an Agile fashion following SCRUM practices, it was difficult to hold and follow the concepts of these principles. For instance, once we implemented the MVVM approach, we had to re visit each and every Activity and class once again in order to make sure each activity was linked to the database through the View approach.
<p>

</p>
Following the coding conventions that were decided by the team were hard to keep up with as we proceeded with the App everyday. However, once Check Styles were implemented for this, it made it much easier to keep track of the conventions and resolve any violations.
<p>

</p>
Finally, working in a team and especially in a collaborative environment demands a lot of communication protocols which was essential and once these were somehow missed, it created problems to resolve the conflicts. But then again, these conflicts actually taught us how to resolve commit issues in Git platforms in an effective manner.
<p>

</p>
<h2> <b><span style="color:#008183 ">Benefits:</span></b></h2>

<h3>Agile and SCRUM:</h3>
The first thing that doing this project taught us was how to apply SCRUM and Agile principles effectively. This not only gave us a real view towards how Software projects actually work but also gave us the flexibility to make mistakes and having a learning curve.

<h3>Java, Android, Testing:</h3>
This project, in order to be completed, required an amalgamation of the above three mentioned terms and hence was sort of like taking three courses at the same time.

<h3>Team Spirit:</h3>
One of the main thing that our team learnt was that with the right conviction and attitude, a three membered team can work as efficiently as a four membered team. Keeping the team's goal above any individual preferences is something we as a team also learnt while doing this project.

<h3>Work Ethic and Discipline:</h3>
When you do not have regular assignments or no one to check if you are doing something right, one often tends to take things for granted and get a bit lazy. So did we. However, we realised after two or three weeks that contributing some amount everyday or every alternate day really helps in the long run as one does not have to compromise with anything that one wanted to implement but could not because of the lack of time.
Having a certain type of work ethic, being considerate and appreciating the  contributions of the team members and having a disciplined work schedule can lead one to achieve things and come up with ideas that would not have been possible otherwise.
<p>

</p>
<h2> <b><span style="color:#008183 ">Review of the Course:</span></b></h2>
The best thing about the course was the fact that it gave an opportunity not only to learn App development but the Apps which were given as options for development were something which shall be extremely useful and fun to use in real life.
<p>

</p>
The course was extremely well organised and the way in which the milestones are divided gives a very definite idea on what the each presentation requires and how one should approach the project. Apart from that, the lectures and the team meetings with the coach were extremely efficient and guided us newbies into developing an amazing App which was not only fun to build but hopefully shall be extremely useful for daily life usage.
<p>

</p>
The course gave us the liberty to use our own imaginations and implement our ideas into something which is useful and is not extremely complicated to implement. The course was flexible enough such that we had the liberty to make and correct our mistakes. It was adequately paced and the Coach made the life of the developers really easy by being extremely open to ideas and basically understanding the limitations and the capacities of each and every student.
<p>

</p>
Also, keeping records of the progress as Blogs instead of a Final Report where everything is scrummed together at the end, is so much of a better idea, as it helps one note down everything that one faced during a particular milestone before everything disappears from the mind and one has to grease the brain and remember everything in the end. Also, it is such an interesting way to learn Blogging from the scratch.
<p>

</p>
<h2> <b><span style="color:#008183 ">Final Thought:</span></b></h2>
It is almost the end of this journey and as hopefully one can make out from the Blog, it was an enlightening journey for each of us. From complete beginners to now with a good amount of knowledge acquired regarding App making, each of us cannot but be grateful that each of us decided to take this course and had been given the opportunity and freedom to learn so much through one course.
<p>

</p>
What we intend to do in our last Blog is simulate a Play Store entry and basically advertise our App in a way which describes (hopefully) how our App can be of use to every person who basically has anything to do with money. So do join us for one last time in the next Blog!
Till then, Tschüss!!
