package happy.family.android.forecastapp

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


fun main() {

}


suspend fun calculatorHardThings(startNum: Int, time: Long = 1000): Int{
    delay(time)
    return startNum * 10
}

suspend fun printLnDelayed(message: String){
    //Complex calculator
    delay(1000)
    println(message)
}

fun exampleBlocking() = runBlocking{
    println("one")
    printLnDelayed("two")
    println("three")
}

// Running on another thread but still blocking the main thread
fun exampleBlockingDispatcher(){
    runBlocking(Dispatchers.Default) {
        println("one - from thread ${Thread.currentThread().name}")
        printLnDelayed("two - from thread ${Thread.currentThread().name}")
    }

    println("three - from thread ${Thread.currentThread().name}")

}

fun exampleLaunchGlobal()= runBlocking{
    println("one - from thread ${Thread.currentThread().name}")

    GlobalScope.launch {
        printLnDelayed("two - from thread ${Thread.currentThread().name}")
    }

    println("three - from thread ${Thread.currentThread().name}")

    delay(3000)
}

fun exampleLaunchGlobalWaiting() = runBlocking{
    println("one - from thread ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printLnDelayed("two - from thread ${Thread.currentThread().name}")
    }

    println("three - from thread ${Thread.currentThread().name}")

    job.join()

}

fun exampleLaunchCoroutineScope() = runBlocking{
    println("one - from thread ${Thread.currentThread().name}")

    // create custome Dispatcher
    val customeDispatcher = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

    launch (customeDispatcher){
        printLnDelayed("two - from thread ${Thread.currentThread().name}")
    }

    println("three - from thread ${Thread.currentThread().name}")

    (customeDispatcher.executor as ExecutorService).shutdown()
}

fun exampleAsyncAwait() = runBlocking {

    var s : String = "Wait Please..."
    println(s)

    val startTime = System.currentTimeMillis()

    val deferred1 = async { calculatorHardThings(10, 4000) }
    val deferred2 = async { calculatorHardThings(20, 3000) }
    val deferred3 = async { calculatorHardThings(30) }

    val sum = deferred1.await() + deferred2.await() + deferred3.await()

    val endTime = System.currentTimeMillis()

    if (deferred1.isCompleted){
        s = "done is $sum"
        println(s)
    }
    println("the Time taken is: ${endTime - startTime}")

    /*
    println("${System.currentTimeMillis()}  deferred1: ${deferred1.isCompleted}")
    println("${System.currentTimeMillis()}  deferred1: ${deferred2.isCompleted}")
    println("${System.currentTimeMillis()}  deferred1: ${deferred3.isCompleted}")

     */

}

fun exampleWithContext() = runBlocking {

    val startTime = System.currentTimeMillis()

    val result1 = withContext(Dispatchers.Default) { calculatorHardThings(10)}
    val result2 = withContext(Dispatchers.Default) { calculatorHardThings(20) }
    val result3 = withContext(Dispatchers.Default) { calculatorHardThings(30) }


    val sum = result1 + result2 + result3
    println("sum $sum")

    val endTime = System.currentTimeMillis()
    println("Time Taken ${endTime - startTime}")





}


