# read-files-testtask


Приложение выполняет слияние нескольких текстовых файлов в один.

При разработке использован OracleOpenJDK 17.0.1, java 11, Apache Maven 4.0.0.

Преобразование .jar в .exe выполняется при помощи плагина launch4j
```
<groupId>com.akathist.maven.plugins.launch4j</groupId>
<artifactId>launch4j-maven-plugin</artifactId>
```

# Installation and Getting Started

1. Разархивировать папку проекта
2. Скопировать входные файлы в папку src/main/resources
3. Открыть проект в Intellij IDEA
4. Проверить наличие JDK и версию java
5. В терминале (или в соответствующем окне справа) выполнить команду mvn clean package
6. Файл sort-it.exe появится в корневой папке проекта
7. Запустить данный файл из командной строки с соответствующими параметрами
8. Выходной файл появится в корневой папке проекта

NOTE: При замене входных файлов необходимо заново выполнить команду mvn clean package
