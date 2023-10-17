import kotlin.test.*
import java.io.File


internal class Test1 {
    /*Тестирование функции put*/
    @Test
    fun test1() {
        main(arrayOf("basa", "put", "100", "appale"))
        assertEquals("appale", File(("basa".toByteArray() +  "100".toByteArray()).toHex()).readLines()[0])
    }
    @Test
    fun test2() {
        main(arrayOf("cat", "put", "aod", "cat1"))
        assertEquals("cat1", File(("cat".toByteArray() +  "aod".toByteArray()).toHex()).readLines()[0])
    }
    @Test
    fun test3() {
        main(arrayOf("pass", "put", "Lena", "qwerty"))
        assertEquals("qwerty", File(("pass".toByteArray() +  "Lena".toByteArray()).toHex()).readLines()[0])
    }
    @Test
    fun test4() {
        main(arrayOf("passport", "put", "Vadim", "Smirnov"))
        assertEquals("Smirnov", File(("passport".toByteArray() +  "Vadim".toByteArray()).toHex()).readLines()[0])
    }
    /*Тестирование функции get*/
    @Test
    fun test5() {
        main(arrayOf("basa", "get", "100"))
        assertEquals(Get(("basa".toByteArray() + "100".toByteArray()).toHex()), File(("basa".toByteArray() +  "100".toByteArray()).toHex()).readLines()[0])
    }
    @Test
    fun test6() {
        main(arrayOf("passport", "get", "Vadim"))
        assertEquals(Get(("passport".toByteArray() + "Vadim".toByteArray()).toHex()), File(("passport".toByteArray() +  "Vadim".toByteArray()).toHex()).readLines()[0])
    }
    @Test
    fun test7() {
        main(arrayOf("pass", "get", "Lena"))
        assertEquals(Get(("pass".toByteArray() + "Lena".toByteArray()).toHex()), File(("pass".toByteArray() +  "Lena".toByteArray()).toHex()).readLines()[0])
    }
    @Test
    fun test8() {
        main(arrayOf("cat", "get", "aod"))
        assertEquals(Get(("cat".toByteArray() + "aod".toByteArray()).toHex()), File(("cat".toByteArray() +  "aod".toByteArray()).toHex()).readLines()[0])
    }
    /*Тестирование функции remove*/
    @Test
    fun test9() {
        main(arrayOf("basa", "put", "key", "200")) /*Для удобсвта вначале создаем строчку в бд (ну или файлик в нашем решении)*/
        main(arrayOf("basa", "remove", "key"))
        assertEquals(File(("basa".toByteArray() +  "key".toByteArray()).toHex()).exists(), false)
    }
    @Test
    fun test10() {
        main(arrayOf("basa1", "put", "key1", "200"))
        main(arrayOf("basa1", "remove", "key1"))
        assertEquals(File(("basa1".toByteArray() +  "key1".toByteArray()).toHex()).exists(), false)
    }
}
