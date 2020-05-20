# DiscoverMars

App that will connect to the NASA API and show photos from Mars that was taken by Mars Rover. :)
So we got our first pictures of Mars :)
Our App starts by showing the most recent Images taken by Mars Rover Curiosity.

![Image description](https://image.futurezone.at/images/cfs_landscape_616w_347h/3876098/pia23378.jpg)

How to open project:
- This project needs Android Studio  4.0 RC 1 or newer.
- If you have problems building the project:
- We use Gradle-6.1.1
In Android Studio, go to Help->Check For Updates->Configure updates... and there check
the box for Automatically check updates for, and switch to Beta Channel, or
Canary Channel, depending on which one you prefer, and update to the newest version.
- Newest Gradle version and newest SDK version are needed
- For SDK go to File->Settings->Appearance&Behavior->System Settings->Android SDK and set the the newest Android API Level, 
and click apply.
- If needed, download the gradle from https://gradle.org/releases/ 
and make sure to set the distribution URL in gradle-wrapper.properties to the one where you put your zip file and then sync.

- - Enter the date and select a camera (Date format is: yyyy-mm-dd, for example: 2020-02-26, and press Done) 
- - First available images are from the date Mars Rover Curiosity landed, which is: 2012-08-06
- - On some days, Rover doesn't take pictures with certain cameras, so try with selecting some other cameras.
- - The app will be developed further, so have fun :)

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
- [ ] Add Image Swiping in Image Detail Fragment
- [ ] Abstract more by putting fragment switching logic into viewmodel
- [ ] Split up UI by creating Mars-Ui Module
- [ ] Add string "No available pictures from this date"
- [ ] Add option to go to first available pictures if there is none on selected date


In Progress:
- [ ] Datepicker always shows the last picked date

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
- [x] Always show latest images from Mars Rover on app start
- [x] Filter out cameras which are not used on a selected date
- [x] Added Date Picker
- [x] Add progress bar
- [x] Work on the UI
- [x] Added some Color Palette
- [x] RxJava implementation
- [x] Add 2 more Rovers
