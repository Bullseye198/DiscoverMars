# DiscoverMars

App that will connect to the NASA API and show photos from Mars that was taken by Mars Rover. :)
So we got our first pictures of Mars :)

How to open project:
- This project needs Android Studio 4.1 or newer.
In Android Studio, go to Help->Check For Updates->Configure updates... and there check
the box for Automatically check updates for, and switch to Canary Channel, or
Beta Channel, depending on which one you prefer, and update to the newest version.
- Newest Gradle version and newest SDK version are needed
- For SDK go to File->Settings->Appearance&Behavior->System Settings->Android SDK and set the the newest Android API Level, 
and click apply.
- If needed, download the gradle from https://gradle.org/releases/ 
and make sure to set the distribution URL in gradle-wrapper.properties to the one where you put your zip file and then sync.

- Enter the date and select a camera (Date format is: yyyy-mm-dd, for example: 2015-05-05, and press Done) 
- First available images are from the date Mars Rover Curiosity landed, which is: 2012-08-06
- On some days, Rover doesn't take pictures with certain cameras, so try with selecting some other cameras.
- The app will be developed further, so have fun :)

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
- [ ] RxJava implementation
- [ ] Work on the UI
- [ ] Abstract more by putting fragment switching logic into viewmodel


In Progress:


Done:

- [x] Set up android-ui, cache, data, domain and remote modules with dependencies
- [x] Dagger2 implementation
- [x] Set up 2 fragments (with listAdapter and description)
- [x] Click on item inside of a list goes to image detail
- [x] ListViewModel call usecase
- [x] Connected to the remote, api works
- [x] Display an list of images (Adapter, List Fragment and ListViewModel)
- [x] Refractor (move PhotoServiceFactory and Remote Module from UI module)
- [x] Connect Image Detail to the database
- [x] Work on the image detail
- [x] Store the data to our local database with Room
- [x] Fixed crash when phone is offline
- [x] Added function to change the camera
- [x] Add function to change date when the picture was taken
