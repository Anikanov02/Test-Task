К сожалению, из-за отключений света выпало много времени, и многие момента можно было бы
доработать и сделать более красиво, но, тем не менее, я решил себе усложнить задачу следующими
дополнительными условиями:
    1. Я решил использовать стандартный REST-сервер вместо GWT RPC, чтобы проверить
        возможности интеграции гвт-фронтенда с обычным REST-api, такой подход расширяет возможности
        последующего масштабирования. Имея универсальный REST-сервер, можно в дальнейшем
        его использовать для доступа не только с гвт-фронтенда.

        Попутно решил много интересных проблем, и не пожалел, что пошел более сложным путем)

    2. Модули бекенда и фронтенда сделал независимыми, чтобы можно было запускать и управлять ими отдельно

    3. Добавил авторизацию/регистрацию на Spring Security
