# SNSApp (SNS앱)

Fastcampus **Android 의존성 주입 완전정복** 강의 실습을 기반으로,  
Compose + 멀티모듈 + Clean Architecture(MVI) 구조로 개발한 SNS 앱 프로젝트입니다.


## 아키텍처 & 설계

### Clean Architecture + MVI
- Presentation / Domain / Data 레이어 분리
- 단방향 데이터 흐름(상태 관리 기반)
- Orbit 라이브러리 기반 MVI 적용

### Multi Module Project
- `app`
- `domain` : UseCase, Model
- `data` : UseCaseImpl, DataStore, DataBase, Network, DI
- `presentation` : Hilt-Navigation-Compose, ViewModel, Activity


## 기술 스택

### DI
- **Hilt**

### Network
- **Retrofit**
- **OkHttp**

### UI
- **Jetpack Compose**
- Coil 
- Compose RichEditor 

### Local Data / Storage
- **Room**
- **Paging 3**
- **DataStore**

### Android Components
- **LifecycleService**
- **BroadcastReceiver**

### MVI / State Management
- **Orbit MVI**
