## PowerMock

기존 Mockito의 단점

-  간단한 기능의 유닛 테스트에는 충분하지만 코드 구조가 복잡할 경우 테스트하기에 힘듦
- 테스트를 위해서 좋은 코드 구조를 포기해야만 하는 경우가 존재

PowerMock는 기존 Mockito의 단점을 보완한다.



## PowerMock 시작하기

PowerMock은 두개의 확장 API로 구성된다.

- EasyMOck
- Mockito

PowerMock을 사용하려면 위의 두 테스트 프레임워크 중 하나에 의존해야한다.

또한 PowerMocke은 TestNG를 지원한다 (둘 다 자바의 유닛테스트 프레임워크다)

PowerMock을 사용하기 전 지원하는 버전을 확인하여 사용해야 한다.

https://github.com/powermock/powermock/wiki/Mockito#supported-versions



## 캡슐화 우회하기

WiteBox Class는 필요한 경우 캡슐화를 우회하는데 도움이 되는 메서드를 제공한다.

일반적으로는 private  필드를 가져오거나 수정하는 것은 좋지 않지만 때로는 리펙토링을 테스트하여 코드를 다루는 방법일 수도 있다.

1. Whitebox.setInternalState(..) 인스턴스 또는 클래스의 비공개 멤버를 설정하는데 사용
2. Whitebox.getInternalState(..) 인스턴스 나 클래스의 비공개 멤버를 얻는데 사용
3. Whitebox.invokeMethod(..) 인스턴스 또는 클래스의 비공개 메서드를 호출하는데 사용
4. Whitebox.invokeConstructor(..) 개인 생성자로 클래스의 인스턴스를 만드는데 사용





