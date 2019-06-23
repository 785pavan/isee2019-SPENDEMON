---
layout: post
title: "Beta Prototype and Testing"
date: 2019-06-25
---
Welcome to Blog#5 for team SPENDEMON. In this particular Blog we shall talk about how we put our App to test in different circumstances and what was the outcome of the tests. In a real-world scenario, a user interacts with an App at various levels from pressing buttons to putting in data to changing something by mistake. As an App Developer, we need to predict every possible circumstance that might test our App in any condition and come up with solutions for each such case. This is where Software Testing plays an important role in developing a user friendly App iteratively. Even though Exhaustive Tests are not possible, we have tried our level best to make our App not only functional, but a well tested App.

**<span style="color:#C2185B ">Methodology:</span>** As one of our team members had done a Software Testing course in the previous semester, we came in terms with the *CORRECT* approach towards implementing Tests from him. Here *CORRECT* stands for:


~~~
C: Conformance
O: Ordering
R: Range
R: Reference
E: Existence
C: Cardinality
T: Time
~~~

We have tried our best to implement the Tests for our App based on these particular norms.

In order to come up with an effective testing scheme, we also referred to the <a href = "https://developer.android.com/training/testing/fundamentals" target="_blank" > site. </a>
This particular site gave us a systematic approach on how to design and implement our Tests. The basic methodology flow diagram that we have followed is as follows:


<img src="{{site.baseurl}}/images/testing-workflow.png" alt="Testing" width="500" />


The full workflow, as shown in the Figure, contains a series of nested, iterative cycles where a long, slow, UI-driven cycle tests the integration of code units. We test the units themselves using shorter, faster development cycles. This set of cycles continues until our app satisfies every use case.

**Configuring the Test Environments:** We had two directories created for running the tests as recommended by the official Android site:

* androidTest Directory: This has all the tests which run on the virtual environments like the integration tests, system tests etc. which cannot be implemented by the JVM alone.
* test Directory: This contains all the tests that runs on the local machine and are limited to the Unit Tests.


**Testing Pyramid:** The testing pyramid as shown below was implemented during each phase of our App development and consisted of the following categories:


<img src="{{site.baseurl}}/images/Pyramid.png" alt="Pyramid" width="500" />



Depending on the size of the Tests, there are three categories of Tests, namely:


* Small tests are unit tests that validate our app's behavior one class at a time and constituted 70% of all Tests.
* Medium tests are integration tests that validate either interactions between levels of the stack within a module, or interactions between related modules and constituted 20% of the Tests.
* Large tests are end-to-end tests that validate user journeys spanning multiple modules of our app and constitute the final 10% of the Tests.

Again, depending on the Test Design Technique, there are further three categories of Tests:
* Functional/Specification based Test: This is analogous to Black Box testing and is completely based on the specifications derived from the customer's requirements.
* Structure based Test: This is the same as White Box testing and depends on the actual structure of the code, thereby can only be done by the developer.
* Experience based Test: These kind of tests are, as suggested by the name, done based on the experience of the Software Engineer.

The implementation of these tests are discussed in details further below.



**Tests Implemented:**

1. Local Unit Tests: Local Unit Tests are run on the Java Virtual machine rather than on the device or the emulator. In these tests we test the component lifecycle, Event loops and Resources that need to be present for running the app.  We have implemented these as Functional and Structural Tests.

~~~
Example: When we need to call other components of the app like a Database or a server
it might take a minute to respond but the test would fail if this didn't happen
instantaneously. For this reason we use mocking, using which we can define the output of
particular dependency before hand.
~~~


2. Instrumentation Unit Tests: The implementation tests are the JUnit tests which test the App on the local level and contain the following test classes:

  i) Launch Tests: These tests check if each component of the activity gets launched or if launching any activity crashes the App at some particular point in time.

~~~
Example: For each particular activity class, we check if every component of the activity
is built by checking if the field entered is null or not. A few snippets of the tests
used are shown below:
~~~
~~~
SignUp and Login Activity:
@Test
    public void testLaunchViews() {
      View editText = mActivity.findViewById(R.id.username1);
      assertNotNull(editText);
      editText = mActivity.findViewById(R.id.password1);
      assertNotNull(editText);
      View button = mActivity.findViewById(R.id.buttonSignUp);
      assertNotNull(button);
~~~

~~~
Summary Activity:
@Test
    public void testLaunchViews() {
        View recyclerView = mSummary.findViewById(R.id.rvEntries);
        View drawerView = mSummary.findViewById(R.id.drawer_layout_sum);
        View navBar = mSummary.findViewById(R.id.navigation_view1_Sum);
        FloatingActionButton fab = mSummary.findViewById(R.id.fabPlus);

        assertNotNull(fab);
        fab = mSummary.findViewById(R.id.fabMinus);
        assertNotNull(fab);
        assertNotNull(navBar);
        navBar = mSummary.findViewById(R.id.navigation_view2_Sum);
        assertNotNull(navBar);
        assertNotNull(drawerView);
        assertNotNull(recyclerView);
    }
~~~

~~~
Edit Data Activity:
@Test
   public void testLaunchViews() {
       View spinner = mActivity.findViewById(R.id.sCategory);
       assertNotNull(spinner);
       spinner = mActivity.findViewById(R.id.sPaymentMethod);
       assertNotNull(spinner);
       spinner = mActivity.findViewById(R.id.sType);
       assertNotNull(spinner);
       View editText = mActivity.findViewById(R.id.etDate);
       assertNotNull(editText);
       editText = mActivity.findViewById(R.id.etAmount);
       assertNotNull(editText);
       editText = mActivity.findViewById(R.id.etDescription);
       assertNotNull(editText);
   }
~~~

~~~
Main Activity:
@Test
    public void testLaunch() {
        View view = mActivity.findViewById(R.id.drawer_layout);
        assertNotNull(view);
    }
~~~
~~~
Balance Activity:
@Test
    public void testLaunch() {
        View view = mActivity.findViewById(R.id.textView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.balanceTextView);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.textViewIncomeValue);
        assertNotNull(view);
        view = mActivity.findViewById(R.id.textViewExpenseValue);
        assertNotNull(view);
    }
