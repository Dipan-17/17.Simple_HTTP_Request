package com.example.simplehttprequest

import android.app.Dialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CallAPILoginAsyncTask().execute()
    }

    private inner class CallAPILoginAsyncTask():AsyncTask<Any,Void,String>(){

        private lateinit var customProgressDialog: Dialog


        //this function contains everything that needs to be done before the background task is executed
        override fun onPreExecute() {
            super.onPreExecute()
            showProgressDialog()

        }

        override fun doInBackground(vararg params: Any?): String {
            var result: String
            var connection: HttpURLConnection? = null

            //if connection ok
            try {
                val url = URL("https://run.mocky.io/v3/e202c0e7-9f2b-4f35-a058-b9b295f0446e")
                connection = url.openConnection() as HttpURLConnection
                //get data
                connection.doInput = true
                //send data
                connection.doOutput = true

                val httpResult: Int = connection.responseCode

                //if result is ok
                if (httpResult == HttpURLConnection.HTTP_OK) {
                    //read the data
                    val inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))

                    val stringBuilder=StringBuilder()//whatever we get as output, build a string out of it
                    var line:String?

                    //try to read the result
                    try{
                        while (reader.readLine().also { line = it } != null) {
                            stringBuilder.append(line + "\n")
                        }
                    }
                    catch (e:IOException){
                        e.printStackTrace()
                        result = "Error"
                    }
                    finally {
                        try {
                            inputStream.close()
                        }catch (e:IOException){
                            e.printStackTrace()
                        }
                    }

                    result=stringBuilder.toString()
                }
                //if result not ok
                else{
                    result=connection.responseMessage
                }


            }
            //connection time out
            catch (e: SocketTimeoutException) {
                e.printStackTrace()
                result = "Connection Timeout"
            }
            //general exception
            catch (e:Exception){
                result="Error " + e.message
            }finally {
                connection?.disconnect()
            }

            return result
        }

        //contains the code that needs to be executed after the background task is completed
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            cancelProgressDialog()

            Log.i("JSON Response", result.toString())
        }



        private fun showProgressDialog(){
            customProgressDialog = Dialog(this@MainActivity)
            customProgressDialog.setContentView(R.layout.dialog_custom_progress)
            customProgressDialog.show()
        }
        private fun cancelProgressDialog(){
           customProgressDialog.dismiss()
        }
    }
}