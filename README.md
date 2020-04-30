# DiscoverMars

App that connects to the NASA API and shows photos from Mars that was taken by Mars Rover. :)

What we will use:
JetPack Navigation,
REST API,
Retrofit,
Dagger2,
Clean Architecture,
MVVM,
ListAdapter,


To do:
- Display an list of images (Adapter, List Fragment and ListViewModel)
- Refractor
- Store the data to our local database
- Abstract more by putting fragment switching logic into viewmodel


In Progress:
- Connect it to the remote to see if api works


Done:

- Set up android-ui, cache, data, domain and remote modules with dependencies
- Dagger2 implementation
- Set up 2 fragments (with listAdapter and description)
- Click on item inside of a list goes to image detail
- ListViewModel call usecase
