# Event_Reminder

Platform: Android 
Language: Kotlin

Description: 
An application for storing birthdays and sending reminder notifications when a stored birthday is today/coming up.
The home screen displays the list of birthdays that the user has entered, as well as a floating action button. When 
the floating action button is pressed, the app navigates to the form screen, where the user enters values for a new
birthday (name, birth date, optional phone number). The user then presses the Add Reminder button to add the new 
birthday to the list on the home screen. 


Done:
  -created layouts for home and form fragments
  -added fragments to improve navigation performance 
  -added data binding objects to improve performance
  -created SQlite database to store birthdays
  -form submit button inserts default values into database
  -home TextView displays contents of database as formatted list
  -added animations for fragment view changes
     
To do:
  -update database insert to use values in form text inputs
  -update birthday list to use RecycleView to improve performance
  -implement reminder notifications 
  -create settings menu layout
  -save user settings with sharedPreferences
