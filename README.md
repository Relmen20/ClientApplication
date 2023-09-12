# ClientApplication

Приложение запускается через команду `sh clientScript.sh`, в папке проекта создается папка out.

При запуске, необходимо изменить порт на порт выставленный в `serverScript.sh`.

Порт изменяется в файле `clientScript.sh` на 3 строчке `java -cp ./out/ Main <NEW_PORT>`.
