package edu.neo.tpfinal

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import edu.neo.tpfinal.view.MainActivity
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginTestView {


    //Instancio la actividad
    @get:Rule
    var activityRule:ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    //Instancio la vista y hago que complete automaticamente los datos
    fun testButton(){
        Espresso.onView(ViewMatchers.withId(R.id.Usuario_login)).perform(ViewActions.typeText("admin@hotmail.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.Contraseña_login)).perform(ViewActions.typeText("admin123"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).perform(ViewActions.click())
    }

    @Test
    //Seteo contexto
    fun usesAppContext(){
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.neo.tpfinal", appContext.packageName)
    }
}