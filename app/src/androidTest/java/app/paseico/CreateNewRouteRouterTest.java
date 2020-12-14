package app.paseico;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import app.paseico.mainMenu.userCreatedRoutes.CreateNewRouteActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class CreateNewRouteRouterTest {

    DatabaseReference myActualUserRef;

    @Rule
    public ActivityScenarioRule<CreateNewRouteActivity> mActivityRule =
            new ActivityScenarioRule<>(CreateNewRouteActivity.class);

    @Before
    public void prepareUser() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String email = "avd@gmail.com";
        String password = "123456";
        firebaseAuth.signInWithEmailAndPassword(email, password);

        myActualUserRef = FirebaseDatabase.getInstance().getReference("users")
                .child("7YuiqRHra8OaosZc9vpbfGXdy9C2").child("points");
    }

    @Test
    public void createRouteSuccessfullyTest() throws InterruptedException {
        myActualUserRef.setValue(200);
        Thread.sleep(5000);
        onView(withId(R.id.route_name_textInputEditText)).perform(typeText("myRoute"),
                closeSoftKeyboard());

        onView(withId(R.id.new_route_map)).perform(longClick());
        onView(withId(R.id.user_created_marker_name_text_input)).perform(typeText("Poi"),
                closeSoftKeyboard());
        onView(withId(R.id.user_created_marker_button)).perform(click());

        onView(withId(R.id.finalize_route_creation_button)).perform(click());
        onView(withText("OK")).perform(click());

        onView(withText("La nueva ruta ha sido guardada satisfactoriamente.")).check(matches(isDisplayed()));
    }

    @Test
    public void createRouteNotSuccessfullyTest() throws InterruptedException {
        myActualUserRef.setValue(20);
        Thread.sleep(5000);
        onView(withId(R.id.route_name_textInputEditText)).perform(typeText("myRoute"),
                closeSoftKeyboard());

        onView(withId(R.id.new_route_map)).perform(longClick());
        onView(withId(R.id.user_created_marker_name_text_input)).perform(typeText("Poi"),
                closeSoftKeyboard());
        onView(withId(R.id.user_created_marker_button)).perform(click());

        onView(withId(R.id.finalize_route_creation_button)).perform(click());

        onView(withText("OK")).perform(click());

        onView(withText("No tienes los puntos suficientes para crear la ruta.")).check(matches(isDisplayed()));
    }
}

