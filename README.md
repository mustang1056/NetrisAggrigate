Необходимо написать код для получения и агрегации данных из нескольких сервисов.

Получение списка доступных видеокамер:</br>
http://www.mocky.io/v2/5c51b9dd3400003252129fb5 Ответ состоит из массива объектов, содержащих поля:</br>
● id​-число,идентификаторкамеры</br>
● sourceDataUrl​-строка,ссылкадляполученияданныхисточника.</br>
● tokenDataUrl​-строка,ссылкадляполучениятокеновбезопасностипокамере.</br>
Формат данных в ответе на запрос на URL из поля ​sourceDataUrl​:</br>
● urlType​-строка,типссылкинавидеопоток.Возможныезначения:​"LIVE",</br>
"ARCHIVE"</br>
● videoUrl​-строка,ссылканавидеопоток</br>
Формат данных в ответе на запрос на URL из поля ​tokenDataUrl​: </br>
● value​-строка,токенбезопасности</br>
● ttl​-число,времяжизнитокена</br>

Тест сервиса:
http://localhost:8081/callable

