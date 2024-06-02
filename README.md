DEMO on How to Use API:

1. Use mocky to test REST API

2. Manifest:</br>
   ```<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>``` </br>
   ``` <uses-permission android:name="android.permission.INTERNET"/>```

Inside application:
	android:usesCleartextTraffic="true"


3. In Main:	

private inner class CallAPILoginAsyncTask():AsyncTask<Any,Void,String>(){ </br>
          override fun doInBackground(vararg p0: Any?): String {  </br>
              TODO("Not yet implemented")  </br>
          }  </br>
      }  </br>





4. Extend the function in 3 as: onPreExecute and onPostExecute

5. Learn to extend onBackground:
	Pass URL</br>
	Get result</br>
	Read result	</br>
	String Builder</br>
	Handle exception</br>


6. How to parse json objects manually 	

7. How to parse json objects using 3rd party -> GSON:
	-Create the data class</br>
	-Parse directly</br>
