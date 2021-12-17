package epos_next.app

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}