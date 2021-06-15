package top.jowanxu.wanandroidclient.test

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach {
        if (it == 3) return // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with implicit label")
}

fun main() {
    foo()
}