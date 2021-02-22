## Dealer and Customer Application
In this Repo I'm impelemnting 2 differet applications Dealer app and customer App with the same code base and different build flavors
I'm using the following stack:-
- MVVM based on Clean Architecture
- Hilt for Dagger2
- RxJava
- LiveData
The code consists of 3 main parts (Main,Customer,Dealer)
### Main:
The main directory consists of the base classes and depencies in both of the applications :-

- Presentation :- Which consists of the UI Utils and the ProductView(Which is a custom view used in both apps that represents the product view)
- DI :- In which I provide the main dependencies for both apps (Schedulers for the Rx, Retrofit Object and HttpInterceptors)
- Some Base Classes (BaseActivity, BaseViewModel and Mapper)
 
### Customer App:-

This Application is based on SingleActivity princible with 2 fragments using a SharedViewModel and implementing Clean Architecutre, MVVM, Room Database for later updates and Repository Pattern

### Dealer App :-
This Application is based on Clean Architecutre, MVVM and Repository Pattern and it fetches the orders from the server
