# WorkTask Autotest

Проект автотестов для системы отслеживания задач WT.

## Содержание
- [Технологии](#технологии)
- [Предварительные требования](#предварительные-требования)
- [Установка](#установка)
- [Конфигурация](#конфигурация)
- [Запуск тестов](#запуск-тестов)
- [Генерация отчетов](#отчеты-allure)
- [Структура проекта](#структура-проекта)

## Технологии
- Java 21
- TestNG
- RestAssured
- Allure
- log4j
- lombok
- MySQL Database
- Gradle

## Установка
1. Клонировать репозиторий:
   ```bash
   git clone https://github.com/WorkTechDevelop/QA.git
   
2. Перейти в директорию проекта

3. Установить зависимости:
    ```bash
   ./gradlew build

#### Структура проекта
```bash
.                                             #
├── src                                       #
│   ├── main                                  #
│   │   ├── java                              #
│   │   │   └───├── DataBaseManageService     #       
│   │   │       ├── enums                     #
│   │   │       ├── worktech                  #
│   │   │       ├── testDataGenerator         #
│   │   └── resources                         #
│   │       └── sql_query                     #
│   │       └── project_config.property       #
│   └── test                                  #
│       ├── java                              #
│       │   └── tests                         #
│       │       ├── AuthorizatonTests         #
│       │       ├── CreateTaskTests.java      #
│       │       ├── RegistrationTests.java    #
│       │       └── UpdateTaskTests.java      #
│       └── resources                         #
│           ├──── META-INF.services           #
│           │     ├── ITestNGListener         #           
│           └──── testng.xml                  #
├── .gitignore                                #
├── .gitlab-ci.yml                            #
├── Dockerfile                                #
├── log4j2.xml                                #
├── lombok.config                             #
├── pom.xml                                   #
├── README.md                                 #
└── settings.xml                              #
```

### Переменные окружения
- `Stand` - тестовое окружение

### Клонирование репозитория

### Запуск тестов

### Примечания
