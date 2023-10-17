import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
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

/*Функция хеширует список байтов. Само идея хеширования нам пригодится, чтобы избавиться от коллизий, а так же обеспечить безопасть, ввиду необратимости хеш функции
А так же дает допущение произвольных символов в ключе.
 */
fun ByteArray.toHex() = joinToString(separator = "") { byte -> "%02x".format(byte) }
fun main(args: Array<String>) {
    /*Процесс шифроки строки dbname + key*/
    val binaryArrDb = args[0].toByteArray()/*Хешируем название стркои и базы даных*/
    val binaryArrKey = args[2].toByteArray()
    val hasName = (binaryArrDb +  binaryArrKey).toHex()
    var file = File(hasName)
    /*Далее идет основная часть программы с обработкой Бд и парсингом запроса*/
    if (args[1] == "put") {
        /*Функция writeText создаст файл с именем file, если его не было, и запишет в него args[3](наше value), это рабоатет за o(1) - время обращение к файлу в каталоге с проектом.*/
        /*Тут я не стал вызывать исключение, если уже есть в dbname файлик с key, поскольку функция writeText просто перезапишет содержимое*/
        file.writeText(args[3])
    }
    /*Обработка "get"*/
    /*Тут мы сделали обработку исключения при ошибке ввода или вывода, например, если файла не существует программа не упадет*/
    else if(args[1] == "get"){
        try {
            val lines = Files.readAllLines(Paths.get(hasName))
            println(lines[0])
        } catch (e: IOException) {
            println("Такого файла нет!")
        }
    }
    /*Аналогично делаем удаление файла с обработкой исключения*/
    else if(args[1] == "remove"){
        try{
            file.delete()
        }
        catch (e: IOException) {
            println("Такого файла нет!")
        }
    }
}
