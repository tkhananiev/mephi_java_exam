# Проект коротких ссылок

## Доступные команды:

`create <originalUrl> <limit>` - создание новой ссылки и нового пользователя.  
`create <uuid> <originalUrl> <limit>` - создание новой ссылки для существующего пользователя.  
`edit <uuid> <shortUrl> <newOriginalUrl>` - редактирование ссылки.  
`goto <shortUrl>` - переход в браузере по ссылке.  
`list <uuid>` - cписок ссылок пользователя.  
`exit` - завершение программы.

## Список параметров:

`originalUrl` - это оригинальная ссылка  
`limit` - заданный пользователем лимит переходов по короткой ссылке  
`uuid` - UUID пользователя (выдается пользователю при создании, сохраняется пользователем для аутентификации и авторизации)  
`shortUrl` - сгенерированная короткая ссылка

Программа запускается с создания нового пользователя и новой короткой ссылки командой create, 
куда надо передать в качестве параметров оригинальную ссылку и желаемый лимит переходов по ней.
В результате выполнения этой команды будет сгенерирован новый UUID польователя и короткая ссылка.
По этой ссылке можно перейти командой goto, а также отредактировать ее командой edit с использованием UUID,
короткой ссылки и ее нового значения. Создать новую ссылку для уже сществующего пользователя можно командой
create с передачей ей пользователем в качестве параметров своего UUID, оригинальной ссылки и лимита переходов.
Список всех ссылок пользователя можно посмотреть командой list с передачей ей UUID пользователя 
в качестве параметра.