# DiscoverMars

App that will connect to the NASA API and show photos from Mars that was taken by Mars Rover. :)
So we got our first pictures of Mars :)
Now we must do our cleanup and 

What we will use:
Clean Architecture,
Modularization,
ListAdapter,
MVVM,
JetPack Navigation,
Dagger2,
REST API,
Retrofit,
Coil,
OkHttp3,
Room,
RxJava,


To do:
- RxJava implementation
- Work on the UI
- Abstract more by putting fragment switching logic into viewmodel


In Progress:
- Add function to change date when the picture was taken, and camera

Done:

- Set up android-ui, cache, data, domain and remote modules with dependencies
- Dagger2 implementation
- Set up 2 fragments (with listAdapter and description)
- Click on item inside of a list goes to image detail
- ListViewModel call usecase
- Connected to the remote, api works
- Display an list of images (Adapter, List Fragment and ListViewModel)
- Refractor (move PhotoServiceFactory and Remote Module from UI module)
- Connect Image Detail to the database
- Work on the image detail
- Store the data to our local database with Room
- Fixed crash when phone is offline
