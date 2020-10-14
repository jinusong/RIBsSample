# RIBsSample
## 만든 이유
걍 KMM보다 RIBs 생각나서 만들어보는 Sample, Compose, Hilt도 함 써볼까?

## 정말 볼품없는 UI
**귀찮아서 급식화면만 만들었습니다.**

![image](image/MealScreen.png)


## Hilt 적용 못한 이유
* uber가 제공하는 ribs 라이브러리를 사용하면 각 ```Builder``` 클래스에서 dagger Component를 만들어서 di를 하는 구조입니다.

* 그러니 Custom Component를 만들어야 했는데 dagger에서는 그냥 ```@Component``` 를 붙이면 되지만 Hilt에서는 ```@DefineComponent``` 로 구현해야 합니다.

* 여기서 문제가 발생합니다. ```@DefineComponent``` 는 상속이 불가능하다고 합니다.

* 그런데 uber가 제공하는 ribs 라이브러리를 활용해서 RIBs를 구현하려면 ```InteractorBaseComponent<Interactor>``` 을  상속해야 했습니다.

* 그래서 Hilt 사용을 못 했습니다...

* 아마 제가 아직 Hilt에 대한 지식이 부족해서 그런 것 같은데 아시는 분있으면 풀리퀘 부탁드립니다!

## Compose 적용 못한 이유
* uber가 제공하는 튜토리얼에서는 single activity를 지향하고 ribs 라이브러리 또한 이 구조를 위해 디자인된 것 같습니다. (그냥 제 생각입니다 ㅎㅎ)

* 그래서 튜토리얼을 따르기 위해 이 방식을 따르기로 하였고, Custom View를 ```addView``` 하는 형태로 구현되기 때문에 ```AbstractComposeView``` 를 활용해 Custom ```ComposeView``` 를 만들었습니다.

* 하지만 ```ComposeView``` 는 ```ViewTreeLifecycleOwner``` 가 인지하지 못해 발생하는 듯한 window에 해당 View를 띄울 수 없고, ```ViewTreeLifecycleOwner.set()``` 을 호출하거나 ```FragmentActivity```, ```ComponentActivity``` 를 사용하라는 에러가 발생했습니다.

* 그래서 기존 방식에 Compose 사용을 제외하고 구현하였더니 정상 작동하였습니다.

* 이 부분도 제가 개발 실력이 부족해서 근본적인 해결방법을 찾지 못한 것 같습니다. 

* 역시 이 것도 아시는 분은 풀리퀘 부탁드립니다!

## 후기
* 아쉽당
