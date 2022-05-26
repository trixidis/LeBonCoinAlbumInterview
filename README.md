# Welcome to LeBonCoin technical test ! 


# Architecture

![case1](https://github.com/trixidis/LeBonCoinAlbumInterview/blob/dev/demos/architecture.png)


#  Technical Stack

- navigation with safe-args navigation and navigation controller
- Hilt 
- Glide 
- ViewModel 
- Room
- Data Binding
- Coroutines
- Flow and StateFlow
- Retrofit
- MockWebServer
- Serialization
- Multi modules concept

## But, what does the app ? 

This app lists the different albums present in the static API provided by LeBonCoin in a screen with their image and id and on the click of a cardView the user navigates to another fragment that will provide a view listing the titles present in this album with for each one of them their image, id and title.


## Tests :
Layers 
 - [x] DataSource
	 - [x] Remote 
	 - [x] Local 
 - [x] Domain
 - [ ] Presentation (problem testing the StateFlow in the ViewModel)
- [x]  App


# Demo
 Here is the main process where the user has access a to a good network and starts the app for the first time


![case1](https://github.com/trixidis/LeBonCoinAlbumInterview/blob/dev/demos/process%20main.gif )

User has no network and opens app for first time

![case2](https://github.com/trixidis/LeBonCoinAlbumInterview/blob/dev/demos/no%20network%20first%20time.gif)

User has no network but has already loaded data another time

![case3](https://github.com/trixidis/LeBonCoinAlbumInterview/blob/dev/demos/network%20second%20time.gif)

User has network and opens app not for the first time

![case4](https://github.com/trixidis/LeBonCoinAlbumInterview/blob/dev/demos/no%20network%20second%20time.gif)


# What I could have done better 

Because I used Flow for the first time I encountered some difficulties as testing a stateflow ( the one present int he AlbumsViewModel) and lost too much time on it . 

I could have done a better system for getting the network state such as a Stateflow whose reponsibility would have been to emit on a change of network an event and so I could have react in real time.

Icould have used a SharedFlow to emit errors as its only fired when the producer emits instead of the stateflow which will reemit the latest element when someone subscribes on it.


# Last things I want to share

I know and like to work with a team and love to learn new things such as new ways to improve the code Im producing. Working for LeBonCoin would be such an accomplishment for me .
