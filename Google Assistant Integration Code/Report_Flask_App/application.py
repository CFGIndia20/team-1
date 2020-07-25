from flask import Flask, render_template
import pyrebase
import os

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

port = int(os.getenv('PORT', 8000))

app = Flask(__name__)

firebase = pyrebase.initialize_app(keys)

db = firebase.database()

patients = db.child("Patient").get()
patients = patients.val()
patients = patients[1:]


@app.route('/', methods=['GET', 'POST'])
def index():
    return render_template("index.html", patients=patients)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=port, debug=True)