~~~



ii) End-to-end Tests: These are the kind of tests which start from one activity of the app and continue till the end of that particular series of operations is reached and testing in every step of the way. We have included a code snippet to help explain this concept. For the tests we have used Espresso library which is very good at emulating steps.
~~~
Example: For instance in SignUp Login Activity, we implemented the test to check what
happens if a user enters wrong password.
In Summary Activity, there are tests which check if pressing the "+" button takes the input type as Incomes and "- button"
takes in input as Expenses or not.
In Edit Data Activity, there are two Entry Tests which takes some default values assigned by
us and creates an Expense and an Income Entry.
In Balance Activity, we check whether the correct balance is being retrieved or not.  
In the Main Activity, we have tested each fragment of the Navigation Drawer if it opens the
activity when the respective fragment is clicked on.
All these individual tests finally gives an overall test implementation, which logs in,
goes into Summary, opens the Navigation Drawer, checks for each fragment, and saves both
an Expense and an Income Entry in an automatic manner, without the user doing anything.
~~~
~~~
Sign Up and Login Activity:
@Test
    public void wrongPassTest() {
        View relativeLayout = mActivity.findViewById(R.id.relativeLayout1);
        Espresso.onView((withId(R.id.username1)))
                .perform(ViewActions.typeText(username));
        int wrongPass = 123456789;
        Espresso.onView(withId(R.id.password1))
                .perform(ViewActions.typeText(String.valueOf(wrongPass)));
        Espresso.pressBack();
        Espresso.onView(withId(R.id.buttonSignUp))
                .perform(ViewActions.click());
        assertEquals(sharedPreferences.getString("UserName", null), username);
        assertNotEquals(sharedPreferences.getInt("Password", 0), wrongPass);
        assertNotNull(relativeLayout);
    }
~~~
~~~
Summary Activity:
@Test
   public void minusClickTest() {
       Intents.init();
       Espresso.onView(withId(R.id.fabMinus)).perform(ViewActions.click());
       intended(hasComponent(EditData.class.getName()));
       Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText("Expenses")));
       Intents.release();
   }

   @Test
   public void plusClickTest() {
       Intents.init();
       Espresso.onView(withId(R.id.fabPlus)).perform(ViewActions.click());
       intended(hasComponent(EditData.class.getName()));
       Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText("Incomes")));
       Intents.release();
   }

   @Test
   public void detailsClickTest() {
       Intents.init();
       Espresso.onView(withId(R.id.rvEntries)).perform(ViewActions.click());
       intended(hasComponent(DetailsActivity.class.getName()));
       Intents.release();
   }
~~~
~~~
Main Activity:
@Test
    public void navBarTestSummary() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_summary));
        intended(hasComponent(Summary.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestOverView() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_balance));
        intended(hasComponent(BalanceActivity.class.getName()));
        Intents.release();

    }

    @Test
    public void navBarTestMonthly() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_monthly));
        intended(hasComponent(ChartMonthActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestAll() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_total));
        intended(hasComponent(PieChartActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestDaily() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_daily));
        intended(hasComponent(PieChartDailyActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void navBarTestTrendLine() {
        Intents.init();
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_trendLine));
        intended(hasComponent(TrendLineActivity.class.getName()));
        Intents.release();
    }
