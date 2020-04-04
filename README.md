# MemoryNotes

Clean Architecture 적용 프로젝트

## 프로젝트 구조

-

## 목차

- [Clean Architecture](#clean-architecture)
- [MVVM pattern](#mvvm-pattern)
- [SOLID principles](#solid-principles)
- [AAC](#aac) (Navigation / Room / ViewModel / LiveData)
- Dependency Injection

---

## Clean architecture

### 소개

#### Uncle Bob (Robert C. Martin)이 말한 Clean Architecture

- A way to organize a project to achieve maintainability and scalability
- One concern per component
- Structured in layers of dependency
- Implementation layers depend on abstract layers
- Is not specific to mobile development

### 구조

![image](https://user-images.githubusercontent.com/29790944/78419058-aedd9180-767c-11ea-9615-f024b02927f0.png)

위 그림에서 각 Layer의 역할이 구분되어 있고, 각 Layer는 하위 Layer에 의존하고, 자신의 상위 Layer에서 하는 일을 모른다.

### 특징

- 역할이 엄격하게 구분되어 있기 때문에 실수할 일이 적다.

    예를 들어, 컴포넌트가 적절한 곳에 있지 않으면 Layer Dependency가 적용되지 않을 것이고, 그렇게 되면 상위 Layer에서 해당 컴포넌트에 접근할 수 없게 된다.

- Business logic과 Presentation logic이 분리되어 있어 사용하고 테스트하기가 쉽다.

- 캡슐화를 통해 Dependencies를 강제할 수 있다.

- Layer마다 역할이 분리되어 있기 때문에 서로 영향을 주지 않고 여러 개발자가 parallel하게 개발할 수 있다.

- 확장성이 높다.

- 이해하기 쉽고, 유지보수가 쉽다.

### 구성 요소

#### Entities

- Domain objects

- Foundational business logic

    business logic 외의 어떤 것도 신경쓰지 않는다.

- POJOs (= Plain Old Java Objects)

    Java나 Kotlin에서 Simple Data 저장 용도 이외에 어떤 작업도 하지 않는 객체

#### Use cases

- Actions that can be taken on the entities

- Depend on entities

- Business logic, plain code

    Business로직 외에 다른 어떤 요소도 신경쓰지 않는다.

- No other dependencies

- A use case doesn't know how the result is going to be used

#### Controllers, presenters, adapters

- Interfaces

    각 controller나 presenter, adapter의 역할 정의

- Retrieve data from various sources

- Present data in a specific format (e.g XML, JSON)

- Depend on lower level layers

#### Infrastructure

- How the data is interpreted and presented

- Most volatile layer, likely to change

    Interface에 변화가 생기면 Infrastructure에도 변화가 생긴다.

- Interacts with interfaces to retrieve data

- UI, Frameworks, devices etc

    안드로이드에서는 Activities, Fragments 등이 여기에 해당된다.

---

## MVVM pattern

---

## SOLID principles

### 5가지 원칙

- **S**ingle responsibility
- **O**pen - closed
- **L**iskov substitution
- **I**nterface segregation
- **D**ependency inversion

#### Single responsibility

- 하나의 클래스 또는 컴포넌트는 하나의 Job만을 가져야한다.
- 클래스에 2가지 작업이 필요한 경우, 클래스를 2개로 나눠야 한다.

#### Open - closed

- open for extension, closed for modification

    새로운 기능이 추가되면 코드 변경이 아닌 extension(상속)으로 해결해야 한다.

- Abstract away stable functionalilty
- Put volatile functionality in extension classes

    변경 가능성이 잦은 기능은 extension class로 만들고, 변경 가능성이 없는 기능은 abstract class로 만들어야 한다.

#### Liskov substitution

- Low level classes can be substituted without affecting higher levels
- Achieved using abstract classes and interfaces

    **리스코프 치환원칙**에 의해 상위 레벨의 클래스는 아무런 속성 변경 없이 하위 레벨의 클래스로 치환할 수 있어야 한다.
    즉, 하위 클래스가 대체되어도 상위 클래스에는 영향이 가지 않으므로 abstract class나 interface를 이용해 안정적인 기능을 만들고 변화가 필요한 클래스들은 extension class로 만드는 것이 좋다.

#### Interface segregation

- Many specific interfaces are better than one generic interface
- An interface only exposes the methods that the dependent class needs not more

    컴포넌트들이 서로 상호작용을 할 때 사용하는 interface는 꼭 필요한 기능만을 가지고 상호작용 하는 것이 좋다.

#### Dependency inversion

- Concrete classes depend on abstract classes not the other way around
- Volatile functionality depends stable functionality
- Framework specific functionality depends on business logic

    상위 모듈에서 하위 모듈을 생성해서 상위 모듈이 하위 모듈에 의존성을 갖는 방식이 아닌, abstract class나 interface를 이용해 하위 모듈을 캡슐화 해서 하위 모듈이 상위 모듈이 정의한 추상 메서드를 구현하는 방식을 이용하라는 것이다.
    이렇게 하면 business logic이 바뀌는 것에 상위 framework가 함께 바뀌지 않아도 된다.

---

## AAC