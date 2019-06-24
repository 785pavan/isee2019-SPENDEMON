---
layout: post
title: "Beta Prototype and Testing"
date: 2019-06-25
---
Welcome to Blog#5 for team SPENDEMON. In this particular Blog we shall talk about how we put our App to test in different circumstances and what was the outcome of the tests. In a real-world scenario, a user interacts with an App at various levels from pressing buttons to putting in data to changing something by mistake. As an App Developer, we need to predict every possible circumstance that might test our App in any condition and come up with solutions for each such case. This is where Software Testing plays an important role in developing a user friendly App iteratively. Even though Exhaustive Tests are not possible, we have tried our level best to make our App not only functional, but a well tested App.

## **<span style="color:#008183 ">Methodology:</span>**

As one of our team members had done a Software Testing course in the previous semester, we came in terms with the *CORRECT* approach towards implementing Tests from him. Here *CORRECT* stands for:

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


<center><img src="{{site.baseurl}}/images/testing-workflow.png" alt="Testing" width="500" /></center>


The full workflow, as shown in the Figure, contains a series of nested, iterative cycles where a long, slow, UI-driven cycle tests the integration of code units. We test the units themselves using shorter, faster development cycles. This set of cycles continues until our app satisfies every use case.

**Configuring the Test Environments:** We had two directories created for running the tests as recommended by the official Android site:

* androidTest Directory: This has all the tests which run on the virtual environments like the integration tests, system tests etc. which cannot be implemented by the JVM alone.
* test Directory: This contains all the tests that runs on the local machine and are limited to the Unit Tests.


**Testing Pyramid:** The testing pyramid as shown below was implemented during each phase of our App development and consisted of the following categories:


<center><img src="{{site.baseurl}}/images/Pyramid.png" alt="Pyramid" width="500" /></center>



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
~~~
@RunWith(MockitoJUnitRunner.class)
public class SignUpLogInTest {

    private static final String FAKE_STRING = "Login was successful";

    @Mock
    Context mMockContext;

    @Test
    public void readStringFromContext_LocalizedString() {
        SignUpLogIn myObjectUnderTest = new SignUpLogIn(mMockContext);
        String result = myObjectUnderTest.validate("admin", "12345");
        assertThat(result, is(FAKE_STRING));
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}
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


**Steps of Implementation:** Now say you want to implement these tests in your own App these are the steps we followed that can help you as well. (We have taken the example of the Main Activity class below except point 6: )
1. First step as always when you implement someone else's code, is to include all the dependencies in your App level gradle file. For the above tests dependencies are as given below:
~~~
androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
   androidTestImplementation 'androidx.test.espresso:espresso-intents:3.2.0'
   androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.2.0'
   androidTestImplementation 'androidx.test:runner:1.2.0'
   androidTestImplementation 'androidx.test:rules:1.2.0'
   testImplementation group: 'org.mockito', name: 'mockito-core', version: '2.28.2'
   testImplementation 'org.junit.jupiter:junit-jupiter-api:5.4.2'
~~~
2. Next we created test classes which extend IntentTestRule as we test intents when testing end to end.
~~~
public class MainActivityTest extends IntentsTestRule
~~~
3. Then we created @Rule for the each class so that we can operate without disturbing the original class.
~~~
@Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
~~~
4. Now we define @Before setup that activity starts with defined Configuration.
~~~
@Before
   public void setUp() throws Exception {
       mActivity = mActivityTestRule.getActivity();
   }
~~~
5. Then we implement tests that are annotated as @Test for example to start an activity from Summary class and go to edit data class enter few details like amount and all the usual stuff there then click save now go to details of the saved activity using the following code.
~~~
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
6. We also write few more tests which take some other route and finally reach the destination.
~~~
(For Edit Data Class:)
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
~~~
7. After finishing the test for a class we had to invalidate the setup this is done in @After annotated classes.
~~~
@After
    public void tearDown() throws Exception {
        mActivity = null;
    }
~~~

These steps needed to be followed for all the classes that need testing. We have made it easier for the reader to copy paste our test implementation by providing all possible implementation code snippets.



## **<span style="color:#008183 ">Summary of Changes:</span>**

- Pre-Testing:
    - A PDF can be downloaded now from the App which gives a summary of all Transactions.
    - Categorical Filtering Activity was added.
    - Definitions and Legends added to graphs and pie-charts.
    -  Swipe Delete and Delete All transactions button added to the   Settings page.
    - Categorical thresholds added to keep track on transactions.


- Post-Testing:
    - Cause: App crashed when Login fields were empty and User tapped anywhere other than the fields. This was found while testing the SignUpLogin class().
      - Solution: A void function was implemented which simply makes the keypad go down once the fields are empty and the User taps in blank space.
    - Cause: App crashed when any category in the filtering activity was left empty. This was found during testing the Summary class.
      - Solution 1: Methods were created to take in all values if the user did not select a value for any field or category.
      - Solution 2: Start and End dates were checked such that Start dates are always after End dates.




## **<span style="color:#008183 ">Final Thoughts:</span>**




This particular phase of the App Development process taught us that no matter how well you think about the implementation and the design of an App, testing brings into the forefront a lot of loopholes that can save the developers from complaints from customers later on. Hence, a good insight into different Testing methods and circumstances can be truly beneficial in the long run.
Next we shall be back with the last to one and then last Blog to reflect on our journey that has been and simulate our Playstore Entry.

So see you again soon!! Tschüss!!