~~~
~~~
Edit Data Activity:
@Test
   public void dataIncomeEntryTest() {
       String type = "Incomes";
       String paymentMethod = "GooglePay";
       String category = "Salary";
       String amount = "750";
       String notes = "This is a test addition";
       Calendar cal = Calendar.getInstance();
       int year = cal.get(Calendar.YEAR);
       int month = cal.get(Calendar.MONTH);
       int day = cal.get(Calendar.DAY_OF_MONTH);
       String date = "" + day + "/" + (month + 1) + "/" + year;

       Intents.init();
       Espresso.onView(withId(R.id.sType)).perform(click());
       Espresso.onData(allOf(is(instanceOf(String.class)), is(type)))
               .perform(click());
       Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText(containsString(type))));
       Espresso.onView(withId(R.id.sCategory)).perform(click());
       Espresso.onData(allOf(is(instanceOf(String.class)), is(category)))
               .perform(click());
       Espresso.onView(withId(R.id.sPaymentMethod)).perform(click());
       Espresso.onData(allOf(is(instanceOf(String.class)), is(paymentMethod)))
               .perform(click());
       Espresso.onView(withId(R.id.sPaymentMethod)).check(matches(withSpinnerText(paymentMethod)));

       Espresso.onView(withId(R.id.sCategory)).check(matches(withSpinnerText(category)));
       Espresso.onView(withId(R.id.etAmount)).perform(ViewActions.typeText(amount));
       Espresso.onView(withId(R.id.etDescription)).perform(ViewActions.typeText(notes));
       Espresso.closeSoftKeyboard();
       Espresso.onView(withId(R.id.save_table)).perform(click());
       intended(hasComponent(Summary.class.getName()));
       Espresso.onView(withId(R.id.rvEntries))
               .perform(RecyclerViewActions
                       .<RecyclerView.ViewHolder>actionOnItemAtPosition(0, click()));
       intended(hasComponent(DetailsActivity.class.getName()));
       Espresso.onView(withId(R.id.tvAmountDetails))
               .check(matches(withText(containsString(amount))));
       Espresso.onView(withId(R.id.tvNotesDetails))
               .check(matches(withText(containsString(notes))));
       Espresso.onView(withId(R.id.tvDateDetails))
               .check(matches(withText(containsString(date))));

       Intents.release();
   }

   @Test
   public void dataExpenseEntryTest() {
       String type = "Expenses";
       String paymentMethod = "Card";
       String category = "Restaurant";
       String amount = "630";
       String notes = "This is a test Expense addition";
       Calendar cal = Calendar.getInstance();
       int year = cal.get(Calendar.YEAR);
       int month = cal.get(Calendar.MONTH);
       int day = cal.get(Calendar.DAY_OF_MONTH);
       String date = "" + day + "/" + (month + 1) + "/" + year;

       Intents.init();
       Espresso.onView(withId(R.id.sType)).perform(click());
       Espresso.onData(allOf(is(instanceOf(String.class)), is(type)))
               .perform(click());
       Espresso.onView(withId(R.id.sType)).check(matches(withSpinnerText(containsString(type))));
       Espresso.onView(withId(R.id.sCategory)).perform(click());
       Espresso.onData(allOf(is(instanceOf(String.class)), is(category)))
               .perform(click());
       Espresso.onView(withId(R.id.sPaymentMethod)).perform(click());
       Espresso.onData(allOf(is(instanceOf(String.class)), is(paymentMethod)))
               .perform(click());
       Espresso.onView(withId(R.id.sPaymentMethod)).check(matches(withSpinnerText(paymentMethod)));

       Espresso.onView(withId(R.id.sCategory)).check(matches(withSpinnerText(category)));
       Espresso.onView(withId(R.id.etAmount)).perform(ViewActions.typeText(amount));
       Espresso.onView(withId(R.id.etDescription)).perform(ViewActions.typeText(notes));
       Espresso.closeSoftKeyboard();
       Espresso.onView(withId(R.id.save_table)).perform(click());
       intended(hasComponent(Summary.class.getName()));
       Espresso.onView(withId(R.id.rvEntries))
               .perform(RecyclerViewActions
                       .<RecyclerView.ViewHolder>actionOnItemAtPosition(0, click()));
       intended(hasComponent(DetailsActivity.class.getName()));
       Espresso.onView(withId(R.id.tvAmountDetails))
               .check(matches(withText(containsString(amount))));
       Espresso.onView(withId(R.id.tvNotesDetails))
               .check(matches(withText(containsString(notes))));
       Espresso.onView(withId(R.id.tvDateDetails))
               .check(matches(withText(containsString(date))));

       Intents.release();
   }
~~~
~~~
Balance Activity:
@Test
    public void balanceCheck() {
        Intents.init();
        onView(withId(R.id.drawer_layout_sum))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.navigation_view1))
                .perform(NavigationViewActions.navigateTo(R.id.nav_balance));
        intended(hasComponent(BalanceActivity.class.getName()));

        ArrayList<Float> iData = new ArrayList<>();
        ArrayList<Float> eData = new ArrayList<>();
        float balance = 0;
        float expenseSum = 0;
        float incomeSum = 0;
        for (com.dbse.android.spendemon.model.Entry entry : entries) {
            if (entry.getType().equals("Incomes")) {
                iData.add((float) entry.getAmount());
            } else if (entry.getType().equals("Expenses")) {
                eData.add((float) entry.getAmount());
            }
        }
        for (float index : iData) {
            balance += index;
            incomeSum += index;
        }
        for (float index : eData) {
            balance -= index;
            expenseSum += index;
        }
        onView(withId(R.id.textView))
                .check(matches(withText(String.valueOf(balance))));
        Intents.release();
    }

~~~



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
