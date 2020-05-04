# DiscoverMars

App that will connect to the NASA API and show photos from Mars that was taken by Mars Rover. :)
So we got our first pictures of Mars :)
Now we must do our cleanup and 

What we will use:
JetPack Navigation,
REST API,
Retrofit,
Dagger2,
Coil,
OkHttp3,
Room,
Clean Architecture,
MVVM,
ListAdapter,


To do:

- Work on the UI
- Abstract more by putting fragment switching logic into viewmodel


In Progress:
- Store the data to our local database

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
