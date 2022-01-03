# Memory Notes

SOLID 원칙과 MVVM을 이용한 Clean Architecture를 구현한 안드로이드 애플리케이션.

&nbsp;

## 프로젝트 구조

### Project Layer

<img alt="project_structure_1" src="https://user-images.githubusercontent.com/29790944/78422806-1c011f00-769d-11ea-9fc4-0164382b24c3.png" width="500">

### Project Structure

![project_structure_2](https://user-images.githubusercontent.com/29790944/78422809-23282d00-769d-11ea-856e-f145c4e73cb5.png)

&nbsp;

## 목차

- [Clean Architecture](#clean-architecture)
- [MVVM pattern](#mvvm-pattern)
- [SOLID principles](#solid-principles)
- [AAC](#aac) (Navigation / Room / ViewModel / LiveData)

&nbsp;

## Clean architecture

### 소개

#### Uncle Bob (Robert C. Martin)이 말한 Clean Architecture

- A way to organize a project to achieve maintainability and scalability
- One concern per component
- Structured in layers of dependency
- Implementation layers depend on abstract layers
- Is not specific to mobile development

&nbsp;

### 구조

![image](https://user-images.githubusercontent.com/29790944/78419058-aedd9180-767c-11ea-9615-f024b02927f0.png)

위 그림에서 각 Layer의 역할이 구분되어 있고, 각 Layer는 하위 Layer에 의존하고, 자신의 상위 Layer에서 하는 일을 모른다.

&nbsp;

### 특징

- 역할이 엄격하게 구분되어 있기 때문에 실수할 일이 적다.

    예를 들어, 컴포넌트가 적절한 곳에 있지 않으면 Layer Dependency가 적용되지 않을 것이고, 그렇게 되면 상위 Layer에서 해당 컴포넌트에 접근할 수 없게 된다.

- Business logic과 Presentation logic이 분리되어 있어 사용하고 테스트하기가 쉽다.

- 캡슐화를 통해 Dependencies를 강제할 수 있다.

- Layer마다 역할이 분리되어 있기 때문에 서로 영향을 주지 않고 여러 개발자가 parallel하게 개발할 수 있다.

- 확장성이 높다.

- 이해하기 쉽고, 유지보수가 쉽다.

&nbsp;

### 구성 요소

#### Entities

- Domain objects

- Foundational business logic

    business logic 외의 어떤 것도 신경쓰지 않는다.

- POJOs (= Plain Old Java Objects)

    Java에서 Simple Data 저장 용도 이외에 어떤 작업도 하지 않는 객체로, Kotlin에서 data class에 해당한다.

#### Use cases

- Actions that can be taken on the entities

- Depend on entities

- Business logic, plain code

    Business로직 외에 다른 어떤 요소도 신경쓰지 않는다.

- No other dependencies

- A use case doesn't know how the result is going to be used

#### Controllers, presenters, adapters

- Interfaces

    각 controller나 presenter, adapter의 역할을 정의한다.

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

&nbsp;

## MVVM pattern

### Model-View-ViewModel의 구조

- **Model** - business logic과 data를 담당한다.
- **View** - UI를 담당한다.
- **ViewModel** - Model과 View를 연결하는 다리 역할로 **async callback**으로 동작하는 것이 특징이다.

&nbsp;

### 동작 과정

![MVVM](https://user-images.githubusercontent.com/29790944/78423335-94b5aa80-76a0-11ea-98fa-46abf6d589be.png)

ViewModel과 Model의 통신은 UI가 block되는 것을 막기 위해 background thread에서 동작하도록 강제한다.

&nbsp;

### 장점

- Google에서 지원하는 기능으로 Android Jetpack의 구성 요소로 들어 있다.
- ViewModel을 사용하면 Activity/Fragment lifecycle과 잘 맞물려서 돌아간다.
- LiveData는 View와 async callback으로 communication 한다.

&nbsp;

## SOLID principles

### 5가지 원칙

- **S**ingle responsibility
- **O**pen - closed
- **L**iskov substitution
- **I**nterface segregation
- **D**ependency inversion

&nbsp;

#### 1. Single responsibility

- 하나의 클래스 또는 컴포넌트는 하나의 Job만을 가져야한다.
- 클래스에 2가지 작업이 필요한 경우, 클래스를 2개로 나눠야 한다.


#### 2. Open - closed

- open for extension, closed for modification

    새로운 기능이 추가되면 코드 변경이 아닌 extension(상속)으로 해결해야 한다.

- Abstract away stable functionalilty
- Put volatile functionality in extension classes

    변경 가능성이 잦은 기능은 extension class로 만들고, 변경 가능성이 없는 기능은 abstract class로 만들어야 한다.

#### 3. Liskov substitution

- Low level classes can be substituted without affecting higher levels
- Achieved using abstract classes and interfaces

    **리스코프 치환원칙**에 의해 상위 레벨의 클래스는 아무런 속성 변경 없이 하위 레벨의 클래스로 치환할 수 있어야 한다.
    즉, 하위 클래스가 대체되어도 상위 클래스에는 영향이 가지 않으므로 abstract class나 interface를 이용해 안정적인 기능을 만들고 변화가 필요한 클래스들은 extension class로 만드는 것이 좋다.

#### 4. Interface segregation

- Many specific interfaces are better than one generic interface
- An interface only exposes the methods that the dependent class needs not more

    컴포넌트들이 서로 상호작용을 할 때 사용하는 interface는 꼭 필요한 기능만을 가지고 상호작용 하는 것이 좋다.

#### 5. Dependency inversion

- Concrete classes depend on abstract classes not the other way around
- Volatile functionality depends stable functionality
- Framework specific functionality depends on business logic

    상위 모듈에서 하위 모듈을 생성해서 상위 모듈이 하위 모듈에 의존성을 갖는 방식이 아닌, abstract class나 interface를 이용해 하위 모듈을 캡슐화 해서 하위 모듈이 상위 모듈이 정의한 추상 메서드를 구현하는 방식을 이용하라는 것이다.
    이렇게 하면 business logic이 바뀌는 것에 상위 framework가 함께 바뀌지 않아도 된다.

&nbsp;

## AAC

AAC는 Android Architecture Component의 약자로, Android Jetpack에 포함되어 있다.
그 중 아래의 4가지 라이브러리를 프로젝트에 적용했다.

- [Navigation](#navigation)
- [Room](#room)
- [ViewModel](#viewmodel)
- [LiveData](#livedata)

&nbsp;

### Navigation

- Handles user app journey

    사용자의 app flow를 쉽게 조작할 수 있다.

- Removes complexity for moving from one screen to another
- Handles complex cases like:

  - Bottom tabbed navigation
  - App drawers

- Generated classes

    Visual Editor를 사용해 screen을 연결 해 주고 전달할 parameter를 정의해 주는 작업으로 Graph를 만들 수 있는데, Graph를 만들기만 하면 알아서 그에 맞는 class가 생성된다.

&nbsp;

#### 장점

- Handles fragment transactions
- Handles back and up actions
- Manages the backstack

    fragment 사용 시 transaction이나 lifecycle 등의 복잡한 작업을 신경써야 하고, 필요에 의해 fragment backstack을 관리해야 하는 경우도 있다.
    Navigation은 이 작업을 알아서 처리하기 때문에 개발자는 이 작업을 신경쓰지 않아도 된다.

- Argument passing

    fragment끼리 전달되는 argument를 navigation 컴포넌트가 관리하도록 할 수 있다.

- Transition animations

    화면이 넘어가는 애니메이션을 navigation을 이용해 쉽게 변경할 수 있다.

- Deep linking

    web url을 이용한 deep linking은 intent-filter를 이용해 구현했어야 했는데, navigation에서는 `<deep link>` 태그를 이용해 uri를 지정할 수 있다.

&nbsp;

#### 구성 요소

Navigation은 다음 3가지로 구성된다.

1. Navigation Graph

    화면이동에 대한 모든정보(moving action, parameter, 화면 단위)를 정의하는 곳이다.

    Graph를 통해 app flow를 시각화 해서 한 눈에 볼 수 있다.

2. Nav Host Fragment

    NavHost는 Navigation Graph에 정의된 화면들을 보여주는 컨테이너의 역할을 한다.

    화면이동에 대한 액션은 모두 Nav Host Fragment에게 위임된다.

    NavHost에는 Nav Controller도 포함한다.

3. Nav Controller

    NavController는 화면이동에 대한 컨트롤러 역할을 한다.

![image](https://user-images.githubusercontent.com/29790944/78469317-f20e3200-775a-11ea-93a9-852dce3d661c.png)

&nbsp;

### Room

Room은 SQL의 abstraction layer로, 기본적으로 단말에 데이터를 저장할 수 있다.

&nbsp;

#### 장점

- SQL의 상위 레벨이기 때문에 Room 라이브러리를 이용하면 connection을 신경쓰지 않아도 되고, 직접 테이블에 데이터를 넣고 빼는 작업을 하지 않는다.
- 데이터베이스에 데이터를 쉽게 저장할 수 있다.
- 데이터베이스나 테이블을 직접 만들지 않아도 알아서 생성해 준다.
- SQL 명령어를 컴파일 시에 체크하기 때문에 문법 오류를 방지할 수 있다.

&nbsp;

#### Set Up

@Entity

- 데이터베이스에 저장하고 싶은 정보를 담고 있는 객체이다.
- Room 라이브러리는 나중에 이 객체를 테이블로 만들어준다.

@Dao

- DAO는 Data Access Object의 약자로 여기서 Entity를 관리한다.
- Entity를 관리할 작업을 정의한 interface이다.

@Database

- Database, Entity를 생성하고, Dao를 구현한 객체를 가지고 있다.

&nbsp;

#### Queries

컴파일 타임에 문법을 체크하는 queries

- @Insert
- @Update
- @Delete
- @Query

컴파일 타임에 문법을 체크하지 않는 queries

- @RawQuery

&nbsp;

#### 제공하는 기능

- Database migration functionality

    Room에서는 데이터베이스 버전이 바뀌었을 때, 이전 버전의 데이터베이스에서 데이터 손실 없이 새로운 버전의 데이터베이스로 변경하는 기능을 제공한다.
    
- Unit testing functionality
- Database access on background thread

    데이터베이스 작업은 IO 작업으로 main thread에서 작업 시 UI가 blocking될 수 있기 때문에 Room에서는 Database를 background thread에서 접근하도록 강제한다.

&nbsp;

### ViewModel

- UI와 관련된 데이터를 저장하는 클래스로, View와 Model을 연결하는 다리 역할을 한다.

- ViewModel도 lifecycle을 갖고 있고, Android System에서 관리한다.
  - ViewModel 개체의 lifecycle은 ViewModel을 가져올 때 ViewModelProvider에 전달 되는 Lifecycle로 지정된다.
  - ViewModel은 lifecycle이 지정된 Lifecycle이 Destroy될 때까지 메모리에 남아있다가 종료된다.

- Activity context에 의존하지 않아도 된다.
  - activity context와 별개로 필요할 때 create하고 필요하지 않을 때 destroy할 수 있다.
  - 필요한 경우 applicatoin context에 의존해서 사용할 수 있다.

&nbsp;

### LiveData

- Observable Data이다.
  - lifecycle owner가 active한 상태일 때, LiveData의 데이터가 변경되면 정의한 콜백이 실행된다.

- Observe하는 Activity나 Fragment의 Lifecycle을 알 수 있다.
  - owner가 destroy되면 안전하게 LiveData도 함께 사라지기 때문에 Memory Leak도 발생하지 않는다.
  - owner가 active상태가 되면 알아서 LiveData의 최근 데이터를 가져오기 때문에 항상 Data가 최신의 상태로 보여진다.

- 화면 구성이 바뀌는 것을 관리할 수 있다.
  - 예를 들어, 화면이 회전될 때 Activity나 Fragment는 메모리에서 지우고 다시 생성되기 때문에 onCreate가 다시 호출되면서 이전 화면에서 보던 데이터가 모두 사라지게 된다.
  - 하지만 LiveData를 ViewModel에서 사용하면 ViewModel은 Activity가 완전히 Destroy되어도 사라지지 않고 해당 Activity가 다시 생성될 때 reattach 되기 때문에 이전 데이터가 사라질 걱정을 하지 않아도 된다.
