# Clean architecture

## 소개

### Uncle Bob (Robert C. Martin)이 말한 Clean Architecture

- A way to organize a project to achieve maintainability and scalability
- One concern per component
- Structured in layers of dependency
- Implementation layers depend on abstract layers
- Is not specific to mobile development

## 구조

![image](https://user-images.githubusercontent.com/29790944/78419058-aedd9180-767c-11ea-9615-f024b02927f0.png)

위 그림에서 각 Layer의 역할이 구분되어 있고, 각 Layer는 하위 Layer에 의존하고, 자신의 상위 Layer에서 하는 일을 모른다.

## 특징

- 역할이 엄격하게 구분되어 있기 때문에 실수할 일이 적다.

    예를 들어, 컴포넌트가 적절한 곳에 있지 않으면 Layer Dependency가 적용되지 않을 것이고, 그렇게 되면 상위 Layer에서 해당 컴포넌트에 접근할 수 없게 된다.

- Business logic과 Presentation logic이 분리되어 있어 사용하고 테스트하기가 쉽다.

- 캡슐화를 통해 Dependencies를 강제할 수 있다.

- Layer마다 역할이 분리되어 있기 때문에 서로 영향을 주지 않고 여러 개발자가 parallel하게 개발할 수 있다.

- 확장성이 높다.

- 이해하기 쉽고, 유지보수가 쉽다.

## 구성 요소

### Entities

- Domain objects

- Foundational business logic

    business logic 외의 어떤 것도 신경쓰지 않는다.

- POJOs (= Plain Old Java Objects)

    Java나 Kotlin에서 Simple Data 저장 용도 이외에 어떤 작업도 하지 않는 객체

### Use cases

- Actions that can be taken on the entities

- Depend on entities

- Business logic, plain code

    Business로직 외에 다른 어떤 요소도 신경쓰지 않는다.

- No other dependencies

- A use case doesn't know how the result is going to be used

### Controllers, presenters, adapters

- Interfaces

    각 controller나 presenter, adapter의 역할 정의

- Retrieve data from various sources

- Present data in a specific format (e.g XML, JSON)

- Depend on lower level layers

### Infrastructure

- How the data is interpreted and presented

- Most volatile layer, likely to change

    Interface에 변화가 생기면 Infrastructure에도 변화가 생긴다.

- Interacts with interfaces to retrieve data

- UI, Frameworks, devices etc

    안드로이드에서는 Activities, Fragments 등이 여기에 해당된다.
