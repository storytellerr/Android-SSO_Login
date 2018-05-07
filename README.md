# Android-SSO_Login
A Sign in platform with android app this app is completely build with Kotlin :muscle: and Kotlin-KTX library.
however the demo app is build with java. feel free to create a pull request.

## how to Use
1. first start a new activity with **startActivityForResult** and putExtra name of your app with key **"Name"** also set type
    **text/plain**.
```
 Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra("Name","DEMO APP ");  //this name will be shown on Sign-in platform
        shareIntent.setType("text/plain");
        startActivityForResult(shareIntent,PICK_CONTACT_REQUEST);
        
```
  
2. then override method **onActivityResult** you will get all data with getStringsExtra()

```
  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_CONTACT_REQUEST ) {
            if(resultCode == Activity.RESULT_OK) {
                var name = data.getStringExtra("name")
                var email = data.getStringExtra("name")
                var age = data.getStringExtra("name")
            }
        }
```        
## Use app
  sign in to firebase and add google-services.json file to app directory and start Android-SSO app.
  
## Screenshots
   SSO app

<img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-38-21.png" width="148px"> <img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-39-13.png" width="148px">  <img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-40-04.png" width="148px"> 

Demo app

<img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-40-23.png" width="148px"> <img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-40-32.png" width="148px">  <img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-40-44.png" width="148px">
<img src="https://github.com/storytellerr/Android-SSO_Login/blob/master/Screenshots/Screenshot_2018-05-07-23-40-52.png" width="148px"> 
     
