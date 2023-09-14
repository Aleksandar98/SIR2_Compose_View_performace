package com.example.benchmark

import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.SearchCondition
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()




//    @Test
//    fun startup() = benchmarkRule.measureRepeated(
//        packageName = "com.example.uianaliticsxml",
//        metrics = listOf(StartupTimingMetric()),
//        compilationMode = CompilationMode.Partial(),
//        iterations = 5,
//        startupMode = StartupMode.HOT
//    ) {
//        pressHome()
//        startActivityAndWait()
//    }

    @Test
    fun addToVisitedTest() = benchmarkRule.measureRepeated(
        packageName = "com.example.uianaliticsxml",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        //UI Automator
        addToVisited()
    }

    @Test
    fun scrollTest() = benchmarkRule.measureRepeated(
        packageName = "com.example.uianaliticsxml",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        //UI Automator
        scrollDown()
    }
    @Test
    fun scrollAndNavigate() = benchmarkRule.measureRepeated(
        packageName = "com.example.uianaliticsxml",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        //UI Automator
        openElement()
    }

    @Test
    fun bottomNavigationTest() = benchmarkRule.measureRepeated(
        packageName = "com.example.uianaliticsxml",
        metrics = listOf(FrameTimingMetric()),
        compilationMode = CompilationMode.Partial(),
        iterations = 1,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()

        //UI Automator
        navigateBottomNavigation()
    }
}

fun MacrobenchmarkScope.navigateBottomNavigation(){
    repeat(10){
        device.wait(Until.findObject(By.text("Traveled")),3000)
        device.findObject(By.text("Traveled")).click()
        device.wait(Until.findObject(By.text("Add destination")),3000)
        device.findObject(By.text("Add destination")).click()
        device.wait(Until.findObject(By.text("Add")),3000)
        device.pressBack()
       // device.findObject(By.text("Home")).click()
    }

}
fun MacrobenchmarkScope.addToVisited(){

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    device.findObject(By.text("Nice France")).click()
    device.wait(Until.findObject(By.text("Have Visited")),3000)
    //device.wait(Until.findObject(By.res("${appContext.packageName}:id/haveVisitedSwitch")),3000)
    //val found = device.findObject(By.res("${appContext.packageName}:id/haveVisitedSwitch"))
    val found = device.findObject(By.text("Have Visited"))
    found.click()
    device.pressBack()
    device.waitForIdle()
    device.findObject(By.text("Traveled")).click()
}

fun MacrobenchmarkScope.scrollDown(){

    val list = device.findObject(By.res("com.example.uianaliticsxml:id/recycler_view"))
    device.waitForIdle()

    if(list!= null){
        list.setGestureMargin(device.displayWidth / 3)
        repeat(5){
            list.fling(Direction.DOWN,2000)
        }
    }
    }

fun MacrobenchmarkScope.openElement(){

    device.findObject(By.text("Nice France")).click()
    device.wait(Until.hasObject(By.text("Nice France")), 5000)
    device.pressBack()
}
