import java.io.File
/*УТилита kvbd*/
/*
1)Как пользоваться:
Первым пишется имя базы данных с которой будет дальней шая работа.
В базе данных хранятся строки в виде key value, разделенные пробелом. Исходно есть файлик basa
Утилита поддерживает операции:
* `dbname put key value` — добавить в БД **dbname** по ключу **key** значение **value**
* `dbname get  key` — получить из БД **dbname** текущее значение по ключу **key**
* `dbname remove  key` — удалить из БД **dbname** текущее значение по ключу **key**
ПРИМЕРЫ ВХОДНЫХ ДАННЫХ:
basa get 1
basa put apple 100
*/

/*Проверка на наличие файла*/
fun isFileExists(file: File): Boolean {
    return file.exists() && !file.isDirectory
}
fun main(args: Array<String>) {
    var input = mapOf<String, String>()
    var file = args[0]
    val file_open = File(file)
    if(!isFileExists(file_open)){
        println("$file такого файла нету")
    }
    else {
        /*Парсинг содержимого файла преобразование в map*/
        file_open.useLines { lines -> lines.forEach {input =  input + Pair(it.split(" ")[0], it.split(" ")[1])}}
    }
    /*ДАлее идет основная часть программы с обработкой Бд и парсингом запроса*/
    if(args[1] == "put"){
        input += Pair(args[2], args[3])
        val key = args[2]
        val value = args[3]
        File(file).appendText("\n$key $value")
        input += Pair(args[2], args[3])
    }
    if(args[1] == "get"){
        println(input.getOrDefault(args[2], ""))
    }
    if(args[1] == "remove"){
        input -= args[2]
        file_open.writeText("")
        for(i in 0..input.size - 1){
            var key = input.keys.toMutableList()[i]
            var value = input.values.toMutableList()[i]
            if(i == 0){
                file_open.appendText("$key $value")
            }
            else {
                file_open.appendText("\n$key $value")
            }
        }
    }

    println(input)
}

