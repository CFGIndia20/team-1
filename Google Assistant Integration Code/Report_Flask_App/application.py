#import the required files
from flask import Flask, render_template
import pyrebase
import os

#the configuration data required to connect to the firebase realtime database
keys = {
    "apiKey": "AIzaSyDZKPNKXkW_R8yUog4onZNm1VDVQuwTPcc",
    "authDomain": "aashray-3d07c.firebaseapp.com",
    "databaseURL": "https://aashray-3d07c.firebaseio.com",
    "projectId": "aashray-3d07c",
    "storageBucket": "aashray-3d07c.appspot.com",
    "messagingSenderId": "417279724498",
    "appId": "1:417279724498:web:66fc11b9ab02c5dbee560b",
    "measurementId": "G-WHC1XZ7MFQ"
}

#Port configuration for the app
port = int(os.getenv('PORT', 8000))

app = Flask(__name__)

#Initialising the database
firebase = pyrebase.initialize_app(keys)
db = firebase.database()

#Getting the patients to render from the database
patients = db.child("Patient").get()
patients = patients.val()
patients = patients[1:]

#Host the patient data on the '/' route
@app.route('/', methods=['GET', 'POST'])
def index():
    return render_template("index.html", patients=patients)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=port, debug=True)
