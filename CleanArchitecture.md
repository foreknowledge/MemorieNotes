## Clean architecture의 의미
### Uncle Bob (Robert C. Martin)이 말한 Clean Architecture
- A way to organize a project to achieve maintainability and scalability
- One concern per component
- Structured in layers of dependency
- Implementation layers depend on abstract layers
- Is not specific to mobile development


## Clean Architecture의 일반적인 구조
![image](https://user-images.githubusercontent.com/29790944/78419058-aedd9180-767c-11ea-9615-f024b02927f0.png)

위 그림에서 각 Layer의 역할이 구분되어 있고, 각 Layer는 하위 Layer에 의존하고, 자신의 상위 Layer에서 하는 일을 모른다.


## Clean Architecture 특징
- 역할이 엄격하게 구분되어 있기 때문에 실수할 일이 적다.
  - 예를 들어, 컴포넌트가 적절한 곳에 있지 않으면 Layer Dependency가 적용되지 않을 것이고, 그렇게 되면 상위 Layer에서 해당 컴포넌트에 접근할 수 없게 된다.
- Business logic과 Presentation logic이 분리되어 있어 사용하고 테스트하기가 쉽다.
- 캡슐화를 통해 Dependencies를 강제할 수 있다.
- Layer마다 역할이 분리되어 있기 때문에 서로 영향을 주지 않고 여러 개발자가 parallel하게 개발할 수 있다.
- 확장성이 높다.
- 이해하기 쉽고, 유지보수가 쉽다.
